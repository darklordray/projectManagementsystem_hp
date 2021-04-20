# Project Management system

Project managment system built using java.

#### Purpose
"Poised" stores the following information for each project that they work on:
* Project number.
* Project name.
* What type of building is being designed? E.g. House, apartment block or
store, etc.
* The physical address for the project.
* ERF number.
* The total fee being charged for the project.
* The total amount paid to date.
* Deadline for the project.
* The name, telephone number, email address and physical address of the
architect for the project.
* The name, telephone number, email address and physical address of the
contractor for the project.
* The name, telephone number, email address and physical address of the
customer for the project.
Poised wants to be able to use your program to do the following:
* Capture information about new projects. If a project name is not provided
when the information is captured, name the project using the surname of
the customer. For example, a house being built by Mike Tyson would be
called “House Tyson.” An apartment block owned by Jared Goldman would
be called “Apartment Goldman.”
* Update information about existing projects. Information may need to be
adjusted at different stages throughout the lifecycle of a project. For
example, the deadline might change after a meeting with various
stakeholders.
* Finalise existing projects. When a project is finalised, the following should
happen:
  * An invoice should be generated for the client. This invoice should
contain the customer’s contact details and the total amount that the
customer must still pay. This amount is calculated by subtracting the
total amount paid to date from the total fee for the project. If the
customer has already paid the full fee, an invoice should not be
generated.
  * The project should be marked as “finalised” and the completion date
should be added. All the information about the project should be
added to a text file called CompletedProject.txt .
* See a list of projects that still need to be completed.
* See a list of projects that are past the due date.
* Find and select a project by entering either the project number or project
name.

### Progress
20/4
* Two classes have been created
* Main program has been created

Currently finanlizing the part 1 of the finalizing of a project

#### Code
```
import java.util.Scanner;

public class projectManagementSystem {
    public static void main(String[] args) {
        Project = new Project();
        Person = new Person();

        //Display options to select for input and changes

        Scanner numberInput = new Scanner(System.in);
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1-\tChange the due date of a project:");
            System.out.println("2-\tUpdate payment information:");
            System.out.println("3-\tUpdate project manager details:");
            System.out.println("4-\tFinalise a project:");
            System.out.println("5-tDisplay outstanding project details:");
            System.out.println("6-tDisplay overdue project details:");
            System.out.println("7-tCreate new project:");
             int choice = numberInput.nextInt();
            
            //Scanner inputs for menu selection and function

            if (choice == 8) {
                System.out.println("Shutting down");
                break;
            }

            if (choice == 1) {
                Scanner projectNumberScanner = new Scanner(System.in);
                System.out.println("Enter number of the project that you want to edit: ");
                String project_number = projectNumberScanner.nextLine();
                
                Scanner deadlineScanner = new Scanner(System.in);
                System.out.println("Enter new due date of the project:");
                String deadline = deadlineScanner.nextLine();
            }

            if (choice == 2){
                Scanner projectNumberScanner = new Scanner(System.in);
                System.out.println("Enter number of the project that you want to update");
                String project_number = projectNumberScanner.nextLine();

                Scanner cost_owedScanner = new Scanner(System.in);
                System.out.println("Enter updated cost owing to the project: ");
                Integer cost_owed = Integer.valueOf(cost_owedScanner.nextLine());;
            }

            if (choice == 3) {
                Scanner titleScanner = new Scanner(System.in);
                System.out.println("Enter titles of the contractor: ");
                String title = titleScanner.nextLine();
                
                Scanner nameScanner = new Scanner(System.in);
                System.out.println("Enter the name of the contractor:");
                String name = nameScanner.nextLine();

                Scanner telephone_numberScanner = new Scanner(System.in);
                System.out.println("Enter the telephone number of the contractor:");
                String telephone_number = telephone_numberScanner.nextLine();

                Scanner email_addressScanner = new Scanner(System.in);
                System.out.println("Enter the email address of the contractor:");
                String email_address = email_addressScanner.nextLine();

                Scanner addressScanner = new Scanner(System.in);
                System.out.println("Enter the address of the contractor:");
                String address = addressScanner.nextLine();

            }

            if (choice == 4) {

                System.out.println(Project.toString());
                System.out.println(Person.toString());
            }

            if (choice == 7) {
                Scanner projectNumberScanner = new Scanner(System.in);
                System.out.println("Enter number of the project");
                String project_number = projectNumberScanner.nextLine();

                Scanner projectNameScanner = new Scanner(System.in);
                System.out.println("Enter the name of the project");
                String project_name = projectNameScanner.nextLine();

                Scanner projectTypeScanner = new Scanner(System.in);
                System.out.println("Enter the type of the project");
                String project_type = projectTypeScanner.nextLine();

                Scanner addressScanner = new Scanner(System.in);
                System.out.println("Enter the address of the project");
                String address = addressScanner.nextLine();

                Scanner erfScanner = new Scanner(System.in);
                System.out.println("Enter the erf of the project");
                String erf = erfScanner.nextLine();

                Scanner total_costScanner = new Scanner(System.in);
                System.out.println("Enter the total cost of the project");
                int total_cost = total_costScanner.nextInt();

                Scanner cost_owedScanner = new Scanner(System.in);
                System.out.println("Enter the total cost of the project that is still owed");
                int cost_owed = cost_owedScanner.nextInt();

                Scanner deadlineScanner = new Scanner(System.in);
                System.out.println("Enter the dead line of the project");
                String deadline = deadlineScanner.nextLine();
            }
        
        }
    }
}

