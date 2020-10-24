package es.kokoro;

import es.kokoro.dao.xml.XmlLineaAccionDAO;
import es.kokoro.dao.xml.XmlProyectoDAO;
import es.kokoro.dao.xml.XmlSubLineaAccionDAO;
import es.kokoro.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static es.kokoro.commons.FormatFecha.FFDateToString;
import static es.kokoro.commons.FormatFecha.FFStringToDate;

public class Main {

    public static void main(String[] args) throws Exception {

        XmlProyectoDAO factoryProyectos = new XmlProyectoDAO();
        XmlLineaAccionDAO lineaPruebaXml = new XmlLineaAccionDAO();
        XmlSubLineaAccionDAO subLineaPruebaXml = new XmlSubLineaAccionDAO();
/*
        Long idProyecto;
        String codigoProyecto;
        String nombreProyecto;
        String pais;
        String localizacion;
        List<SocioLocal> socioLocalList = new ArrayList<SocioLocal>();
        List<Trabajador> trabajadorList = new ArrayList<Trabajador>();
        List<Financiador> financiadorList = new ArrayList<Financiador>();
        List<Accion> accionList = new ArrayList<Accion>();
        Date fechaInicio;
        Date fechaFin;
        List<SubLineaAccion> subLineaAccionList = new ArrayList<SubLineaAccion>();

        idProyecto = 6L;
        codigoProyecto = "06ESP / 0006";
        nombreProyecto = "Prueba cojonuda 6";
        pais = "España";
        localizacion = "Baleares";
        fechaInicio = FFStringToDate("2020-01-01");
        fechaFin = FFStringToDate("2020-09-28");

        System.out.println(FFDateToString(fechaInicio));

        List<Proyecto> proyectosSocioLocal1 = Collections.emptyList();
        List<Proyecto> proyectosSocioLocal2 = Collections.emptyList();

        SocioLocal socioLocal1 = new SocioLocal(1L,"Socio Local Atemporal 1", "Marruecos","Tánger", "C/ de su casa, 1", "TangerSL", "C123456789L", "0034123456789", "tanger@tangersl.com", 1L,proyectosSocioLocal1 );
        SocioLocal socioLocal2 = new SocioLocal(2L,"Socio Local Temporal 2", "Marruecos","Tánger", "C/ de su casa, 2", "Tanger2SL", "C987456321L", "0034987456321", "info@tanger2sl.com", 2L,proyectosSocioLocal2 );
*/
        Proyecto proyecto1 = factoryProyectos.get(4L);

        LineaAccion lineaAccion = lineaPruebaXml.get(3L);
        proyecto1.setLineaAccion(lineaAccion);
        SubLineaAccion subLineaAccion1 = subLineaPruebaXml.get(2L);
        SubLineaAccion subLineaAccion2 = subLineaPruebaXml.get(3L);

        List<SubLineaAccion> subLineaAccionList = new ArrayList<SubLineaAccion>();
        subLineaAccionList.add(subLineaAccion1);
        subLineaAccionList.add(subLineaAccion2);
        proyecto1.setSubLineaAccionList(subLineaAccionList);
        //Proyecto proyecto1 = new Proyecto(idProyecto, codigoProyecto, nombreProyecto, pais, localizacion, socioLocalList, trabajadorList, financiadorList, accionList, fechaInicio, fechaFin, lineaAccion, subLineaAccionList);


         /***
          * Cargamos listado de Proyectos
          * ***/

        factoryProyectos.save(proyecto1);


        //Proyecto dameProyecto = getProyecto.get(2L);
        //System.out.println("Proyecto a eliminar: "+dameProyecto.toString());

        //getProyecto.delete(dameProyecto);
        //System.out.println("Proyecto eliminado");


        List<Proyecto> listadoProyectos;
        listadoProyectos = factoryProyectos.getAll();

        System.out.println(listadoProyectos.toString());


        /***
         * Creando Líneas de Acción
         **/
        //LineaAccion lineaPrueba1 = new LineaAccion(1L,"Linea de prueba");
        //LineaAccion lineaPrueba2 = new LineaAccion(2L,"Linea de prueba 2");
        //LineaAccion lineaPrueba3 = new LineaAccion(3L,"Linea de prueba 3");

        //lineaPruebaXml.save(lineaPrueba1);
        //lineaPruebaXml.save(lineaPrueba2);
        //lineaPruebaXml.save(lineaPrueba3);
       // LineaAccion lineaAEliminar = lineaPruebaXml.get(2L);
        //System.out.println("Linea de Acción a eliminar: "+lineaAEliminar.toString());
        //lineaPruebaXml.delete(lineaAEliminar);

        List<LineaAccion> lineaAccionList = lineaPruebaXml.getAll();
        System.out.println(lineaAccionList.toString());


        /***
         * Creando Sub Líneas de Acción
         */
        //SubLineaAccion subLineaPrueba1 = new SubLineaAccion(1L,"Sub línea de Acción de prueba 1");
        //SubLineaAccion subLineaPrueba2 = new SubLineaAccion(2L,"Sub línea de Acción de prueba 2");
        //SubLineaAccion subLineaPrueba3 = new SubLineaAccion(3L,"Sub línea de Acción de prueba 3");
        //SubLineaAccion subLineaPrueba4 = new SubLineaAccion(4L,"Sub línea de Acción de prueba 4");

         //LineaAccion subLineaAEliminar = subLineaPruebaXml.get(2L);
         //System.out.println("Linea de Acción a eliminar: "+subLineaAEliminar.toString());
         //subLineaPruebaXml.delete(subLineaAEliminar);

        //subLineaPruebaXml.save(subLineaPrueba1);
        //subLineaPruebaXml.save(subLineaPrueba2);
        //subLineaPruebaXml.save(subLineaPrueba3);
        //subLineaPruebaXml.save(subLineaPrueba4);

        subLineaAccionList = subLineaPruebaXml.getAll();
        System.out.println(subLineaAccionList.toString());

    }
}
