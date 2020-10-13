package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.Ingreso;

import java.util.List;
import java.util.Optional;

public class DAOingreso<pIngreso> implements DAO<Ingreso, pIngreso> {
    @Override
    public Optional<Ingreso> devuelve(pIngreso pIngreso) {
        return Optional.empty();
    }

    @Override
    public List<Ingreso> devuelveTodos() {
        return null;
    }

    @Override
    public Long crea(Ingreso ingreso) {
        return null;
    }

    @Override
    public void actualiza(Ingreso ingreso, String[] params) {

    }

    @Override
    public void borra(Ingreso ingreso) {

    }
}
