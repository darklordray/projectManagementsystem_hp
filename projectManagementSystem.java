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
        
        }
    Scanner titleScanner = new Scanner(System.in);
    System.out.println("Enter title");
    String title = titleScanner.nextLine();

    Scanner titleScanner = new Scanner(System.in);
    System.out.println("Enter title");
    String title = titleScanner.nextLine();

    Person Archietect = new Person (title, telephone_number, email_address, address)
    }
}

