package es.kokoro.dao.mysql;

import es.kokoro.dao.SocioDAO;
import es.kokoro.model.Socio;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static es.kokoro.util.HibernateUtil.getSessionFactory;

public class MySQLSocioDAO implements SocioDAO {

    private Session currentSession;
    private Transaction currentTransaction;

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    @Override
    public Socio get(long id) {

        openCurrentSession();
        // Transformo Long a int, por no cambiar todas las clases, se deberia hacer que DAO tire de Integer y no con longs, para eso debemos modificar todos los daos
        Socio socio = getCurrentSession().get(Socio.class, new Long(id).intValue());
        closeCurrentSession();
        return socio;

    }

    @Override
    public List<Socio> getAll() {
        openCurrentSession();
        List<Socio> socios = getCurrentSession().createQuery("from Socio").list();
        closeCurrentSession();

        return socios;
    }

    @Override
    public void save(Socio socio) {

        openCurrentSessionwithTransaction();
        getCurrentSession().save(socio);
        closeCurrentSessionwithTransaction();
    }

    @Override
    public void update(Socio socio) {
        openCurrentSessionwithTransaction();
        getCurrentSession().update(socio);
        closeCurrentSessionwithTransaction();
    }

    @Override
    public void delete(Socio socio) {
        openCurrentSessionwithTransaction();
        getCurrentSession().delete(socio);
        closeCurrentSessionwithTransaction();
    }

    public Session getCurrentSession() {
        return currentSession;
    }
}
