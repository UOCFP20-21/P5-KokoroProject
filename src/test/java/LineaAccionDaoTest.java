import es.kokoro.dao.xml.XmlFactoryDAO;
import es.kokoro.dao.xml.XmlLineaAccionDAO;
import es.kokoro.model.LineaAccion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class LineaAccionDaoTest
{
    private final String xmlFile = "src/test/resources/LineasAccion.xml";

    @BeforeEach
    public void limpiarFichero()
    {
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
    public void testAgregarLineaAccion() throws Exception {
        LineaAccion lAccion1 = new LineaAccion(1L, "Linea de Acción de prueba");

        XmlLineaAccionDAO xmlLineaAccionDao = (XmlLineaAccionDAO) XmlFactoryDAO.getXmlFactoryDao(LineaAccion.class, xmlFile);
        xmlLineaAccionDao.save(lAccion1);
        //Comprueba que socio no es nulo
        assertNotNull(lAccion1);
    }


    @Test
    public void testListadoLineaAccion() throws Exception {
        LineaAccion lAccion1 = new LineaAccion(1L, "Linea de Acción de prueba para listado 1");
        LineaAccion lAccion2 = new LineaAccion(2L, "Linea de Acción de prueba para listado 2");
        LineaAccion lAccion3 = new LineaAccion(3L, "Linea de Acción de prueba para listado 3");
        XmlLineaAccionDAO xmlLineaAccionDao = (XmlLineaAccionDAO) XmlFactoryDAO.getXmlFactoryDao(LineaAccion.class, xmlFile);
        xmlLineaAccionDao.save(lAccion1);
        xmlLineaAccionDao.save(lAccion2);
        xmlLineaAccionDao.save(lAccion3);
        List<LineaAccion> lAccionList = xmlLineaAccionDao.getAll();

        //Comprueba que lAccion no es nulo
        assertNotNull(lAccionList);
        assertEquals(3, lAccionList.size());
    }

    @Test
    public void testLeerLineaAccion() throws Exception {
        LineaAccion lAccion1 = new LineaAccion(1L, "Linea de Acción de prueba para Leer 1");
        XmlLineaAccionDAO xmlLineaAccionDao = (XmlLineaAccionDAO) XmlFactoryDAO.getXmlFactoryDao(LineaAccion.class, xmlFile);
        xmlLineaAccionDao.save(lAccion1);
        LineaAccion lAccionGuardada = xmlLineaAccionDao.get(1L);

        //Comprueba que lAccion no es nulo
        assertNotNull(lAccionGuardada);
    }

    @Test
    public void testActualizarLineaAccion() throws Exception {
        LineaAccion lAccion1 = new LineaAccion(1L, "Linea de Acción de prueba para actualizar");
        XmlLineaAccionDAO xmlLineaAccionDao = (XmlLineaAccionDAO) XmlFactoryDAO.getXmlFactoryDao(LineaAccion.class, xmlFile);
        xmlLineaAccionDao.save(lAccion1);

        lAccion1.setLinea("Linea de Acción actualizada");
        xmlLineaAccionDao.update(lAccion1);

        LineaAccion lAccionUpdated = xmlLineaAccionDao.get(1L);

        //Comprueba que lAccion no es nulo
        assertNotNull(lAccionUpdated);
        assertEquals("Linea de Acción actualizada", lAccionUpdated.getLinea());

    }


    @Test
    public void testBorrarLineaAccuion() throws Exception {
        LineaAccion lAccion1 = new LineaAccion(1L, "Linea de Acción de prueba para borrar");
        XmlLineaAccionDAO xmlLineaAccionDao = (XmlLineaAccionDAO) XmlFactoryDAO.getXmlFactoryDao(LineaAccion.class, xmlFile);
        xmlLineaAccionDao.save(lAccion1);
        xmlLineaAccionDao.delete(lAccion1);
        LineaAccion lAccionBorrado = xmlLineaAccionDao.get(1L);
        //Comprueba que lAccion ES nulo
        assertNull(lAccionBorrado);
    }
}