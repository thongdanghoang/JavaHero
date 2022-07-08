package ctrl;

import ctrl.dto.ClassroomDTO;
import ctrl.map.ClassroomMapping;
import java.util.HashMap;
import java.util.Map;
import model.dao.ClassroomDAO;
import model.dao.exception.DeleteExceptions;
import model.dao.exception.FindByIDExceptions;
import model.dao.exception.GetAllExceptions;
import model.dao.exception.InsertExceptions;
import model.dao.exception.UpdateExceptions;
import model.dbconnection.DBConnectionException;

public class ClassroomCtrl implements IController<ClassroomDTO, String> {

    private final ClassroomMapping mapping = new ClassroomMapping();
    private final ClassroomDAO dao = new ClassroomDAO();

    @Override
    public ClassroomDTO insert(ClassroomDTO t) throws DBConnectionException, InsertExceptions {
        return mapping.toDTO(dao.insert(mapping.toEntity(t)));
    }

    @Override
    public ClassroomDTO updateById(ClassroomDTO t) throws DBConnectionException, UpdateExceptions {
        return mapping.toDTO(dao.updateById(mapping.toEntity(t)));
    }

    @Override
    public boolean deleteById(String k) throws DBConnectionException, DeleteExceptions {
        return dao.deleteById(k);
    }

    @Override
    public Map<String, ClassroomDTO> getAll() throws DBConnectionException, GetAllExceptions {
        Map<String, ClassroomDTO> list = new HashMap<>();
        dao.getAll().values().stream()
                .map(mapping::toDTO)
                .forEach(e -> list.put(e.getIdClassroom(), e));
        return list;
    }

    @Override
    public ClassroomDTO findById(String k) throws DBConnectionException, FindByIDExceptions {
        return mapping.toDTO(dao.findById(k));
    }
}
