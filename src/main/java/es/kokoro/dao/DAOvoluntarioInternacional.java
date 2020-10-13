package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.VoluntarioInternacional;

import java.util.List;
import java.util.Optional;

public class DAOvoluntarioInternacional<pvoluntarioInternacional> implements DAO<VoluntarioInternacional, pvoluntarioInternacional> {
    @Override
    public Optional<VoluntarioInternacional> devuelve(pvoluntarioInternacional pvoluntarioInternacional) {
        return Optional.empty();
    }

    @Override
    public List<VoluntarioInternacional> devuelveTodos() {
        return null;
    }

    @Override
    public Long crea(VoluntarioInternacional voluntarioInternacional) {
        return null;
    }

    @Override
    public void actualiza(VoluntarioInternacional voluntarioInternacional, String[] params) {

    }

    @Override
    public void borra(VoluntarioInternacional voluntarioInternacional) {

    }
}
