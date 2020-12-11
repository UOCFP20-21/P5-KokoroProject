package sql;

import es.kokoro.dao.mysql.MySQLSocioDAO;
import es.kokoro.dao.mysql.MySqlFactoryDAO;
import es.kokoro.enums.Periodo;
import es.kokoro.model.LineaAccion;
import es.kokoro.model.Socio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.List;

import static es.kokoro.commons.FormatFecha.FFStringToDate;
import static es.kokoro.commons.sqlConection.*;
import static org.junit.jupiter.api.Assertions.*;

public class MySQLSocioDaoTest {

    private final String hosturl= "jdbc:mysql://88.24.89.85:3307/mydbtest";
    private static String usuario = "ongjaume";
    private static String password = "ong1234";
    private Connection cnn;


    @BeforeEach
    public void truncarSQL()
    {
        try {
            cnn = conectar(hosturl, usuario, password);
            String query = "DELETE FROM socios";
            Statement sQuery = cnn.createStatement();
            sQuery.execute(query);
            query = "ALTER TABLE socios AUTO_INCREMENT = 1";
            sQuery = cnn.createStatement();
            sQuery.execute(query);
            String query2 = "DELETE FROM personas";
            Statement sQuery2 = cnn.createStatement();
            sQuery.execute(query2);
            query2 = "ALTER TABLE personas AUTO_INCREMENT = 1";
            sQuery2 = cnn.createStatement();
            sQuery2.execute(query2);
            System.out.println("Tabla personas Truncada");
        } catch (SQLException throwables) {
            try {
                cnn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            if (cnn != null)
            {
                try {
                    desconectar(cnn);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }


    @Test
    public void testAgregarSocios() {
        Socio socio1 = null;
        try {
            socio1 = new Socio( "Test: testAgregarSocios", "Apellidos Test 1", "00000000T",
                    "Española", "C/ de mi casa 1", "Mi pueblo", "600000000", "test@prueba1.com",
                    null, Periodo.ANU, 50.00, true, FFStringToDate("1983-07-24"));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("testAgregarSocios socio1 instancia Catch: "+ e);
        }
        boolean exito = false;
        try {

            cnn = conectar(hosturl, usuario, password);
            cnn.setAutoCommit(false);
            MySQLSocioDAO mysqlSocioDAO;
            try {
                mysqlSocioDAO = (MySQLSocioDAO) MySqlFactoryDAO.getMySqlFactoryDAO(Socio.class, cnn);
                try {
                    mysqlSocioDAO.save(socio1);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("testAgregarSocios mysqlSocioDAO.save Catch: "+ e);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("testAgregarSocios mysqlSocioDAO Catch: "+ e);
            }

            commitData(cnn);
            exito = true;

        }catch (SQLException sql)
        {
            try {
                cnn.rollback();
                exito = false;
            } catch (SQLException throwables) {
                System.out.println("testAgregarSocios Catch: "+ throwables);
                throwables.printStackTrace();
            }
            sql.printStackTrace();
        }finally {
            if(cnn != null)
            {
                try {
                    desconectar(cnn);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    System.out.println("testAgregarSocios Desconectar Catch: "+ throwables);
                }
            }else
            {
                exito = false;
            }
        }
        assertTrue(exito);
    }

    @Test
    public void testLeerSocio() {
        testAgregarSocios();
        Socio leerSocio = null;
        MySQLSocioDAO mysqlSocioDao;
        try {
            cnn = conectar(hosturl, usuario, password);
            cnn.setAutoCommit(false);
            try {
                mysqlSocioDao = (MySQLSocioDAO) MySqlFactoryDAO.getMySqlFactoryDAO(Socio.class, cnn);
                leerSocio = mysqlSocioDao.get(1L);
            } catch (Exception e) {
                System.out.println("testLeerSocio Get Catch: "+ e);
            }
        } catch (SQLException throwables) {

            System.out.println("testLeerSocio Connect Catch: "+ throwables);
        }finally {

            if(cnn != null)
            {
                try {
                    desconectar(cnn);
                } catch (SQLException throwables) {
                    System.out.println("testLeerSocio DisConnect Catch: "+ throwables);
                }
            }

        }
        assertNotNull(leerSocio);


    }

    @Test
    public void testActualizarSocio() {

        testAgregarSocios();
        Socio socioUpdated = null;
        MySQLSocioDAO mysqlSocioDao = null;

        try {
            cnn = conectar(hosturl, usuario, password);
            cnn.setAutoCommit(false);
            try {
                mysqlSocioDao = (MySQLSocioDAO) MySqlFactoryDAO.getMySqlFactoryDAO(Socio.class, cnn);
                Socio updateSocio = mysqlSocioDao.get(1L);
                updateSocio.setCuota(35.00);
                mysqlSocioDao.update(updateSocio);
                commitData(cnn);
            } catch (Exception e) {
                e.printStackTrace();
                cnn.rollback();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {

            if(cnn != null)
            {
                try {
                    try {
                        socioUpdated = mysqlSocioDao.get(1L);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        desconectar(cnn);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }


        //Comprueba que Socio se ha actualizado no es nulo
        assertNotNull(socioUpdated);
        assertEquals(35.00, socioUpdated.getCuota());

    }


    @Test
    public void testBorrarSocio()  {

        testAgregarSocios();
        Socio socioDeleted = null;
        MySQLSocioDAO mysqlSocioDao = null;

        try {
            cnn = conectar(hosturl, usuario, password);
            cnn.setAutoCommit(false);
            try {
                mysqlSocioDao = (MySQLSocioDAO) MySqlFactoryDAO.getMySqlFactoryDAO(Socio.class, cnn);
                socioDeleted = mysqlSocioDao.get(1L);
                mysqlSocioDao.delete(socioDeleted);
                commitData(cnn);
            } catch (Exception e) {
                e.printStackTrace();
                cnn.rollback();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {

            if(cnn != null)
            {
                try {
                    try {
                        socioDeleted = mysqlSocioDao.get(1L);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        desconectar(cnn);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }

        //Comprueba que socio ES nulo
        assertNull(socioDeleted);
    }

    @Test
    public void testListadoSocios() {
        boolean exito = false;
        Socio socio1 = null;
        Socio socio2 = null;
        Socio socio3 = null;
        try {
            socio1 = new Socio( "Socio 1", "Apellidos Test 1", "00000000T",
                    "Española", "C/ de mi casa 1", "Mi pueblo1", "600000001", "test@prueba1.com",
                    null, Periodo.ANU, 50.00, true, FFStringToDate("1983-01-24"));
            socio2 = new Socio( "Socio 2", "Apellidos Test 2", "00000001T",
                    "Española", "C/ de mi casa 2", "Mi pueblo2", "600000002", "test@prueba2.com",
                    null, Periodo.ANU, 50.00, true, FFStringToDate("1983-02-24"));
            socio3 = new Socio( "Socio 3", "Apellidos Test 3", "00000002T",
                    "Española", "C/ de mi casa 3", "Mi pueblo3", "600000003", "test@prueba3.com",
                    null, Periodo.ANU, 50.00, true, FFStringToDate("1983-03-24"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Socio> socioList = null;
        MySQLSocioDAO mysqlSocioDao = null;
        try {
            cnn = conectar(hosturl, usuario, password);
            cnn.setAutoCommit(false);
            try {
                mysqlSocioDao = (MySQLSocioDAO) MySqlFactoryDAO.getMySqlFactoryDAO(Socio.class, cnn);
                mysqlSocioDao.save(socio1);
                mysqlSocioDao.save(socio2);
                mysqlSocioDao.save(socio3);
                commitData(cnn);


            } catch (Exception e) {
                try {
                    cnn.rollback();
                    System.out.println("rollBack Save x3");
                } catch (SQLException rollbackexception) {
                    rollbackexception.printStackTrace();
                }
                e.printStackTrace();
            }

        } catch (SQLException cnnexception) {
            try {
                cnn.rollback();
            } catch (SQLException rollbackexception) {
                rollbackexception.printStackTrace();
            }
            cnnexception.printStackTrace();
        }finally {
            if(cnn != null)
            {
                try {
                    try {
                        socioList = mysqlSocioDao.getAll();
                    } catch (Exception getallexception) {
                        getallexception.printStackTrace();
                    }
                    desconectar(cnn);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                exito = true;
            }
        }


        //Comprueba que lAccion no es nulo
        assertTrue(exito);
        assertNotNull(socioList);
        System.out.println(socioList.toString());
        assertEquals(3, socioList.size());

    }

}
