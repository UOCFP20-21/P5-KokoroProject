package sql;

import es.kokoro.dao.mysql.MySQLLineaAccionDAO;
import es.kokoro.dao.mysql.MySqlFactoryDAO;
import es.kokoro.model.LineaAccion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static es.kokoro.commons.SqlConnection.*;
import static org.junit.jupiter.api.Assertions.*;

public class MySQLLineaAccionDaoTest {

    private final String hosturl= "jdbc:mysql://88.24.88.86:3307/mydbtest";
    private static String usuario = "ongjaume";
    private static String password = "ong1234";
    private Connection cnn;


    @BeforeEach
    public void truncarSQL()
    {
        try {
            cnn = conectar(hosturl, usuario, password);
            String query = "DELETE FROM lineasaccion";
            Statement sQuery = cnn.createStatement();
            sQuery.execute(query);
            query = "ALTER TABLE lineasaccion AUTO_INCREMENT = 1";
            sQuery = cnn.createStatement();
            sQuery.execute(query);
            System.out.println("Tabla LineasAccion Truncada");
        } catch (SQLException throwables) {
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
    public void testAgregarLineaAccion() {
        LineaAccion lAccion1 = new LineaAccion(null, "Test: testAgregarLineaAccion");
        boolean exito = false;
        try {

            cnn = conectar(hosturl, usuario, password);
            cnn.setAutoCommit(false);
            MySQLLineaAccionDAO mysqlLineaAccionDao = null;
            try {
                mysqlLineaAccionDao = (MySQLLineaAccionDAO) MySqlFactoryDAO.getMySqlFactoryDAO(LineaAccion.class, cnn);
                try {
                    mysqlLineaAccionDao.save(lAccion1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            commitData(cnn);

        }catch (SQLException sql)
        {
            try {
                cnn.rollback();
            } catch (SQLException throwables) {
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
                }
                exito = true;
            }
        }

        assertTrue(exito);
    }

    @Test
    public void testLeerLineaAccion() {
        testAgregarLineaAccion();
        LineaAccion leerLA = null;
        MySQLLineaAccionDAO mysqlLineaAccionDao = null;
        try {
            cnn = conectar(hosturl, usuario, password);
            cnn.setAutoCommit(false);
            try {
                mysqlLineaAccionDao = (MySQLLineaAccionDAO) MySqlFactoryDAO.getMySqlFactoryDAO(LineaAccion.class, cnn);
                leerLA = mysqlLineaAccionDao.get(1L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }finally {

            if(cnn != null)
            {
                try {
                    desconectar(cnn);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }
        assertNotNull(leerLA);


    }

    @Test
    public void testActualizarLineaAccion() {

        testAgregarLineaAccion();
        MySQLLineaAccionDAO mysqlLineaAccionDao = null;
        LineaAccion lAccionUpdated = null;

        try {
            cnn = conectar(hosturl, usuario, password);
            cnn.setAutoCommit(false);
            try {
                mysqlLineaAccionDao = (MySQLLineaAccionDAO) MySqlFactoryDAO.getMySqlFactoryDAO(LineaAccion.class, cnn);
                LineaAccion updateLA = mysqlLineaAccionDao.get(1L);
                updateLA.setLinea("Linea de Acción actualizada");
                mysqlLineaAccionDao.update(updateLA);
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
                        lAccionUpdated = mysqlLineaAccionDao.get(1L);
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


        //Comprueba que lAccion no es nulo
        assertNotNull(lAccionUpdated);
        assertEquals("Linea de Acción actualizada", lAccionUpdated.getLinea());

    }


    @Test
    public void testBorrarLineaAccuion() throws Exception {

        testAgregarLineaAccion();
        MySQLLineaAccionDAO mysqlLineaAccionDao = null;
        LineaAccion lAccionDeleted = null;

        try {
            cnn = conectar(hosturl, usuario, password);
            cnn.setAutoCommit(false);
            try {
                mysqlLineaAccionDao = (MySQLLineaAccionDAO) MySqlFactoryDAO.getMySqlFactoryDAO(LineaAccion.class, cnn);
                lAccionDeleted = mysqlLineaAccionDao.get(1L);
                mysqlLineaAccionDao.delete(lAccionDeleted);
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
                        lAccionDeleted = mysqlLineaAccionDao.get(1L);
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

        //Comprueba que lAccion ES nulo
        assertNull(lAccionDeleted);
    }

    @Test
    public void testListadoLineaAccion() {
        boolean exito = false;
        LineaAccion lAccion1 = new LineaAccion(null, "Linea de Acción de prueba para listado 1");
        LineaAccion lAccion2 = new LineaAccion(null, "Linea de Acción de prueba para listado 2");
        LineaAccion lAccion3 = new LineaAccion(null, "Linea de Acción de prueba para listado 3");
        List<LineaAccion> lAccionList = null;
        MySQLLineaAccionDAO mysqlLineaAccionDao = null;
        try {
            cnn = conectar(hosturl, usuario, password);
            cnn.setAutoCommit(false);
            try {
                mysqlLineaAccionDao = (MySQLLineaAccionDAO) MySqlFactoryDAO.getMySqlFactoryDAO(LineaAccion.class, cnn);
                mysqlLineaAccionDao.save(lAccion1);
                mysqlLineaAccionDao.save(lAccion2);
                mysqlLineaAccionDao.save(lAccion3);
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
                        lAccionList = mysqlLineaAccionDao.getAll();
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
        assertNotNull(lAccionList);
        System.out.println(lAccionList.toString());
        assertEquals(3, lAccionList.size());

    }

}
