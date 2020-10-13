package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.SubLineaAccion;

import java.util.List;
import java.util.Optional;

public class DAOsubLineaAccion<pSubLineaAccion> implements DAO<SubLineaAccion, pSubLineaAccion> {
    @Override
    public Optional<SubLineaAccion> devuelve(pSubLineaAccion pSubLineaAccion) {
        return Optional.empty();
    }

    @Override
    public List<SubLineaAccion> devuelveTodos() {
        return null;
    }

    @Override
    public Long crea(SubLineaAccion subLineaAccion) {
        return null;
    }

    @Override
    public void actualiza(SubLineaAccion subLineaAccion, String[] params) {

    }

    @Override
    public void borra(SubLineaAccion subLineaAccion) {

    }
}
