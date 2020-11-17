package es.kokoro.dao.mysql;

import es.kokoro.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.FormatFecha.FFStringToDate;
import static es.kokoro.commons.sqlConection.*;

public class MySQLContratadoDAO extends MySQLTrabajadorDAO {


    public MySQLContratadoDAO() {
        super();
    }
    public MySQLContratadoDAO(Connection conexion) {
        super(conexion);
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

    @Override
    public Contratado get(long id) {
        String query = "SELECT * FROM contratados WHERE idContratado = ?";
        PreparedStatement statement;
        Contratado tmpEntrada = null;
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

    protected Contratado setObject(ResultSet set) {
        Contratado tmpEntrada = null;
        ResultSet personaData;
        try {
            long idContratado = set.getLong("idContratado");
            long idTrabajador = set.getLong("idTrabajador");
            long idPersona = set.getLong("idPersona");
            personaData = super.getResult(idTrabajador);
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
            Delegacion delegacion = mysqlDelegacionDAO.get(super.getDelegacion(idTrabajador));
            MySQLOngDAO mysqlOngDAO = new MySQLOngDAO(conexion);
            Ong ong = mysqlOngDAO.get(set.getLong("idOng"));
            tmpEntrada = new Contratado(
                    idPersona, nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email, idTrabajador, delegacion, fechaNac, set.getBoolean("activo"),
                    idContratado, FFStringToDate(set.getString("inicioContrato")), set.getString("fincontrato"), set.getFloat("salario"), set.getString("puestoTrabajo"), ong);
        } catch (SQLException throwables) {
            System.out.println("Error creando la instancia " + throwables);
        } finally {
            return tmpEntrada;
        }

    }

    @Override
    public List<Trabajador> getAll() {

        List entradasList = new ArrayList<Contratado>();
        String query = "SELECT * FROM contratados";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next())
            {
                Contratado tmpEntrada = setObject(set);
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

        String query = "INSERT INTO contratados (inicioContrato, fincontrato, salario, puestoTrabajo, idTrabajador) VALUES(?,?,?,?,?)";
        Contratado contratado = (Contratado) trabajador;
        PreparedStatement nuevaEntrada;
        Long idTrabajador = null;
        try {
            conexion.setAutoCommit(false);
            if(trabajador.getIdPersona() == null)   // No facilitamos ID persona
            {
                if(checkDNI(trabajador.getIdentificador()) == 0)  // No existe el Identificador en nuestra DDBB
                {
                    idTrabajador = super.save((Persona) trabajador);
                    System.out.println("El ID de Trabajador nuevo es: " + idTrabajador);
                }
                else
                {
                    System.out.println("El DNI que intentas introducir ya existe.");
                }
            }
            else // Facilitamos un ID de Persona
            {
                idTrabajador = super.update((Persona) trabajador);
            }

            nuevaEntrada = conexion.prepareStatement(query);
            nuevaEntrada.setDate(1,convertirFecha(contratado.getInicioContract()));
            nuevaEntrada.setString(2,contratado.getFinalContract());
            nuevaEntrada.setDouble(3,contratado.getSalario());
            nuevaEntrada.setString(4,contratado.getPuestoTrabajo());
            nuevaEntrada.setLong(5,idTrabajador);
            nuevaEntrada.executeUpdate();
            commitData(conexion);
            System.out.println("Ejecutamos Save MySQLContratadoDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del nuevo registro " + e);
            }
            System.out.println("Error guardando el nuevo registro  (Save.Trabajador)" + throwables);
        }finally {
            if(idTrabajador == null || idTrabajador == 0)
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

        String query = "UPDATE contratados SET inicioContrato = ?, fincontrato = ?, salario = ?, puestoTrabajo = ?, idTrabajador = ? WHERE idContratado = ?";
        Contratado contratado = (Contratado) trabajador;
        PreparedStatement updateEntrada;
        Long idTrabajador = null;
        try {
            conexion.setAutoCommit(false);
            if(contratado.getIdContratado() == null || contratado.getIdTrabajador() == null)   // No facilitamos IDs
            {
                System.out.println("No se ha indicado la entrada a modificar");
            }
            else // Facilitamos los IDs
            {
                idTrabajador = super.update((Persona) contratado);
                updateEntrada = conexion.prepareStatement(query);
                updateEntrada.setDate(1,convertirFecha(contratado.getInicioContract()));
                updateEntrada.setString(2,contratado.getFinalContract());
                updateEntrada.setDouble(3,contratado.getSalario());
                updateEntrada.setString(4,contratado.getPuestoTrabajo());
                updateEntrada.setLong(5,idTrabajador);
                updateEntrada.setLong(6,contratado.getIdContratado());
                updateEntrada.executeUpdate();
            }

            commitData(conexion);
            System.out.println("Ejecutamos Update MySQLContratadoDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del update del registro (Update.Contratado) " + throwables);
            }finally {
                System.out.println("Error Actualizando el nuevo registro (Update.Contratado) " + throwables);
            }

        }finally {
            if(idTrabajador == null || idTrabajador == 0)
            {
                try {
                    conexion.rollback();
                } catch (SQLException throwables) {
                    System.out.println("Error realizando RollBack del nuevo registro " + throwables);
                }finally {
                    System.out.println("Error Actualizando el nuevo registro  (Update.Contratado)");
                }
            }
        }
    }

    @Override
    public void delete(Trabajador trabajador)  {

        Contratado contratado = (Contratado) trabajador;
        boolean existe = false;
        try {
            if(contratado.getIdContratado() != null ) { // Estamos pasando un ID

                if(get(contratado.getIdContratado()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = " DELETE FROM contratados WHERE idContratado = ?";
                    PreparedStatement borrarEntrada;

                    borrarEntrada = conexion.prepareStatement(query);
                    borrarEntrada.setLong(1,contratado.getIdContratado());
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
