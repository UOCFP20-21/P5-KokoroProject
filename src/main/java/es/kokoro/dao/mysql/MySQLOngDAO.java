package es.kokoro.dao.mysql;

import es.kokoro.commons.SqlConnection;
import es.kokoro.dao.OngDAO;
import es.kokoro.model.Ong;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.SqlConnection.conectar;

public class MySQLOngDAO implements OngDAO {

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
    public Ong get(long id) throws Exception {
        try {
            conexion = SqlConnection.conectar();
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM ongs WHERE idOngs = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Long idOngs = resultSet.getLong("idOngs");
            String nombre = resultSet.getString("nombre");
            return new Ong(idOngs, nombre);
        } catch (Exception exception) {
            System.out.println("Error recuperando la ong " + exception);
        } finally {
            if (conexion != null) {
                SqlConnection.desconectar(conexion);
            }
        }
        return null;
    }

    @Override
    public List<Ong> getAll() throws Exception {

        List<Ong> ongList = new ArrayList<>();

        try {
            conexion = SqlConnection.conectar();
            PreparedStatement statement = conexion.prepareStatement("select * from ongs");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("idOngs");
                String nombre = resultSet.getString("nombre");
                Ong ong = new Ong(id, nombre);
                ongList.add(ong);
            }
        } catch (Exception exception) {
            System.out.println("Error recuperando todas las ongs " + exception);
        } finally {
            if (conexion != null) {
                SqlConnection.desconectar(conexion);
            }
        }
        return ongList;
    }

    @Override
    public Ong save(Ong ong) throws Exception {

        try {
            conexion = SqlConnection.conectar();
            PreparedStatement statement = conexion.prepareStatement("INSERT INTO ongs (nombre) VALUES (?)");

            statement.setString(1, ong.getNombreOng());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            ong.setIdOng(resultSet.getLong(1));

            System.out.println(("Se ejecuta 'save' en SqlOngDAO"));

            return ong;

        } catch (SQLException throwables) {
            System.out.println("Error guardando ong " + throwables);
        } finally {
            if (conexion != null) {
                SqlConnection.desconectar(conexion);
            }
        }
        return ong;
    }

    @Override
    public Ong update(Ong ong) throws Exception {

        try {
            conexion = SqlConnection.conectar();
            PreparedStatement statement = conexion.prepareStatement("UPDATE ongs SET nombre = ? WHERE idOngs = ? ");
            if (ong.getIdOng() != null && get(ong.getIdOng()) != null) {
                statement.setString(1, ong.getNombreOng());
                statement.setLong(2, ong.getIdOng());
                statement.executeUpdate();
            } else {
                return save(ong);
            }
        } catch (Exception exception) {
            System.out.println("Error recuperando la ong " + exception);
        } finally {
            if (conexion != null) {
                SqlConnection.desconectar(conexion);
            }
        }
        return ong;
    }

    @Override
    public void delete(Ong ong) throws Exception {
        try {
            boolean isExist = false;
            conexion = SqlConnection.conectar();
            PreparedStatement statement = conexion.prepareStatement("DELETE FROM Ongs WHERE idOngs = ?");

            if (ong.getIdOng() != null) {
                if (get(ong.getIdOng()) != null) {
                    statement.setLong(1, ong.getIdOng());
                    statement.executeUpdate();
                    isExist = true;
                }
            }
            if (!isExist) {
                System.out.println("Ong no encontrada");
            }
        } catch (Exception exception) {
            System.out.println("Error recuperando la ong " + exception);
        } finally {
            if (conexion != null) {
                SqlConnection.desconectar(conexion);
            }
        }
    }
}
