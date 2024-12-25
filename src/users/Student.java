package users;

import education.Course;
import education.Transcript;
import interfaces.Notifiable;
import papers.ResearchPaper;
import papers.Researcher;
import users.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Student extends User implements Notifiable, Researcher {
    private String researchField;
    private List<ResearchPaper> researchPapers;
    private int yearOfStudy;
    private double gpa;
    private String educationalProgram;
    private List<Course> registeredCourses;
    private Transcript transcript;
    private List<String> notifications = new ArrayList<>();

    public Student(String IIN, String name, String surname, String email, String password, int yearOfStudy, double gpa, String educationalProgram, String researchField) {
        super(IIN, name, surname, email, password);
        this.yearOfStudy = yearOfStudy;
        this.gpa = gpa;
        this.educationalProgram = educationalProgram;
        this.registeredCourses = new ArrayList<>();
        this.transcript = new Transcript();
        this.researchField = researchField;
        this.researchPapers = new ArrayList<>();
    }

    public Student(String IIN, String name, String surname, String email, String password) {
        super(IIN, name, surname, email, password);
        this.registeredCourses = new ArrayList<>();
        this.transcript = new Transcript();
        this.researchPapers = new ArrayList<>();
    }

    @Override
    public void conductResearch() {
        System.out.println(getName() + " is conducting research in " + researchField);
    }

    @Override
    public void publishPaper() {
        System.out.println(getName() + " is publishing a paper in " + researchField);
    }

    @Override
    public void attendConference() {
        System.out.println(getName() + " is attending a research conference.");
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


    @Override
    public void sendNotification(String message) {
        System.out.println("Student cannot send notifications directly");
    }

    @Override
    public void receiveNotification(String message) {
        System.out.println("New notification for Student: " + message);
        notifications.add(message);
    }

    public List<String> getNotifications() {
        return notifications;
    }

    public void viewCourses() {
        System.out.println("Registered Courses:");
        for (Course course : registeredCourses) {
            System.out.println("Course: " + course.getCourseName());
        }
    }

    public void registerForCourse(Course course) {
        course.addStudent(this);
        registeredCourses.add(course);
        System.out.println("Registered for course: " + course.getCourseName());
    }

    public void viewMarks() {
        System.out.println("Marks:");
        for (Map.Entry<Course, Integer> entry : transcript.getAllGrades().entrySet()) {
            System.out.println("Course: " + entry.getKey().getCourseName() + ", Grade: " + entry.getValue());
        }
    }

    public void viewTranscript() {
        System.out.println("Transcript:");
        for (Map.Entry<Course, Integer> entry : transcript.getAllGrades().entrySet()) {
            System.out.println("Course: " + entry.getKey().getCourseName() + ", Grade: " + entry.getValue());
        }
        System.out.println("GPA: " + gpa);
    }

    public void rateTeacher(String teacher, int rating) {
        System.out.println("Rated teacher " + teacher + " with rating: " + rating);
    }

    public double getGpa() {
        return gpa;
    }
}
