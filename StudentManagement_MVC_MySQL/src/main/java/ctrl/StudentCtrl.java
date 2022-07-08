package ctrl;

import ctrl.dto.StudentDTO;
import ctrl.map.StudentMapping;
import java.util.HashMap;
import java.util.Map;
import model.dao.StudentDAO;
import model.dao.exception.DeleteExceptions;
import model.dao.exception.FindByIDExceptions;
import model.dao.exception.GetAllExceptions;
import model.dao.exception.InsertExceptions;
import model.dao.exception.UpdateExceptions;
import model.dbconnection.DBConnectionException;

public class StudentCtrl implements IController<StudentDTO, String> {

    private final StudentMapping mapping = new StudentMapping();
    private final StudentDAO dao = new StudentDAO();

    @Override
    public StudentDTO insert(StudentDTO t) throws DBConnectionException, InsertExceptions {
        return mapping.toDTO(dao.insert(mapping.toEntity(t)));
    }

    @Override
    public StudentDTO updateById(StudentDTO t) throws DBConnectionException, UpdateExceptions {
        return mapping.toDTO(dao.updateById(mapping.toEntity(t)));
    }

    @Override
    public Map<String, StudentDTO> getAll() throws DBConnectionException, GetAllExceptions {
        Map<String, StudentDTO> list = new HashMap<>();
        dao.getAll().values().stream()
                .map(mapping::toDTO)
                .forEach(e -> list.put(e.getIdStudent(), e));
        return list;
    }

    @Override
    public StudentDTO findById(String k) throws DBConnectionException, FindByIDExceptions {
        return mapping.toDTO(dao.findById(k));
    }

    @Override
    public boolean deleteById(String k) throws DBConnectionException, DeleteExceptions {
        return dao.deleteById(k);
    }
}
