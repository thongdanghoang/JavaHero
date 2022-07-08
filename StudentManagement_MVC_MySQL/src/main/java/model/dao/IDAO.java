package model.dao;

import model.dao.exception.*;
import model.dbconnection.DBConnectionException;
import java.util.Map;

public interface IDAO<T, K> {

    T insert(T t) throws DBConnectionException, InsertExceptions;

    T updateById(T t) throws DBConnectionException, UpdateExceptions;

    boolean deleteById(K k) throws DBConnectionException, DeleteExceptions;

    Map<K, T> getAll() throws DBConnectionException, GetAllExceptions;

    T findById(K k) throws DBConnectionException, FindByIDExceptions;
}
