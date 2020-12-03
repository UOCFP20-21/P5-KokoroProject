package es.kokoro.dao.mysql;

import es.kokoro.dao.OngDAO;
import es.kokoro.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.sqlConection.conectar;

public class MySQLOngDAO implements OngDAO {

    private Connection conexion = null;
    public MySQLOngDAO() {
        setConexion(conexion);
    }
    public MySQLOngDAO(Connection conexion) {
        setConexion(conexion);
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection cnn)
    {
        if(cnn == null){
            try {
                this.conexion = conectar();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else
        {
            this.conexion = cnn;
        }
    }

    private Ong setObject(ResultSet set)
    {
        Ong tmpEntrada = null;/*
        try {
            long idOng = set.getLong("idOng");
            String nombre = set.getString("nombre");
            MySQLDelegacionDAO mySQLDelegacionDAO = new MySQLDelegacionDAO(conexion);
            MySQLIngresoDAO mySQLIngresoDAO = new MySQLIngresoDAO(conexion);
            MySQLSocioDAO mySQLSocioDAO = new MySQLSocioDAO(conexion);
            MySQLProyectoDAO mySQLProyectoDAO = new MySQLProyectoDAO(conexion);
            tmpEntrada = new Ong(idOng, nombre, mySQLDelegacionDAO.getAll(), mySQLIngresoDAO.getAll(), mySQLSocioDAO.getAll(), mySQLProyectoDAO.getAll());
        } catch (SQLException throwables) {
            System.out.println("Error creando la instancia " + throwables);
        } finally {
            return tmpEntrada;
        }*/
        return tmpEntrada;
    }


    @Override
    public Ong get(long id) {
        String query = "SELECT * FROM ongs WHERE idOng = ?";
        PreparedStatement statement;
        Ong tmpEntrada = null;
        try {
            statement = conexion.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            tmpEntrada = setObject(set);

        } catch (SQLException throwables) {
            System.out.println("Error obteniendo la instancia " + throwables);
        } finally {
            return tmpEntrada;
        }
    }

    @Override
    public List<Ong> getAll() {

        List<Ong> entradasList = new ArrayList<>();
        String query = "SELECT * FROM ongs";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next())
            {
                Ong tmpEntrada = setObject(set);
                entradasList.add(tmpEntrada);
            }
        } catch (SQLException throwables) {
            System.out.println("Error obteniendo el Result Set " + throwables);
        } finally {
            return entradasList;
        }
    }

    public long saveLong(Ong ong) {
        Long idOng = null;
        String query = "INSERT INTO ongs(nombre) VALUES(?)";
        PreparedStatement nuevaEntrada;
        try {
            nuevaEntrada = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            nuevaEntrada.setString(1,ong.getNombreOng());
            nuevaEntrada.executeUpdate();
            ResultSet resultSet = nuevaEntrada.getGeneratedKeys();
            resultSet.next();
            idOng = resultSet.getLong(1);
            System.out.println("Ejecutamos Save MySQLOngDAO");
        } catch (SQLException throwables) {
            System.out.println("Error guardando el nuevo registro " + throwables);
        }
        return idOng;
    }
    @Override
    public void save(Ong ong) {

        String query = "INSERT INTO ongs(nombre) VALUES(?)";
        PreparedStatement nuevaEntrada;
        try {
            nuevaEntrada = conexion.prepareStatement(query);
            nuevaEntrada.setString(1,ong.getNombreOng());
            nuevaEntrada.executeUpdate();
            System.out.println("Ejecutamos Save MySQLOngDAO");
        } catch (SQLException throwables) {
            System.out.println("Error guardando el nuevo registro " + throwables);
        }
    }

    @Override
    public void update(Ong ong) {

        boolean isUpdate = false;
        try {
            if(ong.getIdOng() != null ) { // Estamos pasando un ID

                if(get(ong.getIdOng()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = "UPDATE ongs SET nombre = ? WHERE idOng = ?";
                    PreparedStatement updateEntrada = null;

                    updateEntrada = conexion.prepareStatement(query);
                    updateEntrada.setString(1,ong.getNombreOng());
                    updateEntrada.setLong(2,ong.getIdOng());
                    updateEntrada.executeUpdate();
                    isUpdate = true;
                }
            }
        } catch (SQLException throwables) {
            System.out.println("Error actualizando el nuevo registro " + throwables);
        }finally {
            if(!isUpdate)   // Si no existe en nuestra DDBB o no facilitan un ID guardamos en lugar de actualizar
            {
                save(ong);
                System.out.println("El registro que se quería actualizar no exitste: Se ha guardado como nuevo registro");
            }
        }

    }

    @Override
    public void delete(Ong ong) {

        boolean existe = false;
        try {
            if(ong.getIdOng() != null ) { // Estamos pasando un ID

                if(get(ong.getIdOng()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = " DELETE FROM ongs WHERE idOng = ?";
                    PreparedStatement borrarEntrada = null;

                    borrarEntrada = conexion.prepareStatement(query);
                    borrarEntrada.setLong(1,ong.getIdOng());
                    borrarEntrada.executeUpdate();
                    existe = true;
                }
            }
        } catch (SQLException throwables) {
            System.out.println("Error Eliminando el registro " + throwables);
        }finally
        {
            if(!existe)   // Si no existe en nuestra DDBB o no facilitan un ID guardamos en lugar de actualizar
            {
                System.out.println("Registro no encontrado: No existe ningún registro con los datos facilitados.");
            }
        }
    }
}
