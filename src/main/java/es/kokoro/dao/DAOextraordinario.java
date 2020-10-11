package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.Extraordinario;

import java.util.List;
import java.util.Optional;

public class DAOextraordinario<pExtraordinario> implements DAO<Extraordinario, pExtraordinario> {

    @Override
    public Optional<Extraordinario> devuelve(pExtraordinario pExtraordinario) {
        return Optional.empty();
    }

    @Override
    public List<Extraordinario> devuelveTodos() {
        return null;
    }

    @Override
    public Long crear(Extraordinario extraordinario) {
        return null;
    }

    @Override
    public void actualiza(Extraordinario extraordinario) {

    }
}
