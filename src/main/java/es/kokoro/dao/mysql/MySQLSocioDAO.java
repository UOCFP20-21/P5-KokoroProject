/*package es.kokoro.dao.mysql;

import es.kokoro.dao.SocioDAO;
import es.kokoro.enums.Periodo;
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
        super();
    }
    public MySQLSocioDAO(Connection conexion) {
        super(conexion);
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
            Periodo periodo = Periodo.valueOf(set.getString("periodo"));
            Double cuota = set.getDouble("cuota");
            Boolean estado = set.getBoolean("estado");
            Date fechaNac = FFStringToDate(personaData.getString("fechaNac"));
            tmpEntrada = new Socio(idPersona, nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email, idSocio, periodo, cuota, estado, fechaNac);
        } catch (SQLException throwables) {
            System.out.println("Error creando la instancia en Socio setObject " + throwables);
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

        String query2 = "INSERT INTO socios (periodo, cuota, estado, idPersona) VALUES(?,?,?,?)";
        //String queryPersona = "INSERT INTO personas(nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email) VALUES(?,?,?,?,?,?,?,?)";

        PreparedStatement nuevaEntrada;
        Long idPersona = null;
        try {
            conexion.setAutoCommit(false);
            if(socio.getPersona().getIdPersona() == null)   // No facilitamos ID persona
            {
                if(checkDNI(socio.getPersona().getIdentificador()) == 0)  // No existe el Identificador en nuestra DDBB
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
            nuevaEntrada = conexion.prepareStatement(query2);
            nuevaEntrada.setString(1, socio.getPeriodo().name());
            nuevaEntrada.setDouble(2,socio.getCuota());
            nuevaEntrada.setBoolean(3,socio.isEstado());
            nuevaEntrada.setLong(4,idPersona);
            //System.out.println("Valor Periodo (toString): " + socio.getPeriodo().toString() + " Vs (Name): " + socio.getPeriodo().name());
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

        String query = "UPDATE socios SET idPersona = ?, periodo = ?, cuota = ?, estado = ?  WHERE idSocio = ?";
        PreparedStatement updateEntrada;
        Long idPersona = null;
        try {
            conexion.setAutoCommit(false);
            if(socio.getIdPersona() == null || socio.getIdSocio() == null)   // No facilitamos IDs
            {
                System.out.println("No se ha indicado la entrada a modificar");
            }
            else // Facilitamos los IDs
            {
                idPersona = super.update(socio);
                updateEntrada = conexion.prepareStatement(query);
                updateEntrada.setLong(1,socio.getIdPersona());
                updateEntrada.setString(2,socio.getPeriodo().toString());
                updateEntrada.setDouble(3,socio.getCuota());
                updateEntrada.setBoolean(4,socio.isEstado());
                updateEntrada.setLong(5,socio.getIdSocio());
                updateEntrada.executeUpdate();
            }

            commitData(conexion);
            System.out.println("Ejecutamos Update MySQLSocioDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del update del registro (Update.Socio) " + throwables);
            }finally {
                System.out.println("Error Actualizando el nuevo registro (Update.Socio) " + throwables);
            }

        }finally {
            if(idPersona == null || idPersona == 0)
            {
                try {
                    conexion.rollback();
                } catch (SQLException throwables) {
                    System.out.println("Error realizando RollBack del nuevo registro " + throwables);
                }finally {
                    System.out.println("Error Actualizando el nuevo registro  (Update.Socio)");
                }
            }
        }
    }

    @Override
    public void delete(Socio socio)  {

        boolean existe = false;
        try {
            if(socio.getIdSocio() != null ) { // Estamos pasando un ID

                if(get(socio.getIdSocio()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = " DELETE FROM socios WHERE idSocio = ?";
                    PreparedStatement borrarEntrada;

                    borrarEntrada = conexion.prepareStatement(query);
                    borrarEntrada.setLong(1,socio.getIdSocio());
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
*/