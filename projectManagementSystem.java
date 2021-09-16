import java.sql.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Raynard Sims
 * @version 1.0
 */
public class projectManagementSystem {

	public static void main(String[] args) throws IOException {

		try {
			// Declare global variables
			ArrayList<Project> projects = new ArrayList<Project>();
			ArrayList<Person> people = new ArrayList<Person>();
			String projectsContents;
			String peopleContents;
			Project projectToUpdate;
			String projectArgument;
			String personArgument;

			// Error found where entering text with a space skipped following input request
			// Use of input.nextLine() resulted in same issue
			// useDelimiter("\r?\n") method prompts scanner to exclude \r prompt if present
			// and \n prompt in all cases
			Scanner input = new Scanner(System.in).useDelimiter("\r?\n");
			Scanner numberInput = new Scanner(System.in);

			// establish connection with PoisedPMS database
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms?allowPublicKeyRetrieval=true&useSSL=false", "otheruser",
					"swordfish");

			// Create a direct line to the database for running our queries
			Statement statement = connection.createStatement();
			ResultSet activeProjects;
			ResultSet peopleList;
			int rowsAffected;

			// Create ArrayList of projects by reading from contents of PoisedPMS database
			peopleList = statement.executeQuery("SELECT * FROM person");

			while (peopleList.next()) {
				peopleContents = peopleList.getString("designation");
				peopleContents += ", " + peopleList.getString("name");
				peopleContents += ", " + peopleList.getString("number");
				peopleContents += ", " + peopleList.getString("email");
				peopleContents += ", " + peopleList.getString("address");

				people.add(new Person(peopleContents));
			}

			activeProjects = statement.executeQuery("SELECT * FROM projects");

			while (activeProjects.next()) {
				projectsContents = activeProjects.getString("projectnumber");
				projectsContents += ", " + activeProjects.getString("buildingtype");
				projectsContents += ", " + activeProjects.getString("customer");
				projectsContents += ", " + activeProjects.getString("projectname");
				projectsContents += ", " + activeProjects.getString("buildingaddress");
				projectsContents += ", " + activeProjects.getString("erfnumber");
				projectsContents += ", " + activeProjects.getBigDecimal("totalfee").toString();
				projectsContents += ", " + activeProjects.getBigDecimal("totalpaid").toString();
				projectsContents += ", " + activeProjects.getString("deadline");
				projectsContents += ", " + activeProjects.getString("architect");
				projectsContents += ", " + activeProjects.getString("projectmanager");
				projectsContents += ", " + activeProjects.getString("structuralengineer");
				projectsContents += ", " + activeProjects.getString("completed");

				projects.add(new Project(projectsContents, people));
			}
			/*
			 * while ((projectsContents = currentProjects.readLine()) != null) {
			 * projects.add(new Project(projectsContents, peopleOutput, people)); }
			 */

			// Loop through program until user decides to exit
			while (true) {
				System.out.println("Please select an option from the menu below");
				System.out.println("1:\tChange the due date of a project");
				System.out.println("2:\tPay into a project");
				System.out.println("3:\tUpdate a project manager details");
				System.out.println("4:\tFinalise a project");
				System.out.println("5:\tDisplay outstanding project details");
				System.out.println("6:\tDisplay overdue project details");
				System.out.println("7:\tCreate new project");
				System.out.println("0:\tSave and Quit\n");
				int choice = numberInput.nextInt();

				// Close the scanner and quit the program
				if (choice == 0) {
					connection.close();
					input.close();
					System.out.println("Shutting down");
					break;
				}

				// Change project due date
				else if (choice == 1) {
					projectToUpdate = Project.projectSearch(projects, input);
					projectToUpdate.updateDeadline(numberInput);
					projects.add(projectToUpdate);
					String updateDate = projectToUpdate.getDeadline().toString();
					String projectID = projectToUpdate.getProjectNumber();
					rowsAffected = statement.executeUpdate("UPDATE projects SET deadline='" + updateDate
							+ "' WHERE projectnumber='" + projectID + "'");
					System.out.println("Query complete, " + rowsAffected + " rows changed in Projects table.\n");
				}

				// Pay into project
				else if (choice == 2) {
					projectToUpdate = Project.projectSearch(projects, input);
					projectToUpdate.updateTotalPaid(numberInput);
					projects.add(projectToUpdate);
					BigDecimal updateTotalPaid = projectToUpdate.getTotalPaid();
					String projectID = projectToUpdate.getProjectNumber();
					rowsAffected = statement.executeUpdate("UPDATE projects SET totalpaid=" + updateTotalPaid.toString()
							+ " WHERE projectnumber='" + projectID + "'");
					System.out.println("Query complete, " + rowsAffected + " rows changed in Projects table.\n");
				}

				// Update project manager details
				else if (choice == 3) {
					projectToUpdate = Project.projectSearch(projects, input);
					String currentPM = projectToUpdate.projectManager.getName();
					String typeOfChange = projectToUpdate.updateProjectManager(input, people);
					projects.add(projectToUpdate);
					
					if (typeOfChange.equalsIgnoreCase("amend")) {
						String updateName = projectToUpdate.projectManager.getName();
						String updateNumber = projectToUpdate.projectManager.getPhoneNumber();
						String updateEmail = projectToUpdate.projectManager.getEmail();
						String updateAddress = projectToUpdate.projectManager.getAddress();
						
						rowsAffected = statement.executeUpdate("UPDATE person SET name='" + updateName + "', number='"
								+ updateNumber + "', email='" + updateEmail + "', address='" + updateAddress
								+ "' WHERE name='" + currentPM + "'");
						System.out.println("Query complete, " + rowsAffected + " rows changed in Person table.\n");
						
						rowsAffected = statement.executeUpdate("UPDATE projects SET projectmanager='" + updateName
								+ "' WHERE projectmanager='" + currentPM + "'");
						System.out.println("Query complete, " + rowsAffected + " rows changed in Projects table.\n");
					} 
					
					else if (typeOfChange.equalsIgnoreCase("change")) {
						String updatePM = projectToUpdate.getProjectManager().getName();
						String projectID = projectToUpdate.getProjectNumber();
						
						rowsAffected = statement.executeUpdate("UPDATE projects SET projectmanager='" + updatePM
								+ "' WHERE projectnumber='" + projectID + "'");
						System.out.println("Query complete, " + rowsAffected + " rows changed in Projects table.\n");
					} 
					
					else if (typeOfChange.equalsIgnoreCase("new")) {
						String addPerson = projectToUpdate.projectManager.toDatabase();
						String newPM = projectToUpdate.projectManager.getName();
						String projectID = projectToUpdate.projectNumber;
						
						rowsAffected = statement.executeUpdate("INSERT INTO person VALUES (" + addPerson + ")");
						System.out.println("Query complete, " + rowsAffected + " rows added to Person table.\n");
						
						rowsAffected = statement.executeUpdate("UPDATE projects SET projectmanager='" + newPM
								+ "' WHERE projectnumber='" + projectID + "'");
						System.out.println("Query complete, " + rowsAffected + " rows changed in Projects table.\n");
					}
				}

				// Finalise project
				else if (choice == 4) {
					projectToUpdate = Project.projectSearch(projects, input);
					String finishedProject = Project.finalise(projectToUpdate);
					String deleteProject = projectToUpdate.projectNumber;
					
					rowsAffected = statement
							.executeUpdate("INSERT INTO finishedprojects VALUES (" + finishedProject + ")");
					System.out.println("Query complete, " + rowsAffected + " rows added to finishedProjects table.\n");
					
					rowsAffected = statement
							.executeUpdate("DELETE FROM projects where projectnumber='" + deleteProject + "'");
					System.out.println("Query complete, " + rowsAffected + " rows removed from Projects table.\n");
				}

				// Display outstanding project details
				else if (choice == 5) {
					for (Project all : projects) {
						System.out.println(all.toString());
					}
				}

				// Display overdue project details
				else if (choice == 6) {
					for (Project all : projects) {
						if (all.deadline.isBefore(LocalDate.now())) {
							System.out.println(all.toString());
						}
					}
				}

				// Create new project
				else if (choice == 7) {
					// Create new project object and add to ArrayList and SQL database
					Project createdProject = new Project(input, numberInput, people);
					projects.add(createdProject);
					projectArgument = createdProject.toDatabase();
					rowsAffected = statement.executeUpdate("INSERT INTO projects VALUES (" + projectArgument + ")");
					System.out.println("Query complete, " + rowsAffected + " rows added to Projects table.\n");

					Person existingCustomer = Person.personSearch(people, createdProject.customer.getName(),
							"customer");
					if (existingCustomer == null) {
						System.out.println("customer picked up as null");
						people.add(createdProject.customer);
						personArgument = createdProject.customer.toDatabase();
						rowsAffected = statement.executeUpdate("INSERT INTO person VALUES (" + personArgument + ")");
						System.out.println("Query complete, " + rowsAffected + " rows added to Person table.\n");
					} else {
						people.add(existingCustomer);
					}

					Person existingarchitect = Person.personSearch(people, createdProject.architect.getName(),
							"architect");
					if (existingarchitect == null) {
						System.out.println("architect picked up as null");
						people.add(createdProject.architect);
						personArgument = createdProject.architect.toDatabase();
						rowsAffected = statement.executeUpdate("INSERT INTO person VALUES (" + personArgument + ")");
						System.out.println("Query complete, " + rowsAffected + " rows added to Person table.\n");
					} else {
						people.add(existingarchitect);
					}

					Person existingProjectManager = Person.personSearch(people, createdProject.projectManager.getName(),
							"project manager");
					if (existingProjectManager == null) {
						people.add(createdProject.projectManager);
						personArgument = createdProject.projectManager.toDatabase();
						rowsAffected = statement.executeUpdate("INSERT INTO person VALUES (" + personArgument + ")");
						System.out.println("Query complete, " + rowsAffected + " rows added to Person table.\n");
					} else {
						people.add(existingProjectManager);
					}

					Person existingStructuralEngineer = Person.personSearch(people,
							createdProject.structuralEngineer.getName(), "structural engineer");
					if (existingStructuralEngineer == null) {
						people.add(createdProject.structuralEngineer);
						personArgument = createdProject.structuralEngineer.toDatabase();
						rowsAffected = statement.executeUpdate("INSERT INTO person VALUES (" + personArgument + ")");
						System.out.println("Query complete, " + rowsAffected + " rows added to Person table.\n");
					} else {
						people.add(existingStructuralEngineer);
					}
					System.out.println();
				}

				else {
					System.out.printf("Your input '%s' does not match a menu item, please try again\n\n", choice);
				}
			}
		} catch (Exception e) {
			//System.out.println("Error encountered: Could not connect to PoisePMS database");
			e.printStackTrace();
		}

	}

}
