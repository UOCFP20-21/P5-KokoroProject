/*package jpa;

import es.kokoro.dao.hibernate.JPASocioDAO;
import es.kokoro.enums.Periodo;
import es.kokoro.model.Persona;
import es.kokoro.model.Socio;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.List;

import static es.kokoro.commons.FormatFecha.FFStringToDate;
import static es.kokoro.commons.JPAManager.closeManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JPASocioDAOTest {

    boolean isId = false;
    @Test
    public void test2AgregarSocio() {
        JPASocioDAO jpaSocioDAO = new JPASocioDAO("KokoroTests");
        List<Socio> listaPrevia = jpaSocioDAO.getAll();
        Socio newSocio = null;
        try {
            Persona newPersona = new Persona(null, "Test: testAgregarSocios", "Apellidos Test 1", "00000000T",
                    "Española", "C/ de mi casa 1", "Mi pueblo", "600000000", "test@prueba1.com", FFStringToDate("1983-07-24"));
            newSocio = new Socio(newPersona, null, Periodo.ANU, 50.00, true);
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            jpaSocioDAO.save(newSocio);
        }

        List<Socio> listaTodos = jpaSocioDAO.getAll();

        assertEquals(listaTodos.size(), (listaPrevia.size()+1));
        closeManager(jpaSocioDAO.getManager());
    }

    @Test
    public void test1ActualizarSocio() {
        System.out.println("Test 3");
        JPASocioDAO jpaSocioDAO = new JPASocioDAO("KokoroTests");
        System.out.println("Test 4");
        Socio newSocio = null;
        System.out.println("Test 5");
        double nuevoValor = 233.00;
        try {
            nuevoValor = 233.00;
            Persona newPersona = new Persona(1L, "Me he cambiado el nombre 2", "Apellidos Test 1", "00000000T",
                    "Española", "C/ de mi casa 1", "Mi pueblo", "600000000", "test@prueba1.com", FFStringToDate("1983-07-24"));
            newSocio = new Socio(newPersona, 1L, Periodo.ANU, nuevoValor, true);
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            System.out.println("Test 6");
            jpaSocioDAO.update(newSocio);
            System.out.println("Test 7");
        }

        Socio nuevo = jpaSocioDAO.get(1L);

        System.out.println("Test " + nuevo.toString());
        assertEquals(nuevo.getCuota(), nuevoValor);
        closeManager(jpaSocioDAO.getManager());
    }
    @Test
    public void test0DeleteSocio() {
        JPASocioDAO jpaSocioDAO = new JPASocioDAO("KokoroTests");
        Socio newSocio = null;
        try {
            Persona newPersona = new Persona(null, "Test: testEliminarSocio", "Apellidos Test 1", "00000000T",
                    "Española", "C/ de mi casa 1", "Mi pueblo", "600000000", "test@prueba1.com", FFStringToDate("1983-07-24"));
            newSocio = new Socio(newPersona, null, Periodo.ANU, 50.00, true);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long currentID = jpaSocioDAO.save(newSocio, isId);
        Socio nuevo = jpaSocioDAO.get(currentID);
        jpaSocioDAO.delete(nuevo);

        assertNull(jpaSocioDAO.get(currentID));
        closeManager(jpaSocioDAO.getManager());
    }
}
*/