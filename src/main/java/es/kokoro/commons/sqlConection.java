package es.kokoro.commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class sqlConection {
    private static Connection conexion;
    private static String mysqlUrl = "jdbc:mysql://88.24.90.50:3307/mydb";
    private static String usuario = "ongjaume";
    private static String password = "ong1234";

    public static Connection conectar() throws SQLException
    {
        try {
            conexion = DriverManager.getConnection(mysqlUrl, usuario, password);
            System.out.println("conexión establecida");
        }catch (SQLException sql) {
            sql.printStackTrace();
        }
        return conexion;
    }

    public static Connection conectar(String usuario, String password) throws SQLException
    {
        try {
            conexion = DriverManager.getConnection(mysqlUrl, usuario, password);
        }catch (SQLException sql) {
            sql.printStackTrace();
        }
        return conexion;
    }
    public static Connection conectar(String sqldata, String usuario, String password) throws SQLException
    {
        try {
            conexion = DriverManager.getConnection(sqldata, usuario, password);
        }catch (SQLException sql) {
            sql.printStackTrace();
        }
        return conexion;
    }
    public static void desconectar(Connection conexion) throws SQLException
    {
        conexion.close();
        System.out.println("desconectado");
    }

    public static void commitData(Connection conexion)
    {
        try {
            conexion.commit();
        } catch (SQLException throwables) {
            System.out.println("Error realizando Commit del nuevo registro " + throwables);
        }
    }
}
