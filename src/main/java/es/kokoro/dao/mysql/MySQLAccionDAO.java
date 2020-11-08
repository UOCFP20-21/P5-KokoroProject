package es.kokoro.dao.mysql;

import es.kokoro.dao.AccionDAO;
import es.kokoro.model.Accion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.SqlConnection.conectar;

public class MySQLAccionDAO implements AccionDAO {

    private Connection conexion = null;

    public MySQLAccionDAO() {
        setConexion(conexion);
    }

    public MySQLAccionDAO(Connection conexion) {
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

    private Accion setObject(ResultSet set) {

        Accion tmpEntrada = null;
        try {
            long idAccion = set.getLong("idAccion");
            String nombre = set.getString("nombre");
            String descripcion = set.getString("descripcion");
            Double coste = set.getDouble("coste");
            tmpEntrada = new Accion(idAccion, nombre, descripcion, coste);
        } catch (SQLException throwables) {
            System.out.println("Error creando la instancia " + throwables);
        } finally {
            return tmpEntrada;
        }
    }

    @Override
    public Accion get(long id) {

        String query = "SELECT * FROM acciones WHERE idAccion = ?";
        PreparedStatement statement;
        Accion tmpEntrada = null;
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
    public List<Accion> getAll() throws Exception {

        List<Accion> entradasList = new ArrayList<>();
        String query = "SELECT * FROM acciones";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Accion tmpEntrada = setObject(set);
                entradasList.add(tmpEntrada);
            }
        } catch (SQLException throwables) {
            System.out.println("Error obteniendo el Result Set " + throwables);
        } finally {
            return entradasList;
        }
    }

    @Override
    public Accion save(Accion accion) {

        String query = "INSERT INTO acciones(nombre, descripcion, coste) VALUES(?,?,?)";
        PreparedStatement nuevaEntrada;
        try {
            nuevaEntrada = conexion.prepareStatement(query);
            nuevaEntrada.setString(1, accion.getNombre());
            nuevaEntrada.setString(2, accion.getDescripcion());
            nuevaEntrada.setDouble(3, accion.getCoste());
            nuevaEntrada.executeUpdate();
            System.out.println("Ejecutamos Save MySQLAccionDAO");
        } catch (SQLException throwables) {
            System.out.println("Error guardando el nuevo registro " + throwables);
        }
        return accion;
    }

    @Override
    public Accion update(Accion accion) {

        boolean isUpdate = false;
        try {
            if (accion.getIdAccion() != null) { // Estamos pasando un ID

                if (get(accion.getIdAccion()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = "UPDATE acciones SET nombre = ?, descripcion = ?, coste = ? WHERE idAccion = ?";
                    PreparedStatement updateEntrada = null;

                    updateEntrada = conexion.prepareStatement(query);
                    updateEntrada.setString(1, accion.getNombre());
                    updateEntrada.setString(2, accion.getDescripcion());
                    updateEntrada.setDouble(3, accion.getCoste());
                    updateEntrada.setLong(4, accion.getIdAccion());
                    updateEntrada.executeUpdate();
                    isUpdate = true;
                }
            }
        } catch (SQLException throwables) {
            System.out.println("Error actualizando el nuevo registro " + throwables);
        } finally {
            if (!isUpdate)   // Si no existe en nuestra DDBB o no facilitan un ID guardamos en lugar de actualizar
            {
                save(accion);
                System.out.println("El registro que se quería actualizar no exitste: Se ha guardado como nuevo registro");
            }
        }
        return accion;
    }

    @Override
    public void delete(Accion accion) {

        boolean existe = false;
        try {
            if (accion.getIdAccion() != null) { // Estamos pasando un ID

                if (get(accion.getIdAccion()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = " DELETE FROM acciones WHERE idAccion = ?";
                    PreparedStatement borrarEntrada = null;

                    borrarEntrada = conexion.prepareStatement(query);
                    borrarEntrada.setLong(1, accion.getIdAccion());
                    borrarEntrada.executeUpdate();
                    existe = true;
                }
            }
        } catch (SQLException throwables) {
            System.out.println("Error Eliminando el registro " + throwables);
        } finally {
            if (!existe)   // Si no existe en nuestra DDBB o no facilitan un ID guardamos en lugar de actualizar
            {
                System.out.println("Registro no encontrado: No existe ningún registro con los datos facilitados.");
            }
        }
    }
}
