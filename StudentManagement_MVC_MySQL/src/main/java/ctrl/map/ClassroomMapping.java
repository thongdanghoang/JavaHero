package ctrl.map;

import ctrl.dto.ClassroomDTO;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.StudentDAO;
import model.dao.exception.GetAllExceptions;
import model.dbconnection.DBConnectionException;
import model.entity.Clazz;
import model.entity.Student;

public class ClassroomMapping implements IMapping<Clazz, ClassroomDTO> {

    private StudentDAO dao = new StudentDAO();

    @Override
    public ClassroomDTO toDTO(Clazz entity) {
        ClassroomDTO dto = new ClassroomDTO();
        dto.setIdClassroom(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    @Override
    public Clazz toEntity(ClassroomDTO dto) {
        Clazz entity = new Clazz();
        entity.setId(dto.getIdClassroom());
        entity.setName(dto.getName());
        try {
            Map<String, Student> list = new HashMap<>();
            dao.getAll().values().stream()
                    .filter(t -> t.getClassroom().getId().equals(dto.getIdClassroom()))
                    .forEach(e -> list.put(e.getClassroom().getId(), e));
            entity.setLsStudent(list);
        } catch (DBConnectionException ex) {
            Logger.getLogger(ClassroomMapping.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GetAllExceptions ex) {
            System.err.println(ex.getMessage());
        }
        return entity;
    }
}
