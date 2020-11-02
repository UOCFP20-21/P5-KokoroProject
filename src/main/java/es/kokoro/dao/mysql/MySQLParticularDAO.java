package es.kokoro.dao.mysql;

import es.kokoro.dao.ParticularDAO;
import es.kokoro.model.Particular;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.sqlConection.commitData;

public class MySQLParticularDAO extends MySQLPersonaDAO implements ParticularDAO {

    public MySQLParticularDAO() {
        setConexion(conexion);
    }
    public MySQLParticularDAO(Connection conexion) {
        setConexion(conexion);
    }

    private Particular setObject(ResultSet set)
    {
        Particular tmpEntrada = null;
        ResultSet personaData;
        try {
            long idParticular = set.getLong("idParticular");
            long idPersona = set.getLong("idPersona");
            personaData = super.getResult(idPersona);
            String nombre = personaData.getString("nombre");
            String apellidos = personaData.getString("apellidos");
            String identificador = personaData.getString("identificador");
            String nacionalidad = personaData.getString("nacionalidad");
            String direccion = personaData.getString("direccion");
            String poblacion = personaData.getString("poblacion");
            String telefono = personaData.getString("telefono");
            String email = personaData.getString("email");
            tmpEntrada = new Particular(idPersona, nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email, idParticular);
        } catch (SQLException throwables) {
            System.out.println("Error creando la instancia " + throwables);
        } finally {
            return tmpEntrada;
        }

    }

    @Override
    public Particular get(long id) {
        String query = "SELECT * FROM particulares WHERE idParticular = ?";
        PreparedStatement statement;
        Particular tmpEntrada = null;
        try {
            statement = conexion.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            tmpEntrada = setObject(set);

        } catch (SQLException throwables) {
            System.out.println("Error obteniendo la instancia " + throwables);
        } finally {
            return tmpEntrada;
        }
    }

    @Override
    public List<Particular> getAll() {

        List<Particular> entradasList = new ArrayList<>();
        String query = "SELECT * FROM particulares";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next())
            {
                Particular tmpEntrada = setObject(set);
                entradasList.add(tmpEntrada);
            }
        } catch (SQLException throwables) {
            System.out.println("Error obteniendo el listado de instancias  " + throwables);
        } finally {
            return entradasList;
        }
    }

    @Override
    public void save(Particular particular) {

        String query = "INSERT INTO particulares (idPersona) VALUES(?)";
        //String queryPersona = "INSERT INTO personas(nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email) VALUES(?,?,?,?,?,?,?,?)";

        PreparedStatement nuevaEntrada;
        Long idPersona = null;
        try {
            conexion.setAutoCommit(false);
            if(particular.getIdPersona() == null)   // No facilitamos ID persona
            {
                if(checkDNI(particular.getIdentificador()) == 0)  // No existe el Identificador en nuestra DDBB
                {
                    idPersona = super.save(particular);
                    System.out.println("El ID de Persona nuevo es: " + idPersona);
                }
                else
                {
                    System.out.println("El DNI que intentas introducir ya existe.");
                }
            }
            else // Facilitamos un ID de Persona
            {
                idPersona = super.update(particular);
            }

            nuevaEntrada = conexion.prepareStatement(query);
            nuevaEntrada.setLong(1,idPersona);
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
        }finally {
            if(idPersona == null || idPersona == 0)
            {
                try {
                    conexion.rollback();
                } catch (SQLException throwables) {
                    System.out.println("Error realizando RollBack del nuevo registro " + throwables);
                }
            }
        }

    }


    @Override
    public void update(Particular particular) {

        String query = "UPDATE particulares SET idPersona = ? WHERE idParticular = ?";
        PreparedStatement updateEntrada;
        Long idPersona = null;
        try {
            conexion.setAutoCommit(false);
            if(particular.getIdPersona() == null || particular.getIdParticular() == null)   // No facilitamos IDs
            {
                System.out.println("No se ha indicado la entrada a modificar");
            }
            else // Facilitamos los IDs
            {
                idPersona = super.update(particular);
                updateEntrada = conexion.prepareStatement(query);
                updateEntrada.setLong(1,particular.getIdPersona());
                updateEntrada.setLong(2,particular.getIdParticular());
                updateEntrada.executeUpdate();
            }

            commitData(conexion);
            System.out.println("Ejecutamos Update MySQLParticularDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del update del registro (Update.Particular) " + throwables);
            }finally {
                System.out.println("Error Actualizando el nuevo registro (Update.Particular) " + throwables);
            }

        }finally {
            if(idPersona == null || idPersona == 0)
            {
                try {
                    conexion.rollback();
                } catch (SQLException throwables) {
                    System.out.println("Error realizando RollBack del nuevo registro " + throwables);
                }finally {
                    System.out.println("Error Actualizando el nuevo registro  (Update.Particular)");
                }
            }
        }

    }

    @Override
    public void delete(Particular particular)  {

        boolean existe = false;
        try {
            if(particular.getIdParticular() != null ) { // Estamos pasando un ID

                if(get(particular.getIdParticular()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = " DELETE FROM particulares WHERE idParticular = ?";
                    PreparedStatement borrarEntrada;

                    borrarEntrada = conexion.prepareStatement(query);
                    borrarEntrada.setLong(1,particular.getIdParticular());
                    borrarEntrada.executeUpdate();
                    existe = true;
                }
            }
        } catch (SQLException throwables) {
            System.out.println("Error Eliminando el registro " + throwables);
        }finally
        {
            if(!existe)   // Si no existe en nuestra DDBB o no facilitan un ID guardamos en lugar de actualizar
            {
                System.out.println("Registro no encontrado: No existe ningún registro con los datos facilitados.");
            }
        }
    }
}
