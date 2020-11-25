package es.kokoro.dao.mysql;

import es.kokoro.dao.*;
import es.kokoro.model.*;

import java.sql.Connection;

public class MySqlFactoryDAO {
    public static DAO getMySqlFactoryDAO(Class classInstance) throws Exception {
        if (classInstance.equals(Socio.class)) {
            return new MySQLSocioDAO();
             } else if (classInstance.equals(Particular.class)) {
            return new MySQLParticularDAO();
        } else if (classInstance.equals(Accion.class)) {
            return new MySQLAccionDAO();
        } else if (classInstance.equals(LineaAccion.class)) {
            return new MySQLLineaAccionDAO();
        } else if (classInstance.equals(SubLineaAccion.class)) {
            return new MySQLSubLineaAccionDAO();
        }
        throw new Exception("Clase desconocida");
    }

    /***
     * Método para definir el archivo XML de forma personalizada
     * @param classInstance Clase a cargar
     * @param cnn Conexión establecida con la DDBB (útil si queremos conectar con una DDBB de prueba)
     * @return instancia MySQL-DAO de la clase classInstance
     * @throws Exception
     */
    public static DAO getMySqlFactoryDAO(Class classInstance, Connection cnn) throws Exception {
        if (classInstance.equals(Socio.class)) {
            return new MySQLSocioDAO(cnn);
        } else if (classInstance.equals(Particular.class)) {
            return new MySQLParticularDAO(cnn);
        } else if (classInstance.equals(Accion.class)) {
            return new MySQLAccionDAO(cnn);
        } else if (classInstance.equals(LineaAccion.class)) {
            return new MySQLLineaAccionDAO(cnn);
        } else if (classInstance.equals(SubLineaAccion.class)) {
            return new MySQLSubLineaAccionDAO(cnn);
        }
        throw new Exception("Clase desconocida");

    }
}
