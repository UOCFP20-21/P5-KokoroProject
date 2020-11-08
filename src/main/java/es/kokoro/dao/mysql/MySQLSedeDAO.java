package es.kokoro.dao.mysql;

import es.kokoro.dao.SedeDAO;
import es.kokoro.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.SqlConnection.commitData;
import static es.kokoro.commons.SqlConnection.conectar;

public class MySQLSedeDAO extends MySQLEmpresaDAO {

    private MySQLTrabajadorDAO mySQLTrabajadorDAO;
    private MySQLOngDAO mySQLOngDAO;

   /* public MySQLSedeDAO(MySQLTrabajadorDAO mySQLTrabajadorDAO, MySQLOngDAO mySQLOngDAO) {
        this.mySQLTrabajadorDAO = mySQLTrabajadorDAO;
        this.mySQLOngDAO = mySQLOngDAO;
        setConexion(conexion);
    }

    public MySQLSedeDAO(Connection conexion, MySQLTrabajadorDAO mySQLTrabajadorDAO, MySQLOngDAO mySQLOngDAO) {
        this.mySQLTrabajadorDAO = mySQLTrabajadorDAO;
        this.mySQLOngDAO = mySQLOngDAO;
        setConexion(conexion);
    }*/

    public Sede get(long id) {

        String query = "SELECT * FROM sedes WHERE idSede = ?";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            ///*TODO Añadir ONg y trabajador LIst
            long idSede = set.getLong("idSede");
            long idEmpresa = set.getLong("idEmpresa");
            long idOng = set.getLong("idOng");

            Ong ong = mySQLOngDAO.get(idOng);
            //TODO crear query en mysqltrabajadordao para  que devuelva todos los trabajadores que pertenecen a una sede
            // asignar la lista despues de recuperarla

            Empresa empresa = super.get(idEmpresa);
            return new Sede(idEmpresa, idSede, empresa.getNombre(), empresa.getPais(), empresa.getPoblacion(), empresa.getRazonSocial(),
                    empresa.getIdentificacionSocial(), empresa.getEmail(), empresa.getTelefono(), empresa.getEmail(),  null,
                    ong);
        } catch (Exception throwables) {
            System.out.println("Error obteniendo la instancia " + throwables);
        }
        return null;
    }

    public List<Empresa> getAll() {

        List<Empresa> empresaList = new ArrayList<>();
        String query = "SELECT * FROM sedes";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                long idEmpresa = set.getLong("idEmpresa");
                Empresa empresa = super.get(idEmpresa);
                empresaList.add(empresa);
            }
        } catch (Exception throwables) {
            System.out.println("Error obteniendo el listado de instancias  " + throwables);
        }
        return empresaList;
    }

    public Sede save(Sede Sede) {

        String query = "INSERT INTO sedes (idEmpresa, idOng) VALUES(?,?)";
        PreparedStatement nuevaEntrada;
        Empresa empresa = null;
        try {
            conexion.setAutoCommit(false);
            if (Sede.getIdEmpresa() == null)   // No facilitamos ID empresa
            {
                if (checkIdentificadorSocial(Sede.getIdentificacionSocial()) == 0)  // No existe el Identificador en nuestra DDBB
                {
                    empresa = super.save(Sede);
                    System.out.println("El ID de Empresa nuevo es: " + empresa.getIdEmpresa());
                } else {
                    System.out.println("El CIF que intentas introducir ya existe.");
                }
            } else // Facilitamos un ID de Persona
            {
                empresa = super.update(Sede);
            }
            nuevaEntrada = conexion.prepareStatement(query);
            nuevaEntrada.setLong(1, empresa.getIdEmpresa());
            nuevaEntrada.executeUpdate();
            commitData(conexion);
            System.out.println("Ejecutamos Save MySQLSedeDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del nuevo registro " + e);
            }
            System.out.println("Error guardando el nuevo registro  (Save.Sede)" + throwables);
        }
        return Sede;
    }

    public Sede update(Sede Sede) {

        String query = "UPDATE sedes SET idEmpresa = ?, idOng = ?, WHERE idSede = ?";
        PreparedStatement updateEntrada;
        try {
            conexion.setAutoCommit(false);
            if (Sede.getIdEmpresa() == null || Sede.getIdSede() == null)   // No facilitamos IDs
            {
                System.out.println("No se ha indicado la entrada a modificar");
            } else // Facilitamos los IDs
            {
                Empresa empresa = super.update(Sede);
                updateEntrada = conexion.prepareStatement(query);
                updateEntrada.setLong(1, empresa.getIdEmpresa());
                updateEntrada.setLong(2, Sede.getIdSede());
                updateEntrada.executeUpdate();
            }
            commitData(conexion);
            System.out.println("Ejecutamos Update MySQLSedeDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del update del registro (Update.Sede) " + throwables);
            } finally {
                System.out.println("Error Actualizando el nuevo registro (Update.Sede) " + throwables);
            }
        }
        return Sede;
    }

    public void delete(Sede Sede) {

        boolean existe = false;
        try {
            if (Sede.getIdSede() != null) { // Estamos pasando un ID
                if (get(Sede.getIdSede()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = " DELETE FROM sedes WHERE idSede = ?";
                    PreparedStatement borrarEntrada;
                    borrarEntrada = conexion.prepareStatement(query);
                    borrarEntrada.setLong(1, Sede.getIdSede());
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