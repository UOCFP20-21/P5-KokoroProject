package es.kokoro.dao.mysql;

import es.kokoro.model.Persona;

import java.sql.*;

import static es.kokoro.commons.sqlConection.conectar;
import static es.kokoro.commons.sqlConection.convertirFecha;

public class MySQLPersonaDAO /*implements PersonaDAO */{

    protected Connection conexion = null;

    public MySQLPersonaDAO() {
        setConexion(conexion);
    }
    public MySQLPersonaDAO(Connection conexion) {
        setConexion(conexion);
    }

    public Connection getConexion() { return conexion; }

    public void setConexion(Connection cnn)
    {
        if(cnn == null){
            try {
                this.conexion = conectar();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else
        {
            this.conexion = cnn;
        }
    }

    public ResultSet getResult(long id) {

        String query = "SELECT * FROM personas WHERE idPersona = ?";
        PreparedStatement statement;
        ResultSet tmpSet = null;

        try {
            statement = conexion.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            tmpSet = set;
            System.out.println("Persona Set obtenido");
        } catch (SQLException throwables) {
            System.out.println("Error obteniendo el Result Set " + throwables);
        } finally {
            return tmpSet;
        }
    }

    protected long save(Persona persona)
    {
        long nuevoIdPersona = 0;
        String queryPersona = "INSERT INTO personas(nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email, fechaNac) VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement nuevaEntrada;

        try {
            nuevaEntrada = conexion.prepareStatement(queryPersona,Statement.RETURN_GENERATED_KEYS);
            nuevaEntrada.setString(1,persona.getNombre());
            nuevaEntrada.setString(2,persona.getApellidos());
            nuevaEntrada.setString(3,persona.getIdentificador());
            nuevaEntrada.setString(4,persona.getNacionalidad());
            nuevaEntrada.setString(5,persona.getDireccion());
            nuevaEntrada.setString(6,persona.getPoblacion());
            nuevaEntrada.setString(7,persona.getTelefono());
            nuevaEntrada.setString(8,persona.getEmail());
            nuevaEntrada.setDate(9,convertirFecha(persona.getFechaNac()));
            nuevaEntrada.executeUpdate();
            ResultSet resultSet = nuevaEntrada.getGeneratedKeys();
            resultSet.next();
            nuevoIdPersona = resultSet.getLong(1);
            System.out.println("Ejecutamos Save MySQLPersonaDAO");
        } catch (SQLException throwables) {
            conexion.rollback();
            System.out.println("Error guardando el nuevo registro (Save.Persona) " + throwables);
        } finally {
            return nuevoIdPersona;
        }
    }

    protected long update(Persona persona)
    {
        String queryPersona = "UPDATE personas SET nombre = ?, apellidos = ?, identificador = ?, nacionalidad = ?, direccion = ?, poblacion = ?, telefono = ?, email = ?, fechaNac = ? WHERE idPersona = ?";
        PreparedStatement updateEntrada;
        long idPersona = 0;
        try {
            if(getResult(persona.getIdPersona()) != null) {
                idPersona = persona.getIdPersona();
                updateEntrada = conexion.prepareStatement(queryPersona,Statement.RETURN_GENERATED_KEYS);
                updateEntrada.setString(1, persona.getNombre());
                updateEntrada.setString(2, persona.getApellidos());
                updateEntrada.setString(3, persona.getIdentificador());
                updateEntrada.setString(4, persona.getNacionalidad());
                updateEntrada.setString(5, persona.getDireccion());
                updateEntrada.setString(6, persona.getPoblacion());
                updateEntrada.setString(7, persona.getTelefono());
                updateEntrada.setString(8, persona.getEmail());
                updateEntrada.setDate(9,convertirFecha(persona.getFechaNac()));
                updateEntrada.setLong(10, idPersona);
                updateEntrada.executeUpdate();
                System.out.println("Ejecutamos Update MySQLPersonaDAO");
            }
            else{
                idPersona = save(persona);
            }

        } catch (SQLException throwables) {
            conexion.rollback();
            System.out.println("Error guardando el nuevo registro (Update.Persona) " + throwables);
        } finally {
            return idPersona;
        }
    }

    protected long checkDNI(String dni)
    {
        String query = "SELECT * FROM personas WHERE identificador='?'";
        long result = 0;
        PreparedStatement checkDNI;

        try {
            checkDNI = conexion.prepareStatement(query);
            ResultSet set = checkDNI.executeQuery();
            while (set.next())
            {
                result = set.getLong("idPersona");
                break;
            }
        } catch (SQLException throwables) {
            System.out.println("No se ha podido comprobar el DNI " + throwables);
        }

        return result;
    }

}
