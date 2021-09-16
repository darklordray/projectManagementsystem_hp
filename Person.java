import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is to create, access and modify Person objects. It links to the
 * projectManagementSystem class.
 * 
 * @author Raynard Sims
 * @version 1.1
 *
 */
public class Person {

	// Attributes
	String designation;
	String firstName;
	String lastName;
	String phoneNumber;
	String email;
	String address;

	// Methods

	// Create 'Person' object
	/**
	 * Primary constructor for creating Person objects. <br>
	 * Taking input from the user, object attributes are set
	 * 
	 * @param designation <br>
	 *                    indication of customer, architect, project manager or
	 *                    structural engineer
	 * @param firstName   <br>
	 *                    Person first name
	 * @param lastName    <br>
	 *                    Person last name
	 * @param input       <br>
	 *                    Scanner object for obtaining user input
	 */
	public Person(String designation, String firstName, String lastName, Scanner input) {
		this.designation = designation;

		this.firstName = firstName;

		this.lastName = lastName;

		System.out.printf("\nPlease enter the %s phone number:\n", designation);
		this.phoneNumber = input.next();

		System.out.printf("\nPlease enter the %s email address:\n", designation);
		this.email = input.next();

		System.out.printf("\nPlease enter the %s physical address:\n", designation);
		this.address = input.next().replace(", ", "; ");
	}

	// Overload for person creator to create objects of already logged people
	/**
	 * Overload constructor method for creating an object from previously logged
	 * person.
	 * 
	 * @param designation <br>
	 *                    String denoting customer, architect or contractor
	 * @param fullName    <br>
	 *                    String consisting of person firstName and lastName
	 *                    attributes
	 * @param people      <br>
	 *                    File storing person details in CSV format
	 * @throws FileNotFoundException <br>
	 *                               Exception thrown if file not found
	 */
	public Person(String person) {
		String[] existingPersonDetails = person.split(", ");
		String[] splitName = existingPersonDetails[1].split(" ");

		this.designation = existingPersonDetails[0];
		this.firstName = splitName[0];
		this.lastName = splitName[1];
		this.phoneNumber = existingPersonDetails[2];
		this.email = existingPersonDetails[3];
		this.address = existingPersonDetails[4];
	}

	// Call each attribute
	/**
	 * 
	 * @return String attribute designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * 
	 * @return String attribute firstName and lastName
	 */
	public String getName() {
		String output = String.format("%s %s", firstName, lastName);
		return output;
	}

	/**
	 * 
	 * @return String attribute lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @return String attribute phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * 
	 * @return String attribute email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @return String attribute address
	 */
	public String getAddress() {
		return address;
	}

	public void setFirstName(Scanner input) {
		this.firstName = input.next();
	}

	public void setLastName(Scanner input) {
		this.lastName = input.next();
	}

	public void setPhoneNumber(Scanner input) {
		this.phoneNumber = input.next();
	}

	public void setEmail(Scanner input) {
		this.email = input.next();
	}

	public void setAddress(Scanner input) {
		this.address = input.next();
	}

	// Call all attributes
	/**
	 * Method to obtain a string detailing all person details in an easy to read
	 * format
	 * 
	 * @return String
	 */
	public String getPerson() {
		String output = "Designation:\t" + designation;
		output += "\nName:\t\t" + getName();
		output += "\nNumber:\t\t" + phoneNumber;
		output += "\nEmail:\t\t" + email;
		output += "\nAddress:\t" + address.replace("; ", "\n\t\t");

		return output;
	}

	// Add functionality to search through objects by person name and designation
	/**
	 * Method to search through list of people by person full name
	 * 
	 * @param peopleList  <br>
	 *                    Array containing all current people
	 * @param inputName   <br>
	 *                    String indicating the person name to be searched
	 * @param designation <br>
	 *                    String indicating the person designation to be searched
	 * @return Person
	 */
	public static Person personSearch(ArrayList<Person> peopleList, String inputName, String designation) {
		for (Person all : peopleList) {
			if (inputName.equalsIgnoreCase(all.getName()) && designation.equalsIgnoreCase(all.getDesignation())) {
				int index = peopleList.indexOf(all);
				Person toBeEdited = peopleList.remove(index);
				return toBeEdited;
			}
		}
		return null;

	}

	public String personDetails() {
		String toPeople = designation + ", " + firstName + ", " + lastName + ", " + phoneNumber + ", " + email + ", "
				+ address + "\n";
		return toPeople;
	}

	/**
	 * 
	 * @return string attribute argument for updating projects database
	 */
	public String toDatabase() {
		String values = "'" + designation + "'";
		values += ", '" + firstName + " " + lastName + "'";
		values += ", '" + phoneNumber + "'";
		values += ", '" + email + "'";
		values += ", '" + address + "'";
		return values;
	}
}
