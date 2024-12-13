package education;

import interfaces.Schedule;
import users.*;

import java.util.*;

public class Lesson implements Schedule {
    private String lessonName;
    private int lessonId;
    private Teacher teacher;
    private List<Student> students;
    private String schedule;

    public Lesson(String lessonName, int lessonId, Teacher teacher) {
        this.lessonName = lessonName;
        this.lessonId = lessonId;
        this.teacher = teacher;
        this.students = new ArrayList<>();
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String getSchedule() {
        return schedule;
    }

    @Override
    public void setSchedule(String dateTime) {
        this.schedule = schedule;
    }

    @Override
    public void displaySchedule() {
        System.out.println(schedule);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }
}
