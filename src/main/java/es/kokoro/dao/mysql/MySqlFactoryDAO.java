package es.kokoro.dao.mysql;

import es.kokoro.dao.DAO;
import es.kokoro.model.Ong;


public class MySqlFactoryDAO {


    public static DAO getMySqlFactoryDAO(Class classInstance) throws Exception {
        if (classInstance.equals(Ong.class)) {
            return new SqlOngDAO();
        }

        throw new Exception("Clase desconocida");
    }



}
