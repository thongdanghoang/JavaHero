package model.entity;

import java.io.Serializable;

public abstract class Staff implements IStaff, Serializable {
    private static int count = 0;
    private String id;
    private String name;
    private Gender gender;
    private float salary;


    public Staff(String id, String name, Gender gender, float salary) {
        this.id = id.concat(String.valueOf(count));
        count++;
        this.name = name;
        this.gender = gender;
        this.salary = salary;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Staff.count = count;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Staff{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", gender=").append(gender);
        sb.append(", salary=").append(salary);
        sb.append('}');
        sb.append("\t");
        return sb.toString();
    }
}
