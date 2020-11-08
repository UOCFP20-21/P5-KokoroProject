package es.kokoro.dao.mysql;

import es.kokoro.commons.SqlConnection;
import es.kokoro.dao.IngresoDAO;
import es.kokoro.model.*;
import es.kokoro.model.interfaces.IIngreso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static es.kokoro.commons.SqlConnection.commitData;
import static es.kokoro.commons.SqlConnection.conectar;

public class MySQLIngresoDAO implements IngresoDAO {
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
    private MySQLOngDAO mySQLOngDAO;
/*
    public MySQLIngresoDAO(MySQLOngDAO mySQLOngDAO) {
        this.mySQLOngDAO = mySQLOngDAO;
        setConexion(conexion);
    }

    public MySQLIngresoDAO(Connection conexion, MySQLOngDAO mySQLOngDAO) {

        this.mySQLOngDAO = mySQLOngDAO;
        setConexion(conexion);
    }*/

    public Ingreso get(long id) {

        String query = "SELECT * FROM Ingresos WHERE idIngreso = ?";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            long idIngreso = set.getLong("idIngreso");
            Object ingreso = set.getObject("ingreso");
            Double importe = set.getDouble("importe");
            Date fecha = set.getDate("fecha");
          //  long idOng = set.getLong("idOng");
          //  Ong ong = mySQLOngDAO.get(idOng);
          //TODO en sql tenemos idOng metido.faltaria poner
            return new Ingreso(idIngreso, (IIngreso) ingreso, importe, fecha);
        } catch (Exception throwables) {
            System.out.println("Error obteniendo la instancia " + throwables);
        }
        return null;
    }

    @Override
    public List<Ingreso> getAll() {

        List<Ingreso> ingresoList = new ArrayList<>();
        String query = "SELECT * FROM Ingresos";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                long idIngreso = set.getLong("idIngreso");
                double importe = set.getDouble("importe");
                Date fecha = set.getDate("fecha");
                Object iIngreso = set.getObject("ingreso");

                Ingreso ingreso = new Ingreso(idIngreso, (IIngreso) iIngreso, importe, fecha);
                ingresoList.add(ingreso);
            }
        } catch (Exception throwables) {
            System.out.println("Error obteniendo el listado de instancias  " + throwables);
        }
        return ingresoList;
    }

@Override
    public Ingreso save(Ingreso ingreso) throws SQLException {

        try {
            conexion = SqlConnection.conectar();
            PreparedStatement statement = conexion.prepareStatement("INSERT INTO ongs (importe, fecha,ingreso) VALUES (?,?,?)");

            statement.setDate(1, (java.sql.Date) ingreso.getFecha());
            statement.setDouble(2, ingreso.getImporte());
            statement.setObject(3, ingreso.getIngreso());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            ingreso.setIdIngreso(resultSet.getLong(1));

            System.out.println(("Se ejecuta 'save' en SqlIngresoDAO"));

            return ingreso;

        } catch (SQLException throwables) {
            System.out.println("Error guardando ingreso " + throwables);
        } finally {
            if (conexion != null) {
                SqlConnection.desconectar(conexion);
            }
        }
        return ingreso;
    }

    public Ingreso update(Ingreso ingreso) throws SQLException {

        try {
            conexion = SqlConnection.conectar();
            PreparedStatement statement = conexion.prepareStatement("UPDATE ingresos SET importe = ?, fecha=?, ingreso =? WHERE idIngreso = ? ");
            if (ingreso.getIdIngreso() != null && get(ingreso.getIdIngreso()) != null) {
                statement.setDouble(1, ingreso.getImporte());
                statement.setDate(2, (java.sql.Date) ingreso.getFecha());
                statement.setObject(3, ingreso.getIngreso());
                statement.executeUpdate();
            } else {
                return save(ingreso);
            }
        } catch (Exception exception) {
            System.out.println("Error recuperando ingresog " + exception);
        } finally {
            if (conexion != null) {
                SqlConnection.desconectar(conexion);
            }
        }
        return ingreso;
    }

    @Override
    public void delete(Ingreso ingreso) throws Exception {
        try {
            boolean isExist = false;
            conexion = SqlConnection.conectar();
            PreparedStatement statement = conexion.prepareStatement("DELETE FROM ingresos WHERE idIngreso = ?");

            if (ingreso.getIdIngreso() != null) {
                if (get(ingreso.getIdIngreso()) != null) {
                    statement.setLong(1, ingreso.getIdIngreso());
                    statement.executeUpdate();
                    isExist = true;
                }
            }
            if (!isExist) {
                System.out.println("Ingreso no encontrado");
            }
        } catch (Exception exception) {
            System.out.println("Error recuperando ingreso " + exception);
        } finally {
            if (conexion != null) {
                SqlConnection.desconectar(conexion);
            }
        }
    }
}