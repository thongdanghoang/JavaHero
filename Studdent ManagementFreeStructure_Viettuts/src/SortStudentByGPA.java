

import java.util.Comparator;

public class SortStudentByGPA implements Comparator<Student> {

    @Override
    public int compare(Student student1, Student student2) {
        return student1.getGpa() > student2.getGpa() ? 1 : -1;
    }
}
