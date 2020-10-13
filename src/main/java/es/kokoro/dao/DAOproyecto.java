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
    public Long crea(Proyecto proyecto) {
        return null;
    }

    @Override
    public void actualiza(Proyecto proyecto, String[] params) {

    }

    @Override
    public void borra(Proyecto proyecto) {

    }
}
