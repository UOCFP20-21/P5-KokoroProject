package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.SocioLocal;

import java.util.List;
import java.util.Optional;

public class DAOsocioLocal<pSocioLocal> implements DAO<SocioLocal, pSocioLocal> {
    @Override
    public Optional<SocioLocal> devuelve(pSocioLocal pSocioLocal) {
        return Optional.empty();
    }

    @Override
    public List<SocioLocal> devuelveTodos() {
        return null;
    }

    @Override
    public Long crea(SocioLocal socioLocal) {
        return null;
    }

    @Override
    public void actualiza(SocioLocal socioLocal, String[] params) {

    }

    @Override
    public void borra(SocioLocal socioLocal) {

    }
}
