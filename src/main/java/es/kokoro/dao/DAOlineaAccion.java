package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.LineaAccion;

import java.util.List;
import java.util.Optional;

public class DAOlineaAccion<pLineaAccion> implements DAO<LineaAccion,pLineaAccion> {
    @Override
    public Optional<LineaAccion> devuelve(pLineaAccion pLineaAccion) {
        return Optional.empty();
    }

    @Override
    public List<LineaAccion> devuelveTodos() {
        return null;
    }

    @Override
    public Long crear(LineaAccion lineaAccion) {
        return null;
    }

    @Override
    public void actualiza(LineaAccion lineaAccion) {

    }
}
