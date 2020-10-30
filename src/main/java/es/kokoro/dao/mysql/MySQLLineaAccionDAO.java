package es.kokoro.dao.mysql;

import es.kokoro.dao.LineaAccionDAO;
import es.kokoro.model.LineaAccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.sqlConection.conectar;

public class MySQLLineaAccionDAO implements LineaAccionDAO {

    private Connection conexion;

    public MySQLLineaAccionDAO() {
        setConexion(conexion);
    }
    public MySQLLineaAccionDAO(Connection conexion) {
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

    public void setConexion()
    {
        try {
            this.conexion = conectar();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Override
    public LineaAccion get(long id) throws Exception {
        String query = "SELECT * FROM lineasaccion WHERE idLineaAccion = ?";
        PreparedStatement statement = conexion.prepareStatement(query);
        statement.setLong(1, id);

        ResultSet set = statement.executeQuery();
        while (set.next())
        {
            long idLineaAccion = set.getLong("idLineaAccion");
            String linea = set.getString("linea");
            LineaAccion tmpLienaAccion = new LineaAccion(idLineaAccion, linea);
            return tmpLienaAccion;
        }
        return null;
    }

    @Override
    public List<LineaAccion> getAll() throws Exception {
        List<LineaAccion> lineaAccionList = new ArrayList<>();
        String query = "SELECT * FROM lineasaccion";
        PreparedStatement statement = conexion.prepareStatement(query);
        ResultSet set = statement.executeQuery();
        while (set.next())
        {
            long idLineaAccion = set.getLong("idLineaAccion");
            String linea = set.getString("linea");
            LineaAccion tmpLienaAccion = new LineaAccion(idLineaAccion, linea);
            lineaAccionList.add(tmpLienaAccion);
        }
        return lineaAccionList;
    }

    @Override
    public void save(LineaAccion lineaAccion) {
        String query = "INSERT INTO lineasaccion(linea) VALUES(?)";
        PreparedStatement nuevaLineaAccion = null;

        try {
            nuevaLineaAccion = conexion.prepareStatement(query);
            nuevaLineaAccion.setString(1,lineaAccion.getLinea());
            nuevaLineaAccion.executeUpdate();
            System.out.println("Ejecutamos Save MySQLLineaAccionDAO");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(LineaAccion lineaAccion) throws Exception {

        LineaAccion currentLA;
        boolean isUpdate = false;
        if(lineaAccion.getIdLineaAccion() != null ) { // Estamos pasando un ID

            if(get(lineaAccion.getIdLineaAccion()) != null) // El objeto pasado existe en nuestra DDBB
            {
                String query = "UPDATE lineasaccion SET linea = ? WHERE idLineaAccion = ?";
                PreparedStatement nuevaLineaAccion = null;

                nuevaLineaAccion = conexion.prepareStatement(query);
                nuevaLineaAccion.setString(1,lineaAccion.getLinea());
                nuevaLineaAccion.setLong(2,lineaAccion.getIdLineaAccion());
                nuevaLineaAccion.executeUpdate();
                isUpdate = true;
            }
        }
        if(!isUpdate)   // Si no existe en nuestra DDBB o no facilitan un ID guardamos en lugar de actualizar
        {
            save(lineaAccion);
        }
    }

    @Override
    public void delete(LineaAccion lineaAccion) throws Exception {

        LineaAccion currentLA;
        boolean existe = false;
        if(lineaAccion.getIdLineaAccion() != null ) { // Estamos pasando un ID

            if(get(lineaAccion.getIdLineaAccion()) != null) // El objeto pasado existe en nuestra DDBB
            {
                String query = " DELETE FROM lineasaccion WHERE idLineaAccion = ?";
                PreparedStatement deleteLineaAccion = null;

                deleteLineaAccion = conexion.prepareStatement(query);
                deleteLineaAccion.setLong(1,lineaAccion.getIdLineaAccion());
                deleteLineaAccion.executeUpdate();
                existe = true;
            }
        }
        if(!existe)   // Si no existe en nuestra DDBB o no facilitan un ID guardamos en lugar de actualizar
        {
            throw new Exception("Linea de Accion no encontrada");
        }
    }
}
