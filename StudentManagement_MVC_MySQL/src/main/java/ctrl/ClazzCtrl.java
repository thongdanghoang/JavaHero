package ctrl;

import ctrl.dto.ClazzDTO;
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

public class ClazzCtrl implements IController<ClazzDTO, String> {

    private final ClassroomMapping mapping = new ClassroomMapping();
    private final ClassroomDAO dao = new ClassroomDAO();

    @Override
    public ClazzDTO insert(ClazzDTO t) throws DBConnectionException, InsertExceptions {
        return mapping.toDTO(dao.insert(mapping.toEntity(t)));
    }

    @Override
    public ClazzDTO updateById(ClazzDTO t) throws DBConnectionException, UpdateExceptions {
        return mapping.toDTO(dao.updateById(mapping.toEntity(t)));
    }

    @Override
    public boolean deleteById(String k) throws DBConnectionException, DeleteExceptions {
        return dao.deleteById(k);
    }

    @Override
    public Map<String, ClazzDTO> getAll() throws DBConnectionException, GetAllExceptions {
        Map<String, ClazzDTO> list = new HashMap<>();
        dao.getAll().values().stream()
                .map(mapping::toDTO)
                .forEach(e -> list.put(e.getIdClassroom(), e));
        return list;
    }

    @Override
    public ClazzDTO findById(String k) throws DBConnectionException, FindByIDExceptions {
        return mapping.toDTO(dao.findById(k));
    }
}
