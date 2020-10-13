package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.Contratado;

import java.util.List;
import java.util.Optional;

public class DAOcontratado<pContratado> implements DAO<Contratado, pContratado> {
    @Override
    public Optional<Contratado> devuelve(pContratado pContratado) {
        return Optional.empty();
    }

    @Override
    public List<Contratado> devuelveTodos() {
        return null;
    }

    @Override
    public Long crea(Contratado contratado) {
        return null;
    }

    @Override
    public void actualiza(Contratado contratado, String[] params) {

    }

    @Override
    public void borra(Contratado contratado) {

    }
}
