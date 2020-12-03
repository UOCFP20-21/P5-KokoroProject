package es.kokoro.dao.hibernate;

import es.kokoro.dao.LineaAccionDAO;
import es.kokoro.model.LineaAccion;

import javax.persistence.EntityManager;
import java.util.List;

import static es.kokoro.commons.JPAManager.getEntityManager;

public class JPALineaAccionDAO implements LineaAccionDAO {
    EntityManager manager;
    public JPALineaAccionDAO()
    {
        manager = getEntityManager();
    }
    public JPALineaAccionDAO(String unitName)
    {
        manager = getEntityManager(unitName);
    }
    public EntityManager getManager(){ return manager; }
    @Override
    public LineaAccion get(long id) {
        LineaAccion la = manager.find(LineaAccion.class, id);

        return la;
    }

    @Override
    public List<LineaAccion> getAll() {

        List<LineaAccion> lista = (List<LineaAccion>) manager.createQuery("FROM LineaAccion").getResultList();

        return lista;
    }

    @Override
    public void save(LineaAccion lineaAccion) {
        manager.getTransaction().begin();
        manager.persist(lineaAccion);
        manager.getTransaction().commit();
    }

    public Long save(LineaAccion lineaAccion, boolean widthId) {
        manager.getTransaction().begin();
        manager.persist(lineaAccion);
        manager.getTransaction().commit();
        Long id = lineaAccion.getIdLineaAccion();
        return id;
    }

    @Override
    public void update(LineaAccion lineaAccion) {
        manager.getTransaction().begin();
        LineaAccion la = get(lineaAccion.getIdLineaAccion());
        la.setLinea(lineaAccion.getLinea());
        manager.getTransaction().commit();
    }

    @Override
    public void delete(LineaAccion lineaAccion) {
        manager.getTransaction().begin();
        LineaAccion la = get(lineaAccion.getIdLineaAccion());
        manager.remove(la);
        manager.getTransaction().commit();
    }
}
