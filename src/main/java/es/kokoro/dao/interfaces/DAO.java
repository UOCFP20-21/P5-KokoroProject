package es.kokoro.dao.interfaces;

import java.util.List;
import java.util.Optional;

/***
 * DAO Genéricos
 * @param <T> Tipo Objeto
 * @param <P> Nombre del "tipo de Atributo" (long, String, int...)
 */
public interface DAO<T, P> {

    Optional<T> devuelve(P p);

    List<T> devuelveTodos();

    Long crear(T t);

    void actualiza(T t);

}
