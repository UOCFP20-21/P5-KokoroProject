package es.kokoro.dao.mysql;

import es.kokoro.dao.IngresoDAO;
import es.kokoro.model.Ingreso;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//TODO Se ha creado la clase solo para poder obtener el listado de Ingresos vacío en OngDAO
import static es.kokoro.commons.sqlConection.conectar;

public class MySQLIngresoDAO implements IngresoDAO {

    private Connection conexion = null;
    public MySQLIngresoDAO() {
        setConexion(conexion);
    }
    public MySQLIngresoDAO(Connection conexion) {
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

    @Override
    public Ingreso get(long id) throws Exception {
        return null;
    }

    @Override
    public List<Ingreso> getAll() throws Exception {
        List<Ingreso> ingresoList = new ArrayList<>();

        return ingresoList;
    }

    @Override
    public void save(Ingreso ingreso) throws Exception {

    }

    @Override
    public void update(Ingreso ingreso) throws Exception {

    }

    @Override
    public void delete(Ingreso ingreso) throws Exception {

    }
}
