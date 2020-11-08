package es.kokoro.dao.mysql;

import es.kokoro.model.Persona;
import es.kokoro.model.Trabajador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.SqlConnection.commitData;

public abstract class MySQLTrabajadorDAO extends MySQLPersonaDAO {

    protected Connection conexion = null;

    public Connection getConexion() {
        return conexion;
    }

    public MySQLTrabajadorDAO(){

    }
    public MySQLTrabajadorDAO(Connection cnn) {
    }

    @Override
    public Trabajador get(long id) {
        String query = "SELECT * FROM trabajadores WHERE idTrabajador = ?";
        PreparedStatement statement;
        Trabajador trabajador = null;
        try {
            statement = conexion.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            set.next();

            long idTrabajador = set.getLong("idTrabajador");
            long idPersona = set.getLong("idPersona");
            Persona persona = super.get(idPersona);
            long idSede = set.getLong("idSede");
            trabajador = new Trabajador(idPersona, persona.getNombre(), persona.getApellidos(), persona.getIdentificador(), persona.getNacionalidad(), persona.getDireccion(), persona.getPoblacion(),
                    persona.getTelefono(), persona.getEmail(), idTrabajador,
                    null, persona.getFechaNac(), false);

        } catch (Exception throwables) {
            System.out.println("Error obteniendo la instancia " + throwables);
        }

        return trabajador;
    }
/*
    public List<Persona> getAll() {

        List<Persona> personaList = new ArrayList<>();
        String query = "SELECT * FROM trabajadores";
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
*/
    public Trabajador save(Trabajador trabajador) {

        String query = "INSERT INTO trabajadores (idPersona, activo) VALUES(?,?)";
        //String queryPersona = "INSERT INTO personas(nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement nuevaEntrada;
        Persona persona;
        try {
            conexion.setAutoCommit(false);

            if (trabajador.getIdPersona() == null) {
                persona = super.save(trabajador);
                nuevaEntrada = conexion.prepareStatement(query);
                nuevaEntrada.setLong(1, persona.getIdPersona());
            } else {
                nuevaEntrada = conexion.prepareStatement(query);
                nuevaEntrada.setLong(1, trabajador.getIdPersona());
            }

            nuevaEntrada.executeUpdate();
            commitData(conexion);
            System.out.println("Ejecutamos Save MySQLTrabajadorDAO");

        } catch (Exception throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del nuevo registro " + e);
            }
            System.out.println("Error guardando el nuevo registro  (Save.Trabajador)" + throwables);
        }
        return trabajador;
    }

    public Trabajador update(Trabajador trabajador) {

        String query = "UPDATE trabajadores SET idPersona = ?, activo = ? WHERE idTrabajador = ?";
        PreparedStatement updateEntrada;
        Persona persona = null;
        try {
            conexion.setAutoCommit(false);
            if (trabajador.getIdPersona() == null || trabajador.getIdTrabajador() == null)   // No facilitamos IDs
            {
                System.out.println("No se ha indicado la entrada a modificar");
            } else // Facilitamos los IDs
            {
                //TODO revisar que actualizamos
                persona = super.update(trabajador);
                updateEntrada = conexion.prepareStatement(query);
                updateEntrada.setLong(1, trabajador.getIdPersona());
                updateEntrada.setLong(2, trabajador.getIdTrabajador());
                updateEntrada.executeUpdate();
            }
            commitData(conexion);
            System.out.println("Ejecutamos Update MySQLTrabajadorDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del update del registro (Update.Trabajador) " + throwables);
            } finally {
                System.out.println("Error Actualizando el nuevo registro (Update.Trabajador) " + throwables);
            }

        }
        return trabajador;
    }


    public void delete(Trabajador trabajador) {

        boolean existe = false;
        try {
            if (trabajador.getIdTrabajador() != null) { // Estamos pasando un ID

                if (get(trabajador.getIdTrabajador()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = " DELETE FROM trabajadores WHERE idTrabajador = ?";
                    PreparedStatement borrarEntrada;

                    borrarEntrada = conexion.prepareStatement(query);
                    borrarEntrada.setLong(1, trabajador.getIdTrabajador());
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
