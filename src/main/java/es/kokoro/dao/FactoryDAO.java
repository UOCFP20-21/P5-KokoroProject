package es.kokoro.dao;

import es.kokoro.dao.mysql.MySqlFactoryDAO;
import es.kokoro.dao.xml.XmlFactoryDAO;

public abstract class FactoryDAO {
    // Tipos de DAO que admitiremos
    public static final int XML = 1;
    public static final int MYSQL = 2;

    public static DAO getDAOFactory (int whichFactory, Class classInstance ) throws Exception {
        switch (whichFactory) {
            case XML:
                return XmlFactoryDAO.getXmlFactoryDao(classInstance);
            case MYSQL:
                return MySqlFactoryDAO.getMySqlFactoryDAO(classInstance);
            default:
                return null;
        }
    }

}


