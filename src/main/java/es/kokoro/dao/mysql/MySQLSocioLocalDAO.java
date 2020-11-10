package es.kokoro.dao.mysql;

import es.kokoro.dao.SocioLocalDAO;
import es.kokoro.model.Empresa;
import es.kokoro.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.sqlConection.commitData;

public class MySQLSocioLocalDAO extends MySQLEmpresaDAO implements SocioLocalDAO {

    public MySQLSocioLocalDAO() {
        setConexion(conexion);
    }
    public MySQLSocioLocalDAO(Connection conexion) {
        setConexion(conexion);
    }

    private Empresa setObject(ResultSet set)
    {
        Empresa tmpEntrada = null;
        ResultSet empresaData;
        try {
            long idSocioLocal = set.getLong("idSocioLocal");
            List<Proyecto> proyectoList = new ArrayList<>();
            long idEmpresa = set.getLong("idEmpresa");
            empresaData = super.getResult(idEmpresa);
            String nombre = empresaData.getString("nombre");
            String pais = empresaData.getString("pais");
            String poblacion = empresaData.getString("poblacion");
            String direccionSocial = empresaData.getString("direccionSocial");
            String razonSocial = empresaData.getString("razonSocial");
            String telefono = empresaData.getString("telefono");
            String email = empresaData.getString("email");
            tmpEntrada = new SocioLocal(idEmpresa, nombre, pais, poblacion, direccionSocial, direccionSocial, razonSocial, telefono, email, idSocioLocal, proyectoList);
        } catch (SQLException throwables) {
            System.out.println(("Error creando la instacia" + throwables));
        } finally {
            return tmpEntrada;
        }

    }

    @Override
    public SocioLocal get(long id){
        String query = "SELECT * FROM socioslocales WHERE idSocioLocal = ?";
        PreparedStatement statement;
        SocioLocal tmpEntrada = null;
        try {
            statement = conexion.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            tmpEntrada = (SocioLocal) setObject(set);

        } catch (SQLException throwables) {
            System.out.println("Error obteniendo la instancia " + throwables);
        } finally {
            return tmpEntrada;
        }
    }

    @Override
    public List<SocioLocal> getAll(){

        List<SocioLocal> entradasList = new ArrayList<>();
        String query = "SELECT * FROM socioslocales";
        PreparedStatement statement;
        try{
            statement = conexion.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next())
            {
                SocioLocal tmpEntrada = (SocioLocal) setObject(set);
                entradasList.add(tmpEntrada);
            }
        } catch (SQLException throwables) {
            System.out.println("Error obteniendo el listado de instancias  " + throwables);
        } finally {
            return entradasList;
        }
    }

    @Override
    public void save(SocioLocal socioLocal){

        String query = "INSERT INTO socioslocales (idSocioLocal) VALUES(?)";

        PreparedStatement nuevaEntrada;
        Long idEmpresa = null;
        try {
            conexion.setAutoCommit(false);
            if(socioLocal.getIdEmpresa() == null)   // No facilitamos ID persona
            {
                if(checkIdentificadorSocial(socioLocal.getIdentificacionSocial()) == 0)  // No existe el Identificador en nuestra DDBB
                {
                    idEmpresa = super.save(socioLocal);
                    System.out.println("El ID de Empresa nuevo es: " + idEmpresa);
                }
                else
                {
                    System.out.println("El NIF que intentas introducir ya existe.");
                }
            }
            else // Facilitamos un ID de Persona
            {
                idEmpresa = super.update(socioLocal);
            }

            nuevaEntrada = conexion.prepareStatement(query);
            nuevaEntrada.setLong(1,idEmpresa);
            nuevaEntrada.executeUpdate();
            commitData(conexion);
            System.out.println("Ejecutamos Save MySQLSocioLocalDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del nuevo registro " + e);
            }
            System.out.println("Error guardando el nuevo registro  (Save.SocioLocal)" + throwables);
        }finally {
            if(idEmpresa == null || idEmpresa == 0)
            {
                try {
                    conexion.rollback();
                } catch (SQLException throwables) {
                    System.out.println("Error realizando RollBack del nuevo registro " + throwables);
                }
            }
        }

    }
    @Override
    public void update(SocioLocal socioLocal){

        String query = "UPDATE socioslocales SET idEmpresa = ? WHERE idSocioSocal = ?";
        PreparedStatement updateEntrada;
        Long idEmpresa = null;
        try {
            conexion.setAutoCommit(false);
            if(socioLocal.getIdEmpresa() == null || socioLocal.getIdEmpresa() == null)   // No facilitamos IDs
            {
                System.out.println("No se ha indicado la entrada a modificar");
            }
            else // Facilitamos los IDs
            {
                idEmpresa = super.update(socioLocal);
                updateEntrada = conexion.prepareStatement(query);
                updateEntrada.setLong(1,socioLocal.getIdEmpresa());
                updateEntrada.setLong(2,socioLocal.getIdSocioLocal());
                updateEntrada.executeUpdate();
            }

            commitData(conexion);
            System.out.println("Ejecutamos Update MySQLSocioLocalDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del update del registro (Update.SocioLocal) " + throwables);
            }finally {
                System.out.println("Error Actualizando el nuevo registro (Update.Empresa) " + throwables);
            }

        }finally {
            if(idEmpresa == null || idEmpresa == 0)
            {
                try {
                    conexion.rollback();
                } catch (SQLException throwables) {
                    System.out.println("Error realizando RollBack del nuevo registro " + throwables);
                }finally {
                    System.out.println("Error Actualizando el nuevo registro  (Update.Empresa)");
                }
            }
        }

    }

    @Override
    public void delete(SocioLocal socioLocal){

        boolean existe = false;
        try {
            if(socioLocal.getIdSocioLocal() != null ) { // Estamos pasando un ID

                if(get(socioLocal.getIdSocioLocal()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = " DELETE FROM socioslocales WHERE idSocioLocal = ?";
                    PreparedStatement borrarEntrada;

                    borrarEntrada = conexion.prepareStatement(query);
                    borrarEntrada.setLong(1,socioLocal.getIdSocioLocal());
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


