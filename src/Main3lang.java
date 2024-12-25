import communication.LocalizationManager;
import communication.Message;
import communication.News;
import database.UserService;
import education.Course;
import education.Library;
import education.Mark;
import papers.ResearchPaper;
import users.*;

import java.util.*;

public class Main3lang {

    private static final Scanner scanner = new Scanner(System.in);
    private static LocalizationManager localizationManager;
    private UserService userService = new UserService();


    public static void main(String[] args) {
        LocalizationManager localizationManager = new LocalizationManager("ru"); // Пример языка
        Admin admin = new Admin("1", "Admin", "Admin", "admin@kbtu.kz", "123", localizationManager);
        UserService userService = new UserService();
        Library library = new Library();
        List<Course> courses = new ArrayList<>();
        List<News> newsList = new ArrayList<>();
        List<Message> messages = new ArrayList<>();
        List<User> users = new ArrayList<>();
        List<Teacher> teachers = userService.getAllTeachers();
        List<Student> students = userService.getAllStudents();



        // Проверяем, существует ли юзер в базе данных
        if (!userService.isUserExists(admin.getIIN())) {
            userService.signUp(admin.getIIN(), admin.getName(), admin.getSurname(), admin.getEmail(), admin.getPassword(), "admin");
            System.out.println();
        } else {
            System.out.println();
        }

        initializeSystem(courses, library);
        chooseLanguage();

        while (true) {
            try {
                System.out.println("\n" + localizationManager.getMessage("welcome"));
                System.out.println("1. " + localizationManager.getMessage("log_in"));
                System.out.println("2. " + localizationManager.getMessage("exit"));
                System.out.print(localizationManager.getMessage("choose_option") + " ");
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    logIn(userService, courses, library, newsList, messages, teachers, students);
                } else if (choice == 2) {
                    System.out.println(localizationManager.getMessage("goodbye"));
                    break;
                } else {
                    System.out.println(localizationManager.getMessage("invalid_option"));
                }
            } catch (Exception e) {
                System.out.println(localizationManager.getMessage("error_occurred"));
            }
        }
    }


    private static void chooseLanguage() {
        System.out.println("Choose a language / Выберите язык / Тілді таңдаңыз:");
        System.out.println("1. English\n2. Русский\n3. Қазақша");
        try {
            int languageChoice = Integer.parseInt(scanner.nextLine());
            switch (languageChoice) {
                case 1 -> localizationManager = new LocalizationManager("en");
                case 2 -> localizationManager = new LocalizationManager("ru");
                case 3 -> localizationManager = new LocalizationManager("kz");
                default -> {
                    System.out.println("Invalid choice. Defaulting to English.");
                    localizationManager = new LocalizationManager("en");
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Defaulting to English.");
            localizationManager = new LocalizationManager("en");
        }
    }

    private static void initializeSystem(List<Course> courses, Library library) {
        library.addBook("Introduction to Algorithms");
        library.addBook("Clean Code");
        library.addBook("Artificial Intelligence: A Modern Approach");

        Course oop = new Course("Object-Oriented Programming", 101, null);
        Course dataStructures = new Course("Data Structures", 102, null);
        Course machineLearning = new Course("Machine Learning", 103, null);

        courses.add(oop);
        courses.add(dataStructures);
        courses.add(machineLearning);
    }


    private static void logIn(UserService userService, List<Course> courses, Library library, List<News> newsList, List<Message> messages, List<Teacher> teachers, List<Student> students) {
        System.out.print(localizationManager.getMessage("enter_username") + " ");
        String username = scanner.nextLine();
        System.out.print(localizationManager.getMessage("enter_password") + " ");
        String password = scanner.nextLine();

        boolean loginSuccess = userService.logIn(username, password);
        if (!loginSuccess) {
            System.out.println(localizationManager.getMessage("login_failed"));
            return;
        }

        System.out.println(localizationManager.getMessage("login_success") + ", " + username);

        try {
            String role = userService.getUserRoleByUsername(username); // получаем роль из базы
            switch (role) {
                case "admin" -> adminActions(new Admin("1", "Admin", "Admin", username, password, localizationManager), userService, newsList);
                case "teacher" -> {
                    System.out.print("Are you a researcher? (yes/no): ");
                    String isResearcher = scanner.nextLine().toLowerCase();

                    Teacher teacher;
                    if (isResearcher.equals("yes")) {
                        System.out.print("Enter research field: ");
                        String researchField = scanner.nextLine();
                        teacher = new Teacher("", username, "Doe", username, password, "PhD", "Science", userService, localizationManager, researchField);
                    } else {
                        teacher = new Teacher("", username, "Doe", username, password, "PhD", "Science", userService, localizationManager);
                    }

                    teacherActions(teacher, courses);
                }
                case "student" -> {
                    System.out.print("Enter year of study: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter GPA: ");
                    double gpa = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter educational program: ");
                    String program = scanner.nextLine();
                    System.out.print("Enter research field: ");
                    String researchField = scanner.nextLine();

                    Student student = new Student("", username, "Doe", username, password, year, gpa, program, researchField);
                    studentActions(student, courses, library);
                }
                case "manager" -> managerActions(new Manager("", username, "", "", password, scanner), courses, students, teachers);
                case "librarian" -> librarianActions(new Librarian("", username, "Doe", username, password, library));
                default -> System.out.println(localizationManager.getMessage("invalid_role"));
            }
        } catch (Exception e) {
            System.out.println(localizationManager.getMessage("error_occurred") + ": " + e.getMessage());
        }
    }

    private static void librarianActions(Librarian librarian) {
        while (true) {
            System.out.println("\n" + localizationManager.getMessage("librarian_dashboard"));
            System.out.println("1. " + localizationManager.getMessage("view_books"));
            System.out.println("2. " + localizationManager.getMessage("lend_book"));
            System.out.println("3. " + localizationManager.getMessage("return_book"));
            System.out.println("4. " + localizationManager.getMessage("logout"));
            System.out.print(localizationManager.getMessage("choose_option") + " ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> librarian.viewBooks();
                case 2 -> {
                    System.out.print(localizationManager.getMessage("enter_book_name") + " ");
                    String bookName = scanner.nextLine();
                    librarian.lendBookToStudent(bookName);
                }
                case 3 -> {
                    System.out.print(localizationManager.getMessage("enter_book_name") + " ");
                    String bookName = scanner.nextLine();
                    librarian.returnBookFromStudent(bookName);
                }
                case 4 -> {
                    System.out.println(localizationManager.getMessage("goodbye"));
                    return;
                }
                default -> System.out.println(localizationManager.getMessage("invalid_option"));
            }
        }
    }

    private static void adminActions(Admin admin, UserService userService, List<News> newsList) {
        while (true) {
            System.out.println("\n" + localizationManager.getMessage("admin_panel"));
            System.out.println("1. " + localizationManager.getMessage("add_user"));
            System.out.println("2. " + localizationManager.getMessage("remove_user"));
            System.out.println("3. " + localizationManager.getMessage("update_user"));
            System.out.println("4. " + localizationManager.getMessage("add_news"));
            System.out.println("5. " + localizationManager.getMessage("view_news"));
            System.out.println("6. " + localizationManager.getMessage("send_message"));
            System.out.println("7. " + localizationManager.getMessage("view_messages"));
            System.out.println("8. " + localizationManager.getMessage("logout"));
            System.out.print(localizationManager.getMessage("choose_option") + " ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> admin.addUser();
                case 2 -> admin.removeUser();
                case 3 -> admin.updateUser();
                case 4 -> admin.addNews();
                case 5 -> admin.viewNews(newsList);
                case 6 -> {
                    System.out.print("Enter recipient email: ");
                    String recipient = scanner.nextLine();
                    System.out.print("Enter message body: ");
                    String body = scanner.nextLine();
                    admin.sendMessage(recipient, body);
                }
                case 7 -> admin.viewMessages();
                case 8 -> {
                    System.out.println(localizationManager.getMessage("logout")); // "Выход"
                    return;
                }
                default -> System.out.println(localizationManager.getMessage("invalid_option")); // "Неверный вариант"
            }
        }
    }



    private static void teacherActions(Teacher teacher, List<Course> courses) {
        while (true) {
            System.out.println("\n" + localizationManager.getMessage("teacher_dashboard"));
            System.out.println("1. " + localizationManager.getMessage("view_courses"));
            System.out.println("2. " + localizationManager.getMessage("assign_mark"));
            System.out.println("3. " + localizationManager.getMessage("view_students_in_course"));
            System.out.println("4. " + localizationManager.getMessage("send_message"));
            System.out.println("5. " + localizationManager.getMessage("view_messages"));
            System.out.println("6. " + localizationManager.getMessage("send_complaint"));
            System.out.println("7. " + localizationManager.getMessage("logout"));

            System.out.print(localizationManager.getMessage("choose_option") + " ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> teacher.viewCourses();
                case 2 -> assignMarkToStudent(teacher, courses);
                case 3 -> {
                    System.out.print(localizationManager.getMessage("enter_course_name") + " ");
                    String courseName = scanner.nextLine();
                    Course course = courses.stream()
                            .filter(c -> c.getCourseName().equalsIgnoreCase(courseName))
                            .findFirst()
                            .orElse(null);
                    if (course != null) {
                        teacher.viewStudentsInCourse(course);
                    } else {
                        System.out.println(localizationManager.getMessage("course_not_found"));
                    }
                }
                case 4 -> {
                    System.out.print("Enter recipient email: ");
                    String recipient = scanner.nextLine();
                    System.out.print("Enter message body: ");
                    String body = scanner.nextLine();
                    teacher.sendMessage(recipient, body);
                }
                case 5 -> teacher.viewMessages();
                case 6 -> {
                    System.out.print(localizationManager.getMessage("enter_complaint") + " ");
                    String complaint = scanner.nextLine();
                    teacher.sendComplaint(complaint);
                }
                case 7 -> {
                    System.out.println(localizationManager.getMessage("goodbye"));
                    return;
                }
                case 8 -> teacher.viewMessages();

                default -> System.out.println(localizationManager.getMessage("invalid_option"));
            }
        }
    }

    private static void sendMessageFromTeacher(Teacher teacher) {
        System.out.print(localizationManager.getMessage("enter_recipient") + " ");
        String recipient = scanner.nextLine();
        System.out.print(localizationManager.getMessage("enter_message") + " ");
        String messageBody = scanner.nextLine();

        teacher.sendMessage(recipient, messageBody);
    }



    private static void assignMarkToStudent(Teacher teacher, List<Course> courses) {
        System.out.print(localizationManager.getMessage("enter_course_name") + " ");
        String courseName = scanner.nextLine();
        Course course = courses.stream()
                .filter(c -> c.getCourseName().equalsIgnoreCase(courseName))
                .findFirst()
                .orElse(null);

        if (course == null) {
            System.out.println(localizationManager.getMessage("course_not_found"));
            return;
        }

        System.out.print(localizationManager.getMessage("enter_student_name") + " ");
        String studentName = scanner.nextLine();
        Student student = course.getEnrolledStudents().stream()
                .filter(s -> s.getName().equalsIgnoreCase(studentName))
                .findFirst()
                .orElse(null);

        if (student == null) {
            System.out.println(localizationManager.getMessage("student_not_found"));
            return;
        }

        System.out.print(localizationManager.getMessage("enter_mark") + " ");
        int mark = Integer.parseInt(scanner.nextLine());
        teacher.assignMark(student, course, new Mark(mark));
        System.out.println(localizationManager.getMessage("mark_assigned") + ": " + mark);
    }

    private static void studentActions(Student student, List<Course> courses, Library library) {
        while (true) {
            System.out.println("\n" + localizationManager.getMessage("student_dashboard"));
            System.out.println("1. " + localizationManager.getMessage("view_courses"));
            System.out.println("2. " + localizationManager.getMessage("register_course"));
            System.out.println("3. " + localizationManager.getMessage("view_transcript"));
            System.out.println("4. " + localizationManager.getMessage("view_marks"));
            System.out.println("5. " + localizationManager.getMessage("conduct_research"));
            System.out.println("6. " + localizationManager.getMessage("publish_paper"));
            System.out.println("7. " + localizationManager.getMessage("view_research_papers"));
            System.out.println("8. " + localizationManager.getMessage("calculate_h_index"));
            System.out.println("9. " + localizationManager.getMessage("logout"));
            System.out.print(localizationManager.getMessage("choose_option") + " ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> student.viewCourses();
                case 2 -> {
                    System.out.print(localizationManager.getMessage("enter_course_name") + " ");
                    String courseName = scanner.nextLine();
                    Course course = courses.stream()
                            .filter(c -> c.getCourseName().equalsIgnoreCase(courseName))
                            .findFirst()
                            .orElse(null);
                    if (course != null) {
                        student.registerForCourse(course);
                    } else {
                        System.out.println(localizationManager.getMessage("course_not_found"));
                    }
                }
                case 3 -> student.viewTranscript();
                case 4 -> student.viewMarks();
                case 5 -> System.out.println(student.getName() + " " + localizationManager.getMessage("is_conducting_research") + " " + student.getResearchField());
                case 6 -> {
                    System.out.print(localizationManager.getMessage("enter_paper_title") + " ");
                    String paperTitle = scanner.nextLine();
                    System.out.print(localizationManager.getMessage("enter_paper_abstract") + " ");
                    String paperAbstract = scanner.nextLine();
                    ResearchPaper newPaper = new ResearchPaper("Title", Collections.singletonList("Authors"), Collections.singletonList("0"),2023,"Journal");
                    student.addResearchPaper(newPaper);
                    System.out.println(localizationManager.getMessage("paper_added") + ": " + paperTitle);
                }
                case 7 -> student.printPapers(Comparator.comparing(ResearchPaper::getTitle));
                case 8 -> System.out.println(localizationManager.getMessage("h_index") + ": " + student.calculateHIndex());
                case 9 -> {
                    System.out.println(localizationManager.getMessage("goodbye"));
                    return;
                }
                default -> System.out.println(localizationManager.getMessage("invalid_option"));
            }
        }
    }


    private static void managerActions(Manager manager, List<Course> courses, List<Student> students, List<Teacher> teachers) {
        while (true) {
            System.out.println("\n" + localizationManager.getMessage("manager_dashboard"));
            System.out.println("1. " + localizationManager.getMessage("add_course"));
            System.out.println("2. " + localizationManager.getMessage("assign_course"));
            System.out.println("3. " + localizationManager.getMessage("view_courses"));
            System.out.println("4. " + localizationManager.getMessage("view_students"));
            System.out.println("5. " + localizationManager.getMessage("view_teachers"));
            System.out.println("6. " + localizationManager.getMessage("generate_report"));
            System.out.println("7. " + localizationManager.getMessage("logout"));
            System.out.print(localizationManager.getMessage("choose_option") + " ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    System.out.print("Enter major: ");
                    String major = scanner.nextLine();
                    System.out.print("Enter year of study: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    manager.addCourse(courseName, major, year, courses);
                }
                case 2 -> manager.assignCourseToTeacher(courses, teachers, students);
                case 3 -> manager.viewManagedCourses();
                case 4 -> {
                    System.out.println("1. Sort by GPA");
                    System.out.println("2. Sort alphabetically");
                    System.out.print("Choose option: ");
                    int sortOption = Integer.parseInt(scanner.nextLine());
                    if (sortOption == 1) {
                        manager.viewStudentsByGpa(students);
                    } else if (sortOption == 2) {
                        manager.viewStudentsAlphabetically(students);
                    } else {
                        System.out.println("Invalid option!");
                    }
                }
                case 5 -> manager.viewTeachers(teachers);
                case 6 -> manager.generatePerformanceReport(students);
                case 7 -> {
                    System.out.println(localizationManager.getMessage("goodbye"));
                    return;
                }
                default -> System.out.println(localizationManager.getMessage("invalid_option"));
            }
        }
    }
}