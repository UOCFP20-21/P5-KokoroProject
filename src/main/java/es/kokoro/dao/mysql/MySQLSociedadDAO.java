package es.kokoro.dao.mysql;

import es.kokoro.dao.SociedadDAO;
import es.kokoro.model.Sociedad;
import es.kokoro.model.Empresa;
import es.kokoro.dao.EmpresaDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.SqlConnection.commitData;

public class MySQLSociedadDAO extends MySQLEmpresaDAO {

        public MySQLSociedadDAO() {
            setConexion(conexion);
        }

        public MySQLSociedadDAO(Connection conexion) {
            setConexion(conexion);
        }

        public Sociedad get(long id) {
            String query = "SELECT * FROM sociedades WHERE idSociedad = ?";
            PreparedStatement statement;
            try {
                statement = conexion.prepareStatement(query);
                statement.setLong(1, id);
                ResultSet set = statement.executeQuery();
                set.next();

                long idSociedad = set.getLong("idSociedad");
                long idEmpresa = set.getLong("idEmpresa");
                Empresa empresa = super.get(idEmpresa);
                return new Sociedad(idEmpresa, empresa.getNombre(), empresa.getPais(), empresa.getPoblacion(), empresa.getRazonSocial(),
                        empresa.getIdentificacionSocial(),  empresa.getEmail(), empresa.getTelefono(), empresa.getEmail(), idSociedad);

            } catch (Exception throwables) {
                System.out.println("Error obteniendo la instancia " + throwables);
            }
            return null;
        }

        //aunque el método devuelva List<Persona>, en esa lista puede haber cualquier clase que extienda de persona
        public List<Empresa> getAll() {

            List<Empresa> empresaList = new ArrayList<>();
            String query = "SELECT * FROM sociedades";
            PreparedStatement statement;
            try {
                statement = conexion.prepareStatement(query);
                ResultSet set = statement.executeQuery();
                while (set.next()) {

                    long idEmpresa = set.getLong("idEmpresa");
                    Empresa empresa = super.get(idEmpresa);
                    empresaList.add(empresa);
                }
            } catch (Exception throwables) {
                System.out.println("Error obteniendo el listado de instancias  " + throwables);
            }
            return empresaList;
        }

        public Sociedad save(Sociedad sociedad) {

            String query = "INSERT INTO sociedades (idEmpresa) VALUES(?)";
            PreparedStatement nuevaEntrada;
            Empresa empresa = null;
            try {
                conexion.setAutoCommit(false);
                if (sociedad.getIdEmpresa() == null)   // No facilitamos ID persona
                {
                    if (checkIdentificadorSocial(sociedad.getIdentificacionSocial()) == 0)  // No existe el Identificador en nuestra DDBB
                    {
                        empresa = super.save(sociedad);
                        System.out.println("El ID de Empresa nuevo es: " + empresa.getIdEmpresa());
                    } else {
                        System.out.println("El DNI que intentas introducir ya existe.");
                    }
                } else // Facilitamos un ID de Persona
                {
                    empresa = super.update(sociedad);
                }

                nuevaEntrada = conexion.prepareStatement(query);
                nuevaEntrada.setLong(1, empresa.getIdEmpresa());
                nuevaEntrada.executeUpdate();
                commitData(conexion);
                System.out.println("Ejecutamos Save MySQLSociedadDAO");
            } catch (SQLException throwables) {
                try {
                    conexion.rollback();
                } catch (SQLException e) {
                    System.out.println("Error realizando RollBack del nuevo registro " + e);
                }
                System.out.println("Error guardando el nuevo registro  (Save.Sociedad)" + throwables);
            }
            return sociedad;
        }


        public Sociedad update(Sociedad sociedad) {

            String query = "UPDATE sociedades SET idEmpresa = ? WHERE idSociedad = ?";
            PreparedStatement updateEntrada;
            try {
                conexion.setAutoCommit(false);
                if (sociedad.getIdEmpresa() == null || sociedad.getIdSociedad() == null)   // No facilitamos IDs
                {
                    System.out.println("No se ha indicado la entrada a modificar");
                } else // Facilitamos los IDs
                {
                    Empresa empresa = super.update(sociedad);
                    updateEntrada = conexion.prepareStatement(query);
                    updateEntrada.setLong(1, empresa.getIdEmpresa());
                    updateEntrada.setLong(2, sociedad.getIdSociedad());
                    updateEntrada.executeUpdate();
                }
                commitData(conexion);
                System.out.println("Ejecutamos Update MySQLSociedadDAO");
            } catch (SQLException throwables) {
                try {
                    conexion.rollback();
                } catch (SQLException e) {
                    System.out.println("Error realizando RollBack del update del registro (Update.Sociedad) " + throwables);
                } finally {
                    System.out.println("Error Actualizando el nuevo registro (Update.Sociedad) " + throwables);
                }
            }
            return sociedad;
        }


        public void delete(Sociedad sociedad) {

            boolean existe = false;
            try {
                if (sociedad.getIdSociedad() != null) { // Estamos pasando un ID

                    if (get(sociedad.getIdSociedad()) != null) // El objeto pasado existe en nuestra DDBB
                    {
                        String query = " DELETE FROM sociedades WHERE idSociedad = ?";
                        PreparedStatement borrarEntrada;

                        borrarEntrada = conexion.prepareStatement(query);
                        borrarEntrada.setLong(1, sociedad.getIdSociedad());
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

