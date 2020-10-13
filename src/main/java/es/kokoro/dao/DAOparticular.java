package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.Particular;

import java.util.List;
import java.util.Optional;

public class DAOparticular<pParticular> implements DAO<Particular,pParticular> {
    @Override
    public Optional<Particular> devuelve(pParticular pParticular) {
        return Optional.empty();
    }

    @Override
    public List<Particular> devuelveTodos() {
        return null;
    }

    @Override
    public Long crea(Particular particular) {
        return null;
    }

    @Override
    public void actualiza(Particular particular, String[] params) {

    }

    @Override
    public void borra(Particular particular) {

    }
}
