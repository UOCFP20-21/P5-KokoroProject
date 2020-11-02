package es.kokoro.dao.mysql;

import es.kokoro.dao.DelegacionDAO;
import es.kokoro.model.*;
import es.kokoro.commons.sqlConection;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class MySQLDelegacionDAO implements DelegacionDAO {
    private Connection connection = null;

    @Override
    public Delegacion get(long id) throws Exception {
        try {
            connection = sqlConection.conectar();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM delegaciones WHERE idDelegaciones = ?");
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

            List<Delegacion> delegacionLista = new ArrayList<>();
            List<Ingreso> ingresoList = new ArrayList<>();
            List<Socio> socioList = new ArrayList<>();
            List<Proyecto> proyectoList = new ArrayList<>();

            Ong ong = new Ong(
                    1L,
                    "Kokoro Sin Fronteras",
                    delegacionLista,
                    ingresoList,
                    socioList,
                    proyectoList

            );
            return new Delegacion(idEmpresa, nombre, pais, poblacion, direccionSocial, razonSocial, identificacionSocial, telefono, email, idDelegacion, trabajadorList, ong, areaOperativa);


        } catch (Exception exception) {
            System.out.println("Error recuperando la Delegacion " + exception);
        } finally {
            if (connection != null) {
                sqlConection.desconectar(connection);
            }
        }
        return null;
    }

    @Override
    public List<Delegacion> getAll() throws Exception {

        List<Delegacion> delegacionList = new ArrayList<>();
        try{
            connection = sqlConection.conectar();
            PreparedStatement statement = connection.prepareStatement("select * from delegaciones");
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

                List<Delegacion> delegacionLista = new ArrayList<>();
                List<Ingreso> ingresoList = new ArrayList<>();
                List<Socio> socioList = new ArrayList<>();
                List<Proyecto> proyectoList = new ArrayList<>();

                Ong ong = new Ong(
                        1L,
                        "Kokoro Sin Fronteras",
                        delegacionLista,
                        ingresoList,
                        socioList,
                        proyectoList

                );


                Delegacion delegacion = new Delegacion(idEmpresa, nombre, pais, poblacion, direccionSocial, razonSocial, identificacionSocial, telefono, email, idDelegacion, trabajadorList, ong, areaOperativa);
                delegacionList.add(delegacion);
            }
        }

        catch (Exception exception) {
            System.out.println("Error recuperando todas las delegaciones " + exception);
        } finally {
            if (connection != null) {
                sqlConection.desconectar(connection);
            }
        }


        return delegacionList;
    }

    @Override
    public void save(Delegacion delegacion) throws Exception {
        try {
            connection = sqlConection.conectar();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO delegaciones (nombre) VALUES (?)");

            statement.setLong(1, delegacion.getIdDelegacion());
            statement.setLong(2, delegacion.getOng().getIdOng());
            statement.setLong(3, delegacion.getIdEmpresa());
            statement.executeUpdate();
            System.out.println(("Se ejecuta 'save' en MySQLDelegacionDAO"));


        } catch (SQLException throwables) {
            System.out.println("Error guardando delegacion " + throwables);
        } finally {
            if (connection != null) {
                sqlConection.desconectar(connection);
            }
        }

    }

    @Override
    public void update(Delegacion delegacion) throws Exception {

        try {
            boolean isUpdate = false;
            connection = sqlConection.conectar();
            PreparedStatement statement = connection.prepareStatement("UPDATE delegaciones WHERE idDelegaciones = ? ");

            delegacion.getIdDelegacion();
            if (get(delegacion.getIdDelegacion()) != null) {

                statement.setLong(1, delegacion.getIdDelegacion());
                statement.setLong(2, delegacion.getOng().getIdOng());
                statement.setLong(3, delegacion.getIdEmpresa());
                statement.executeUpdate();
                isUpdate = true;

            }
            if (!isUpdate) {
                save(delegacion);
            }

        } catch (Exception exception) {
            System.out.println("Error recuperando la delegacion " + exception);
        } finally {
            if (connection != null) {
                sqlConection.desconectar(connection);
            }
        }

    }



    @Override
    public void delete(Delegacion delegacion) throws Exception {
        try {
            boolean isExist = false;
            connection = sqlConection.conectar();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM delegaciones WHERE idDelegaciones = ?");

            delegacion.getIdDelegacion();
            if (get(delegacion.getIdDelegacion()) != null) {

                statement.setLong(1, delegacion.getIdDelegacion());
                statement.executeUpdate();
                isExist = true;

            }

            if (!isExist) {
                System.out.println("Delegacion no encontrada");
            }

        } catch (Exception exception) {
            System.out.println("Error recuperando la Delegacion " + exception);
        } finally {
            if (connection != null) {
                sqlConection.desconectar(connection);
            }
        }
    }

}