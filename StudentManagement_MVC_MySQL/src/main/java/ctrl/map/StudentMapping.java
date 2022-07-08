package ctrl.map;

import ctrl.dto.StudentDTO;
import model.dao.ClassroomDAO;
import model.dao.exception.FindByIDExceptions;
import model.dbconnection.DBConnectionException;
import model.entity.Gender;
import model.entity.Student;
import utils.DateTimeConvert;

public class StudentMapping implements IMapping<Student, StudentDTO> {

    private ClassroomDAO clDAO = new ClassroomDAO();

    @Override
    public StudentDTO toDTO(Student entity) {
        StudentDTO dto = new StudentDTO();
        dto.setIdStudent(entity.getId());
        dto.setIdClassroom(entity.getClassroom().getId());
        dto.setFullName(entity.getFullName());
        dto.setBirthday(DateTimeConvert.dateToString(entity.getBirthday()));
        dto.setGender(entity.getGender().toString());
        return dto;
    }

    @Override
    public Student toEntity(StudentDTO dto) {
        Student entity = new Student();
        entity.setId(dto.getIdStudent());
        entity.setGender(Gender.valueOf(dto.getGender().toUpperCase()));
        entity.setFullName(dto.getFullName());
        entity.setBirthday(DateTimeConvert.stringToDate(dto.getBirthday()));
        try {
            entity.setClassroom(clDAO.findById(dto.getIdClassroom()));
        } catch (DBConnectionException | FindByIDExceptions ex) {
            System.err.println(ex.getMessage());
        }
        return entity;
    }
}
