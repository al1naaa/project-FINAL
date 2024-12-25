package database;

import java.util.Scanner;

public class TestApp {
    public static void main(String[] args) {
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Sign Up");
        System.out.println("2. Log In");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        if (option == 1) {
            System.out.print("Enter IIN: ");
            String IIN = scanner.nextLine();
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter surname: ");
            String surname = scanner.nextLine();
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            System.out.print("Enter role (student/teacher/admin): ");
            String role = scanner.nextLine();

            userService.signUp(IIN, name, surname, email, password, role);
        } else if (option == 2) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            boolean success = userService.logIn(username, password);
            if (success) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid username or password.");
            }
        } else {
            System.out.println("Invalid option.");
        }

        scanner.close();
    }
}
