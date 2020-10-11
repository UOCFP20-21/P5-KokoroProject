package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.Estatal;

import java.util.List;
import java.util.Optional;

public class DAOestatal<pEstatal> implements DAO<Estatal, pEstatal> {
    @Override
    public Optional<Estatal> devuelve(pEstatal pEstatal) {
        return Optional.empty();
    }

    @Override
    public List<Estatal> devuelveTodos() {
        return null;
    }

    @Override
    public Long crear(Estatal estatal) {
        return null;
    }

    @Override
    public void actualiza(Estatal estatal) {

    }
}
