package es.kokoro.dao.mysql;

import es.kokoro.dao.SocioDAO;
import es.kokoro.enums.Periodo;
import es.kokoro.model.Particular;
import es.kokoro.model.Socio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static es.kokoro.commons.FormatFecha.FFStringToDate;
import static es.kokoro.commons.sqlConection.commitData;

public class MySQLSocioDAO extends MySQLPersonaDAO implements SocioDAO {

    public MySQLSocioDAO() {
        setConexion(conexion);
    }
    public MySQLSocioDAO(Connection conexion) {
        setConexion(conexion);
    }

    private Socio setObject(ResultSet set)
    {
        Socio tmpEntrada = null;
        ResultSet personaData;
        try {
            long idSocio = set.getLong("idSocio");
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
            Periodo periodo = Periodo.valueOf(personaData.getString("periodo"));
            Double cuota = personaData.getDouble("cuota");
            Boolean estado = personaData.getBoolean("estado");
            Date fechaNac = FFStringToDate(personaData.getString("fechaNac"));
            tmpEntrada = new Socio(idPersona, nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email, idSocio, periodo, cuota, estado, fechaNac);
        } catch (SQLException throwables) {
            System.out.println("Error creando la instancia " + throwables);
        } finally {
            return tmpEntrada;
        }

    }


    @Override
    public Socio get(long id) {
        String query = "SELECT * FROM socios WHERE idSocio = ?";
        PreparedStatement statement;
        Socio tmpEntrada = null;
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
    public List<Socio> getAll() {

        List<Socio> entradasList = new ArrayList<>();
        String query = "SELECT * FROM socios";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next())
            {
                Socio tmpEntrada = setObject(set);
                entradasList.add(tmpEntrada);
            }
        } catch (SQLException throwables) {
            System.out.println("Error obteniendo el listado de instancias  " + throwables);
        } finally {
            return entradasList;
        }
    }

    @Override
    public void save(Socio socio)  {

        String query = "INSERT INTO socios (periodo, cuota, estado, idPersona, idOng) VALUES(?,?,?,?,?)";
        //String queryPersona = "INSERT INTO personas(nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email) VALUES(?,?,?,?,?,?,?,?)";

        PreparedStatement nuevaEntrada;
        Long idPersona = null;
        try {
            conexion.setAutoCommit(false);
            if(socio.getIdPersona() == null)   // No facilitamos ID persona
            {
                if(checkDNI(socio.getIdentificador()) == 0)  // No existe el Identificador en nuestra DDBB
                {
                    idPersona = super.save(socio);
                    System.out.println("El ID de Persona nuevo es: " + idPersona);
                }
                else
                {
                    System.out.println("El DNI que intentas introducir ya existe.");
                }
            }
            else // Facilitamos un ID de Persona
            {
                idPersona = super.update(socio);
            }
            nuevaEntrada = conexion.prepareStatement(query);
            nuevaEntrada.setString(1,socio.getPeriodo().getNombrePeriodo());
            nuevaEntrada.setDouble(2,socio.getCuota());
            nuevaEntrada.setBoolean(3,socio.isEstado());
            nuevaEntrada.setLong(4,idPersona);
            nuevaEntrada.setLong(5,1L);
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
    public void update(Socio socio)  {

    }

    @Override
    public void delete(Socio socio)  {

    }
}
