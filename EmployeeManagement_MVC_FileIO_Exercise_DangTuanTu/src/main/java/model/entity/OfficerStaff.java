package model.entity;

import java.io.Serializable;

public class OfficerStaff extends Staff implements Serializable {

    private float salaryCoefficient;

    public OfficerStaff(String id, String name, Gender gender, float salary, float salaryCoefficient) {
        super(id, name, gender, salary);
        this.salaryCoefficient = salaryCoefficient;
    }

    public float getSalaryCoefficient() {
        return salaryCoefficient;
    }

    public void setSalaryCoefficient(float salaryCoefficient) {
        this.salaryCoefficient = salaryCoefficient;
    }

    @Override
    public float calSalary() {
        return (float) (getSalary() + getSalary() * getSalaryCoefficient());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("OfficerStaff{");
        sb.append("salaryCoefficient=").append(salaryCoefficient);
        sb.append(", calculated salary=").append(calSalary());
        sb.append('}');
        return sb.toString();
    }
}
