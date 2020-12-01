package es.kokoro.dao.hibernate;

import es.kokoro.dao.PersonaDAO;
import es.kokoro.model.Persona;

import java.util.List;

public class JPAPersonaDAO implements PersonaDAO {

    @Override
    public Persona buscarDNI(String dni) {
        return null;
    }

    @Override
    public Persona get(long id) throws Exception {
        return null;
    }

    @Override
    public List<Persona> getAll() throws Exception {
        return null;
    }

    @Override
    public void save(Persona persona) throws Exception {

    }

    @Override
    public void update(Persona persona) throws Exception {

    }

    @Override
    public void delete(Persona persona) throws Exception {

    }
}
