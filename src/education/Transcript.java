package education;

import java.util.*;

public class Transcript {
    private Map<Course, Integer> grades;

    public Transcript() {
        grades = new HashMap<>();
    }

    public void addGrade(Course course, int grade) {
        grades.put(course, grade);
    }

    public int getGrade(Course course) {
        return grades.getOrDefault(course, 0);
    }

    public Map<Course, Integer> getAllGrades() {
        return grades;
    }
}