package users;

import education.Course;
import education.Mark;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends User {
    private String degree;
    private String faculty;
    private List<Course> courses;
    private List<String> complaints;

    public Teacher(String IIN, String name, String surname, String email, String password, String degree, String faculty) {
        super(IIN, name, surname, email, password);
        this.degree = degree;
        this.faculty = faculty;
        this.courses = new ArrayList<>();
        this.complaints = new ArrayList<>();
    }

    public void manageCourse(Course course, boolean add) {
        if (add) {
            courses.add(course);
            course.setTeacher(this);
        } else {
            courses.remove(course);
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

    public void sendMessage(String recipient, String message) {
        System.out.println("Message sent to " + recipient + ": " + message);
    }

    public void sendComplaint(String complaint) {
        complaints.add(complaint);
        System.out.println("Complaint registered: " + complaint);
    }
}
