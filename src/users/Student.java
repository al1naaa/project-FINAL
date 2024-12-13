package users;

import education.Course;
import education.Transcript;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Student extends User {
    private int yearOfStudy;
    private double gpa;
    private String educationalProgram;
    private List<Course> registeredCourses;
    private Transcript transcript;

    public Student(String IIN, String name, String surname, String email, String password, int yearOfStudy, double gpa, String educationalProgram) {
        super(IIN, name, surname, email, password);
        this.yearOfStudy = yearOfStudy;
        this.gpa = gpa;
        this.educationalProgram = educationalProgram;
        this.registeredCourses = new ArrayList<>();
        this.transcript = new Transcript();
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
