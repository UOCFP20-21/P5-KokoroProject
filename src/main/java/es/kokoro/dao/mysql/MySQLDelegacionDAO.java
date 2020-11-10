package es.kokoro.dao.mysql;

import es.kokoro.dao.DelegacionDAO;
import es.kokoro.model.*;
import es.kokoro.commons.sqlConection;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.sqlConection.commitData;
import static es.kokoro.commons.sqlConection.conectar;

public class MySQLDelegacionDAO extends MySQLEmpresaDAO implements DelegacionDAO  {

    private Connection conexion = null;
    public MySQLDelegacionDAO() {
        setConexion(conexion);
    }
    public MySQLDelegacionDAO(Connection conexion) {
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
    public Delegacion get(long id) throws Exception {
        Delegacion delegacion = null;

        try {

            String query = "SELECT d.*, e.* FROM delegaciones AS d LEFT JOIN empresas AS e ON d.idEmpresa = e.idEmpresa WHERE idDelegacion = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Long idDelegacion = resultSet.getLong("idDelegaciones");
            String areaOperativa = resultSet.getString("areaOperativa");
            Long idEmpresa = resultSet.getLong("idEmpresa");
            String nombre = resultSet.getString("nombre");
            String pais = resultSet.getString("pais");
            String poblacion = resultSet.getString("poblacion");
            String direccionSocial = resultSet.getString("direccionSocial");
            String razonSocial = resultSet.getString("razonSocial");
            String identificacionSocial = resultSet.getString("identificacionSocial");
            String telefono = resultSet.getString("telefono");
            String email = resultSet.getString("email");
            List<Trabajador> trabajadorList = new ArrayList<>();

            Ong ong = null;


            delegacion = new Delegacion(idEmpresa, nombre, pais, poblacion, direccionSocial, razonSocial, identificacionSocial, telefono, email, idDelegacion, trabajadorList, ong, areaOperativa);


        } catch (Exception exception) {
            System.out.println("Error recuperando la Delegacion " + exception);
        } finally {
            return delegacion;
        }

    }

    @Override
    public List<Delegacion> getAll() throws Exception {

        List<Delegacion> delegacionList = new ArrayList<>();
        try{

            PreparedStatement statement = conexion.prepareStatement("select * from delegaciones");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Long idDelegacion = resultSet.getLong("idDelegaciones");
                String areaOperativa = resultSet.getString("areaOperativa");
                Long idEmpresa = resultSet.getLong("idEmpresa");
                String nombre = resultSet.getString("nombre");
                String pais = resultSet.getString("pais");
                String poblacion = resultSet.getString("poblacion");
                String direccionSocial = resultSet.getString("direccionSocial");
                String razonSocial = resultSet.getString("razonSocial");
                String identificacionSocial = resultSet.getString("identificacionSocial");
                String telefono = resultSet.getString("telefono");
                String email = resultSet.getString("email");
                List<Trabajador> trabajadorList = new ArrayList<>();

                Ong ong = null;

                Delegacion delegacion = new Delegacion(idEmpresa, nombre, pais, poblacion, direccionSocial, razonSocial, identificacionSocial, telefono, email, idDelegacion, trabajadorList, ong, areaOperativa);
                delegacionList.add(delegacion);
            }
        }

        catch (Exception exception) {
            System.out.println("Error recuperando todas las delegaciones " + exception);
        } finally {
            return delegacionList;
        }

    }

    @Override
    public void save(Delegacion delegacion) throws Exception {
        try {

            PreparedStatement statement = conexion.prepareStatement("INSERT INTO delegaciones (nombre) VALUES (?)");

            statement.setLong(1, delegacion.getIdDelegacion());
            statement.setLong(2, delegacion.getOng().getIdOng());
            statement.setLong(3, delegacion.getIdEmpresa());
            statement.executeUpdate();
            System.out.println(("Se ejecuta 'save' en MySQLDelegacionDAO"));


        } catch (SQLException throwables) {
            System.out.println("Error guardando delegacion " + throwables);
        }
    }

    @Override
    public void update(Delegacion delegacion)  {
        String query = "UPDATE delegaciones SET idEmpresa = ?, areaOperativa = ?, idOng = ? WHERE idDelegacion = ?";
        PreparedStatement updateEntrada;
        Long idEmpresa = null;

        try {
            conexion.setAutoCommit(false);
            if(delegacion.getIdEmpresa() == null || delegacion.getIdEmpresa() == null)   // No facilitamos IDs
            {
                System.out.println("No se ha indicado la entrada a modificar");
            }
            else // Facilitamos los IDs
            {
                idEmpresa = super.update(delegacion);
                updateEntrada = conexion.prepareStatement(query);
                updateEntrada.setLong(4, delegacion.getIdDelegacion());
                updateEntrada.setLong(3, delegacion.getOng().getIdOng());
                updateEntrada.setLong(1, idEmpresa);
                updateEntrada.setString(2, delegacion.getAreaOperativa());
                updateEntrada.executeUpdate();
            }
            commitData(conexion);
            System.out.println("Ejecutamos Update MyDQLDelegacionDAO");

        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del update del registro (Update.Delegacion) " + throwables);
            }finally {
                System.out.println("Error Actualizando el nuevo registro (Update.Delegacion) " + throwables);
            }

        }finally {
            if(idEmpresa == null || idEmpresa == 0)
            {
                try {
                    conexion.rollback();
                } catch (SQLException throwables) {
                    System.out.println("Error realizando RollBack del nuevo registro " + throwables);
                }finally {
                    System.out.println("Error Actualizando el nuevo registro  (Update.Delegacion)");
                }
            }
        }
    }


    @Override
    public void delete(Delegacion delegacion) throws Exception {
        boolean existe = false;
        try {
            if (get(delegacion.getIdDelegacion()) != null) {
                if(get(delegacion.getIdDelegacion()) != null)
                {
                    String query = " DELETE FROM delegaciones WHERE idDelegacion = ?";
                    PreparedStatement borrarEntrada;

                    borrarEntrada = conexion.prepareStatement(query);
                    borrarEntrada.setLong(1,delegacion.getIdDelegacion());
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