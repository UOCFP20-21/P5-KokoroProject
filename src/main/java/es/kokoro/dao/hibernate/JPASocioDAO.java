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
        manager.getTransaction().begin();
        Socio nuevosocio = this.get(socio.getIdSocio());

        nuevosocio.setCuota(socio.getCuota());

        nuevosocio.setEstado(socio.isEstado());

        nuevosocio.setPeriodo(socio.getPeriodo());
        System.out.println("UpdateTest 5");
/*
        nuevosocio.setPersona(socio.getPersona());
        System.out.println("UpdateTest 6");
        Persona persona = nuevosocio.getPersona();
        System.out.println("UpdateTest 7");
        persona.setNombre(nuevosocio.getPersona().getNombre());
        persona.setApellidos(nuevosocio.getPersona().getApellidos());
        persona.setIdentificador(nuevosocio.getPersona().getIdentificador());
        persona.setDireccion(nuevosocio.getPersona().getDireccion());
        persona.setPoblacion(nuevosocio.getPersona().getPoblacion());
        persona.setNacionalidad(nuevosocio.getPersona().getNacionalidad());
        persona.setFechaNac(nuevosocio.getPersona().getFechaNac());
        persona.setTelefono(nuevosocio.getPersona().getTelefono());
        persona.setEmail(nuevosocio.getPersona().getEmail());
        System.out.println("UpdateTest 8");*/
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
