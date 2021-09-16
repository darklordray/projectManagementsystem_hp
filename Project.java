import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
//import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is to create, access and modify Project objects. It links to the
 * projectManagementSystem class.
 * 
 * @author Raynard Sims
 * @version 1.1
 */
public class Project {

	// Attributes
	String projectNumber;
	String projectName;
	String buildingType;
	String buildingAddress;
	String erfNumber;
	BigDecimal totalFee;
	BigDecimal totalPaid;
	LocalDate deadline;
	// Person class created separately
	Person architect;
	Person projectManager;
	Person structuralEngineer;
	Person customer;
	boolean completed;

	// Methods

	// Create 'Project' object
	/**
	 * Primary constructor for creating Project objects. <br>
	 * Taking input from the user, object attributes are set
	 * 
	 * @param input       <br>
	 *                    Scanner object which has been platform sanitised by
	 *                    accounting for \r and \n characters as appropriate.
	 * @param numberInput <br>
	 *                    Second Scanner object for reading numbers. Implemented as
	 *                    sanitising of first Scanner object caused an error when
	 *                    inputting numbers
	 * 
	 */
	public Project(Scanner input, Scanner numberInput, ArrayList<Person> people) {
		System.out.println("\nPlease enter the customers first name:");
		String customerFirstName = input.next();
		System.out.println("\nPlease enter the customers last name:");
		String customerLastName = input.next();
		String customerFullName = customerFirstName + " " + customerLastName;
		Person existingCustomer = Person.personSearch(people, customerFullName, "customer");
		if (existingCustomer == null) {
			this.customer = new Person("Customer", customerFirstName, customerLastName, input);
		} else {
			this.customer = existingCustomer;
			people.add(existingCustomer);
		}

		System.out.println("\nPlease enter the project number:");
		this.projectNumber = input.next();

		System.out.println("\nPlease enter the type of building required:");
		this.buildingType = input.next();

		System.out.println("\nPlease enter the project name, or 'no' to autogenerate:");
		String projectNameChoice = input.next();
		if (projectNameChoice.equalsIgnoreCase("no")) {
			this.projectName = buildingType + " " + customer.lastName;
		} else {
			this.projectName = projectNameChoice;
		}

		System.out.println("\nPlease enter the building physical address:");
		this.buildingAddress = input.next().replace(", ", "; ");

		System.out.println("\nPlease enter the type of building ERF number:");
		this.erfNumber = input.next();

		System.out.println("\nPlease enter the job fee:");
		this.totalFee = numberInput.nextBigDecimal();
		System.out.println("\nPlease enter how much the customer has paid to date:");
		this.totalPaid = numberInput.nextBigDecimal();

		setDate(numberInput);

		System.out.println("\nPlease enter the architect first name:");
		String architectFirstName = input.next();
		System.out.println("\nPlease enter the architect last name:");
		String architectLastName = input.next();
		String architectFullName = architectFirstName + " " + architectLastName;
		Person existingArchitect = Person.personSearch(people, architectFullName, "architect");
		if (existingArchitect == null) {
			this.architect = new Person("Architect", architectFirstName, architectLastName, input);
		} else {
			this.architect = existingArchitect;
			people.add(existingArchitect);
		}

		System.out.println("\nPlease enter the Project Manager first name:");
		String projectManagerFirstName = input.next();
		System.out.println("\nPlease enter the Project Manager last name:");
		String projectManagerLastName = input.next();
		String projectManagerFullName = projectManagerFirstName + " " + projectManagerLastName;
		Person existingProjectManager = Person.personSearch(people, projectManagerFullName, "project manager");
		if (existingProjectManager == null) {
			this.projectManager = new Person("Project Manager", projectManagerFirstName, projectManagerLastName, input);
		} else {
			this.projectManager = existingProjectManager;
			people.add(existingProjectManager);
		}

		System.out.println("\nPlease enter the Structural Engineer first name:");
		String structuralEngineerFirstName = input.next();
		System.out.println("\nPlease enter the Structural Engineer last name:");
		String structuralEngineerLastName = input.next();
		String structuralEngineerFullName = structuralEngineerFirstName + " " + structuralEngineerLastName;
		Person existingStructuralEngineer = Person.personSearch(people, structuralEngineerFullName,
				"structural engineer");
		if (existingStructuralEngineer == null) {
			this.structuralEngineer = new Person("Structural Engineer", structuralEngineerFirstName,
					structuralEngineerLastName, input);
		} else {
			this.structuralEngineer = existingStructuralEngineer;
			people.add(existingStructuralEngineer);
		}

		this.completed = false;

	}

	private void setDate(Scanner numberInput) {
		// While loop to get valid date input
		while (true) {
			LocalDate today = LocalDate.now();
			int thisYear = today.getYear();
			int thisMonth = today.getMonthValue();
			int thisDay = today.getDayOfMonth();
			int dueYear = 0;
			int dueMonth = 0;
			int dueDay = 0;
			int maxNumberOfDays = 0;

			// While loop to get year
			while (true) {
				System.out.println("\nPlease enter the year the project is due:");
				dueYear = numberInput.nextInt();
				if (dueYear < thisYear) {
					System.out.println("Deadline year cannot be before current year. Please try again.");
				} else {
					break;
				}
			}

			// While loop to get current month and determine maximum number of days in month
			while (true) {
				System.out.println(
						"\nPlease enter the month, using 1 for January to 12 for December, the project is due:");
				dueMonth = numberInput.nextInt();
				if (dueYear == thisYear && dueMonth < thisMonth) {
					System.out.println("Deadline month cannot be before current month. Please try again.");
				} else if (dueMonth < 1 || dueMonth > 12) {
					System.out.println("Deadline month value invalid. Please try again.");
				} else {
					break;
				}

			}
			// Switch statement to determine number of days in month
			switch (dueMonth) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				maxNumberOfDays = 31;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				maxNumberOfDays = 30;
				break;
			case 2:
				// Check if given due year is a leap year
				if (((dueYear % 4 == 0) && !(dueYear % 100 == 0)) || (dueYear % 400 == 0))
					maxNumberOfDays = 29;
				else
					maxNumberOfDays = 28;
				break;
			default:
				System.out.println("Please try again.");
				break;
			}
			while (true) {
				System.out.println("\nPlease enter the day of the month the project is due:");
				dueDay = numberInput.nextInt();
				if (dueYear == thisYear && dueMonth == thisMonth && dueDay <= thisDay) {
					System.out.println("Deadline month cannot be before current month. Please try again.");
				} else if (dueDay > maxNumberOfDays) {
					System.out.println("Deadline day cannot exceed days of deadline month. Please try again.");
				} else {
					break;
				}
			}
			this.deadline = LocalDate.of(dueYear, dueMonth, dueDay);
			break;
		}
	}

	// Overload for project constructor to create objects of already logged projects
	/**
	 * Overload constructor method for creating an object from previously logged
	 * project.
	 * 
	 * @param loggedProject <br>
	 *                      CSV string containing details of project
	 * @param people        <br>
	 *                      File containing details of people objects namely
	 *                      customers, architects and contractors
	 * @param persons       <br>
	 *                      ArrayList of Person objects
	 * @throws FileNotFoundException <br>
	 *                               Exception thrown if file not found
	 */
	public Project(String loggedProject, ArrayList<Person> persons) {
		loggedProject.strip();
		String[] existingProject = loggedProject.split(", ");

		this.projectNumber = existingProject[0];
		this.buildingType = existingProject[1];
		this.projectName = existingProject[3];
		this.buildingAddress = existingProject[4];
		this.erfNumber = existingProject[5];
		this.totalFee = new BigDecimal(existingProject[6]);
		this.totalPaid = new BigDecimal(existingProject[7]);
		this.deadline = LocalDate.parse(existingProject[8]);
		for (Person element : persons) {
			if (element.getDesignation().equalsIgnoreCase("customer")
					&& element.getName().equalsIgnoreCase(existingProject[2])) {
				this.customer = element;
			} else if (element.getDesignation().equalsIgnoreCase("architect")
					&& element.getName().equalsIgnoreCase(existingProject[9])) {
				this.architect = element;
			} else if (element.getDesignation().equalsIgnoreCase("project manager")
					&& element.getName().equalsIgnoreCase(existingProject[10])) {
				this.projectManager = element;
			}
			if (element.getDesignation().equalsIgnoreCase("structural engineer")
					&& element.getName().equalsIgnoreCase(existingProject[11])) {
				this.structuralEngineer = element;
			}
		}
		this.completed = Boolean.parseBoolean(existingProject[12]);
	}

	// Call each attribute
	/**
	 * 
	 * @return string attribute projectNumber
	 */
	public String getProjectNumber() {
		return projectNumber;
	}

	/**
	 * 
	 * @return string attribute projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * 
	 * @return string attribute buildingType
	 */
	public String getBuildingType() {
		return buildingType;
	}

	/**
	 * 
	 * @return string attribute buildingAddress
	 */
	public String getBuildingAddress() {
		return buildingAddress;
	}

	/**
	 * 
	 * @return string attribute erfNumber
	 */
	public String getErfNumber() {
		return erfNumber;
	}

	/**
	 * 
	 * @return float attribute totalFee
	 */
	public BigDecimal getTotalFee() {
		return totalFee;
	}

	/**
	 * 
	 * @return float attribute totalPaid
	 */
	public BigDecimal getTotalPaid() {
		return totalPaid;
	}

	/**
	 * 
	 * @return localdate attribute deadline
	 */
	public LocalDate getDeadline() {
		return deadline;
	}

	/**
	 * 
	 * @return person attribute architect
	 */
	public Person getArchitect() {
		return architect;
	}

	/**
	 * 
	 * @return person attribute project manager
	 */
	public Person getProjectManager() {
		return projectManager;
	}

	/**
	 * 
	 * @return person attribute structural engineer
	 */
	public Person getStructuralEngineer() {
		return structuralEngineer;
	}

	/**
	 * 
	 * @return person attribute customer
	 */
	public Person getCustomer() {
		return customer;
	}

	// Call all attributes
	/**
	 * Method to obtain a string detailing all project details in an easy to read
	 * format
	 * 
	 * @return String
	 */
	public String toString() {
		String output = "";
		for (int i = 0; i <= 50; i++) {
			output += "*";
		}
		output += "\nProject Number:\n\t" + projectNumber;
		output += "\nProject Name:\n\t" + projectName;
		output += "\nBuilding Type:\n\t" + buildingType;
		output += "\nBuilding Address:\n\t" + buildingAddress.replace("; ", "\n\t");
		output += "\nERF Number:\n\t" + erfNumber;
		output += String.format("\nTotal Fee:\n\tR%.2f", totalFee);
		output += String.format("\nTotal Paid:\n\tR%.2f", totalPaid);
		output += String.format("\nTotal Outstanding:\n\tR%.2f", (totalFee.subtract(totalPaid)));
		output += "\nDeadline:\n\t" + deadline;
		output += "\nArchitect:\n\t" + architect.getName();
		output += "\nProject Manager:\n\t" + projectManager.getName();
		output += "\nStructural Engineer:\n\t" + structuralEngineer.getName();
		output += "\nCustomer:\n\t" + customer.getName();
		if (completed == false) {
			output += "\nProject completed:\n\tNo";
		} else {
			output += "\nProject completed:\n\tYes";
		}
		output += "\n";

		return output;
	}

	/**
	 * Method to obtain a string detailing all project details in CSV format
	 * 
	 * @return String
	 */
	public String toOutput() {
		String Output = projectNumber + ", " + buildingType + ", " + customer.getName() + ", " + projectName + ", "
				+ buildingAddress + ", " + erfNumber + ", " + totalFee + ", " + totalPaid + ", " + deadline + ", "
				+ architect.getName() + ", " + projectManager.getName() + ", " + structuralEngineer.getName() + ", "
				+ completed + "\n";

		return Output;
	}

	// Add functionality to change project deadline
	/**
	 * Method to allow user to update project deadline
	 * 
	 * @param numberInput <br>
	 *                    Scanner object to obtain user input
	 */
	public void updateDeadline(Scanner numberInput) {
		setDate(numberInput);
	}

	// Add functionality to change fee paid to date
	/**
	 * Method to allow user to pay into a job
	 * 
	 * @param numberInput <br>
	 *                    Scanner object to obtain user input
	 */
	public void updateTotalPaid(Scanner numberInput) {
		System.out.println(String.format("\nFee outstanding: R%.2f", (this.totalFee.subtract(this.totalPaid))));
		System.out.println("\nHow much is the client paying into the account?");
		BigDecimal paid = numberInput.nextBigDecimal();
		System.out.println();

		this.totalPaid = totalPaid.add(paid);
	}

	// Add functionality to update contractor details
	/**
	 * Method to update contractor details or assign a new contractor to a project
	 * 
	 * @param input  <br>
	 *               Scanner to obtain user input
	 * @param people <br>
	 *               ArrayList of type Person
	 * 
	 */
	public String updateProjectManager(Scanner input, ArrayList<Person> people) {
		String option = "";
		while (true) {
			System.out.println("\nPlease select from the following:");
			System.out.println("Amend \t- amend current project manager details");
			System.out.println("Change \t- assign a different project manager from the system");
			System.out.println("New \t- assign a new project manager");
			System.out.println("Exit \t- return to main menu");
			String choice = input.next();

			if (choice.equalsIgnoreCase("amend")) {
				option = "amend";

				Person oldDetails = Person.personSearch(people, projectManager.getName(), "project manager");

				System.out.println("\nWhat is the project manager first name?\n");
				oldDetails.setFirstName(input);

				System.out.println("What is the project manager last name?\n");
				oldDetails.setLastName(input);

				System.out.print("What is the project manager phone number?\n");
				oldDetails.setPhoneNumber(input);

				System.out.print("What is the project manager email address?\n");
				oldDetails.setEmail(input);

				System.out.print("What is the project manager address?\n");
				oldDetails.setAddress(input);

				this.projectManager = oldDetails;

				people.add(oldDetails);
				break;

			} else if (choice.equalsIgnoreCase("change")) {
				option = "change";
				System.out.println("\nThese are the currently registered project managers:\n");
				for (Person element : people) {
					if (element.designation.equalsIgnoreCase("project manager")) {
						System.out.println(element.personDetails());
					}
				}

				while (true) {
					System.out.println(
							"Please enter the full name of the project manager you would like to assign to this project:");
					String newManagerName = input.next();
					Person newManager = Person.personSearch(people, newManagerName, "project manager");
					if (newManager != null) {
						this.projectManager = newManager;
						people.add(newManager);
						break;
					}
				}

				break;
			} else if (choice.equalsIgnoreCase("new")) {
				option = "new";
				System.out.println("Please enter the new project manager first name:");
				String firstName = input.next();
				System.out.println("Please enter the new project manager last name:");
				String lastName = input.next();
				Person replacementProjectManager = new Person("Project Manager", firstName, lastName, input);
				this.projectManager = replacementProjectManager;
				people.add(replacementProjectManager);
				break;
			} else if (choice.equalsIgnoreCase("exit")) {
				break;
			} else {
				System.out.println("\nYour input did not match a given option. Please try again.\n");
			}
		}
		return option;
	}

	// Add functionality to finalise project
	/**
	 * Static method to mark project as complete, remove it from list of current
	 * projects, add it to list of <br>
	 * completed projects and generate a customer invoice if any fees are
	 * outstanding
	 * 
	 * @param finaliseProject <br>
	 *                        Project object to be finalised
	 * @throws IOException <br>
	 *                     IOException thrown if FileWriter encounters an error
	 */
	public static String finalise(Project finaliseProject) throws IOException {
		System.out.println();
		// Set completed attribute to true
		finaliseProject.completed = true;

		// Generate invoice if there are outstanding fees
		if ((finaliseProject.totalFee.compareTo(finaliseProject.totalPaid)) > 0) {
			try {
				// Variable to name generated invoice as projectNumber
				String fileName = String.format("%s-%s.txt", finaliseProject.customer.getLastName(),
						finaliseProject.projectNumber);
				// Create File object linked to "Completed_projects.txt"
				File invoice = new File(fileName);
				// Create FileWriter object
				FileWriter invoiceWriter = new FileWriter(invoice);
				// generate invoice document if not present
				invoice.createNewFile();

				String toInvoice = "\tPOISED INVOICE\n";
				toInvoice += "invoice adressed to:\n";
				toInvoice += finaliseProject.customer.getPerson() + "\n";
				toInvoice += "Date completed: " + LocalDate.now() + "\n";
				toInvoice += finaliseProject.toString();
				invoiceWriter.write(toInvoice);
				invoiceWriter.close();
			} catch (IOException e) {
				System.out.println("Error encountered: invoice file could not be created");
			}
		}

		String forDatabase = "'" + finaliseProject.projectNumber + "'";
		forDatabase += ", '" + finaliseProject.projectName + "'";
		forDatabase += ", '" + finaliseProject.buildingType + "'";
		forDatabase += ", '" + finaliseProject.buildingAddress + "'";
		forDatabase += ", '" + finaliseProject.erfNumber + "'";
		forDatabase += ", " + finaliseProject.totalFee.toString();
		forDatabase += ", " + finaliseProject.totalPaid.toString();
		forDatabase += ", '" + finaliseProject.deadline + "'";
		forDatabase += ", '" + LocalDate.now() + "'";
		forDatabase += ", '" + finaliseProject.architect.getName() + "'";
		forDatabase += ", '" + finaliseProject.customer.getName() + "'";
		forDatabase += ", '" + finaliseProject.projectManager.getName() + "'";
		forDatabase += ", '" + finaliseProject.structuralEngineer.getName() + "'";
		forDatabase += ", '" + finaliseProject.completed + "'";

		return forDatabase;

	}

	// Add functionality to search through objects by project name or project number
	/**
	 * Method to search through list of projects by attributes projectName or
	 * projectNumber
	 * 
	 * @param projectsList <br>
	 *                     Array containing all current projects
	 * @param input        <br>
	 *                     Scanner object to accept user input
	 * @return Project
	 */
	public static Project projectSearch(ArrayList<Project> projectsList, Scanner input) {
		while (true) {
			System.out
					.println("Please enter a project name, project number, or 'list' to view all valid search terms:");
			String searchTerm = input.nextLine();
			if (searchTerm.equalsIgnoreCase("list")) {
				for (Project all : projectsList) {
					System.out.println("Project Number: " + all.getProjectNumber() + "\nProject Name: "
							+ all.getProjectName() + "\n");
				}
				System.out.println();
			} else {
				for (Project all : projectsList) {
					if (searchTerm.equalsIgnoreCase(all.getProjectName())
							|| searchTerm.equalsIgnoreCase(all.getProjectNumber())) {
						int index = projectsList.indexOf(all);
						Project toBeEdited = projectsList.remove(index);
						return toBeEdited;
					}
				}

				System.out.println("Your input did not match a listed project. Please try again.\n");
			}
		}
	}

	/**
	 * 
	 * @return string attribute argument for updating projects database
	 */
	public String toDatabase() {
		String values = "'" + projectNumber + "'";
		values += ", '" + projectName + "'";
		values += ", '" + buildingType + "'";
		values += ", '" + buildingAddress + "'";
		values += ", '" + erfNumber + "'";
		values += ", " + totalFee;
		values += ", " + totalPaid;
		values += ", '" + deadline + "'";
		values += ", '" + architect.getName() + "'";
		values += ", '" + customer.getName() + "'";
		values += ", '" + projectManager.getName() + "'";
		values += ", '" + structuralEngineer.getName() + "'";
		values += ", '" + completed + "'";
		return values;
	}

}
