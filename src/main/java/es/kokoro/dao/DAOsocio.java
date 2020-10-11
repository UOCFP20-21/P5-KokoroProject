package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.Socio;

import java.util.List;
import java.util.Optional;

public class DAOsocio<pSocio> implements DAO<Socio, pSocio> {
    @Override
    public Optional<Socio> devuelve(pSocio pSocio) {
        return Optional.empty();
    }

    @Override
    public List<Socio> devuelveTodos() {
        return null;
    }

    @Override
    public Long crear(Socio socio) {
        return null;
    }

    @Override
    public void actualiza(Socio socio) {

    }
}
