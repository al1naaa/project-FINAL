package comparators;

import users.Student;

import java.util.Comparator;

public class GpaComparator implements Comparator<Student> {
        public int compare(Student o1, Student o2) {
        return Double.compare(o1.getGpa(), o2.getGpa());
    }
}
