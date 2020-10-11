package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.Accion;

import java.util.List;
import java.util.Optional;

public class DAOaccion<vAccion> implements DAO<Accion, vAccion> {


    @Override
    public Optional<Accion> devuelve(vAccion vAccion) {
        return Optional.empty();
    }

    @Override
    public List<Accion> devuelveTodos() {
        return null;
    }

    @Override
    public Long crear(Accion accion) {
        return null;
    }

    @Override
    public void actualiza(Accion accion) {

    }
}
