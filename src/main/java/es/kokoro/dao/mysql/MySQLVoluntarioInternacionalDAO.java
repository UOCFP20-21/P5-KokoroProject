package es.kokoro.dao.mysql;

import es.kokoro.model.Persona;
import es.kokoro.model.Trabajador;
import es.kokoro.model.VoluntarioInternacional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static es.kokoro.commons.SqlConnection.commitData;

public class MySQLVoluntarioInternacionalDAO extends MySQLTrabajadorDAO {

    public MySQLVoluntarioInternacionalDAO() {
        setConexion(conexion);
    }

    public MySQLVoluntarioInternacionalDAO(Connection conexion) {
        setConexion(conexion);
    }

    public VoluntarioInternacional get(long id) {
        String query = "SELECT * FROM voluntarioInternacional WHERE idVoluntarioInternacional = ?";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            long idVoluntarioInternacional = set.getLong("idVoluntarioInternacional");
            long idTrabajador = set.getLong("idTrabajador");
            Date inicioVoluntariado = set.getDate("inicioVoluntariado");
            Trabajador trabajador = super.get(idTrabajador);
            return new VoluntarioInternacional(trabajador.getIdPersona(), trabajador.getNombre(), trabajador.getApellidos(), trabajador.getIdentificador(), trabajador.getNacionalidad(), trabajador.getDireccion(),
                    trabajador.getPoblacion(), trabajador.getTelefono(), trabajador.getEmail(), idTrabajador,null, trabajador.getFechaNac(),false, idVoluntarioInternacional, inicioVoluntariado);
        } catch (Exception throwables) {
            System.out.println("Error obteniendo la instancia " + throwables);
        }
        return null;
    }

    //aunque el método devuelva List<Trabajador>, en esa lista puede haber cualquier clase que extienda de persona
    public List<Persona> getAll() {

        List<Persona> voluntarioLIst = new ArrayList<>();
        String query = "SELECT * FROM voluntarioInternacional";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                long idTrabajador = set.getLong("idTrabajador");
                Trabajador trabajador = super.get(idTrabajador);
                voluntarioLIst.add(trabajador);
            }
        } catch (Exception throwables) {
            System.out.println("Error obteniendo el listado de instancias  " + throwables);
        }
        return voluntarioLIst;
    }

    public VoluntarioInternacional save(VoluntarioInternacional voluntarioInternacional) {

        String query = "INSERT INTO voluntarioInternacional (idTrabajador,inicioVoluntariado) VALUES(?,?)";
        PreparedStatement nuevaEntrada;
        Trabajador trabajador = null;
        try {
            conexion.setAutoCommit(false);
            if (voluntarioInternacional.getIdTrabajador() == null)   // No facilitamos ID persona
            {
                if (checkDNI(voluntarioInternacional.getIdentificador()) == 0)  // No existe el Identificador en nuestra DDBB
                {
                    trabajador = super.save(voluntarioInternacional);
                    System.out.println("El ID de Trabajador nuevo es: " + trabajador.getIdTrabajador());
                } else {
                    System.out.println("El DNI que intentas introducir ya existe.");
                }
            } else // Facilitamos un ID de Persona
            {
                trabajador = super.update(voluntarioInternacional);
            }
            nuevaEntrada = conexion.prepareStatement(query);
            nuevaEntrada.setLong(1, trabajador.getIdTrabajador());
            nuevaEntrada.executeUpdate();
            commitData(conexion);
            System.out.println("Ejecutamos Save MySQLVoluntarioInternacionalDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del nuevo registro " + e);
            }
            System.out.println("Error guardando el nuevo registro  (Save.VoluntarioInternacional)" + throwables);
        }
        return voluntarioInternacional;
    }

    public VoluntarioInternacional update(VoluntarioInternacional voluntarioInternacional) {

        String query = "UPDATE voluntarioInternacional SET idTrabajador = ?, inicioVoluntariado = ? WHERE idVoluntarioInternacional = ?";
        PreparedStatement updateEntrada;
        try {
            conexion.setAutoCommit(false);
            if (voluntarioInternacional.getIdTrabajador() == null || voluntarioInternacional.getIdVoluntarioInternacional() == null)   // No facilitamos IDs
            {
                System.out.println("No se ha indicado la entrada a modificar");
            } else // Facilitamos los IDs
            {
                Trabajador trabajador = super.update(voluntarioInternacional);
                updateEntrada = conexion.prepareStatement(query);
                updateEntrada.setLong(1, trabajador.getIdTrabajador());
                updateEntrada.setLong(2, voluntarioInternacional.getIdVoluntarioInternacional());
                updateEntrada.executeUpdate();
            }
            commitData(conexion);
            System.out.println("Ejecutamos Update MySQLVoluntarioInternacionalDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del update del registro (Update.VoluntarioInternacional) " + throwables);
            } finally {
                System.out.println("Error Actualizando el nuevo registro (Update.VoluntarioInternacional) " + throwables);
            }
        }

        return voluntarioInternacional;
    }

    public void delete(VoluntarioInternacional voluntarioInternacional) {

        boolean existe = false;
        try {
            if (voluntarioInternacional.getIdVoluntarioInternacional() != null) { // Estamos pasando un ID
                if (get(voluntarioInternacional.getIdVoluntarioInternacional()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = " DELETE FROM voluntarioInternacional WHERE idVoluntarioInternacional = ?";
                    PreparedStatement borrarEntrada;
                    borrarEntrada = conexion.prepareStatement(query);
                    borrarEntrada.setLong(1, voluntarioInternacional.getIdVoluntarioInternacional());
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
