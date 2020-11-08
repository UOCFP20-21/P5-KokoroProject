package es.kokoro.dao.mysql;

import es.kokoro.dao.InternacionalDAO;
import es.kokoro.model.Internacional;
import es.kokoro.model.EntePublico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.SqlConnection.commitData;

public class MySQLInternacionalDAO extends MySQLEntePublicoDAO {

    public MySQLInternacionalDAO() {
        setConexion(conexion);
    }

    public MySQLInternacionalDAO(Connection conexion) {
        setConexion(conexion);
    }

    public Internacional get(long id) {
        String query = "SELECT * FROM internacionales WHERE idInternacional = ?";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            long idInternacional = set.getLong("idInternacional");
            long idEntePublico = set.getLong("idEntePublico");
            String pais = set.getString("pais");
            EntePublico entePublico = super.get(idEntePublico);
            return new Internacional(idEntePublico, entePublico.getNombre(), entePublico.getSubvencion(), idInternacional, pais);
        } catch (Exception throwables) {
            System.out.println("Error obteniendo la instancia " + throwables);
        }
        return null;
    }

    //aunque el método devuelva List<EntePublico>, en esa lista puede haber cualquier clase que extienda de ente publico
    public List<EntePublico> getAll() {
        List<EntePublico> entePublicoList = new ArrayList<>();
        String query = "SELECT * FROM internacionales";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next()) {

                long idEntePublico = set.getLong("idEntePublico");
                EntePublico entePublico = super.get(idEntePublico);
                entePublicoList.add(entePublico);
            }
        } catch (Exception throwables) {
            System.out.println("Error obteniendo el listado de instancias  " + throwables);
        }

        return entePublicoList;
    }

    public Internacional save(Internacional internacional) {

        String query = "INSERT INTO internacionales (idInternacional) VALUES(?)";
        PreparedStatement nuevaEntrada;
        EntePublico entePublico = null;
        try {
            conexion.setAutoCommit(false);
            if (internacional.getIdEntePublico() == null)   // No facilitamos ID persona
            {
                entePublico = super.update(internacional);
            }
            nuevaEntrada = conexion.prepareStatement(query);
            nuevaEntrada.setLong(1, entePublico.getIdEntePublico());
            nuevaEntrada.executeUpdate();
            commitData(conexion);
            System.out.println("Ejecutamos Save MySQLInternacionalDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del nuevo registro " + e);
            }
            System.out.println("Error guardando el nuevo registro  (Save.Internacional)" + throwables);
        }
        return internacional;
    }

    public Internacional update(Internacional internacional) {

        String query = "UPDATE internacionales SET idEntePublico = ? WHERE idInternacional = ?";
        PreparedStatement updateEntrada;
        try {
            conexion.setAutoCommit(false);
            if (internacional.getIdEntePublico() == null || internacional.getIdInternacional() == null)   // No facilitamos IDs
            {
                System.out.println("No se ha indicado la entrada a modificar");
            } else // Facilitamos los IDs
            {
                EntePublico entePublico = super.update(internacional);
                updateEntrada = conexion.prepareStatement(query);
                updateEntrada.setLong(1, entePublico.getIdEntePublico());
                updateEntrada.setLong(2, internacional.getIdInternacional());
                updateEntrada.executeUpdate();
            }
            commitData(conexion);
            System.out.println("Ejecutamos Update MySQLInternacionalDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del update del registro (Update.Internacional) " + throwables);
            } finally {
                System.out.println("Error Actualizando el nuevo registro (Update.Internacional) " + throwables);
            }
        }
        return internacional;
    }

    public void delete(Internacional internacional) {

        boolean existe = false;
        try {
            if (internacional.getIdInternacional() != null) { // Estamos pasando un ID
                if (get(internacional.getIdInternacional()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = " DELETE FROM internacionales WHERE idInternacional = ?";
                    PreparedStatement borrarEntrada;
                    borrarEntrada = conexion.prepareStatement(query);
                    borrarEntrada.setLong(1, internacional.getIdInternacional());
                    borrarEntrada.executeUpdate();
                    existe = true;
                }
            }
        } catch (SQLException throwables) {
            System.out.println("Error Eliminando el registro " + throwables);
        } finally {
            if (!existe)   // Si no existe en nuestra DDBB o no facilitan un ID guardamos en lugar de actualizar
            {
                System.out.println("Registro no encontrado: No existe ningún registro con los datos facilitados.");
            }
        }
    }
}



