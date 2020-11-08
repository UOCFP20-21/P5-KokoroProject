package es.kokoro.dao.mysql;

import es.kokoro.model.Persona;
import es.kokoro.model.Trabajador;
import es.kokoro.model.VoluntarioColaborador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static es.kokoro.commons.SqlConnection.commitData;

public class MySQLVoluntarioColaboradorDAO extends MySQLTrabajadorDAO {

        public MySQLVoluntarioColaboradorDAO() {
            setConexion(conexion);
        }

        public MySQLVoluntarioColaboradorDAO(Connection conexion) {
            setConexion(conexion);
        }

        public VoluntarioColaborador get(long id) {
            String query = "SELECT * FROM voluntarioColaborador WHERE idVoluntarioColaborador = ?";
            PreparedStatement statement;
            try {
                statement = conexion.prepareStatement(query);
                statement.setLong(1, id);
                ResultSet set = statement.executeQuery();
                set.next();
                long idVoluntarioColaborador = set.getLong("idVoluntarioColaborador");
                long idTrabajador = set.getLong("idTrabajador");
                Date inicioVoluntariado = set.getDate("inicioVoluntariado");
                Trabajador trabajador = super.get(idTrabajador);
                return new VoluntarioColaborador(trabajador.getIdPersona(), trabajador.getNombre(), trabajador.getApellidos(), trabajador.getIdentificador(), trabajador.getNacionalidad(), trabajador.getDireccion(),
                        trabajador.getPoblacion(), trabajador.getTelefono(), trabajador.getEmail(), idTrabajador,null, trabajador.getFechaNac(),false, idVoluntarioColaborador, inicioVoluntariado);
            } catch (Exception throwables) {
                System.out.println("Error obteniendo la instancia " + throwables);
            }
            return null;
        }

        //aunque el método devuelva List<Trabajador>, en esa lista puede haber cualquier clase que extienda de persona
        public List<Persona> getAll() {

            List<Persona> voluntarioLIst = new ArrayList<>();
            String query = "SELECT * FROM voluntarioColaborador";
            PreparedStatement statement;
            try {
                statement = conexion.prepareStatement(query);
                ResultSet set = statement.executeQuery();
                while (set.next()) {
                    long idTrabajador = set.getLong("idTrabajador");
                    Trabajador trabajador = super.get(idTrabajador);
                    voluntarioLIst.add(trabajador);
                }
            } catch (Exception throwables) {
                System.out.println("Error obteniendo el listado de instancias  " + throwables);
            }
            return voluntarioLIst;
        }

        public VoluntarioColaborador save(VoluntarioColaborador voluntarioColaborador) {

            String query = "INSERT INTO voluntarioColaborador (idTrabajador,inicioVoluntariado) VALUES(?,?)";
            PreparedStatement nuevaEntrada;
            Trabajador trabajador = null;
            try {
                conexion.setAutoCommit(false);
                if (voluntarioColaborador.getIdTrabajador() == null)   // No facilitamos ID persona
                {
                    if (checkDNI(voluntarioColaborador.getIdentificador()) == 0)  // No existe el Identificador en nuestra DDBB
                    {
                        trabajador = super.save(voluntarioColaborador);
                        System.out.println("El ID de Trabajador nuevo es: " + trabajador.getIdTrabajador());
                    } else {
                        System.out.println("El DNI que intentas introducir ya existe.");
                    }
                } else // Facilitamos un ID de Persona
                {
                    trabajador = super.update(voluntarioColaborador);
                }
                nuevaEntrada = conexion.prepareStatement(query);
                nuevaEntrada.setLong(1, trabajador.getIdTrabajador());
                nuevaEntrada.executeUpdate();
                commitData(conexion);
                System.out.println("Ejecutamos Save MySQLVoluntarioColaboradorDAO");
            } catch (SQLException throwables) {
                try {
                    conexion.rollback();
                } catch (SQLException e) {
                    System.out.println("Error realizando RollBack del nuevo registro " + e);
                }
                System.out.println("Error guardando el nuevo registro  (Save.VoluntarioColaborador)" + throwables);
            }
            return voluntarioColaborador;
        }

        public VoluntarioColaborador update(VoluntarioColaborador voluntarioColaborador) {

            String query = "UPDATE voluntarioColaborador SET idTrabajador = ?, inicioVoluntariado = ? WHERE idVoluntarioColaborador = ?";
            PreparedStatement updateEntrada;
            try {
                conexion.setAutoCommit(false);
                if (voluntarioColaborador.getIdTrabajador() == null || voluntarioColaborador.getIdVoluntarioColaborador() == null)   // No facilitamos IDs
                {
                    System.out.println("No se ha indicado la entrada a modificar");
                } else // Facilitamos los IDs
                {
                    Trabajador trabajador = super.update(voluntarioColaborador);
                    updateEntrada = conexion.prepareStatement(query);
                    updateEntrada.setLong(1, trabajador.getIdTrabajador());
                    updateEntrada.setLong(2, voluntarioColaborador.getIdVoluntarioColaborador());
                    updateEntrada.executeUpdate();
                }
                commitData(conexion);
                System.out.println("Ejecutamos Update MySQLVoluntarioColaboradorDAO");
            } catch (SQLException throwables) {
                try {
                    conexion.rollback();
                } catch (SQLException e) {
                    System.out.println("Error realizando RollBack del update del registro (Update.VoluntarioColaborador) " + throwables);
                } finally {
                    System.out.println("Error Actualizando el nuevo registro (Update.VoluntarioColaborador) " + throwables);
                }
            }

            return voluntarioColaborador;
        }

        public void delete(VoluntarioColaborador voluntarioColaborador) {

            boolean existe = false;
            try {
                if (voluntarioColaborador.getIdVoluntarioColaborador() != null) { // Estamos pasando un ID
                    if (get(voluntarioColaborador.getIdVoluntarioColaborador()) != null) // El objeto pasado existe en nuestra DDBB
                    {
                        String query = " DELETE FROM voluntarioColaborador WHERE idVoluntarioColaborador = ?";
                        PreparedStatement borrarEntrada;
                        borrarEntrada = conexion.prepareStatement(query);
                        borrarEntrada.setLong(1, voluntarioColaborador.getIdVoluntarioColaborador());
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
