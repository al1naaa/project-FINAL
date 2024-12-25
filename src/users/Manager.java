package users;

import education.Course;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Manager extends User {
    private List<Course> managedCourses;
    private Scanner scanner;

    public Manager(String IIN, String name, String surname, String email, String password, Scanner scanner) {
        super(IIN, name, surname, email, password);
        this.scanner = scanner;
        this.managedCourses = new ArrayList<>();
    }

    public void addCourse(String courseName, String major, int year, List<Course> courses) {
        Course newCourse = new Course(courseName, courses.size() + 1, null);
        newCourse.setMajor(major);
        newCourse.setYearOfStudy(year);

        managedCourses.add(newCourse);
        courses.add(newCourse);
        System.out.println("Course added: " + courseName);
    }

    public void assignCourseToTeacher(List<Course> courses, List<Teacher> teachers, List<Student> students) {
        System.out.println("Available courses:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i).getCourseName());
        }
        System.out.print("Choose course: ");
        int courseIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (courseIndex < 0 || courseIndex >= courses.size()) {
            System.out.println("Invalid course!");
            return;
        }

        System.out.println("Available teachers:");
        for (int i = 0; i < teachers.size(); i++) {
            System.out.println((i + 1) + ". " + teachers.get(i).getName());
        }
        System.out.print("Choose teacher: ");
        int teacherIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (teacherIndex < 0 || teacherIndex >= teachers.size()) {
            System.out.println("Invalid teacher!");
            return;
        }

        Course course = courses.get(courseIndex);
        Teacher teacher = teachers.get(teacherIndex);

        course.setTeacher(teacher);
        System.out.println("Assigned " + teacher.getName() + " to course " + course.getCourseName());
    }

    public void viewManagedCourses() {
        System.out.println("Managed Courses:");
        if (managedCourses.isEmpty()) {
            System.out.println("No managed courses available.");
        } else {
            for (Course course : managedCourses) {
                System.out.println("Course: " + course.getCourseName() + " | Major: " + course.getMajor() + " | Year: " + course.getYearOfStudy());
            }
        }
    }

    public void viewStudentsByGpa(List<Student> students) {
        students.stream()
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed())
                .forEach(student -> System.out.println("Name: " + student.getName() + " | GPA: " + student.getGpa()));
    }

    public void viewStudentsAlphabetically(List<Student> students) {
        students.stream()
                .sorted(Comparator.comparing(Student::getName, Comparator.nullsLast(String::compareTo)))
                .forEach(student -> System.out.println("Name: " + student.getName()));
    }

    public void viewTeachers(List<Teacher> teachers) {
        System.out.println("Teachers:");
        for (Teacher teacher : teachers) {
            System.out.println("Name: " + teacher.getName() + " | Degree: " + teacher.getDegree());
        }
    }

    public void generatePerformanceReport(List<Student> students) {
        System.out.println("Performance Report:");
        for (Student student : students) {
            System.out.println("Name: " + student.getName() + " | GPA: " + student.getGpa());
        }
    }
}
