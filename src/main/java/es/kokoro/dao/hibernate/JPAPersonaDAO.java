package es.kokoro.dao.hibernate;

import es.kokoro.model.Persona;
import es.kokoro.model.Socio;

import javax.persistence.EntityManager;
import java.util.List;

import static es.kokoro.commons.JPAManager.getEntityManager;

public class JPAPersonaDAO /*implements PersonaDAO*/ {
    public EntityManager manager;
    public JPAPersonaDAO()
    {
        manager = getEntityManager();
    }
    public JPAPersonaDAO(String unitName)
    {
        manager = getEntityManager(unitName);
    }
    public EntityManager getManager(){ return manager; }

    public Persona buscarDNI(String dni) {
        return null;
    }

    public Persona get(long id) {
        Persona persona = manager.find(Persona.class, id);
        return persona;
    }

    public List<Socio> getAll() throws Exception {
        return null;
    }

    public void save(Persona persona) {

    }

    public void update(Persona persona) {
        manager.getTransaction().begin();
        Persona nuevapersona = get(persona.getIdPersona());
        nuevapersona.setNombre(persona.getNombre());
        nuevapersona.setApellidos(persona.getApellidos());
        nuevapersona.setIdentificador(persona.getIdentificador());
        nuevapersona.setDireccion(persona.getDireccion());
        nuevapersona.setPoblacion(persona.getPoblacion());
        nuevapersona.setNacionalidad(persona.getNacionalidad());
        nuevapersona.setFechaNac(persona.getFechaNac());
        nuevapersona.setTelefono(persona.getTelefono());
        nuevapersona.setEmail(persona.getEmail());
        manager.getTransaction().commit();
    }

    public void delete(Persona persona) throws Exception {

    }
}
