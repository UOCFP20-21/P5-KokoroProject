package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.Institucion;

import java.util.List;
import java.util.Optional;

public class DAOinstitucion<pInstitucion> implements DAO<Institucion,pInstitucion> {
    @Override
    public Optional<Institucion> devuelve(pInstitucion pInstitucion) {
        return Optional.empty();
    }

    @Override
    public List<Institucion> devuelveTodos() {
        return null;
    }

    @Override
    public Long crea(Institucion institucion) {
        return null;
    }

    @Override
    public void actualiza(Institucion institucion, String[] params) {

    }

    @Override
    public void borra(Institucion institucion) {

    }
}
