package es.kokoro.dao.mysql;

import es.kokoro.model.Persona;
import es.kokoro.model.Trabajador;
import es.kokoro.model.Contratado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static es.kokoro.commons.SqlConnection.commitData;

public class MySQLContratadoDAO extends MySQLTrabajadorDAO {

    public MySQLContratadoDAO() {
        setConexion(conexion);
    }

    public MySQLContratadoDAO(Connection conexion) {
        setConexion(conexion);
    }

    public Contratado get(long id) {
        String query = "SELECT * FROM contratados WHERE idContratado = ?";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            long idContratado = set.getLong("idContratado");
            long idTrabajador = set.getLong("idTrabajador");
            Date inicioContract = set.getDate("inicioContract");
            String finalContract = set.getString("finalContract");
            float salario = set.getFloat("salario");
            String puestTrabajo = set.getString("puestoTrabajo");
            Trabajador trabajador = super.get(idTrabajador);
            return new Contratado(idTrabajador, trabajador.getNombre(), trabajador.getApellidos(), trabajador.getIdentificador(), trabajador.getNacionalidad(), trabajador.getDireccion(),
                    trabajador.getPoblacion(), trabajador.getTelefono(), trabajador.getEmail(), trabajador.getFechaNac(), idContratado,
                    null,false,idContratado,inicioContract,finalContract,salario,puestTrabajo);
        } catch (Exception throwables) {
            System.out.println("Error obteniendo la instancia " + throwables);
        }
        return null;
    }

    //aunque el método devuelva List<Trabajador>, en esa lista puede haber cualquier clase que extienda de persona
    public List<Persona> getAll() {

        List<Persona> contratadoList = new ArrayList<>();
        String query = "SELECT * FROM contratados";
        PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                long idTrabajador = set.getLong("idTrabajador");
                Trabajador trabajador = super.get(idTrabajador);
                contratadoList.add(trabajador);
            }
        } catch (Exception throwables) {
            System.out.println("Error obteniendo el listado de instancias  " + throwables);
        }
        return contratadoList;
    }

    public Contratado save(Contratado contratado) {

        String query = "INSERT INTO contratados (idTrabajador, inicioContrato,finContrato,salario,puestoTrabajo) VALUES(?,?,?,?,?)";
        PreparedStatement nuevaEntrada;
        Trabajador trabajador = null;
        try {
            conexion.setAutoCommit(false);
            if (contratado.getIdTrabajador() == null)   // No facilitamos ID persona
            {
                if (checkDNI(contratado.getIdentificador()) == 0)  // No existe el Identificador en nuestra DDBB
                {
                    trabajador = super.save(contratado);
                    System.out.println("El ID de Trabajador nuevo es: " + trabajador.getIdTrabajador());
                } else {
                    System.out.println("El DNI que intentas introducir ya existe.");
                }
            } else // Facilitamos un ID de Persona
            {
                trabajador = super.update(contratado);
            }
            nuevaEntrada = conexion.prepareStatement(query);
            nuevaEntrada.setLong(1, trabajador.getIdTrabajador());
            nuevaEntrada.executeUpdate();
            commitData(conexion);
            System.out.println("Ejecutamos Save MySQLContratadoDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del nuevo registro " + e);
            }
            System.out.println("Error guardando el nuevo registro  (Save.Contratado)" + throwables);
        }
        return contratado;
    }

    public Contratado update(Contratado contratado) {

        String query = "UPDATE contratados SET idTrabajador = ?, inicioContrato = ?, finContrato = ?, salario = ?, puestoTrabajo = ? WHERE idContratado = ?";
        PreparedStatement updateEntrada;
        try {
            conexion.setAutoCommit(false);
            if (contratado.getIdTrabajador() == null || contratado.getIdContratado() == null)   // No facilitamos IDs
            {
                System.out.println("No se ha indicado la entrada a modificar");
            } else // Facilitamos los IDs
            {
                Trabajador trabajador = super.update(contratado);
                updateEntrada = conexion.prepareStatement(query);
                updateEntrada.setLong(1, trabajador.getIdTrabajador());
                updateEntrada.setLong(2, contratado.getIdContratado());
                updateEntrada.executeUpdate();
            }

            commitData(conexion);
            System.out.println("Ejecutamos Update MySQLContratadoDAO");
        } catch (SQLException throwables) {
            try {
                conexion.rollback();
            } catch (SQLException e) {
                System.out.println("Error realizando RollBack del update del registro (Update.Contratado) " + throwables);
            } finally {
                System.out.println("Error Actualizando el nuevo registro (Update.Contratado) " + throwables);
            }
        }

        return contratado;
    }

    public void delete(Contratado contratado) {

        boolean existe = false;
        try {
            if (contratado.getIdContratado() != null) { // Estamos pasando un ID
                if (get(contratado.getIdContratado()) != null) // El objeto pasado existe en nuestra DDBB
                {
                    String query = " DELETE FROM contratados WHERE idContratado = ?";
                    PreparedStatement borrarEntrada;
                    borrarEntrada = conexion.prepareStatement(query);
                    borrarEntrada.setLong(1, contratado.getIdContratado());
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
