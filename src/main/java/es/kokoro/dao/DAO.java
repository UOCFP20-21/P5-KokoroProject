package es.kokoro.dao;

import java.util.List;

public interface DAO<T> {

    T get(long id);

    List<T> getAll() throws Exception;

    void save(T t);

    void update(T t, String[] params);

    void delete(T t);

}
