package model.entity;

import java.util.List;

/**
 *
 * @author thomas
 */
public class Classroom {

    private int id;
    private String name;
    private List<Student> lsStudent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getLsStudent() {
        return lsStudent;
    }

    public void setLsStudent(List<Student> lsStudent) {
        this.lsStudent = lsStudent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Classroom{");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append('}');
        return sb.toString();
    }

}
