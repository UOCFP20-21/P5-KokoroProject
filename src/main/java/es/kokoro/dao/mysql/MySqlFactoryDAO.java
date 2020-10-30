package es.kokoro.dao.mysql;

import es.kokoro.dao.DAO;
import es.kokoro.model.Ong;

import java.sql.*;

public class MySqlFactoryDAO {


    public static DAO getMySqlFactoryDAO(Class classInstance) throws Exception {
        if (classInstance.equals(Ong.class)) {
            return new SqlOngDAO();
        }

        throw new Exception("Clase desconocida");
    }

    public Connection getConnection() {
        try {
            // Setup the connection with the DB
            return DriverManager.getConnection("jdbc:mysql://localhost/mydb?"
                    + "user=splanas&password=sara&serverTimezone=UTC");
        } catch (Exception e) {
            System.out.println("error conectando a base de datos" + e);
        }
        return null;
    }


}
