package es.kokoro.dao.mysql;

import es.kokoro.dao.TrabajadorDAO;
import es.kokoro.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.sqlConection.commitData;
import static es.kokoro.commons.sqlConection.conectar;

public class MySQLTrabajadorDAO implements TrabajadorDAO {

    public MySQLTrabajadorDAO() {
        super();
    }

    public Connection getConexion() { return null; }

    @Override
    public Trabajador get(long id) throws Exception {
        return null;
    }

    @Override
    public List<Trabajador> getAll() throws Exception {
        return null;
    }

    @Override
    public void save(Trabajador trabajador) throws Exception {

    }

    @Override
    public void update(Trabajador trabajador) throws Exception {

    }

    @Override
    public void delete(Trabajador trabajador) throws Exception {

    }
/*
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

        String query = "SELECT * FROM trabajadores AS t LEFT JOIN personas AS p ON t.idPersona = p.idPersona WHERE idTrabajador = ? ";
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

    protected long checkorsavePersona(Trabajador trabajador)
    {
        long nuevoIdPersona = trabajador.getIdPersona();
        if(trabajador.getIdPersona() == null || super.getResult(trabajador.getIdPersona()) == null)
        { nuevoIdPersona = super.save(trabajador); }
        return nuevoIdPersona;
    }

    protected long checkorsaveOng(Trabajador trabajador)
    {
        long nuevoIdOng = trabajador.getOng().getIdOng();
        MySQLOngDAO ongDAO = new MySQLOngDAO(conexion);
        if(trabajador.getOng().getIdOng() == null || ongDAO.get(trabajador.getOng().getIdOng()) == null)
        { nuevoIdOng = ongDAO.saveLong(trabajador.getOng()); }
        return nuevoIdOng;
    }

    protected long save(Persona persona)
    {
        Trabajador trabajador = (Trabajador) persona;
        Long nuevoIdPersona = trabajador.getIdPersona();
        Long nuevoIdOng = trabajador.getOng().getIdOng();
        long nuevoIdTrabajador = 0;
        String queryPersona = "INSERT INTO trabajadores (activo, idPersona, idOng) VALUES(?,?,?)";
        PreparedStatement nuevaEntrada;

        try {
            nuevoIdPersona = checkorsavePersona(trabajador);
            nuevoIdOng = checkorsaveOng(trabajador);
            nuevaEntrada = conexion.prepareStatement(queryPersona, Statement.RETURN_GENERATED_KEYS);
            nuevaEntrada.setBoolean(1,trabajador.isActivo());
            nuevaEntrada.setLong(2,nuevoIdPersona);
            nuevaEntrada.setLong(3,nuevoIdOng);
            nuevaEntrada.executeUpdate();
            ResultSet resultSet = nuevaEntrada.getGeneratedKeys();
            resultSet.next();
            nuevoIdTrabajador = resultSet.getLong(1);
            System.out.println("Ejecutamos Save MySQLTrabajadorDAO");
        } catch (SQLException throwables) {
            conexion.rollback();
            System.out.println("Error guardando el nuevo registro (Save.Trabajador) " + throwables);
        } finally {
            return nuevoIdTrabajador;
        }
    }

    protected long update(Persona persona)
    {
        Trabajador trabajador = (Trabajador) persona;
        String queryPersona = "UPDATE trabajadores SET activo = ?, idPersona = ?, idOng = ? WHERE idTrabajador = ?";
        PreparedStatement updateEntrada;
        long idTrabajador = 0;
        try {
            if(getResult(trabajador.getIdTrabajador()) != null) {
                idTrabajador = trabajador.getIdTrabajador();
                updateEntrada = conexion.prepareStatement(queryPersona,Statement.RETURN_GENERATED_KEYS);
                updateEntrada.setBoolean(1, trabajador.isActivo());
                updateEntrada.setLong(2, checkorsavePersona(trabajador));
                updateEntrada.setLong(3, checkorsaveOng(trabajador));
                updateEntrada.setLong(4, idTrabajador);
                updateEntrada.executeUpdate();
                System.out.println("Ejecutamos Update MySQLTrabajadorDAO");
            }else
            {
                idTrabajador = save(persona);
            }

        } catch (SQLException throwables) {
            conexion.rollback();
            System.out.println("Error guardando el nuevo registro (Update.Trabajador) " + throwables);
        } finally {
            return idTrabajador;
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

    protected Long getDelegacion(long idTrabajador)
    {
        Long idDelegacion = null;
        String query = "SELECT idDelegacion FROM trabajadores_delegacion WHERE idTrabajador = ? ";
        PreparedStatement statement;

        try {
            statement = conexion.prepareStatement(query);
            statement.setLong(1, idTrabajador);
            ResultSet set = statement.executeQuery();
            set.next();
            idDelegacion = set.getLong("idDelegacion");
            System.out.println("Delegacion ID obtenido");
        } catch (SQLException throwables) {
            System.out.println("Error obteniendo el Result Set " + throwables);
        }
        return idDelegacion;

    }

    protected Trabajador setObject(ResultSet set)
    {
        Trabajador tmpEntrada = null;
        ResultSet personaData;
        try {
            long idTrabajador = set.getLong("idTrabajador");
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
            Date fechaNac = personaData.getDate("fechaNac");
            MySQLDelegacionDAO mysqlDelegacionDAO = new MySQLDelegacionDAO(conexion);
            Delegacion delegacion = mysqlDelegacionDAO.get(getDelegacion(idTrabajador));
            MySQLOngDAO mysqlOngDAO = new MySQLOngDAO(conexion);
            Ong ong = mysqlOngDAO.get(set.getLong("idOng"));
            tmpEntrada = new Trabajador(idPersona, nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email, idTrabajador, delegacion, fechaNac, set.getBoolean("activo"), ong);
        } catch (SQLException throwables) {
            System.out.println("Error creando la instancia " + throwables);
        } finally {
            return tmpEntrada;
        }

    }

    @Override
    public Trabajador get(long id) {
        String query = "SELECT * FROM trabajadores WHERE idTrabajador = ?";
        PreparedStatement statement;
        Trabajador tmpEntrada = null;
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
    public List<Trabajador> getAll() throws Exception {

        List<Trabajador> entradasList = new ArrayList<>();
        String query = "SELECT * FROM particulares";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next())
            {
                Trabajador tmpEntrada = setObject(set);
                entradasList.add(tmpEntrada);
            }
        } catch (SQLException throwables) {
            System.out.println("Error obteniendo el listado de instancias  " + throwables);
        } finally {
            return entradasList;
        }
    }

    @Override
    public void save(Trabajador trabajador) {

        String query = "INSERT INTO trabajadores (activo, idPersona, idOng) VALUES(?,?,?)";

        PreparedStatement nuevaEntrada;
        Long idPersona = null;
        try {
            conexion.setAutoCommit(false);
            if(trabajador.getIdPersona() == null)   // No facilitamos ID persona
            {
                if(checkDNI(trabajador.getIdentificador()) == 0)  // No existe el Identificador en nuestra DDBB
                {
                    idPersona = super.save(trabajador);
                    System.out.println("El ID de Persona nuevo es: " + idPersona);
                }
                else
                {
                    System.out.println("El DNI que intentas introducir ya existe.");
                }
            }
            else // Facilitamos un ID de Persona
            {
                idPersona = super.update(trabajador);
            }

            nuevaEntrada = conexion.prepareStatement(query);
            nuevaEntrada.setLong(1,idPersona);
            nuevaEntrada.setLong(2,trabajador.getDelegacion().getIdDelegacion());
            nuevaEntrada.setLong(3,trabajador.getOng().getIdOng());
            nuevaEntrada.executeUpdate();
            commitData(conexion);
            System.out.println("Ejecutamos Save MySQLTrabajadorDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del nuevo registro " + e);
            }
            System.out.println("Error guardando el nuevo registro  (Save.Trabajador)" + throwables);
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
    public void update(Trabajador trabajador) {

        String query = "UPDATE particulares SET activo = ?, idPersona = ?, idOng = ? WHERE idTrabajador = ?";
        PreparedStatement updateEntrada;
        Long idPersona = null;
        try {
            conexion.setAutoCommit(false);
            if(trabajador.getIdPersona() == null || trabajador.getIdTrabajador() == null)   // No facilitamos IDs
            {
                System.out.println("No se ha indicado la entrada a modificar");
            }
            else // Facilitamos los IDs
            {
                idPersona = super.update(trabajador);
                updateEntrada = conexion.prepareStatement(query);
                updateEntrada.setBoolean(1,trabajador.isActivo());
                updateEntrada.setLong(2,trabajador.getIdPersona());
                updateEntrada.setLong(3,trabajador.getDelegacion().getIdDelegacion());
                updateEntrada.setLong(4,trabajador.getIdTrabajador());
                updateEntrada.executeUpdate();
            }

            commitData(conexion);
            System.out.println("Ejecutamos Update MySQLTrabajadorDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del update del registro (Update.Trabajador) " + throwables);
            }finally {
                System.out.println("Error Actualizando el nuevo registro (Update.Trabajador) " + throwables);
            }

        }finally {
            if(idPersona == null || idPersona == 0)
            {
                try {
                    conexion.rollback();
                } catch (SQLException throwables) {
                    System.out.println("Error realizando RollBack del nuevo registro " + throwables);
                }finally {
                    System.out.println("Error Actualizando el nuevo registro  (Update.Trabajador)");
                }
            }
        }
    }

    @Override
    public void delete(Trabajador trabajador)  {

        boolean existe = false;
        try {
            if(trabajador.getIdTrabajador() != null ) { // Estamos pasando un ID

                if(get(trabajador.getIdTrabajador()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = " DELETE FROM trabajadores WHERE idTrabajador = ?";
                    PreparedStatement borrarEntrada;

                    borrarEntrada = conexion.prepareStatement(query);
                    borrarEntrada.setLong(1,trabajador.getIdTrabajador());
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
    }*/
}
