import java.util.Scanner;

public class projectManagementSystem {
    public static void main(String[] args) {
        Person Contractor = new Person(Billy, 021 520 0000, contractor@info.com, 1 Contractors street);
        Person Architect = new Person(Bobby, 021 650 0000, architect@info.com, 1 architects street);

        //Display options to select for input and changes

        Scanner numberInput = new Scanner(System.in);
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1:\tChange the due date of a project:");
            System.out.println("2:\tUpdate payment information:");
            System.out.println("3:\tUpdate project manager details:");
            System.out.println("4:\tFinalise a project:");
            System.out.println("5:\tDisplay outstanding project details:");
            System.out.println("6:\tDisplay overdue project details:");
            System.out.println("7:\tCreate new project:");
            System.out.println("8:\tSave and Quit\n");
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

            if (choice == 2) {
                Scanner projectNumberScanner = new Scanner(System.in);
                System.out.println("Enter number of the project that you want to update");
                String project_number = projectNumberScanner.nextLine();

                Scanner cost_owedScanner = new Scanner(System.in);
                System.out.println("Enter updated cost owing to the project: ");
                Integer cost_owed = Integer.valueOf(cost_owedScanner.nextLine());
                ;
            }

            if (choice == 3) {
                Scanner titleScanner = new Scanner(System.in);
                System.out.println("Enter titles of the contractor: ");
                String contractortitle = titleScanner.nextLine();

                Scanner titleScanner = new Scanner(System.in);
                System.out.println("Enter titles of the Architech: ");
                String title = titleScanner.nextLine();

                Person() = new Person(title, )
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

