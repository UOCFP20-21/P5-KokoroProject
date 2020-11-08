package es.kokoro.dao.mysql;

import es.kokoro.dao.EstatalDAO;
import es.kokoro.model.Estatal;
import es.kokoro.model.EntePublico;
import es.kokoro.enums.Organismo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.SqlConnection.commitData;

public class MySQLEstatalDAO extends MySQLEntePublicoDAO {

    public MySQLEstatalDAO() {
        setConexion(conexion);
    }

    public MySQLEstatalDAO(Connection conexion) {
        setConexion(conexion);
    }

    public Estatal get(long id) {
        String query = "SELECT * FROM estatales WHERE idEstatal = ?";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            long idEstatal = set.getLong("idEstatal");
            long idEntePublico = set.getLong("idEntePublico");
            String nombreOrganismo = set.getString("nombreOrganismo");
            Organismo organismo =Organismo.valueOf(set.getString("organismo"));
            EntePublico entePublico = super.get(idEntePublico);
            return new Estatal(idEntePublico, entePublico.getNombre(), entePublico.getSubvencion(), idEstatal, organismo, nombreOrganismo);
        } catch (Exception throwables) {
            System.out.println("Error obteniendo la instancia " + throwables);
        }
        return null;
    }

    //aunque el método devuelva List<EntePublico>, en esa lista puede haber cualquier clase que extienda de ente publico
    public List<EntePublico> getAll() {

        List<EntePublico> entePublicoList = new ArrayList<>();
        String query = "SELECT * FROM estatales";
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


    public Estatal save(Estatal estatal) {

        String query = "INSERT INTO estatales (idEstatal) VALUES(?)";
        PreparedStatement nuevaEntrada;
        EntePublico entePublico = null;
        try {
            conexion.setAutoCommit(false);
            if (estatal.getIdEntePublico() == null)   // No facilitamos ID persona
            {
                entePublico = super.update(estatal);
            }
            nuevaEntrada = conexion.prepareStatement(query);
            nuevaEntrada.setLong(1, entePublico.getIdEntePublico());
            nuevaEntrada.executeUpdate();
            commitData(conexion);
            System.out.println("Ejecutamos Save MySQLEstatalDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del nuevo registro " + e);
            }
            System.out.println("Error guardando el nuevo registro  (Save.Estatal)" + throwables);
        }
        return estatal;
    }

    public Estatal update(Estatal estatal) {

        String query = "UPDATE estatales SET idEntePublico = ? WHERE idEstatal = ?";
        PreparedStatement updateEntrada;
        try {
            conexion.setAutoCommit(false);
            if (estatal.getIdEntePublico() == null || estatal.getIdEstatal() == null)   // No facilitamos IDs
            {
                System.out.println("No se ha indicado la entrada a modificar");
            } else // Facilitamos los IDs
            {
                EntePublico entePublico = super.update(estatal);
                updateEntrada = conexion.prepareStatement(query);
                updateEntrada.setLong(1, entePublico.getIdEntePublico());
                updateEntrada.setLong(2, estatal.getIdEstatal());
                updateEntrada.executeUpdate();
            }
            commitData(conexion);
            System.out.println("Ejecutamos Update MySQLEstatalDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del update del registro (Update.Estatal) " + throwables);
            } finally {
                System.out.println("Error Actualizando el nuevo registro (Update.Estatal) " + throwables);
            }
        }
        return estatal;
    }


    public void delete(Estatal estatal) {

        boolean existe = false;
        try {
            if (estatal.getIdEstatal() != null) { // Estamos pasando un ID
                if (get(estatal.getIdEstatal()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = " DELETE FROM estatales WHERE idEstatal = ?";
                    PreparedStatement borrarEntrada;

                    borrarEntrada = conexion.prepareStatement(query);
                    borrarEntrada.setLong(1, estatal.getIdEstatal());
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


