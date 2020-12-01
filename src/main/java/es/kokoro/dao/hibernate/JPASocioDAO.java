package es.kokoro.dao.hibernate;

import es.kokoro.dao.SocioDAO;
import es.kokoro.model.Persona;
import es.kokoro.model.Socio;

import javax.persistence.EntityManager;

import java.util.List;

import static es.kokoro.commons.JPAManager.getEntityManager;

public class JPASocioDAO implements SocioDAO {
    EntityManager manager;
    public JPASocioDAO()
    {
        manager = getEntityManager();
    }
    public JPASocioDAO(String unitName)
    {
        manager = getEntityManager(unitName);
    }
    public EntityManager getManager(){ return manager; }

    @Override
    public Socio get(long id) {
        Socio socio = manager.find(Socio.class, id);

        return socio;
    }

    @Override
    public List<Socio> getAll() {

        List<Socio> lista = (List<Socio>) manager.createQuery("FROM Socio").getResultList();

        return lista;
    }

    @Override
    public void save(Socio socio) {
        manager.getTransaction().begin();
        Persona persona = socio.getPersona();
        manager.persist(persona);
        manager.persist(socio);
        manager.getTransaction().commit();
    }

    public Long save(Socio socio, boolean widthId) {
        manager.getTransaction().begin();
        Persona persona = socio.getPersona();
        manager.persist(persona);
        manager.persist(socio);
        manager.getTransaction().commit();
        Long id = socio.getIdSocio();
        return id;
    }

    @Override
    public void update(Socio socio) {
        manager.getTransaction().begin();
        Socio nuevosocio = get(socio.getIdSocio());
        Persona persona = socio.getPersona();
        nuevosocio.setCuota(socio.getCuota());
        nuevosocio.setEstado(socio.isEstado());
        nuevosocio.setPeriodo(socio.getPeriodo());
        nuevosocio.setPersona(persona);
        manager.persist(persona);
        manager.persist(nuevosocio);
        manager.getTransaction().commit();
    }

    @Override
    public void delete(Socio socio) {
        manager.getTransaction().begin();
        Socio delSocio = get(socio.getIdSocio());
        manager.remove(delSocio);
        manager.getTransaction().commit();
    }

}
