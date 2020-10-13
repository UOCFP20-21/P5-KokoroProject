package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.Sede;

import java.util.List;
import java.util.Optional;

public class DAOsede<pSede> implements DAO<Sede,pSede> {
    @Override
    public Optional<Sede> devuelve(pSede pSede) {
        return Optional.empty();
    }

    @Override
    public List<Sede> devuelveTodos() {
        return null;
    }

    @Override
    public Long crea(Sede sede) {
        return null;
    }

    @Override
    public void actualiza(Sede sede, String[] params) {

    }

    @Override
    public void borra(Sede sede) {

    }
}
