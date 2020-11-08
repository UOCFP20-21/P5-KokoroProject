package es.kokoro.dao.mysql;

import es.kokoro.dao.*;
import es.kokoro.model.*;
import es.kokoro.dao.mysql.*;

import java.sql.Connection;

public class MySqlFactoryDAO {
    public static DAO getMySqlFactoryDAO(Class classInstance) throws Exception {

        if (classInstance.equals(Accion.class)) {
            return new MySQLAccionDAO();
        } else if (classInstance.equals(Contratado.class)) {
            return new MySQLContratadoDAO();
        } else if (classInstance.equals(Delegacion.class)) {
            return new MySQLDelegacionDAO();
        } else if (classInstance.equals(Empresa.class)) {
          //  return new MySQLEmpresaDAO();
        } else if (classInstance.equals(EntePublico.class)) {
          //  return new MySQLEntePublicoDAO();
        } else if (classInstance.equals(Estatal.class)) {
            return new MySQLEstatalDAO();
        } else if (classInstance.equals(Extraordinario.class)) {
            return new MySQLExtraordinarioDAO();
        } else if (classInstance.equals(Financiador.class)) {
            return new MySQLFinanciadorDAO();
        } else if (classInstance.equals(Herencia.class)) {
            return new MySQLHerenciaDAO();
        } else if (classInstance.equals(Ingreso.class)) {
            return new MySQLIngresoDAO();
        } else if (classInstance.equals(Institucion.class)) {
            return new MySQLInstitucionDAO();
        } else if (classInstance.equals(Internacional.class)) {
            return new MySQLInternacionalDAO();
        } else if (classInstance.equals(LineaAccion.class)) {
            return new MySQLLineaAccionDAO();
        } else if (classInstance.equals(Ong.class)) {
            return new MySQLOngDAO();
        } else if (classInstance.equals(Particular.class)) {
            return new MySQLParticularDAO();
        } else if (classInstance.equals(Persona.class)) {
            //return new MySQLPersonaDAO();
            // MySqlPersonaDao es abstracta porque no debe operar por si sola, sino a través de una implementacion
        } else if (classInstance.equals(Proyecto.class)) {
            return new MySQLProyectoDAO();
        } else if (classInstance.equals(Sede.class)) {
            return new MySQLSedeDAO();
        } else if (classInstance.equals(Sociedad.class)) {
            return new MySQLSociedadDAO();
        } else if (classInstance.equals(Socio.class)) {
            return new MySQLSocioDAO();
        }else if (classInstance.equals(SocioLocal.class)) {
            return new MySQLSocioLocalDAO();
        } else if (classInstance.equals(SubLineaAccion.class)) {
            return new MySQLSubLineaAccionDAO();
        } else if (classInstance.equals(Trabajador.class)) {
            //return new MySQLTrabajadorDAO();
            // MySqlPersonaDao es abstracta porque no debe operar por si sola, sino a través de una implementacion
        } else if (classInstance.equals(VoluntarioColaborador.class)) {
            return new MySQLVoluntarioColaboradorDAO();
        } else if (classInstance.equals(VoluntarioInternacional.class)) {
            return new MySQLVoluntarioInternacionalDAO();

        }
        throw new

                Exception("Clase desconocida");

    }

    /***
     * Método para definir el archivo XML de forma personalizada
     * @param classInstance Clase a cargar
     * @param cnn Conexión establecida con la DDBB (útil si queremos conectar con una DDBB de prueba)
     * @return instancia MySQL-DAO de la clase classInstance
     * @throws Exception
     */
    public static DAO getMySqlFactoryDAO(Class classInstance, Connection cnn) throws Exception {
        if (classInstance.equals(Accion.class)) {
            return new MySQLAccionDAO(cnn);
        } else if (classInstance.equals(Contratado.class)) {
            return new MySQLContratadoDAO(cnn);
        } else if (classInstance.equals(Delegacion.class)) {
            return new MySQLDelegacionDAO(cnn);
        } else if (classInstance.equals(Empresa.class)) {
            //return new MySQLEmpresaDAO(cnn);
        } else if (classInstance.equals(EntePublico.class)) {
           // return new MySQLEntePublicoDAO(cnn);
        } else if (classInstance.equals(Estatal.class)) {
            return new MySQLEstatalDAO();
        } else if (classInstance.equals(Extraordinario.class)) {
            return new MySQLExtraordinarioDAO();
        } else if (classInstance.equals(Financiador.class)) {
            return new MySQLFinanciadorDAO();
        } else if (classInstance.equals(Herencia.class)) {
            return new MySQLHerenciaDAO();
        } else if (classInstance.equals(Ingreso.class)) {
            return new MySQLIngresoDAO();
        } else if (classInstance.equals(Institucion.class)) {
            return new MySQLInstitucionDAO();
        } else if (classInstance.equals(Internacional.class)) {
            return new MySQLInternacionalDAO();
        } else if (classInstance.equals(LineaAccion.class)) {
            return new MySQLLineaAccionDAO(cnn);
        } else if (classInstance.equals(Ong.class)) {
            return new MySQLOngDAO();
        } else if (classInstance.equals(Particular.class)) {
            return new MySQLParticularDAO(cnn);
        } else if (classInstance.equals(Persona.class)) {
      //      return new MySQLPersonaDAO(cnn);
        } else if (classInstance.equals(Proyecto.class)) {
            return new MySQLProyectoDAO();
        } else if (classInstance.equals(Sede.class)) {
            return new MySQLSedeDAO();
        } else if (classInstance.equals(Sociedad.class)) {
            return new MySQLSociedadDAO(cnn);
        } else if (classInstance.equals(Socio.class)) {
            return new MySQLSocioDAO();
        } else if (classInstance.equals(SocioLocal.class)) {
            return new MySQLSocioLocalDAO();
        } else if (classInstance.equals(SubLineaAccion.class)) {
            return new MySQLSubLineaAccionDAO(cnn);
        } else if (classInstance.equals(Trabajador.class)) {
            //return new MySQLTrabajadorDAO(cnn);
            // MySqlPersonaDao es abstracta porque no debe operar por si sola, sino a través de una implementacion
        } else if (classInstance.equals(VoluntarioColaborador.class)) {
            return new MySQLVoluntarioColaboradorDAO(cnn);
        } else if (classInstance.equals(VoluntarioInternacional.class)) {
            return new MySQLVoluntarioInternacionalDAO(cnn);
        }

        throw new Exception("Clase desconocida");

    }
}
