package es.kokoro.dao.mysql;

import es.kokoro.enums.Periodo;
import es.kokoro.model.Socio;
import es.kokoro.model.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.SqlConnection.commitData;

public class MySQLSocioDAO extends MySQLPersonaDAO {

    public MySQLSocioDAO() {
        setConexion(conexion);
    }

    public MySQLSocioDAO(Connection conexion) {
        setConexion(conexion);
    }

    public Socio get(long id) {
        String query = "SELECT * FROM socios WHERE idSocio = ?";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            long idSocio = set.getLong("idSocio");
            long idPersona = set.getLong("idPersona");
            Persona persona = super.get(idPersona);
            Object periodo = set.getObject("periodo");
            float cuota = set.getFloat("cuota");
            boolean estado = set.getBoolean("estado");
            return new Socio(idPersona, persona.getNombre(), persona.getApellidos(), persona.getIdentificador(), persona.getNacionalidad(), persona.getDireccion(),
                    persona.getPoblacion(), persona.getTelefono(), persona.getEmail(), idSocio,( Periodo) periodo, cuota, estado, persona.getFechaNac());
        } catch (Exception throwables) {
            System.out.println("Error obteniendo la instancia " + throwables);
        }
        return null;
    }

    //aunque el método devuelva List<Persona>, en esa lista puede haber cualquier clase que extienda de persona
    public List<Persona> getAll() {

        List<Persona> personaList = new ArrayList<>();
        String query = "SELECT * FROM socios";
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

    public Socio save(Socio Socio) {

        String query = "INSERT INTO socios (idPersona,periodo,cuota,estado,idOng) VALUES(?,?,?,?,?)";
        PreparedStatement nuevaEntrada;
        Persona persona = null;
        try {
            conexion.setAutoCommit(false);
            if (Socio.getIdPersona() == null)   // No facilitamos ID persona
            {
                if (checkDNI(Socio.getIdentificador()) == 0)  // No existe el Identificador en nuestra DDBB
                {
                    persona = super.save(Socio);
                    System.out.println("El ID de Persona nuevo es: " + persona.getIdPersona());
                } else {
                    System.out.println("El DNI que intentas introducir ya existe.");
                }
            } else // Facilitamos un ID de Persona
            {
                persona = super.update(Socio);
            }
            nuevaEntrada = conexion.prepareStatement(query);
            nuevaEntrada.setLong(1, persona.getIdPersona());
            nuevaEntrada.executeUpdate();
            commitData(conexion);
            System.out.println("Ejecutamos Save MySQLSocioDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del nuevo registro " + e);
            }
            System.out.println("Error guardando el nuevo registro  (Save.Socio)" + throwables);
        }
        return Socio;
    }

    public Socio update(Socio Socio) {

        String query = "UPDATE socios SET idPersona = ?, periodo = ?, cuota = ?, estado = ?, idOng = ? WHERE idSocio = ?";
        PreparedStatement updateEntrada;
        try {
            conexion.setAutoCommit(false);
            if (Socio.getIdPersona() == null || Socio.getIdSocio() == null)   // No facilitamos IDs
            {
                System.out.println("No se ha indicado la entrada a modificar");
            } else // Facilitamos los IDs
            {
                Persona persona = super.update(Socio);
                updateEntrada = conexion.prepareStatement(query);
                updateEntrada.setLong(1, persona.getIdPersona());
                updateEntrada.setLong(2, Socio.getIdSocio());
                updateEntrada.executeUpdate();
            }
            commitData(conexion);
            System.out.println("Ejecutamos Update MySQLSocioDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del update del registro (Update.Socio) " + throwables);
            } finally {
                System.out.println("Error Actualizando el nuevo registro (Update.Socio) " + throwables);
            }
        }
        return Socio;
    }

    public void delete(Socio Socio) {

        boolean existe = false;
        try {
            if (Socio.getIdSocio() != null) { // Estamos pasando un ID
                if (get(Socio.getIdSocio()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = " DELETE FROM socios WHERE idSocio = ?";
                    PreparedStatement borrarEntrada;
                    borrarEntrada = conexion.prepareStatement(query);
                    borrarEntrada.setLong(1, Socio.getIdSocio());
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
