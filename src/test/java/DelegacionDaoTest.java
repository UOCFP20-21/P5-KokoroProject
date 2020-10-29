import es.kokoro.dao.xml.XmlDelegacionDAO;
import es.kokoro.dao.xml.XmlFactoryDAO;
import es.kokoro.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DelegacionDaoTest {
    private final String xmlFile = "src/main/resources/xml/Delegaciones.xml";

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
    public void testAgregarDelegacion() throws Exception {

        Delegacion Delegacion1 = new Delegacion(1L,
                "Barcelona",
                "España",
                "Barcelona",
                "Av. Maresme, 32",
                "Kokoro, S.A.",
                "123456789Z",
                "939998855",
                "kokorobarcelona@kokoro.es",
                1L,
                trabajadorList,
                ong,
                ""
        );

        XmlDelegacionDAO xmlDelegacionDAO = (XmlDelegacionDAO) XmlFactoryDAO.getXmlFactoryDao(Delegacion.class, xmlFile);
        xmlDelegacionDAO.save(Delegacion1);
        //Comprueba que delegacion no es nulo
        assertNotNull(Delegacion1);
    }

    @Test
    public void testListadoDelegacion() throws Exception {
        Delegacion Delegacion1 = new Delegacion(1L,
                "Barcelona",
                "España",
                "Barcelona",
                "Av. Maresme, 32",
                "Kokoro BArcelona, S.A.",
                "123456789Z",
                "939998855",
                "kokorobarcelona@kokoro.es",
                1L,
                trabajadorList,
                ong,
                ""
        );
        Delegacion Delegacion2 = new Delegacion(2L,
                "Madrid",
                "España",
                "Madrid",
                "Plaza España,1",
                "Kokoro Madrid, S.A.",
                "123456789Z",
                "955656968",
                "kokoromadrid@kokoro.es",
                2L,
                trabajadorList,
                ong,
                ""
        );
        Delegacion Delegacion3 = new Delegacion(3L,
                "Mallorca",
                "España",
                "Mallorca",
                "C/Lorca,1",
                "Kokoro Mallorca, S.A.",
                "112365478Z",
                "939998855",
                "kokoromallorca@kokoro.es",
                3L,
                trabajadorList,
                ong,
                ""
        );
        XmlDelegacionDAO xmlDelegacionDAO = (XmlDelegacionDAO) XmlFactoryDAO.getXmlFactoryDao(Delegacion.class, xmlFile);
        xmlDelegacionDAO.save(Delegacion1);
        xmlDelegacionDAO.save(Delegacion2);
        xmlDelegacionDAO.save(Delegacion3);
        List<Delegacion> dDelegacionList = xmlDelegacionDAO.getAll();

        //Comprueba que dDelegacionList no es nulo
        assertNotNull(dDelegacionList);
        assertEquals(3, dDelegacionList.size());
    }
    @Test
    public void testLeerDelegacion() throws Exception{
        Delegacion Delegacion3 = new Delegacion(3L,
                "Mallorca",
                "España",
                "Mallorca",
                "C/Lorca,1",
                "Kokoro Mallorca, S.A.",
                "112365478Z",
                "939998855",
                "kokoromallorca@kokoro.es",
                3L,
                trabajadorList,
                ong,
                ""
        );
        XmlDelegacionDAO xmlDelegacionDAO = (XmlDelegacionDAO) XmlFactoryDAO.getXmlFactoryDao(Delegacion.class, xmlFile);
        xmlDelegacionDAO.save(Delegacion3);
        xmlDelegacionDAO.get(3L);
        //Comprueba que delegacion no es nulo
        assertNotNull(Delegacion3);
    }

    @Test
    public void testActualizarDelegacion() throws Exception {
        Delegacion Delegacion3 = new Delegacion(3L,
                "Mallorca",
                "España",
                "Mallorca",
                "C/Lorca,1",
                "Kokoro Mallorca, S.A.",
                "112365478Z",
                "939998855",
                "kokoromallorca@kokoro.es",
                3L,
                trabajadorList,
                ong,
                ""
        );
        XmlDelegacionDAO xmlDelegacionDAO = (XmlDelegacionDAO) XmlFactoryDAO.getXmlFactoryDao(Delegacion.class, xmlFile);
        xmlDelegacionDAO.save(Delegacion3);

        Delegacion3.setDireccionSocial("Av. Maresme, 32");
        xmlDelegacionDAO.update(Delegacion3);
        Delegacion dAccionUpdated = xmlDelegacionDAO.get(3L);

        //Comprueba que Delegacion no es nulo
        assertNotNull(Delegacion3);
        assertEquals("Av. Maresme, 32", dAccionUpdated.getDireccionSocial());

    }

    @Test
    public void testBorrarDelegacion() throws Exception {
        Delegacion Delegacion1 = new Delegacion(1L,
                "Barcelona",
                "España",
                "Barcelona",
                "Av. Maresme, 32",
                "Kokoro, S.A.",
                "123456789Z",
                "939998855",
                "kokorobarcelona@kokoro.es",
                1L,
                trabajadorList,
                ong,
                ""
        );
        XmlDelegacionDAO xmlDelegacionDAO = (XmlDelegacionDAO) XmlFactoryDAO.getXmlFactoryDao(Delegacion.class, xmlFile);
        xmlDelegacionDAO.save(Delegacion1);
        xmlDelegacionDAO.delete(Delegacion1);
        Delegacion dDelegacionBorrado = xmlDelegacionDAO.get(1L);
        //Comprueba que dDelegacion ES nulo
        assertNull(dDelegacionBorrado);

    }
}

