package es.kokoro.dao.mysql;

import es.kokoro.commons.SqlConnection;
import es.kokoro.dao.EmpresaDAO;
import es.kokoro.dao.SocioLocalDAO;
import es.kokoro.model.Empresa;
import es.kokoro.model.SocioLocal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.SqlConnection.commitData;

public class MySQLSocioLocalDAO extends MySQLEmpresaDAO {

    public MySQLSocioLocalDAO() {
        setConexion(conexion);
    }

    public MySQLSocioLocalDAO(Connection conexion) {
        setConexion(conexion);
    }

    public SocioLocal get(long id){
        String query = "SELECT * FROM sociosLocales WHERE idSocioLocal = ?";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            long idSocioLocal = set.getLong("idSocioLocal");

            long idEmpresa = set.getLong("idEmpresa");
            Empresa empresa = super.get(idEmpresa);
            return new SocioLocal(idEmpresa, empresa.getNombre(), empresa.getPais(), empresa.getPoblacion(), empresa.getRazonSocial(),
                    empresa.getIdentificacionSocial(), empresa.getEmail(), empresa.getTelefono(), empresa.getEmail(), idSocioLocal);
        } catch (Exception throwables) {
            System.out.println("Error obteniendo la instancia " + throwables);
        }
        return null;
    }

    //aunque el método devuelva List<Empresa>, en esa lista puede haber cualquier clase que extienda de empresa
    public List<Empresa> getAll() {

        List<Empresa> empresaList = new ArrayList<>();
        String query = "SELECT * FROM sociosLocales";
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

    public SocioLocal save(SocioLocal socioLocal) {

        String query = "INSERT INTO sociosLocales (idEmpresa) VALUES(?)";
        PreparedStatement nuevaEntrada;
        Empresa empresa = null;
        try {
            conexion.setAutoCommit(false);
            if (socioLocal.getIdEmpresa() == null)   // No facilitamos ID socioLocal
            {
                if (checkIdentificadorSocial(socioLocal.getIdentificacionSocial()) == 0)  // No existe el Identificador en nuestra DDBB
                {
                    empresa = super.save(socioLocal);
                    System.out.println("El ID de Empresa nuevo es: " + empresa.getIdEmpresa());
                } else {
                    System.out.println("El CIF que intentas introducir ya existe.");
                }
            } else // Facilitamos un ID de socioLocal
            {
                empresa = super.update(socioLocal);
            }
            nuevaEntrada = conexion.prepareStatement(query);
            nuevaEntrada.setLong(1, empresa.getIdEmpresa());
            nuevaEntrada.executeUpdate();
            commitData(conexion);
            System.out.println("Ejecutamos Save MySQLSocioLocalDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del nuevo registro " + e);
            }
            System.out.println("Error guardando el nuevo registro  (Save.Extraordinario)" + throwables);
        }
        return socioLocal;
    }

    public SocioLocal update(SocioLocal socioLocal) {

        String query = "UPDATE sociosLocales SET idEmpresa = ? WHERE idSocioLocal = ?";
        PreparedStatement updateEntrada;
        try {
            conexion.setAutoCommit(false);
            if (socioLocal.getIdEmpresa() == null || socioLocal.getIdSocioLocal() == null)   // No facilitamos IDs
            {
                System.out.println("No se ha indicado la entrada a modificar");
            } else // Facilitamos los IDs
            {
                Empresa empresa = super.update(socioLocal);
                updateEntrada = conexion.prepareStatement(query);
                updateEntrada.setLong(1, empresa.getIdEmpresa());
                updateEntrada.setLong(2, socioLocal.getIdSocioLocal());
                updateEntrada.executeUpdate();
            }
            commitData(conexion);
            System.out.println("Ejecutamos Update MySQLSocioLocalDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del update del registro (Update.SocioLocal) " + throwables);
            } finally {
                System.out.println("Error Actualizando el nuevo registro (Update.SocioLocal) " + throwables);
            }
        }
        return socioLocal;
    }

    public void delete(SocioLocal socioLocal){

        boolean existe = false;
        try {
            if (socioLocal.getIdSocioLocal() != null) { // Estamos pasando un ID
                if (get(socioLocal.getIdSocioLocal()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = " DELETE FROM sociosLocales WHERE idSocioLocal = ?";
                    PreparedStatement borrarEntrada;
                    borrarEntrada = conexion.prepareStatement(query);
                    borrarEntrada.setLong(1, socioLocal.getIdSocioLocal());
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
