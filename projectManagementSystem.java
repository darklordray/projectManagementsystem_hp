import java.util.Scanner;

public class projectManagementSystem {
    public static void main(String[] args) {

        Scanner numberInput = new Scanner(System.in);
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
                System.out.println("Shutting down");
                break;
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

