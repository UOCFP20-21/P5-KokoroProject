package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.Persona;

import java.util.List;
import java.util.Optional;

public class DAOpersona<pPersona> implements DAO<Persona,pPersona> {
    @Override
    public Optional<Persona> devuelve(pPersona pPersona) {
        return Optional.empty();
    }

    @Override
    public List<Persona> devuelveTodos() {
        return null;
    }

    @Override
    public Long crear(Persona persona) {
        return null;
    }

    @Override
    public void actualiza(Persona persona) {

    }
}
