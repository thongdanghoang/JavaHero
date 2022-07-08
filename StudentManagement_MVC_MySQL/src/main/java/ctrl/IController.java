package ctrl;

import model.dao.exception.InsertExceptions;
import model.dbconnection.DBConnectionException;

import java.util.Map;
import model.dao.exception.DeleteExceptions;
import model.dao.exception.FindByIDExceptions;
import model.dao.exception.GetAllExceptions;
import model.dao.exception.UpdateExceptions;

public interface IController<T, K> {

    T insert(T t) throws DBConnectionException, InsertExceptions;

    T updateById(T t) throws DBConnectionException, UpdateExceptions;

    boolean deleteById(K k) throws DBConnectionException, DeleteExceptions;

    Map<K, T> getAll() throws DBConnectionException, GetAllExceptions;

    T findById(K k) throws DBConnectionException, FindByIDExceptions;
}
