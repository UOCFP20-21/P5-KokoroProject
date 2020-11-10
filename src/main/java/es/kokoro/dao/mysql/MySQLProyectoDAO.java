package es.kokoro.dao.mysql;

import es.kokoro.dao.ProyectoDAO;
import es.kokoro.model.Proyecto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.sqlConection.conectar;
//TODO Se ha creado la clase solo para poder obtener el listado de Proyectos vacío en OngDAO
public class MySQLProyectoDAO implements ProyectoDAO {

    protected Connection conexion = null;

    public MySQLProyectoDAO() {
        setConexion(conexion);
    }
    public MySQLProyectoDAO(Connection conexion) {
        setConexion(conexion);
    }

    public Connection getConexion() { return conexion; }

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

    @Override
    public Proyecto get(long id) throws Exception {
        return null;
    }

    @Override
    public List<Proyecto> getAll() throws Exception {
        List<Proyecto> proyectoList = new ArrayList<>();

        return proyectoList;
    }

    @Override
    public void save(Proyecto proyecto) throws Exception {

    }

    @Override
    public void update(Proyecto proyecto) throws Exception {

    }

    @Override
    public void delete(Proyecto proyecto) throws Exception {

    }
}
