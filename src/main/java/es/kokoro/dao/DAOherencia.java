package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.Herencia;

import java.util.List;
import java.util.Optional;

public class DAOherencia<pHerencia> implements DAO<Herencia,pHerencia> {
    @Override
    public Optional<Herencia> devuelve(pHerencia pHerencia) {
        return Optional.empty();
    }

    @Override
    public List<Herencia> devuelveTodos() {
        return null;
    }

    @Override
    public Long crear(Herencia herencia) {
        return null;
    }

    @Override
    public void actualiza(Herencia herencia) {

    }
}
