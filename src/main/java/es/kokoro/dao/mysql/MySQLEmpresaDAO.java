package es.kokoro.dao.mysql;

import es.kokoro.commons.SqlConnection;
import es.kokoro.dao.EmpresaDAO;
import es.kokoro.model.Empresa;

import java.sql.*;
import java.util.List;

import static es.kokoro.commons.SqlConnection.conectar;

public abstract class MySQLEmpresaDAO implements EmpresaDAO {

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
    public Empresa get(long id) throws Exception {
        try {
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM empresas WHERE idEmpresa = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Long idEmpresa = resultSet.getLong("idEmpresa");
            String nombre = resultSet.getString("nombre");
            String pais = resultSet.getString("pais");
            String poblacion = resultSet.getString("poblacion");
            String direccionSocial = resultSet.getString("direccionSocial");
            String razonSocial = resultSet.getString("razonSocial");
            String identificacionSocial = resultSet.getString("identificacionSocial");
            String telefono = resultSet.getString("telefono");
            String email = resultSet.getString("email");
            return new Empresa(idEmpresa, nombre, pais, poblacion, direccionSocial, razonSocial, identificacionSocial, telefono, email);
        } catch (Exception exception) {
            System.out.println("Error recuperando la empresa " + exception);
        }
        return null;
    }

    @Override
    public List<Empresa> getAll() throws Exception {
        throw new UnsupportedOperationException("No puedes recuperar un listado de empresas. Tiene que ser una de sus implementaciones");
    }

    public Empresa save(Empresa empresa) {

        String queryEmpresa = "INSERT INTO empresas(nombre,pais,poblacion,direccionSocial,razonSocial,identificacionSocial,telefono,email) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement nuevaEntrada;
        try {
            nuevaEntrada = conexion.prepareStatement(queryEmpresa, Statement.RETURN_GENERATED_KEYS);
            nuevaEntrada.setString(1, empresa.getNombre());
            nuevaEntrada.setString(2, empresa.getPais());
            nuevaEntrada.setString(3, empresa.getPoblacion());
            nuevaEntrada.setString(4, empresa.getDireccionSocial());
            nuevaEntrada.setString(5, empresa.getRazonSocial());
            nuevaEntrada.setString(6, empresa.getIdentificacionSocial());
            nuevaEntrada.setString(7, empresa.getTelefono());
            nuevaEntrada.setString(8, empresa.getEmail());
            nuevaEntrada.executeUpdate();

            if (empresa.getIdEmpresa() == null) {
                ResultSet resultSet = nuevaEntrada.getGeneratedKeys();
                resultSet.next();
                empresa.setIdEmpresa(resultSet.getLong(1));
            }
            System.out.println("Ejecutamos Save MySQLEmpresaDAO");
        } catch (SQLException throwables) {
            System.out.println("Error guardando el nuevo registro (Save.Empresa)" + throwables);
        }
        return empresa;
    }

    public Empresa update(Empresa empresa) {

        String queryEmpresa = "UPDATE empresas SET nombre = ?, pais = ?, poblacion = ?, direccionSocial = ?, razonSocial = ?,identificacionSocial = ?,telefono = ?,email = ? WHERE idEmpresa = ?";
        PreparedStatement updateEmpresa;
        try {
            if (get(empresa.getIdEmpresa()) != null) {
                long idEmpresa = empresa.getIdEmpresa();
                updateEmpresa = conexion.prepareStatement(queryEmpresa, Statement.RETURN_GENERATED_KEYS);
                updateEmpresa.setString(1, empresa.getNombre());
                updateEmpresa.setString(2, empresa.getPais());
                updateEmpresa.setString(3, empresa.getPoblacion());
                updateEmpresa.setString(4, empresa.getDireccionSocial());
                updateEmpresa.setString(5, empresa.getRazonSocial());
                updateEmpresa.setString(6, empresa.getIdentificacionSocial());
                updateEmpresa.setString(7, empresa.getTelefono());
                updateEmpresa.setString(8, empresa.getEmail());
                updateEmpresa.setLong(9, idEmpresa);
                updateEmpresa.executeUpdate();
                System.out.println("Ejecutamos update MySQLEmpresaDAO");
            } else {
                return save(empresa);
            }
        } catch (Exception throwables) {
            System.out.println("Error guardando el nuevo registro (Update.Empresa)" + throwables);
        }
        return empresa;
    }

    @Override
    public void delete(Empresa empresa) throws Exception {

        try {
            boolean isExist = false;
            conexion = SqlConnection.conectar();
            PreparedStatement statement = conexion.prepareStatement("DELETE FROM empresas WHERE idEmpresa = ?");

            if (empresa.getIdEmpresa() != null) {
                if (get(empresa.getIdEmpresa()) != null) {
                    statement.setLong(1, empresa.getIdEmpresa());
                    statement.executeUpdate();
                    isExist = true;
                }
            }
            if (!isExist) {
                System.out.println("Empresa no encontrada");
            }
        } catch (Exception exception) {
            System.out.println("Error recuperando empresa " + exception);
        } finally {
            if (conexion != null) {
                SqlConnection.desconectar(conexion);
            }
        }
    }

    protected long checkIdentificadorSocial(String cif) {
        String query = "SELECT * FROM empresas WHERE identificadorSocial='?'";
        long result = 0;
        try {
            PreparedStatement checkIdentificadorSocial = conexion.prepareStatement(query);
            checkIdentificadorSocial.setString(1, cif);
            ResultSet set = checkIdentificadorSocial.executeQuery();
            set.next();
            result = set.getLong("idEmpresa");
        } catch (SQLException throwables) {
            System.out.println("No se ha podido comprobar el CIF " + throwables);
        }
        return result;
    }
}