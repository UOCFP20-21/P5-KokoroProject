package es.kokoro.dao.mysql;

import es.kokoro.commons.SqlConnection;
import es.kokoro.dao.ExtraordinarioDAO;
import es.kokoro.model.Empresa;
import es.kokoro.model.Extraordinario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.SqlConnection.commitData;

public class MySQLExtraordinarioDAO extends MySQLEmpresaDAO {

    public MySQLExtraordinarioDAO() {
        setConexion(conexion);
    }

    public MySQLExtraordinarioDAO(Connection conexion) {
        setConexion(conexion);
    }

    public Extraordinario get(long id) {
        String query = "SELECT * FROM extraordinarios WHERE idExtraordinario = ?";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            long idExtraordinario = set.getLong("idExtraordinario");
            long idEmpresa = set.getLong("idEmpresa");
            String concepto = set.getString("concepto");
            Empresa empresa = super.get(idEmpresa);
            return new Extraordinario(idEmpresa, empresa.getNombre(), empresa.getPais(), empresa.getPoblacion(), empresa.getRazonSocial(),
                    empresa.getIdentificacionSocial(), empresa.getEmail(), empresa.getTelefono(), empresa.getEmail(), idExtraordinario, concepto);
        } catch (Exception throwables) {
            System.out.println("Error obteniendo la instancia " + throwables);
        }
        return null;
    }

    //aunque el método devuelva List<Empresa>, en esa lista puede haber cualquier clase que extienda de empresa
    public List<Empresa> getAll() {

        List<Empresa> empresaList = new ArrayList<>();
        String query = "SELECT * FROM extraordinarios";
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

    public Extraordinario save(Extraordinario extraordinario) {

        String query = "INSERT INTO extraordinarios (idEmpresa) VALUES(?)";
        PreparedStatement nuevaEntrada;
        Empresa empresa = null;
        try {
            conexion.setAutoCommit(false);
            if (extraordinario.getIdEmpresa() == null)   // No facilitamos ID extraordinario
            {
                if (checkIdentificadorSocial(extraordinario.getIdentificacionSocial()) == 0)  // No existe el Identificador en nuestra DDBB
                {
                    empresa = super.save(extraordinario);
                    System.out.println("El ID de Empresa nuevo es: " + empresa.getIdEmpresa());
                } else {
                    System.out.println("El CIF que intentas introducir ya existe.");
                }
            } else // Facilitamos un ID de extraordinario
            {
                empresa = super.update(extraordinario);
            }
            nuevaEntrada = conexion.prepareStatement(query);
            nuevaEntrada.setLong(1, empresa.getIdEmpresa());
            nuevaEntrada.executeUpdate();
            commitData(conexion);
            System.out.println("Ejecutamos Save MySQLExtraordinarioDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del nuevo registro " + e);
            }
            System.out.println("Error guardando el nuevo registro  (Save.Extraordinario)" + throwables);
        }
        return extraordinario;
    }

    public Extraordinario update(Extraordinario extraordinario) {

        String query = "UPDATE extraordinarios SET idEmpresa = ?, concepto = ?, WHERE idExtraordinario = ?";
        PreparedStatement updateEntrada;
        try {
            conexion.setAutoCommit(false);
            if (extraordinario.getIdEmpresa() == null || extraordinario.getIdExtraordinario() == null)   // No facilitamos IDs
            {
                System.out.println("No se ha indicado la entrada a modificar");
            } else // Facilitamos los IDs
            {
                Empresa empresa = super.update(extraordinario);
                updateEntrada = conexion.prepareStatement(query);
                updateEntrada.setLong(1, empresa.getIdEmpresa());
                updateEntrada.setLong(2, extraordinario.getIdExtraordinario());
                updateEntrada.executeUpdate();
            }
            commitData(conexion);
            System.out.println("Ejecutamos Update MySQLExtraordinarioDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del update del registro (Update.Extraordinario) " + throwables);
            } finally {
                System.out.println("Error Actualizando el nuevo registro (Update.Extraordinario) " + throwables);
            }
        }
        return extraordinario;
    }

    public void delete(Extraordinario extraordinario) {

        boolean existe = false;
        try {
            if (extraordinario.getIdExtraordinario() != null) { // Estamos pasando un ID

                if (get(extraordinario.getIdExtraordinario()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = " DELETE FROM extraordinarios WHERE idExtraordinario = ?";
                    PreparedStatement borrarEntrada;

                    borrarEntrada = conexion.prepareStatement(query);
                    borrarEntrada.setLong(1, extraordinario.getIdExtraordinario());
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

