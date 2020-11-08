package es.kokoro.dao.mysql;

import es.kokoro.dao.SubLineaAccionDAO;
import es.kokoro.model.SubLineaAccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.SqlConnection.conectar;

public class MySQLSubLineaAccionDAO implements SubLineaAccionDAO {

    private Connection conexion = null;

    public MySQLSubLineaAccionDAO() {
        setConexion(conexion);
    }
    public MySQLSubLineaAccionDAO(Connection conexion) {
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
    private SubLineaAccion setObject(ResultSet set)
    {
        long idSubLineaAccion = 0;
        SubLineaAccion tmpSubLienaAccion = null;
        try {
            idSubLineaAccion = set.getLong("idSubLineaAccion");
            String nombre = set.getString("nombreSubLineaAccion");
            tmpSubLienaAccion = new SubLineaAccion(idSubLineaAccion, nombre);
        } catch (SQLException throwables) {
            System.out.println("Error creando la instancia " + throwables);
        } finally {
            return tmpSubLienaAccion;
        }

    }

    @Override
    public SubLineaAccion get(long id)  {
        String query = "SELECT * FROM sublineasaccion WHERE idSubLineaAccion = ?";
        PreparedStatement statement;
        SubLineaAccion tmpSubLienaAccion = null;
        try {
            statement = conexion.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            tmpSubLienaAccion = setObject(set);

        } catch (SQLException throwables) {
            System.out.println("Error obteniendo la instancia " + throwables);
        } finally {
            return tmpSubLienaAccion;
        }

    }

    @Override
    public List<SubLineaAccion> getAll() {
        List<SubLineaAccion> subLineaAccionList = new ArrayList<>();
        String query = "SELECT * FROM sublineasaccion";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next())
            {
                SubLineaAccion tmpSubLienaAccion = setObject(set);
                subLineaAccionList.add(tmpSubLienaAccion);
            }
        } catch (SQLException throwables) {
            System.out.println("Error obteniendo el Result Set " + throwables);
        } finally {
            return subLineaAccionList;
        }
    }

    @Override
    public SubLineaAccion save(SubLineaAccion subLineaAccion) {
        String query = "INSERT INTO sublineasaccion(nombreSubLineaAccion) VALUES(?)";
        PreparedStatement nuevaEntrada;

        try {
            nuevaEntrada = conexion.prepareStatement(query);
            nuevaEntrada.setString(1,subLineaAccion.getNombreSubLinea());
            nuevaEntrada.executeUpdate();
            System.out.println("Ejecutamos Save MySQLSubLineaAccionDAO");
        } catch (SQLException throwables) {
            System.out.println("Error guardando el nuevo registro " + throwables);
        }
        return subLineaAccion;
    }

    @Override
    public SubLineaAccion update(SubLineaAccion subLineaAccion) {

        boolean isUpdate = false;

        try {
            if(subLineaAccion.getIdSubLinea() != null ) { // Estamos pasando un ID

                if(get(subLineaAccion.getIdSubLinea()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = "UPDATE sublineasaccion SET nombreSubLineaAccion = ? WHERE idSubLineaAccion = ?";
                    PreparedStatement updateEntrada = null;

                    updateEntrada = conexion.prepareStatement(query);
                    updateEntrada.setString(1,subLineaAccion.getNombreSubLinea());
                    updateEntrada.setLong(2,subLineaAccion.getIdSubLinea());
                    updateEntrada.executeUpdate();
                    isUpdate = true;

                }
            }
        } catch (SQLException throwables) {
            System.out.println("Error actualizando el nuevo registro " + throwables);
        }finally {
            if(!isUpdate)   // Si no existe en nuestra DDBB o no facilitan un ID guardamos en lugar de actualizar
            {
                save(subLineaAccion);
                System.out.println("El registro que se quería actualizar no exitste: Se ha guardado como nuevo registro");
            }
        }
        return subLineaAccion;
    }

    @Override
    public void delete(SubLineaAccion subLineaAccion) {

        boolean existe = false;
        try {
            if(subLineaAccion.getIdSubLinea() != null ) { // Estamos pasando un ID

                if(get(subLineaAccion.getIdSubLinea()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = " DELETE FROM sublineasaccion WHERE idSubLineaAccion = ?";
                    PreparedStatement borrarEntrada = null;

                    borrarEntrada = conexion.prepareStatement(query);
                    borrarEntrada.setLong(1,subLineaAccion.getIdSubLinea());
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
