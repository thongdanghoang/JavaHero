package model.dao;

import java.util.List;

public interface IDAO<T, K> {

    T insert(T t);

    T updateById(T t);

    boolean deleteById(T t);

    List<T> getAll();

    T findById(K k);
}
