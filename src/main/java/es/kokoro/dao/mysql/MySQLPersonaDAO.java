package es.kokoro.dao.mysql;

import es.kokoro.commons.SqlConnection;
import es.kokoro.dao.PersonaDAO;
import es.kokoro.model.Persona;

import java.sql.*;
import java.util.List;

import static es.kokoro.commons.SqlConnection.conectar;

public abstract class MySQLPersonaDAO implements PersonaDAO {

    protected Connection conexion = null;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection cnn) {
        if (cnn == null) {
            try {
                this.conexion = conectar();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            this.conexion = cnn;
        }
    }

    @Override
    public Persona get(long id) throws Exception {
        try {
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM personas WHERE idPersona = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Long idPersona = resultSet.getLong("idPersona");
            String nombre = resultSet.getString("nombre");
            String apellidos = resultSet.getString("apellidos");
            String identificador = resultSet.getString("identificador");
            String nacionalidad = resultSet.getString("nacionalidad");
            String direccion = resultSet.getString("direccion");
            String poblacion = resultSet.getString("poblacion");
            String telefono = resultSet.getString("telefono");
            String email = resultSet.getString("email");
            Date fechaNac = resultSet.getDate("fechaNac");
            return new Persona(idPersona, nombre, apellidos, identificador, nacionalidad, direccion,
                    poblacion, telefono, email, fechaNac);
        } catch (Exception exception) {
            System.out.println("Error recuperando la persona " + exception);
        }
        return null;
    }
/*
    @Override
    public List<Persona> getAll() throws Exception {
        return null;
    }
*/
    public Persona save(Persona persona) {
        String queryPersona = "INSERT INTO personas(nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email, fechaNac) VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement nuevaEntrada;
        try {
            nuevaEntrada = conexion.prepareStatement(queryPersona, Statement.RETURN_GENERATED_KEYS);
            nuevaEntrada.setString(1, persona.getNombre());
            nuevaEntrada.setString(2, persona.getApellidos());
            nuevaEntrada.setString(3, persona.getIdentificador());
            nuevaEntrada.setString(4, persona.getNacionalidad());
            nuevaEntrada.setString(5, persona.getDireccion());
            nuevaEntrada.setString(6, persona.getPoblacion());
            nuevaEntrada.setString(7, persona.getTelefono());
            nuevaEntrada.setString(8, persona.getEmail());
            nuevaEntrada.setDate(9, (Date) persona.getFechaNac());
            nuevaEntrada.executeUpdate();
            if (persona.getIdPersona() == null) {
                ResultSet resultSet = nuevaEntrada.getGeneratedKeys();
                resultSet.next();
                persona.setIdPersona(resultSet.getLong(1));
            }
            System.out.println("Ejecutamos Save MySQLPersonaDAO");
        } catch (SQLException throwables) {
            System.out.println("Error guardando el nuevo registro (Save.Persona) " + throwables);
        }
        return persona;
    }

    public Persona update(Persona persona) {
        String queryPersona = "UPDATE personas SET nombre = ?, apellidos = ?, identificador = ?, nacionalidad = ?, direccion = ?, poblacion = ?, telefono = ?, email = ?, fechaNac = ? WHERE idPersona = ?";
        PreparedStatement updateEntrada;
        try {
            if (get(persona.getIdPersona()) != null) {
                long idPersona = persona.getIdPersona();
                updateEntrada = conexion.prepareStatement(queryPersona, Statement.RETURN_GENERATED_KEYS);
                updateEntrada.setString(1, persona.getNombre());
                updateEntrada.setString(2, persona.getApellidos());
                updateEntrada.setString(3, persona.getIdentificador());
                updateEntrada.setString(4, persona.getNacionalidad());
                updateEntrada.setString(5, persona.getDireccion());
                updateEntrada.setString(6, persona.getPoblacion());
                updateEntrada.setString(7, persona.getTelefono());
                updateEntrada.setString(8, persona.getEmail());
                updateEntrada.setDate(9, new Date(persona.getFechaNac().getTime()));
                updateEntrada.setLong(10, idPersona);
                updateEntrada.executeUpdate();
                System.out.println("Ejecutamos Update MySQLPersonaDAO");
            } else {
                return save(persona);
            }
        } catch (Exception throwables) {
            System.out.println("Error guardando el nuevo registro (Update.Persona) " + throwables);
        }

        return persona;
    }

    @Override
    public void delete(Persona persona) throws Exception {
        try {
            boolean isExist = false;
            conexion = SqlConnection.conectar();
            PreparedStatement statement = conexion.prepareStatement("DELETE FROM personas WHERE idPersona = ?");

            if (persona.getIdPersona() != null) {
                if (get(persona.getIdPersona()) != null) {
                    statement.setLong(1, persona.getIdPersona());
                    statement.executeUpdate();
                    isExist = true;
                }
            }
            if (!isExist) {
                System.out.println("Persona no encontrada");
            }
        } catch (Exception exception) {
            System.out.println("Error recuperando la persona " + exception);
        } finally {
            if (conexion != null) {
                SqlConnection.desconectar(conexion);
            }
        }
    }

    protected long checkDNI(String dni) {
        String query = "SELECT * FROM personas WHERE identificador='?'";
        long result = 0;

        try {
            PreparedStatement checkDNI = conexion.prepareStatement(query);
            checkDNI.setString(1, dni);
            ResultSet set = checkDNI.executeQuery();
            set.next();
            result = set.getLong("idPersona");
        } catch (SQLException throwables) {
            System.out.println("No se ha podido comprobar el DNI " + throwables);
        }
        return result;
    }
}