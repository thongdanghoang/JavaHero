package model.entity;

import java.io.Serializable;

public class WorkerStaff extends Staff implements Serializable {

    private int workdays;

    public WorkerStaff(String id, String name, Gender gender, float salary, int workdays) {
        super(id, name, gender, salary);
        this.workdays = workdays;
    }

    @Override
    public float calSalary() {
        return (float) (getSalary() + getSalary() * (workdays / 26));
    }

    public int getWorkdays() {
        return workdays;
    }

    public void setWorkdays(int workdays) {
        this.workdays = workdays;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("WorkerStaff{");
        sb.append("workdays=").append(workdays);
        sb.append(", calculated salary=").append(calSalary());
        sb.append('}');
        return sb.toString();
    }
}
