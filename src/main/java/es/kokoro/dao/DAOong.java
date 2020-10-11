package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.Ong;

import java.util.List;
import java.util.Optional;

public class DAOong<pOng> implements DAO<Ong,pOng> {
    @Override
    public Optional<Ong> devuelve(pOng pOng) {
        return Optional.empty();
    }

    @Override
    public List<Ong> devuelveTodos() {
        return null;
    }

    @Override
    public Long crear(Ong ong) {
        return null;
    }

    @Override
    public void actualiza(Ong ong) {

    }
}
