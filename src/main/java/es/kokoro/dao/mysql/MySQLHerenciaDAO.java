package es.kokoro.dao.mysql;

import es.kokoro.model.Herencia;
import es.kokoro.model.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.SqlConnection.commitData;

public class MySQLHerenciaDAO extends MySQLPersonaDAO {

    public MySQLHerenciaDAO() {
        setConexion(conexion);
    }

    public MySQLHerenciaDAO(Connection conexion) {
        setConexion(conexion);
    }

    public Herencia get(long id) {
        String query = "SELECT * FROM herencias WHERE idHerencia = ?";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            long idHerencia = set.getLong("idHerencia");
            long idPersona = set.getLong("idPersona");
            Persona persona = super.get(idPersona);
            return new Herencia(idPersona, persona.getNombre(), persona.getApellidos(), persona.getIdentificador(), persona.getNacionalidad(), persona.getDireccion(),
                    persona.getPoblacion(), persona.getTelefono(), persona.getEmail(), idHerencia, persona.getFechaNac());
        } catch (Exception throwables) {
            System.out.println("Error obteniendo la instancia " + throwables);
        }
        return null;
    }

    //aunque el método devuelva List<Persona>, en esa lista puede haber cualquier clase que extienda de persona
    public List<Persona> getAll() {

        List<Persona> personaList = new ArrayList<>();
        String query = "SELECT * FROM herencias";
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

    public Herencia save(Herencia Herencia) {

        String query = "INSERT INTO herencias (idPersona) VALUES(?)";
        PreparedStatement nuevaEntrada;
        Persona persona = null;
        try {
            conexion.setAutoCommit(false);
            if (Herencia.getIdPersona() == null)   // No facilitamos ID persona
            {
                if (checkDNI(Herencia.getIdentificador()) == 0)  // No existe el Identificador en nuestra DDBB
                {
                    persona = super.save(Herencia);
                    System.out.println("El ID de Persona nuevo es: " + persona.getIdPersona());
                } else {
                    System.out.println("El DNI que intentas introducir ya existe.");
                }
            } else // Facilitamos un ID de Persona
            {
                persona = super.update(Herencia);
            }
            nuevaEntrada = conexion.prepareStatement(query);
            nuevaEntrada.setLong(1, persona.getIdPersona());
            nuevaEntrada.executeUpdate();
            commitData(conexion);
            System.out.println("Ejecutamos Save MySQLHerenciaDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del nuevo registro " + e);
            }
            System.out.println("Error guardando el nuevo registro  (Save.Herencia)" + throwables);
        }
        return Herencia;
    }

    public Herencia update(Herencia Herencia) {

        String query = "UPDATE herencias SET idPersona = ? WHERE idHerencia = ?";
        PreparedStatement updateEntrada;
        try {
            conexion.setAutoCommit(false);
            if (Herencia.getIdPersona() == null || Herencia.getIdHerencia() == null)   // No facilitamos IDs
            {
                System.out.println("No se ha indicado la entrada a modificar");
            } else // Facilitamos los IDs
            {
                Persona persona = super.update(Herencia);
                updateEntrada = conexion.prepareStatement(query);
                updateEntrada.setLong(1, persona.getIdPersona());
                updateEntrada.setLong(2, Herencia.getIdHerencia());
                updateEntrada.executeUpdate();
            }
            commitData(conexion);
            System.out.println("Ejecutamos Update MySQLHerenciaDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del update del registro (Update.Herencia) " + throwables);
            } finally {
                System.out.println("Error Actualizando el nuevo registro (Update.Herencia) " + throwables);
            }
        }
        return Herencia;
    }

    public void delete(Herencia Herencia) {

        boolean existe = false;
        try {
            if (Herencia.getIdHerencia() != null) { // Estamos pasando un ID
                if (get(Herencia.getIdHerencia()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = " DELETE FROM herencias WHERE idHerencia = ?";
                    PreparedStatement borrarEntrada;
                    borrarEntrada = conexion.prepareStatement(query);
                    borrarEntrada.setLong(1, Herencia.getIdHerencia());
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
