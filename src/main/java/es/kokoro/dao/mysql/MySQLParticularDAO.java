package es.kokoro.dao.mysql;

import es.kokoro.model.Particular;
import es.kokoro.model.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.SqlConnection.commitData;

public class MySQLParticularDAO extends MySQLPersonaDAO {

    public MySQLParticularDAO() {
        setConexion(conexion);
    }

    public MySQLParticularDAO(Connection conexion) {
        setConexion(conexion);
    }

    public Particular get(long id) {
        String query = "SELECT * FROM particulares WHERE idParticular = ?";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            long idParticular = set.getLong("idParticular");
            long idPersona = set.getLong("idPersona");
            Persona persona = super.get(idPersona);
            return new Particular(idPersona, persona.getNombre(), persona.getApellidos(), persona.getIdentificador(), persona.getNacionalidad(), persona.getDireccion(),
                    persona.getPoblacion(), persona.getTelefono(), persona.getEmail(), idParticular, persona.getFechaNac());
        } catch (Exception throwables) {
            System.out.println("Error obteniendo la instancia " + throwables);
        }
        return null;
    }

    //aunque el método devuelva List<Persona>, en esa lista puede haber cualquier clase que extienda de persona
    public List<Persona> getAll() {

        List<Persona> personaList = new ArrayList<>();
        String query = "SELECT * FROM particulares";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                long idPersona = set.getLong("idPersona");
                Persona persona = super.get(idPersona);
                personaList.add(persona);
            }
        } catch (Exception throwables) {
            System.out.println("Error obteniendo el listado de instancias  " + throwables);
        }
        return personaList;
    }

    public Particular save(Particular particular) {

        String query = "INSERT INTO particulares (idPersona) VALUES(?)";
        PreparedStatement nuevaEntrada;
        Persona persona = null;
        try {
            conexion.setAutoCommit(false);
            if (particular.getIdPersona() == null)   // No facilitamos ID persona
            {
                if (checkDNI(particular.getIdentificador()) == 0)  // No existe el Identificador en nuestra DDBB
                {
                    persona = super.save(particular);
                    System.out.println("El ID de Persona nuevo es: " + persona.getIdPersona());
                } else {
                    System.out.println("El DNI que intentas introducir ya existe.");
                }
            } else // Facilitamos un ID de Persona
            {
                persona = super.update(particular);
            }
            nuevaEntrada = conexion.prepareStatement(query);
            nuevaEntrada.setLong(1, persona.getIdPersona());
            nuevaEntrada.executeUpdate();
            commitData(conexion);
            System.out.println("Ejecutamos Save MySQLParticularDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del nuevo registro " + e);
            }
            System.out.println("Error guardando el nuevo registro  (Save.Particular)" + throwables);
        }
        return particular;
    }

    public Particular update(Particular particular) {

        String query = "UPDATE particulares SET idPersona = ? WHERE idParticular = ?";
        PreparedStatement updateEntrada;
        try {
            conexion.setAutoCommit(false);
            if (particular.getIdPersona() == null || particular.getIdParticular() == null)   // No facilitamos IDs
            {
                System.out.println("No se ha indicado la entrada a modificar");
            } else // Facilitamos los IDs
            {
                Persona persona = super.update(particular);
                updateEntrada = conexion.prepareStatement(query);
                updateEntrada.setLong(1, persona.getIdPersona());
                updateEntrada.setLong(2, particular.getIdParticular());
                updateEntrada.executeUpdate();
            }
            commitData(conexion);
            System.out.println("Ejecutamos Update MySQLParticularDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del update del registro (Update.Particular) " + throwables);
            } finally {
                System.out.println("Error Actualizando el nuevo registro (Update.Particular) " + throwables);
            }
        }
        return particular;
    }

    public void delete(Particular particular) {

        boolean existe = false;
        try {
            if (particular.getIdParticular() != null) { // Estamos pasando un ID
                if (get(particular.getIdParticular()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = " DELETE FROM particulares WHERE idParticular = ?";
                    PreparedStatement borrarEntrada;
                    borrarEntrada = conexion.prepareStatement(query);
                    borrarEntrada.setLong(1, particular.getIdParticular());
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
