package users;

import education.Course;
import communication.News;
import communication.Request;

import java.util.*;

public class Manager extends Employee {
    private List<Course> availableCourses;
    private List<News> newsList;
    private List<Request> requests;

    public Manager(String specialization, Double salary, Integer experience, String department, String dateOfJoining) {
        super(specialization, salary, experience, department, dateOfJoining);
        this.availableCourses = new ArrayList<>();
        this.newsList = new ArrayList<>();
        this.requests = new ArrayList<>();
    }

    public void approveRegistration(Student student, Course course) {
        if (!course.getEnrolledStudents().contains(student)) {
            course.addStudent(student);
            System.out.println("Approved registration for student " + student.getName() + " in course " + course.getCourseName());
        } else {
            System.out.println("Student " + student.getName() + " is already registered for course " + course.getCourseName());
        }
    }

    public void addCourseForRegistration(Course course, String major, int yearOfStudy) {
        course.setMajor(major);
        course.setYearOfStudy(yearOfStudy);
        availableCourses.add(course);
        System.out.println("Added course " + course.getCourseName() + " for major " + major + " and year " + yearOfStudy);
    }

    public void assignCourseToTeacher(Course course, Teacher teacher) {
        course.setTeacher(teacher);
        System.out.println("Assigned course " + course.getCourseName() + " to teacher " + teacher.getName());
    }

    public void createStatisticalReport(List<Student> students) {
        double totalGPA = 0;
        int count = 0;

        for (Student student : students) {
            totalGPA += student.getGpa();
            count++;
        }

        double averageGPA = count == 0 ? 0 : totalGPA / count;
        System.out.println("Average GPA of students: " + averageGPA);

        System.out.println("Top Students:");
        students.sort((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()));
        for (int i = 0; i < Math.min(5, students.size()); i++) {
            System.out.println(students.get(i).getName() + " - GPA: " + students.get(i).getGpa());
        }
    }

    public void manageNews(String action, News news) {
        if (action.equalsIgnoreCase("add")) {
            newsList.add(news);
            System.out.println("Added news: " + news.getTitle());
        } else if (action.equalsIgnoreCase("remove")) {
            newsList.remove(news);
            System.out.println("Removed news: " + news.getTitle());
        } else {
            System.out.println("Invalid action for news management.");
        }
    }

    public void viewInfo(List<? extends User> users, String criteria) {
        if (criteria.equalsIgnoreCase("gpa")) {
            for (User user : users) {
                if (user instanceof Student) {
                    System.out.println(user.getName() + " - GPA: " + ((Student) user).getGpa());
                }
            }
        } else if (criteria.equalsIgnoreCase("alphabetical")) {
            users.sort(Comparator.comparing(User::getName));
            for (User user : users) {
                System.out.println(user.getName());
            }
        } else {
            System.out.println("Invalid criteria for viewing info.");
        }
    }

    public void viewRequests() {
        System.out.println("Requests from employees:");
        for (Request request : requests) {
            System.out.println(request.getDescription());
        }
    }

    public void addRequest(Request request) {
        requests.add(request);
    }

    public void removeRequest(Request request) {
        requests.remove(request);
    }
}
