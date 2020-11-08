package es.kokoro.dao.mysql;

import es.kokoro.dao.DelegacionDAO;
import es.kokoro.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.SqlConnection.commitData;
import static es.kokoro.commons.SqlConnection.conectar;

public class MySQLDelegacionDAO extends MySQLEmpresaDAO {

    public MySQLDelegacionDAO() {
        setConexion(conexion);
    }

    public MySQLDelegacionDAO(Connection conexion) {
        setConexion(conexion);
    }

    public Delegacion get(long id) {

        String query = "SELECT * FROM delegaciones WHERE idDelegacion = ?";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
//TODO Añadir ONg y trabajador LIst
            //TODO lo mismo que con sede, porque tenemos el campo en workbench, sin clave foranea?, porque no hay delegacion
            long idDelegacion = set.getLong("idDelegacion");
            long idEmpresa = set.getLong("idEmpresa");
            String areaOperativa = set.getString("areaOperativa");
            Empresa empresa = super.get(idEmpresa);
            return new Delegacion(idEmpresa, empresa.getNombre(), empresa.getPais(), empresa.getPoblacion(), empresa.getRazonSocial(),
                    empresa.getIdentificacionSocial(), empresa.getEmail(), empresa.getTelefono(), empresa.getEmail(), idDelegacion, null,
                    null, areaOperativa);
        } catch (Exception throwables) {
            System.out.println("Error obteniendo la instancia " + throwables);
        }
        return null;
    }

    public List<Empresa> getAll() {

        List<Empresa> empresaList = new ArrayList<>();
        String query = "SELECT * FROM delegaciones";
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

    public Delegacion save(Delegacion delegacion) {

        String query = "INSERT INTO delegaciones (idEmpresa, areaOperativa) VALUES(?,?)";
        PreparedStatement nuevaEntrada;
        Empresa empresa = null;
        try {
            conexion.setAutoCommit(false);
            if (delegacion.getIdEmpresa() == null)   // No facilitamos ID empresa
            {
                if (checkIdentificadorSocial(delegacion.getIdentificacionSocial()) == 0)  // No existe el Identificador en nuestra DDBB
                {
                    empresa = super.save(delegacion);
                    System.out.println("El ID de Empresa nuevo es: " + empresa.getIdEmpresa());
                } else {
                    System.out.println("El CIF que intentas introducir ya existe.");
                }
            } else // Facilitamos un ID de Persona
            {
                empresa = super.update(delegacion);
            }
            nuevaEntrada = conexion.prepareStatement(query);
            nuevaEntrada.setLong(1, empresa.getIdEmpresa());
            nuevaEntrada.executeUpdate();
            commitData(conexion);
            System.out.println("Ejecutamos Save MySQLDelegacionDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del nuevo registro " + e);
            }
            System.out.println("Error guardando el nuevo registro  (Save.Delegacion)" + throwables);
        }
        return delegacion;
    }

    public Delegacion update(Delegacion delegacion) {

        String query = "UPDATE delegaciones SET idEmpresa = ?, areaOperativa = ? WHERE idDelegacion = ?";
        PreparedStatement updateEntrada;
        try {
            conexion.setAutoCommit(false);
            if (delegacion.getIdEmpresa() == null || delegacion.getIdDelegacion() == null)   // No facilitamos IDs
            {
                System.out.println("No se ha indicado la entrada a modificar");
            } else // Facilitamos los IDs
            {
                Empresa empresa = super.update(delegacion);
                updateEntrada = conexion.prepareStatement(query);
                updateEntrada.setLong(1, empresa.getIdEmpresa());
                updateEntrada.setLong(2, delegacion.getIdDelegacion());
                updateEntrada.executeUpdate();
            }
            commitData(conexion);
            System.out.println("Ejecutamos Update MySQLDelegacionDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del update del registro (Update.Delegacion) " + throwables);
            } finally {
                System.out.println("Error Actualizando el nuevo registro (Update.Delegacion) " + throwables);
            }
        }
        return delegacion;
    }

    public void delete(Delegacion delegacion) {

        boolean existe = false;
        try {
            if (delegacion.getIdDelegacion() != null) { // Estamos pasando un ID
                if (get(delegacion.getIdDelegacion()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = " DELETE FROM delegaciones WHERE idDelegacion = ?";
                    PreparedStatement borrarEntrada;
                    borrarEntrada = conexion.prepareStatement(query);
                    borrarEntrada.setLong(1, delegacion.getIdDelegacion());
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