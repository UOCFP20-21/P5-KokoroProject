package es.kokoro.dao.mysql;

import es.kokoro.commons.SqlConnection;
import es.kokoro.dao.FinanciadorDAO;
import es.kokoro.model.*;
import es.kokoro.model.interfaces.IFinanciador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static es.kokoro.commons.SqlConnection.commitData;
import static es.kokoro.commons.SqlConnection.conectar;

public class MySQLFinanciadorDAO implements FinanciadorDAO {
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
    private MySQLProyectoDAO mySQLProyectoDAO;
/*
    public MySQLFinanciadorDAO(MySQLProyectoDAO mySQLProyectoDAO) {
        this.mySQLProyectoDAO = mySQLProyectoDAO;
        setConexion(conexion);
    }

    public MySQLFinanciadorDAO(Connection conexion, MySQLProyectoDAO mySQLProyectoDAO) {

        this.mySQLProyectoDAO = mySQLProyectoDAO;
        setConexion(conexion);
    }*/

    public Financiador get(long id) {

        String query = "SELECT * FROM financiadores WHERE idFinanciador = ?";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            long idFinanciador = set.getLong("idFinanciador");
            Double financiacionAportada = set.getDouble("financiacionAportada");
            String moneda  = set.getString("moneda");
            Object financiador = set.getObject("financiador");

            //  long idProyecto = set.getLong("idProyecto");
            //  Proyecto proyecto = mySQLproyectoDAO.get(idProyecto);
            //TODO en sql tenemos proyecto metido.faltaria poner
            return new Financiador(idFinanciador, financiador, financiacionAportada, moneda);
        } catch (Exception throwables) {
            System.out.println("Error obteniendo la instancia " + throwables);
        }
        return null;
    }

    @Override
    public List<Financiador> getAll() {

        List<Financiador> financiadorList = new ArrayList<>();
        String query = "SELECT * FROM financiadores";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                long idFinanciador = set.getLong("idFinanciador");
                double financiacionAportada = set.getDouble("financiacionAportada");
                String  moneda = set.getString("moneda");
                Object iFinanciador = set.getObject("financiador");

                Financiador financiador = new Financiador(idFinanciador, (IFinanciador) iFinanciador, financiacionAportada, moneda);
                financiadorList.add(financiador);
            }
        } catch (Exception throwables) {
            System.out.println("Error obteniendo el listado de instancias  " + throwables);
        }
        return financiadorList;
    }

    @Override
    public Financiador save(Financiador financiador) throws SQLException {

        try {
            conexion = SqlConnection.conectar();
            PreparedStatement statement = conexion.prepareStatement("INSERT INTO financiadores (financiacionAportada, moneda,Financiador) VALUES (?,?,?)");

            statement.setDouble(1, financiador.getFinanciacionAportada());
            statement.setString(2, financiador.getMoneda());
            statement.setObject(3, financiador.getTipoFinanciador());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            financiador.setIdFinanciador(resultSet.getLong(1));

            System.out.println(("Se ejecuta 'save' en SqlFinanciadorDAO"));

            return financiador;

        } catch (SQLException throwables) {
            System.out.println("Error guardando Financiador " + throwables);
        } finally {
            if (conexion != null) {
                SqlConnection.desconectar(conexion);
            }
        }
        return financiador;
    }

    public Financiador update(Financiador financiador) throws SQLException {

        try {
            conexion = SqlConnection.conectar();
            PreparedStatement statement = conexion.prepareStatement("UPDATE financiadores SET financiacionAportada = ?, moneda=?, Financiador =? WHERE idFinanciador = ? ");
            if (financiador.getIdFinanciador() != null && get(financiador.getIdFinanciador()) != null) {
                statement.setDouble(1, financiador.getFinanciacionAportada());
                statement.setString(2, financiador.getMoneda());
                statement.setObject(3, financiador.getTipoFinanciador());
                statement.executeUpdate();
            } else {
                return save(financiador);
            }
        } catch (Exception exception) {
            System.out.println("Error recuperando Financiador " + exception);
        } finally {
            if (conexion != null) {
                SqlConnection.desconectar(conexion);
            }
        }
        return financiador;
    }

    @Override
    public void delete(Financiador financiador) throws Exception {
        try {
            boolean isExist = false;
            conexion = SqlConnection.conectar();
            PreparedStatement statement = conexion.prepareStatement("DELETE FROM financiadores WHERE idFinanciador = ?");

            if (financiador.getIdFinanciador() != null) {
                if (get(financiador.getIdFinanciador()) != null) {
                    statement.setLong(1, financiador.getIdFinanciador());
                    statement.executeUpdate();
                    isExist = true;
                }
            }
            if (!isExist) {
                System.out.println("Financiador no encontrado");
            }
        } catch (Exception exception) {
            System.out.println("Error recuperando Financiador " + exception);
        } finally {
            if (conexion != null) {
                SqlConnection.desconectar(conexion);
            }
        }
    }
}