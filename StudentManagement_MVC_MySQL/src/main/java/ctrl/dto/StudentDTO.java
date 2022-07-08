package ctrl.dto;

public class StudentDTO {

    private String idClassroom;
    private String idStudent;
    private String fullName;
    private String birthday;
    private String gender;

    public StudentDTO() {
    }

    public StudentDTO(String idStudent, String idClassroom, String fullName, String birthday, String gender) {
        this.idStudent = idStudent;
        this.idClassroom = idClassroom;
        this.fullName = fullName;
        this.birthday = birthday;
        this.gender = gender;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getIdClassroom() {
        return idClassroom;
    }

    public void setIdClassroom(String idClassroom) {
        this.idClassroom = idClassroom;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StudentDTO{");
        sb.append("idStudent='").append(idStudent).append('\'');
        sb.append(", idClassroom='").append(idClassroom).append('\'');
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", birthday='").append(birthday).append('\'');
        sb.append(", gender='").append(gender).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
