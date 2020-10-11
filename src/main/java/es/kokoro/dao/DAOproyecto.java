package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.Proyecto;

import java.util.List;
import java.util.Optional;

public class DAOproyecto<pProyecto> implements DAO<Proyecto,pProyecto> {
    @Override
    public Optional<Proyecto> devuelve(pProyecto pProyecto) {
            return Optional.empty();
    }

    @Override
    public List<Proyecto> devuelveTodos() {
        return null;
    }

    @Override
    public Long crear(Proyecto proyecto) {
        return null;
    }

    @Override
    public void actualiza(Proyecto proyecto) {

    }
}
