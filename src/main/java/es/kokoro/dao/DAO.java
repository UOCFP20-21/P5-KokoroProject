package es.kokoro.dao;

import java.util.List;

public interface DAO<T> {

    T get(long id) throws Exception;

    List<T> getAll() throws Exception;

    T save(T t) throws Exception;

    T update(T t) throws Exception;

    void delete(T t) throws Exception;

}
