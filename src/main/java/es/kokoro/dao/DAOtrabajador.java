package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.Trabajador;

import java.util.List;
import java.util.Optional;

public class DAOtrabajador<pTrabajador> implements DAO<Trabajador, pTrabajador> {
    @Override
    public Optional<Trabajador> devuelve(pTrabajador pTrabajador) {
        return Optional.empty();
    }

    @Override
    public List<Trabajador> devuelveTodos() {
        return null;
    }

    @Override
    public Long crear(Trabajador trabajador) {
        return null;
    }

    @Override
    public void actualiza(Trabajador trabajador) {

    }
}
