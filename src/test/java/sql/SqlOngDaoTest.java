package sql;

import es.kokoro.dao.mysql.MySqlFactoryDAO;
import es.kokoro.dao.mysql.SqlOngDAO;
import es.kokoro.model.Ong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import static es.kokoro.commons.SqlConnection.desconectar;
import static org.junit.jupiter.api.Assertions.*;

public class SqlOngDaoTest {

    private Connection cnn;


    @BeforeEach
    public void truncarSQL() {
        try {
            cnn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?"
                    + "user=splanas&password=sara&serverTimezone=UTC");
            String query = "DELETE FROM ongs";
            Statement sQuery = cnn.createStatement();
            sQuery.execute(query);
            query = "ALTER TABLE ongs AUTO_INCREMENT = 1";
            sQuery = cnn.createStatement();
            sQuery.execute(query);
            System.out.println("Tabla ongs Truncada");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (cnn != null) {
                try {
                    desconectar(cnn);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }


    @Test
    public void guardarYLeerTest() throws Exception {
        Ong ong1 = new Ong(null, "OngPrueba");
        SqlOngDAO sqlOngDAO = (SqlOngDAO) MySqlFactoryDAO.getMySqlFactoryDAO(Ong.class);

        sqlOngDAO.save(ong1);

        Ong ong = sqlOngDAO.get(1);

        assertNotNull(ong);
        assertEquals(ong1.getNombreOng(), ong.getNombreOng());

    }


    @Test
    public void testActualizarOng() throws Exception {

        SqlOngDAO sqlOngDAO = (SqlOngDAO) MySqlFactoryDAO.getMySqlFactoryDAO(Ong.class);

        Ong ongPrueba = new Ong(null, "ongPrueba");

        sqlOngDAO.save(ongPrueba);

        Ong ongRecuperada = sqlOngDAO.get(1);

        ongRecuperada.setNombreOng("ongPrueba2");

        sqlOngDAO.update(ongRecuperada);

        Ong ongDespuesDeUpdate = sqlOngDAO.get(1);

        assertEquals("ongPrueba2", ongDespuesDeUpdate.getNombreOng());


    }


    @Test
    public void testBorrarOng() throws Exception {

        SqlOngDAO sqlOngDAO = (SqlOngDAO) MySqlFactoryDAO.getMySqlFactoryDAO(Ong.class);
        sqlOngDAO.save(new Ong(null, "ongPrueba"));

        Ong ongAntesDeBorrar = sqlOngDAO.get(1);

        assertNotNull(ongAntesDeBorrar);

        sqlOngDAO.delete(ongAntesDeBorrar);

        Ong ongDespuesDeBorrar = sqlOngDAO.get(ongAntesDeBorrar.getIdOng());

        assertNull(ongDespuesDeBorrar);


    }

    @Test
    public void testListadoOng() throws Exception {
        Ong ong1 = new Ong(null, "Entreculturas 1");
        Ong ong2 = new Ong(null, "Entreculturas 2");
        Ong ong3 = new Ong(null, "Entreculturas 3");

        SqlOngDAO sqlOngDAO = (SqlOngDAO) MySqlFactoryDAO.getMySqlFactoryDAO(Ong.class);

        sqlOngDAO.save(ong1);
        sqlOngDAO.save(ong2);
        sqlOngDAO.save(ong3);

        List<Ong> all = sqlOngDAO.getAll();

        assertEquals(3, all.size());

    }

}