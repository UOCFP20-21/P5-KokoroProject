package es.kokoro.commons;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAManager {

    private static EntityManager manager;
    private static EntityManagerFactory managerFactory;

    public static EntityManager getEntityManager(String unitName)
    {
        managerFactory = Persistence.createEntityManagerFactory(unitName);
        manager = managerFactory.createEntityManager();

        return manager;
    }
    public static EntityManager getEntityManager()
    {
        managerFactory = Persistence.createEntityManagerFactory("Kokoro");
        manager = managerFactory.createEntityManager();

        return manager;
    }

    public static void closeManager(EntityManager manager)
    {
        manager.close();
    }
}
