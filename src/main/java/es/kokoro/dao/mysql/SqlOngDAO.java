package es.kokoro.dao.mysql;

import es.kokoro.dao.OngDAO;
import es.kokoro.model.Ong;

import es.kokoro.commons.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SqlOngDAO implements OngDAO {
    private Connection connection = null;

    @Override
    public Ong get(long id) throws Exception {
        try {
            connection = SqlConnection.conectar();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ongs WHERE idOngs = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Long idOngs = resultSet.getLong("idOngs");
            String nombre = resultSet.getString("nombre");
            return new Ong(idOngs, nombre);


        } catch (Exception exception) {
            System.out.println("Error recuperando la ong " + exception);
        } finally {
            if (connection != null) {
                SqlConnection.desconectar(connection);
            }
        }
        return null;
    }

    @Override
    public List<Ong> getAll() throws Exception {

        List<Ong> ongList = new ArrayList<>();

        try {
            connection = SqlConnection.conectar();
            PreparedStatement statement = connection.prepareStatement("select * from ongs");
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
            if (connection != null) {
                SqlConnection.desconectar(connection);
            }
        }

        return ongList;
    }

    @Override
    public void save(Ong ong) throws Exception {

        try {
            connection = SqlConnection.conectar();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ongs (nombre) VALUES (?)");

            statement.setString(1, ong.getNombreOng());
            statement.executeUpdate();
            System.out.println(("Se ejecuta 'save' en SqlOngDAO"));


        } catch (SQLException throwables) {
            System.out.println("Error guardando ong " + throwables);
        } finally {
            if (connection != null) {
                SqlConnection.desconectar(connection);
            }
        }

    }

    @Override
    public void update(Ong ong) throws Exception {


        try {
            boolean isUpdate = false;
            connection = SqlConnection.conectar();
            PreparedStatement statement = connection.prepareStatement("UPDATE ongs SET nombre = ? WHERE idOngs = ? ");

            if (ong.getIdOng() != null) {
                if (get(ong.getIdOng()) != null) {

                    statement.setString(1, ong.getNombreOng());
                    statement.setLong(2, ong.getIdOng());
                    statement.executeUpdate();
                    isUpdate = true;

                }
            }
            if (!isUpdate) {
                save(ong);
            }

        } catch (Exception exception) {
            System.out.println("Error recuperando la ong " + exception);
        } finally {
            if (connection != null) {
                SqlConnection.desconectar(connection);
            }
        }

    }

    @Override
    public void delete(Ong ong) throws Exception {
        try {
            boolean isExist = false;
            connection = SqlConnection.conectar();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Ongs WHERE idOngs = ?");

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
            if (connection != null) {
                SqlConnection.desconectar(connection);
            }
        }
    }

}
