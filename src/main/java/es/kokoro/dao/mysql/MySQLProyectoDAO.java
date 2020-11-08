package es.kokoro.dao.mysql;

import es.kokoro.commons.SqlConnection;
import es.kokoro.dao.ProyectoDAO;
import es.kokoro.model.LineaAccion;
import es.kokoro.model.Proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static es.kokoro.commons.SqlConnection.conectar;

public class MySQLProyectoDAO implements ProyectoDAO {

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
    public Proyecto get(long id) throws Exception {
        try {
            conexion = SqlConnection.conectar();
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM proyectos WHERE idProyecto = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Long idProyecto = resultSet.getLong("idProyecto");
            String codigoProyecto = resultSet.getString("codigoProyecto");
            String nombreProyecto = resultSet.getString("nombreProyecto");
            String pais = resultSet.getString("pais");
            String localizacion = resultSet.getString("localizacion");
            Date fechaInicio = resultSet.getDate("fechaInicio");
            Date fechaFin = resultSet.getDate("fechaFin");
            //TODO recuperar varias clases
            return new Proyecto(idProyecto, codigoProyecto,nombreProyecto,pais,localizacion,null,null,
            null, null, fechaInicio,fechaFin,null, null);
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
    public List<Proyecto> getAll() throws Exception {

        List<Proyecto> proyectoList = new ArrayList<>();

        try {
            conexion = SqlConnection.conectar();
            PreparedStatement statement = conexion.prepareStatement("select * from proyectos");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("idProyecto");
                String codigoProyecto = resultSet.getString("codigoProyecto");
                String nombreProyecto = resultSet.getString("nombreProyecto");
                String pais = resultSet.getString("pais");
                String localizacion = resultSet.getString("localizacion");
                Date fechaInicio = resultSet.getDate("fechaInicio");
                Date fechaFin = resultSet.getDate("fechaFin");
                Proyecto proyecto = new Proyecto(id, codigoProyecto, nombreProyecto, pais,
                localizacion, null, null, null, null, fechaInicio, fechaFin, null
                , null);
                proyectoList.add(proyecto);
            }
        } catch (Exception exception) {
            System.out.println("Error recuperando todas los proyectos " + exception);
        } finally {
            if (conexion != null) {
                SqlConnection.desconectar(conexion);
            }
        }
        return proyectoList;
    }

    @Override
    public Proyecto save(Proyecto proyecto) throws Exception {

        try {
            conexion = SqlConnection.conectar();
            PreparedStatement statement = conexion.prepareStatement("INSERT INTO proyectos (codigoProyecto,nombreProyecto,pais,localizacion,fechaInicio,fechaFin) VALUES (?,?,?,?,?,?)");

            statement.setString(1, proyecto.getNombreProyecto());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            proyecto.setIdProyecto(resultSet.getLong(1));

            System.out.println(("Se ejecuta 'save' en SqlProyectoDAO"));

            return proyecto;

        } catch (SQLException throwables) {
            System.out.println("Error guardando proyecto" + throwables);
        } finally {
            if (conexion != null) {
                SqlConnection.desconectar(conexion);
            }
        }
        return proyecto;
    }

    @Override
    public Proyecto update(Proyecto proyecto) throws Exception {

        try {
            conexion = SqlConnection.conectar();
            PreparedStatement statement = conexion.prepareStatement("UPDATE proyectos SET codigoProyecto = ?, nombreProyecto = ?, pais = ?, localizacion = ?, fechaInicio = ?, fechaFin = ? WHERE idProyecto = ? ");
            if (proyecto.getIdProyecto() != null && get(proyecto.getIdProyecto()) != null) {
                statement.setString(1, proyecto.getNombreProyecto());
                statement.setLong(2, proyecto.getIdProyecto());
                statement.executeUpdate();
            } else {
                return save(proyecto);
            }
        } catch (Exception exception) {
            System.out.println("Error recuperando el proyecto " + exception);
        } finally {
            if (conexion != null) {
                SqlConnection.desconectar(conexion);
            }
        }
        return proyecto;
    }

    @Override
    public void delete(Proyecto proyecto) throws Exception {
        try {
            boolean isExist = false;
            conexion = SqlConnection.conectar();
            PreparedStatement statement = conexion.prepareStatement("DELETE FROM proyectos WHERE idProyecto = ?");

            if (proyecto.getIdProyecto() != null) {
                if (get(proyecto.getIdProyecto()) != null) {
                    statement.setLong(1, proyecto.getIdProyecto());
                    statement.executeUpdate();
                    isExist = true;
                }
            }
            if (!isExist) {
                System.out.println("Proyecto no encontrado");
            }
        } catch (Exception exception) {
            System.out.println("Error recuperando el proyecto " + exception);
        } finally {
            if (conexion != null) {
                SqlConnection.desconectar(conexion);
            }
        }
    }
}
