package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.Internacional;

import java.util.List;
import java.util.Optional;

public class DAOinternacional<pInternacional> implements DAO<Internacional,pInternacional> {
    @Override
    public Optional<Internacional> devuelve(pInternacional pInternacional) {
        return Optional.empty();
    }

    @Override
    public List<Internacional> devuelveTodos() {
        return null;
    }

    @Override
    public Long crea(Internacional internacional) {
        return null;
    }

    @Override
    public void actualiza(Internacional internacional, String[] params) {

    }

    @Override
    public void borra(Internacional internacional) {

    }
}
