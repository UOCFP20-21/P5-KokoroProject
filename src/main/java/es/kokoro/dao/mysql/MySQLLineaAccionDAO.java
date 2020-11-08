package es.kokoro.dao.mysql;

import es.kokoro.dao.LineaAccionDAO;
import es.kokoro.model.LineaAccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.SqlConnection.conectar;

public class MySQLLineaAccionDAO implements LineaAccionDAO {

    private Connection conexion = null;

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

    private LineaAccion setObject(ResultSet set)
    {
        LineaAccion tmpEntrada = null;
        try {
            long idLineaAccion = set.getLong("idLineaAccion");
            String linea = set.getString("linea");
            tmpEntrada = new LineaAccion(idLineaAccion, linea);
        } catch (SQLException throwables) {
            System.out.println("Error creando la instancia " + throwables);
        } finally {
            return tmpEntrada;
        }
    }

    @Override
    public LineaAccion get(long id) {

        String query = "SELECT * FROM lineasaccion WHERE idLineaAccion = ?";
        PreparedStatement statement;
        LineaAccion tmpEntrada = null;
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
    public List<LineaAccion> getAll() {

        List<LineaAccion> entradasList = new ArrayList<>();
        String query = "SELECT * FROM lineasaccion";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next())
            {
                LineaAccion tmpEntrada = setObject(set);
                entradasList.add(tmpEntrada);
            }
        } catch (SQLException throwables) {
            System.out.println("Error obteniendo el Result Set " + throwables);
        } finally {
            return entradasList;
        }

    }

    @Override
    public LineaAccion save(LineaAccion lineaAccion) {

        String query = "INSERT INTO lineasaccion(linea) VALUES(?)";
        PreparedStatement nuevaEntrada;
        try {
            nuevaEntrada = conexion.prepareStatement(query);
            nuevaEntrada.setString(1,lineaAccion.getLinea());
            nuevaEntrada.executeUpdate();
            System.out.println("Ejecutamos Save MySQLAccionDAO");
        } catch (SQLException throwables) {
            System.out.println("Error guardando el nuevo registro " + throwables);
        }
return lineaAccion;
    }

    @Override
    public LineaAccion update(LineaAccion lineaAccion) {

        boolean isUpdate = false;
        try {
            if(lineaAccion.getIdLineaAccion() != null ) { // Estamos pasando un ID

                if(get(lineaAccion.getIdLineaAccion()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = "UPDATE lineasaccion SET linea = ? WHERE idLineaAccion = ?";
                    PreparedStatement updateEntrada = null;

                    updateEntrada = conexion.prepareStatement(query);
                    updateEntrada.setString(1,lineaAccion.getLinea());
                    updateEntrada.setLong(2,lineaAccion.getIdLineaAccion());
                    updateEntrada.executeUpdate();
                    isUpdate = true;
                }
            }
        } catch (SQLException throwables) {
            System.out.println("Error actualizando el nuevo registro " + throwables);
        }finally {
            if(!isUpdate)   // Si no existe en nuestra DDBB o no facilitan un ID guardamos en lugar de actualizar
            {
                save(lineaAccion);
                System.out.println("El registro que se quería actualizar no exitste: Se ha guardado como nuevo registro");
            }
        }
return lineaAccion;
    }

    @Override
    public void delete(LineaAccion lineaAccion) {

        boolean existe = false;
        try {
            if(lineaAccion.getIdLineaAccion() != null ) { // Estamos pasando un ID

                if(get(lineaAccion.getIdLineaAccion()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = " DELETE FROM lineasaccion WHERE idLineaAccion = ?";
                    PreparedStatement borrarEntrada = null;

                    borrarEntrada = conexion.prepareStatement(query);
                    borrarEntrada.setLong(1,lineaAccion.getIdLineaAccion());
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
