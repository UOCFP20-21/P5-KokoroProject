package es.kokoro.dao;

import java.util.List;

public interface DAO<T> {

    T get(long id) throws Exception;

    List<T> getAll() throws Exception;

    void save(T t) throws Exception;

    void update(T t, String[] params) throws Exception;

    void delete(T t) throws Exception;

}
