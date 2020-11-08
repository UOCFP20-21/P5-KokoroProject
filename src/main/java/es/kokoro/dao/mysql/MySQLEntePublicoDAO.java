package es.kokoro.dao.mysql;

import es.kokoro.commons.SqlConnection;
import es.kokoro.dao.EntePublicoDAO;
import es.kokoro.model.EntePublico;

import java.sql.*;
import java.util.List;

import static es.kokoro.commons.SqlConnection.conectar;

public class MySQLEntePublicoDAO implements EntePublicoDAO {

    protected Connection conexion = null;

    private long id;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection cnn) {
        if (cnn == null) {
            try {
                this.conexion = conectar();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            this.conexion = cnn;
        }
    }

    @Override
    public EntePublico get(long id) throws Exception {
        try {
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM entesPublicos WHERE idEntePublico = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Long idEntePublico = resultSet.getLong("idEntePublico");
            String nombre = resultSet.getString("nombre");
            String subvencion = resultSet.getString("subvencion");
            return new EntePublico(idEntePublico, nombre, subvencion);
        } catch (Exception exception) {
            System.out.println("Error recuperando el ente Publico " + exception);
        }
        return null;
    }

    @Override
    public List<EntePublico> getAll() throws Exception {
        throw new UnsupportedOperationException("No puedes recuperar un listado de entes Publicos. Tiene que ser una de sus implementaciones");
    }

    public EntePublico save(EntePublico entePublico) {

        String queryEntePublico = "INSERT INTO entesPublicos(nombre,subvencion) VALUES(?,?)";
        PreparedStatement nuevaEntrada;
        try {
            nuevaEntrada = conexion.prepareStatement(queryEntePublico, Statement.RETURN_GENERATED_KEYS);
            nuevaEntrada.setString(1, entePublico.getNombre());
            nuevaEntrada.setString(2, entePublico.getSubvencion());
            nuevaEntrada.executeUpdate();
            if (entePublico.getIdEntePublico() == null) {
                ResultSet resultSet = nuevaEntrada.getGeneratedKeys();
                resultSet.next();
                entePublico.setIdEntePublico(resultSet.getLong(1));
            }
            System.out.println("Ejecutamos Save MySQLEntePublicoDAO");
        } catch (SQLException throwables) {
            System.out.println("Error guardando el nuevo registro (Save.EntePublico)" + throwables);
        }
        return entePublico;
    }

    public EntePublico update(EntePublico entePublico) {

        String queryEntePublico = "UPDATE entesPublicos SET nombre = ?, subvencion = ? WHERE idEntePublico = ?";
        PreparedStatement updateEntePublico;
        try {
            if (get(entePublico.getIdEntePublico()) != null) {
                long idEntePublico = entePublico.getIdEntePublico();
                updateEntePublico = conexion.prepareStatement(queryEntePublico, Statement.RETURN_GENERATED_KEYS);
                updateEntePublico.setString(1, entePublico.getNombre());
                updateEntePublico.setString(2, entePublico.getSubvencion());
                updateEntePublico.setLong(9, idEntePublico);
                updateEntePublico.executeUpdate();
                System.out.println("Ejecutamos update MySQLEntePublicoDAO");
            } else {
                return save(entePublico);
            }
        } catch (Exception throwables) {
            System.out.println("Error guardando el nuevo registro (Update.EntePublico)" + throwables);
        }
        return entePublico;
    }

    @Override
    public void delete(EntePublico entePublico) throws Exception {
        try {
            boolean isExist = false;
            conexion = SqlConnection.conectar();
            PreparedStatement statement = conexion.prepareStatement("DELETE FROM entesPublicos WHERE idEntePublico = ?");

            if (entePublico.getIdEntePublico() != null) {
                if (get(entePublico.getIdEntePublico()) != null) {
                    statement.setLong(1, entePublico.getIdEntePublico());
                    statement.executeUpdate();
                    isExist = true;
                }
            }
            if (!isExist) {
                System.out.println("EntePublico no encontrado");
            }
        } catch (Exception exception) {
            System.out.println("Error recuperando el ente Publico " + exception);
        } finally {
            if (conexion != null) {
                SqlConnection.desconectar(conexion);
            }
        }
    }

}
