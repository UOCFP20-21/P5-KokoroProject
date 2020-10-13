package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.Delegacion;

import java.util.List;
import java.util.Optional;

public class DAOdelegacion<pDelegacion> implements DAO<Delegacion, pDelegacion> {

    @Override
    public Optional<Delegacion> devuelve(pDelegacion pDelegacion) {
        return Optional.empty();
    }

    @Override
    public List<Delegacion> devuelveTodos() {
        return null;
    }

    @Override
    public Long crea(Delegacion delegacion) {
        return null;
    }

    @Override
    public void actualiza(Delegacion delegacion, String[] params) {

    }

    @Override
    public void borra(Delegacion delegacion) {

    }
}
