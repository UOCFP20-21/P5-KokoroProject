package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.Sociedad;

import java.util.List;
import java.util.Optional;

public class DAOsociedad<pSociedad> implements DAO<Sociedad, pSociedad> {
    @Override
    public Optional<Sociedad> devuelve(pSociedad pSociedad) {
        return Optional.empty();
    }

    @Override
    public List<Sociedad> devuelveTodos() {
        return null;
    }

    @Override
    public Long crear(Sociedad sociedad) {
        return null;
    }

    @Override
    public void actualiza(Sociedad sociedad) {

    }
}
