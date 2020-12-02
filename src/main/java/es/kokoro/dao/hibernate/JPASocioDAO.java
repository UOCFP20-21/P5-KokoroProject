package es.kokoro.dao.hibernate;

import es.kokoro.dao.SocioDAO;
import es.kokoro.model.Persona;
import es.kokoro.model.Socio;

import javax.persistence.EntityManager;

import java.util.List;

import static es.kokoro.commons.JPAManager.closeManager;
import static es.kokoro.commons.JPAManager.getEntityManager;

public class JPASocioDAO implements SocioDAO {
    EntityManager manager;
    String unitName = null;
    public JPASocioDAO()
    {
        this.manager = getEntityManager();
    }
    public JPASocioDAO(String unitName)
    {
        this.unitName = unitName;
        this.manager = getEntityManager(this.unitName);
    }
    public EntityManager getManager(){ return manager; }

    @Override
    public Socio get(long id) {
        Socio socio = manager.find(Socio.class, id);
        if(socio == null){
            System.out.println("GET is Null");
        }else {
            System.out.println("GET: " + socio.toString());
        }

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
        JPAPersonaDAO jpaPersonaDAO;
        if(this.unitName != null)
        {
            jpaPersonaDAO = new JPAPersonaDAO(unitName);
        }
        else
        {
            jpaPersonaDAO = new JPAPersonaDAO();
        }

        jpaPersonaDAO.update(socio.getPersona());
        //Persona persona = jpaPersonaDAO.get(socio.getPersona().getIdPersona());
        closeManager(jpaPersonaDAO.getManager());
        manager.getTransaction().begin();
        Socio nuevosocio = get(socio.getIdSocio());
        nuevosocio.setCuota(socio.getCuota());
        nuevosocio.setEstado(socio.isEstado());
        nuevosocio.setPeriodo(socio.getPeriodo());
        //nuevosocio.setPersona(persona);
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
