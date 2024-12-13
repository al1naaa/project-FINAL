import database.UserService;
import education.*;
import communication.*;
import users.*;
import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        UserService userService = new UserService();
        Library library = new Library();
        List<Course> courses = new ArrayList<>();
        List<News> newsList = new ArrayList<>();
        List<Message> messages = new ArrayList<>();

        initializeSystem(courses, library);

        while (true) {
            System.out.println("\nWelcome to University System!");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 1) {
                signUp(userService);
            } else if (choice == 2) {
                logIn(userService, courses, library, newsList, messages);
            } else if (choice == 3) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void initializeSystem(List<Course> courses, Library library) {
        // Initialize library with books
        library.addBook("Introduction to Algorithms");
        library.addBook("Clean Code");
        library.addBook("Artificial Intelligence: A Modern Approach");

        // Initialize courses
        courses.add(new Course("Object-Oriented Programming", 101, null));
        courses.add(new Course("Data Structures", 102, null));
        courses.add(new Course("Machine Learning", 103, null));
    }

    private static void signUp(UserService userService) {
        System.out.println("\nSign Up:");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (student/teacher/admin/manager): ");
        String role = scanner.nextLine().toLowerCase();

        userService.signUp(username, email, password, role);
        System.out.println("User signed up successfully!");
    }

    private static void logIn(UserService userService, List<Course> courses, Library library, List<News> newsList, List<Message> messages) {
        System.out.println("\nLog In:");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userService.logIn(username, password)) {
            System.out.println("Login successful!");
            // Simulate role-based actions
            System.out.print("Enter your role (student/teacher/admin/manager): ");
            String role = scanner.nextLine().toLowerCase();

            switch (role) {
                case "student":
                    studentActions(courses, library, username);
                    break;
                case "teacher":
                    teacherActions(courses, messages, username);
                    break;
                case "admin":
                    adminActions(newsList);
                    break;
                case "manager":
                    managerActions(courses, newsList, messages);
                    break;
                default:
                    System.out.println("Invalid role.");
            }
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void studentActions(List<Course> courses, Library library, String username) {
        System.out.println("\nStudent Dashboard:");
        System.out.println("1. View Courses");
        System.out.println("2. Borrow Book");
        System.out.println("3. Return Book");
        System.out.println("4. View Notifications");
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {
            for (Course course : courses) {
                course.displayCourseInfo();
            }
        } else if (choice == 2) {
            System.out.print("Enter book name to borrow: ");
            String bookName = scanner.nextLine();
            if (library.isBookAvailable(bookName)) {
                library.removeBook(bookName);
                System.out.println("You borrowed: " + bookName);
            } else {
                System.out.println("Book is not available.");
            }
        } else if (choice == 3) {
            System.out.print("Enter book name to return: ");
            String bookName = scanner.nextLine();
            library.addBook(bookName);
            System.out.println("You returned: " + bookName);
        } else if (choice == 4) {
            System.out.println("No notifications for " + username);
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static void teacherActions(List<Course> courses, List<Message> messages, String username) {
        System.out.println("\nTeacher Dashboard:");
        System.out.println("1. View Courses");
        System.out.println("2. Send Message");
        System.out.println("3. View Notifications");
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {
            for (Course course : courses) {
                course.displayCourseInfo();
            }
        } else if (choice == 2) {
            System.out.print("Enter recipient: ");
            String recipient = scanner.nextLine();
            System.out.print("Enter message: ");
            String body = scanner.nextLine();
            messages.add(new Message(body, username, recipient));
            System.out.println("Message sent to " + recipient);
        } else if (choice == 3) {
            System.out.println("No notifications for " + username);
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static void adminActions(List<News> newsList) {
        System.out.println("\nAdmin Dashboard:");
        System.out.println("1. View News");
        System.out.println("2. Add News");
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {
            for (News news : newsList) {
                news.displayNews();
            }
        } else if (choice == 2) {
            System.out.print("Enter news title: ");
            String title = scanner.nextLine();
            System.out.print("Enter news content: ");
            String content = scanner.nextLine();
            News news = new News(title, content, "General");
            newsList.add(news);
            System.out.println("News added.");
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static void managerActions(List<Course> courses, List<News> newsList, List<Message> messages) {
        System.out.println("\nManager Dashboard:");
        System.out.println("1. Approve Course Registration");
        System.out.println("2. Manage News");
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {
            System.out.print("Enter course name to approve: ");
            String courseName = scanner.nextLine();
            Course course = courses.stream().filter(c -> c.getCourseName().equals(courseName)).findFirst().orElse(null);
            if (course != null) {
                System.out.println("Approved course: " + course.getCourseName());
            } else {
                System.out.println("Course not found.");
            }
        } else if (choice == 2) {
            adminActions(newsList);
        } else {
            System.out.println("Invalid option.");
        }
    }
}
