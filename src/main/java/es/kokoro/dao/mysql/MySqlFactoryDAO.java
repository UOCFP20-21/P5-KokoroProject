package es.kokoro.dao.mysql;

import es.kokoro.dao.*;
import es.kokoro.dao.xml.*;
import es.kokoro.model.*;

import java.sql.Connection;

public class MySqlFactoryDAO {
    public static DAO getMySqlFactoryDAO(Class classInstance) throws Exception {
        /*if (classInstance.equals(Socio.class)) {
            return new XmlSocioDAO();
        } else if (classInstance.equals(Contratado.class)) {
            return new XmlContratadoDAO();
        } else if (classInstance.equals(VoluntarioColaborador.class)) {
            return new XmlVoluntarioColaboradorDAO();
        } else if (classInstance.equals(VoluntarioInternacional.class)) {
            return new XmlVoluntarioInternacionalDAO();
        } else if (classInstance.equals(Sede.class)) {
            return new XmlSedeDAO();
        } else if (classInstance.equals(Trabajador.class)) {
            return new XmlTrabajadorDAO();
        } else if (classInstance.equals(Persona.class)) {
            return new XmlPersonaDAO();
        } else if (classInstance.equals(Delegacion.class)) {
            return new XmlDelegacionDAO();
        } else if (classInstance.equals(Empresa.class)) {
            return new XmlEmpresaDAO();
        } else if (classInstance.equals(Particular.class)) {
            return new XmlParticularDAO();
        } else if (classInstance.equals(Herencia.class)) {
            return new XmlHerenciaDAO();
        } else if (classInstance.equals(Sociedad.class)) {
            return new XmlSociedadDAO();
        } else if (classInstance.equals(Institucion.class)) {
            return new XmlInstitucionDAO();
        } else if (classInstance.equals(Extraordinario.class)) {
            return new XmlExtraordinarioDAO();
        } else if (classInstance.equals(Ong.class)) {
            return new XmlOngDAO();
        } else if (classInstance.equals(SocioLocal.class)) {
            return new XmlSocioLocalDAO();
        } else if (classInstance.equals(Financiador.class)) {
            return new XmlFinanciadorDAO();
        } else if (classInstance.equals(Proyecto.class)) {
            return new XmlProyectoDAO();
        } else if (classInstance.equals(Ingreso.class)) {
            return new XmlIngresoDAO();
        } else if (classInstance.equals(Estatal.class)) {
            return new XmlEstatalDAO();
        } else if (classInstance.equals(Internacional.class)) {
            return new XmlInternacionalDAO();
        } else if (classInstance.equals(EntePublico.class)) {
            return new XmlEntePublicoDAO();
        } else if (classInstance.equals(Accion.class)) {
            return new XmlAccionDAO();
        } else*/ if (classInstance.equals(LineaAccion.class)) {
            return new MySQLLineaAccionDAO();
        }/* else if (classInstance.equals(SubLineaAccion.class)) {
            return new XmlSubLineaAccionDAO();
        }*/
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
        if (classInstance.equals(LineaAccion.class)) {
            return new MySQLLineaAccionDAO(cnn);
        }
        throw new Exception("Clase desconocida");

    }
}
