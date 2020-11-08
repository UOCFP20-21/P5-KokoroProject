package es.kokoro.dao.xml;

import es.kokoro.dao.PersonaDAO;
import es.kokoro.enums.Periodo;
import es.kokoro.model.Persona;

import java.util.List;

public class XmlPersonaDAO implements PersonaDAO {
    @Override
    public Persona get(long id) {
        return null;
    }

    @Override
    public List<Persona> getAll() {
        return null;
    }

    @Override
    public Persona save(Persona persona) {
        return persona;

    }

    @Override
    public Persona update(Persona persona) {
        return persona;

    }

    @Override
    public void delete(Persona persona) {

    }

    public Persona buscarDNI(String dni) {
        return null;
    }

}
