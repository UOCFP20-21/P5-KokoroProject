package es.kokoro.dao.mysql;

import es.kokoro.dao.InstitucionDAO;
import es.kokoro.model.Institucion;
import es.kokoro.model.Empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.SqlConnection.commitData;

public class MySQLInstitucionDAO extends MySQLEmpresaDAO
{
    public MySQLInstitucionDAO() {
        setConexion(conexion);
    }

    public MySQLInstitucionDAO(Connection conexion) {
        setConexion(conexion);
    }

    public Institucion get(long id) {
        String query = "SELECT * FROM instituciones WHERE idInstitucion = ?";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            long idInstitution = set.getLong("idInstitucion");
            long idEmpresa = set.getLong("idEmpresa");
            Empresa empresa = super.get(idEmpresa);
            return new Institucion(idEmpresa, empresa.getNombre(), empresa.getPais(), empresa.getPoblacion(), empresa.getRazonSocial(),
                    empresa.getIdentificacionSocial(),  empresa.getEmail(), empresa.getTelefono(), empresa.getEmail(), idInstitution);
        } catch (Exception throwables) {
            System.out.println("Error obteniendo la instancia " + throwables);
        }
        return null;
    }


    //aunque el método devuelva List<Persona>, en esa lista puede haber cualquier clase que extienda de persona
    public List<Empresa> getAll() {

        List<Empresa> empresaList = new ArrayList<>();
        String query = "SELECT * FROM instituciones";
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


    public Institucion save(Institucion institucion) {

        String query = "INSERT INTO instituciones (idEmpresa) VALUES(?)";
        PreparedStatement nuevaEntrada;
        Empresa empresa = null;
        try {
            conexion.setAutoCommit(false);
            if (institucion.getIdEmpresa() == null)   // No facilitamos ID persona
            {
                if (checkIdentificadorSocial(institucion.getIdentificacionSocial()) == 0)  // No existe el Identificador en nuestra DDBB
                {
                    empresa = super.save(institucion);
                    System.out.println("El ID de Empresa nuevo es: " + empresa.getIdEmpresa());
                } else {
                    System.out.println("El CIF que intentas introducir ya existe.");
                }
            } else // Facilitamos un ID de Persona
            {
                empresa = super.update(institucion);
            }
            nuevaEntrada = conexion.prepareStatement(query);
            nuevaEntrada.setLong(1, empresa.getIdEmpresa());
            nuevaEntrada.executeUpdate();
            commitData(conexion);
            System.out.println("Ejecutamos Save MySQLInstitucionDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del nuevo registro " + e);
            }
            System.out.println("Error guardando el nuevo registro  (Save.Institucion)" + throwables);
        }
        return institucion;
    }


    public Institucion update(Institucion institucion) {

        String query = "UPDATE instituciones SET idEmpresa = ? WHERE idInstitucion = ?";
        PreparedStatement updateEntrada;
        try {
            conexion.setAutoCommit(false);
            if (institucion.getIdEmpresa() == null || institucion.getIdInstitucion() == null)   // No facilitamos IDs
            {
                System.out.println("No se ha indicado la entrada a modificar");
            } else // Facilitamos los IDs
            {
                Empresa empresa = super.update(institucion);
                updateEntrada = conexion.prepareStatement(query);
                updateEntrada.setLong(1, empresa.getIdEmpresa());
                updateEntrada.setLong(2, institucion.getIdInstitucion());
                updateEntrada.executeUpdate();
            }
            commitData(conexion);
            System.out.println("Ejecutamos Update MySQLInstitucionDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del update del registro (Update.Institucion) " + throwables);
            } finally {
                System.out.println("Error Actualizando el nuevo registro (Update.Institucion) " + throwables);
            }
        }
        return institucion;
    }


    public void delete(Institucion institucion) {

        boolean existe = false;
        try {
            if (institucion.getIdInstitucion() != null) { // Estamos pasando un ID
                if (get(institucion.getIdInstitucion()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = " DELETE FROM instituciones WHERE idInstitucion = ?";
                    PreparedStatement borrarEntrada;
                    borrarEntrada = conexion.prepareStatement(query);
                    borrarEntrada.setLong(1, institucion.getIdInstitucion());
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
