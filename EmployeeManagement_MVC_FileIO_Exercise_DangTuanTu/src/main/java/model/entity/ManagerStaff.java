package model.entity;

import java.io.Serializable;

public class ManagerStaff extends Staff implements Serializable {

    private int nEmployees;

    public ManagerStaff(String id, String name, Gender gender, float salary, int nEmployees) {
        super(id, name, gender, salary);
        this.nEmployees = nEmployees;
    }

    public int getnEmployees() {
        return nEmployees;
    }

    public void setnEmployees(int nEmployees) {
        this.nEmployees = nEmployees;
    }

    @Override
    public float calSalary() {
        if (getnEmployees() > 0 && getnEmployees() < 10) {
            return (float) (getSalary() + 500);
        } else if (getnEmployees() <= 20) {
            return (float) (getSalary() + 1000);
        } else {
            return (float) (getSalary() + 2000);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("ManagerStaff{");
        sb.append("nEmployees=").append(nEmployees);
        sb.append(", calculated salary=").append(calSalary());
        sb.append('}');
        return sb.toString();
    }
}
