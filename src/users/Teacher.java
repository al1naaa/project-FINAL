package users;

import communication.LocalizationManager;
import communication.Message;
import database.UserService;
import education.Course;
import education.Mark;
import interfaces.Messagable;
import interfaces.Notifiable;
import papers.ResearchPaper;
import papers.Researcher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Teacher extends User implements Notifiable, Messagable, Researcher {
    private String degree;
    private String faculty;
    private List<Course> courses;
    private List<String> complaints;
    private List<ResearchPaper> researchPapers;
    private String researchField;
    private List<String> notifications = new ArrayList<>();
    private UserService userService;
    private LocalizationManager localizationManager;

    public Teacher(String IIN, String name, String surname, String email, String password, String degree, String faculty,
                   UserService userService, LocalizationManager localizationManager, String researchField) {
        super(IIN, name, surname, email, password);
        this.degree = degree;
        this.faculty = faculty;
        this.courses = new ArrayList<>();
        this.complaints = new ArrayList<>();
        this.userService = userService;
        this.localizationManager = localizationManager;
        this.researchPapers = new ArrayList<>();
        this.researchField = researchField;
    }
    public Teacher(String iin, String name, String surname, String email, String password, UserService userService) {
        super(iin, name, surname, email, password); // Call the User constructor
        this.userService = userService;
        this.courses = new ArrayList<>();
        this.complaints = new ArrayList<>();
        this.researchPapers = new ArrayList<>();
    }


    public Teacher(String IIN, String name, String surname, String email, String password, String degree, String faculty,
                   UserService userService, LocalizationManager localizationManager) {
        this(IIN, name, surname, email, password, degree, faculty, userService, localizationManager, null);
    }


    @Override
    public void sendNotification(String message) {
        System.out.println("Teacher sends notification: " + message);
    }

    @Override
    public void receiveNotification(String message) {
        notifications.add(message);
        System.out.println("New notification for Teacher: " + message);
    }

    public List<String> getNotifications() {
        return notifications;
    }

    @Override
    public void sendMessage(String receiverEmail, String body) {
        userService.sendMessage(this.getEmail(), receiverEmail, body);
        System.out.println(localizationManager.getMessage("message_sent_to") + ": " + receiverEmail);
    }

    @Override
    public List<Message> viewMessages() {
        List<Message> messages = userService.getMessages(this.getEmail());
        if (messages.isEmpty()) {
            System.out.println(localizationManager.getMessage("no_messages"));
        } else {
            System.out.println(localizationManager.getMessage("your_messages") + ":");
            for (Message message : messages) {
                System.out.println(message.displayMessage());
            }
        }
        return messages;
    }

    public void sendComplaint(String complaint) {
        complaints.add(complaint);
        System.out.println("Complaint registered: " + complaint);
    }

    public void viewCourses() {
        if (courses == null || courses.isEmpty()) {
            System.out.println(localizationManager.getMessage("no_courses"));
        } else {
            System.out.println(localizationManager.getMessage("your_courses") + ":");
            for (Course course : courses) {
                System.out.println("- " + course.getCourseName());
            }
        }
    }

    public void addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            course.setTeacher(this);
        }
    }

    public void removeCourse(Course course) {
        if (courses.contains(course)) {
            courses.remove(course);
            course.setTeacher(null);
        }
    }

    public void viewStudentsInCourse(Course course) {
        System.out.println("Students in course " + course.getCourseName() + ":");
        for (Student student : course.getEnrolledStudents()) {
            System.out.println(student.getName());
        }
    }

    public void assignMark(Student student, Course course, Mark mark) {
        System.out.println("Assigned mark for " + student.getName() + " in course " + course.getCourseName());
    }

    @Override
    public void conductResearch() {
        if (researchField == null) {
            System.out.println("This teacher is not a researcher.");
        } else {
            System.out.println(getName() + " is conducting research in " + researchField);
        }
    }

    @Override
    public void publishPaper() {
        if (researchField == null) {
            System.out.println("This teacher is not a researcher.");
        } else {
            System.out.println(getName() + " is publishing a paper in " + researchField);
        }
    }

    @Override
    public void attendConference() {
        if (researchField == null) {
            System.out.println("This teacher is not a researcher.");
        } else {
            System.out.println(getName() + " is attending a research conference.");
        }
    }

    @Override
    public String getResearchField() {
        return researchField;
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> comparator) {
        researchPapers.sort(comparator);
        for (ResearchPaper paper : researchPapers) {
            System.out.println(paper);
        }
    }

    @Override
    public int calculateHIndex() {
        researchPapers.sort(Comparator.comparingInt(ResearchPaper::getCitations).reversed());
        int hIndex = 0;
        for (int i = 0; i < researchPapers.size(); i++) {
            if (researchPapers.get(i).getCitations() >= i + 1) {
                hIndex = i + 1;
            } else {
                break;
            }
        }
        return hIndex;
    }

    public void addResearchPaper(ResearchPaper paper) {
        researchPapers.add(paper);
    }

    public String getDegree() {
        return degree;
    }
}
