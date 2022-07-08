package ctrl.dto;

public class ClassroomDTO {
    private String idClassroom;
    private String name;

    public ClassroomDTO() {
    }

    public ClassroomDTO(String idClassroom, String name) {
        this.idClassroom = idClassroom;
        this.name = name;
    }

    public String getIdClassroom() {
        return idClassroom;
    }

    public void setIdClassroom(String idClassroom) {
        this.idClassroom = idClassroom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClassroomDTO{");
        sb.append("idClassroom='").append(idClassroom).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
