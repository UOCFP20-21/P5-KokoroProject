import es.kokoro.dao.xml.XmlFactoryDAO;
import es.kokoro.enums.Periodo;
import es.kokoro.model.Socio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.kokoro.dao.xml.XmlSocioDAO;
import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SocioDaoTest {

    private final String xmlFile = "src/test/resources/Socios.xml";
    @BeforeEach
    public void limpiarFichero() {
        File fichero = new File(xmlFile);

        if (fichero.exists()) {
            fichero.delete();
        }

    }
    @AfterEach
    public void limpiarFicheroDespues() {
        File fichero = new File(xmlFile);

        if (fichero.exists()) {
            fichero.delete();
        }

    }

    @Test
    public void testAgregarSocio() throws Exception {
        Socio socio1 = new Socio(1L, "Daniella", "Robles",
                "12345678G", "española", "mi casa, 32",
                "Barcelona", "933535353", "daniella@test.com", 1L,
                Periodo.TRI, 200.0, true);
        XmlSocioDAO xmlSocioDao = (XmlSocioDAO) XmlFactoryDAO.getXmlFactoryDao(Socio.class, xmlFile);
        xmlSocioDao.save(socio1);
        //Comprueba que socio no es nulo
        assertNotNull(socio1);
    }

    @Test
    public void testListadoSocio() throws Exception {
        Socio socio1 = new Socio(1L, "Daniella", "Robles",
                "12345678G", "española", "mi casa, 32",
                "Barcelona", "933535353", "daniella@test.com", 1L,
                Periodo.TRI, 200.0, true);
        Socio socio2 = new Socio(2L, "Alex", "Robles",
                "98765432G", "española", "Calle rio mora, 25",
                "Barcelona", "678145555", "alex@test.com", 2L,
                Periodo.MES, 30.0, true);
        Socio socio3 = new Socio(3L, "Sara", "Planas",
                "12365478A", "francesa", "calle nou pins, 12",
                "Barcelona", "654987123", "sara@test.com", 3L,
                Periodo.ANU, 500.0, true);
        XmlSocioDAO xmlSocioDao = (XmlSocioDAO) XmlFactoryDAO.getXmlFactoryDao(Socio.class, xmlFile);
        xmlSocioDao.save(socio1);
        xmlSocioDao.save(socio2);
        xmlSocioDao.save(socio3);
        List<Socio> socioList = xmlSocioDao.getAll();

        //Comprueba que socio no es nulo
        assertNotNull(socioList);
        assertEquals(3, socioList.size());
    }

    @Test
    public void testLeerSocio() throws Exception {
        Socio socio1 = new Socio(1L, "Daniella", "Robles",
                "12345678G", "española", "mi casa, 32",
                "Barcelona", "933535353", "daniella@test.com", 1L,
                Periodo.TRI, 200.0, true);
        XmlSocioDAO xmlSocioDao = (XmlSocioDAO) XmlFactoryDAO.getXmlFactoryDao(Socio.class, xmlFile);
        xmlSocioDao.save(socio1);
        xmlSocioDao.get(1L);
        //Comprueba que socio no es nulo
        assertNotNull(socio1);
    }

    @Test
    public void testActualizarSocio() throws Exception {
        Socio socio1 = new Socio(1L, "Daniella", "Robles",
                "12345678G", "española", "mi casa, 32",
                "Barcelona", "933535353", "daniella@test.com", 1L,
                Periodo.TRI, 200.0, true);

        XmlSocioDAO xmlSocioDao = (XmlSocioDAO) XmlFactoryDAO.getXmlFactoryDao(Socio.class, xmlFile);
        xmlSocioDao.save(socio1);
        socio1.setCuota(50.0);
        xmlSocioDao.update(socio1);
        Socio socioActualizado = xmlSocioDao.get(1L);

        //Comprueba que socio no es nulo
        assertNotNull(socio1);
        assertEquals(50, socioActualizado.getCuota());
    }

    @Test
    public void testBorrarSocio() throws Exception {
        Socio socio1 = new Socio(1L, "Daniella", "Robles",
                "12345678G", "española", "mi casa, 32",
                "Barcelona", "933535353", "daniella@test.com", 1L,
                Periodo.TRI, 200.0, true);

        XmlSocioDAO xmlSocioDao = (XmlSocioDAO) XmlFactoryDAO.getXmlFactoryDao(Socio.class, xmlFile);
        xmlSocioDao.save(socio1);
        xmlSocioDao.delete(socio1);
        Socio socioBorrado = xmlSocioDao.get(1L);
        //Comprueba que socio no es nulo
        assertNull(socioBorrado);
    }
}


