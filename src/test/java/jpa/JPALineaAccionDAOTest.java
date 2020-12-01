package jpa;

import es.kokoro.dao.hibernate.JPALineaAccionDAO;
import es.kokoro.model.LineaAccion;
import org.junit.jupiter.api.Test;


import static es.kokoro.commons.JPAManager.closeManager;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Random;

public class JPALineaAccionDAOTest {

    boolean isId = false;
    @Test
    public void test2AgregarLineaAccion() {
        JPALineaAccionDAO jpaLineaAccion = new JPALineaAccionDAO("KokoroTests");
        List<LineaAccion> listaPrevia = jpaLineaAccion.getAll();
        LineaAccion newLA = new LineaAccion(null, "Linea de Test Agregar Linea de acción "+listaPrevia.size());
        jpaLineaAccion.save(newLA);
        List<LineaAccion> listaTodos = jpaLineaAccion.getAll();

        assertEquals(listaTodos.size(), (listaPrevia.size()+1));
        closeManager(jpaLineaAccion.getManager());
    }

    @Test
    public void test1ActualizarLineaAccion() {
        JPALineaAccionDAO jpaLineaAccion = new JPALineaAccionDAO("KokoroTests");
        List<LineaAccion> listaTodos = jpaLineaAccion.getAll();
        String nuevoValor = "Linea de Accion Actualizar Linea 1L con el total de líneas: "+listaTodos.size();
        LineaAccion newLA = new LineaAccion(1L, nuevoValor);
        jpaLineaAccion.update(newLA);
        LineaAccion nuevo = jpaLineaAccion.get(1L);

        assertEquals(nuevo.getLinea(), nuevoValor);
        closeManager(jpaLineaAccion.getManager());
    }
    @Test
    public void test0DeleteLineaAccion() {
        JPALineaAccionDAO jpaLineaAccion = new JPALineaAccionDAO("KokoroTests");
        Random rand = new Random();
        // Agregamos una linea de Accion y recuperamos su ID
        LineaAccion newLA = new LineaAccion(null, "Linea de Accion Test para eliminar: "+rand.nextFloat());
        Long currentID = jpaLineaAccion.save(newLA, isId);

        LineaAccion nuevo = jpaLineaAccion.get(currentID);
        System.out.println(nuevo.toString());
        jpaLineaAccion.delete(nuevo);

        assertNull(jpaLineaAccion.get(currentID));
        closeManager(jpaLineaAccion.getManager());
    }
}
