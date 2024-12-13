package education;

import users.*;

import java.util.*;

public class Course {
    private String courseName;
    private int courseId;
    private List<Student> enrolledStudents;
    private Teacher teacher;
    private String major;
    private int yearOfStudy;

    public Course(String courseName, int courseId, Teacher teacher) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.teacher = teacher;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public void addStudent(Student student) {
        enrolledStudents.add(student);
    }

    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void displayCourseInfo() {
        System.out.println("Course: " + courseName + " | Teacher: " + (teacher != null ? teacher.getName() : "Not assigned"));
    }
}