package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.VoluntarioColaborador;

import java.util.List;
import java.util.Optional;

public class DAOvoluntarioColaborador<pVoluntariocolaborador> implements DAO<VoluntarioColaborador, pVoluntariocolaborador> {
    @Override
    public Optional<VoluntarioColaborador> devuelve(pVoluntariocolaborador pVoluntariocolaborador) {
        return Optional.empty();
    }

    @Override
    public List<VoluntarioColaborador> devuelveTodos() {
        return null;
    }

    @Override
    public Long crear(VoluntarioColaborador voluntarioColaborador) {
        return null;
    }

    @Override
    public void actualiza(VoluntarioColaborador voluntarioColaborador) {

    }
}
