package es.kokoro.dao.mysql;
import es.kokoro.model.Empresa;
import java.sql.*;

import static es.kokoro.commons.sqlConection.conectar;

public class MySQLEmpresaDAO {

    protected Connection conexion = null;
    private long id;

    public MySQLEmpresaDAO() {
        setConexion(conexion);
    }
    public MySQLEmpresaDAO(Connection conexion) {
        setConexion(conexion);
    }

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
    public ResultSet getResult(long id) {

        String query = "SELECT * FROM empresas WHERE idEmpresa = ?";
        PreparedStatement statement;
        ResultSet tmpSet = null;

        try {
            statement = conexion.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            tmpSet = set;
            System.out.println("Empresa Set obtenido");
        } catch (SQLException throwables) {
            System.out.println("Error obteniendo el Result Set " + throwables);
        } finally {
            return tmpSet;
        }
    }
    protected long save(Empresa empresa){

        long nuevoIdEmpresa = 0;
        String queryEmpresa = "INSERT INTO empresas(nombre,pais,poblacion,direccionSocial,razonSocial,identificacionSocial,telefono,email) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement nuevaEntrada;

        try {
            nuevaEntrada = conexion.prepareStatement(queryEmpresa, Statement.RETURN_GENERATED_KEYS);
            nuevaEntrada.setString(1,empresa.getNombre());
            nuevaEntrada.setString(2,empresa.getPais());
            nuevaEntrada.setString(3,empresa.getPoblacion());
            nuevaEntrada.setString(4,empresa.getDireccionSocial());
            nuevaEntrada.setString(5,empresa.getRazonSocial());
            nuevaEntrada.setString(6,empresa.getIdentificacionSocial());
            nuevaEntrada.setString(7,empresa.getTelefono());
            nuevaEntrada.setString(8,empresa.getEmail());
            nuevaEntrada.executeUpdate();
            ResultSet resultSet = nuevaEntrada.getGeneratedKeys();
            resultSet.next();
            nuevoIdEmpresa = resultSet.getLong(1);
            System.out.println("Ejecutamos Save MySQLEmpresaDAO");


        } catch (SQLException throwables) {
            conexion.rollback();
            System.out.println("Error guardando el nuevo registro (Save.Empresa)" + throwables);
        } finally {
            return nuevoIdEmpresa;

        }
    }
    protected long update(Empresa empresa){

        String queryEmpresa = "UPDATE empresas SET nombre = ?, pais = ?, poblacion = ?, direccionSocial = ?, razonSocial = ?,identificacionSocial = ?,telefono = ?,email = ? WHERE idEmpresa = ?";
        PreparedStatement updateEmpresa;
        long idEmpresa = 0;
        try{
            if(getResult(empresa.getIdEmpresa()) != null) {
                idEmpresa = empresa.getIdEmpresa();
                updateEmpresa = conexion.prepareStatement(queryEmpresa);
                updateEmpresa.setString(1,empresa.getNombre());
                updateEmpresa.setString(2,empresa.getPais());
                updateEmpresa.setString(3,empresa.getPoblacion());
                updateEmpresa.setString(4,empresa.getDireccionSocial());
                updateEmpresa.setString(5,empresa.getRazonSocial());
                updateEmpresa.setString(6,empresa.getIdentificacionSocial());
                updateEmpresa.setString(7,empresa.getTelefono());
                updateEmpresa.setString(8,empresa.getEmail());
                updateEmpresa.setLong(9, idEmpresa);
                updateEmpresa.executeUpdate();
                System.out.println("Ejecutamos update MySQLEmpresaDAO");
            }
            else{
                idEmpresa = save(empresa);
            }
        } catch (SQLException throwables) {
            conexion.rollback();
            System.out.println("Error guardando el nuevo registro (Update.Empresa)" + throwables);
        } finally {
            return  idEmpresa;
        }
    }
    protected long checkIdentificadorSocial(String identificadorSocial)
    {
        String query = "SELECT * FROM empresas WHERE identificadorSocial='?'";
        long result = 0;
        PreparedStatement checkIdentificadotSocial;

        try {
            checkIdentificadotSocial = conexion.prepareStatement(query);
            ResultSet set = checkIdentificadotSocial.executeQuery();
            while (set.next())
            {
                result = set.getLong("idEmpresa");
                break;
            }
        } catch (SQLException throwables) {
            System.out.println("No se ha podido comprobar el NIF " + throwables);
        }

        return result;
    }

}
