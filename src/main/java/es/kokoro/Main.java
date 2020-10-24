package es.kokoro;

import es.kokoro.dao.xml.XmlLineaAccionDAO;
import es.kokoro.dao.xml.XmlProyectoDAO;
import es.kokoro.dao.xml.XmlSubLineaAccionDAO;
import es.kokoro.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static es.kokoro.commons.FormatFecha.FFDateToString;
import static es.kokoro.commons.FormatFecha.FFStringToDate;

public class Main {

    public static void main(String[] args) throws Exception {
/*
        Long idProyecto;
        String codigoProyecto;
        String nombreProyecto;
        String pais;
        String localizacion;
        List<SocioLocal> socioLocalList = Collections.emptyList();
        List<Trabajador> trabajadorList = Collections.emptyList();
        List<Financiador> financiadorList = Collections.emptyList();
        List<Accion> accionList = Collections.emptyList();
        Date fechaInicio;
        Date fechaFin;
        List<SubLineaAccion> subLineaAccionList = Collections.emptyList();

        idProyecto = 4L;
        codigoProyecto = "04ESP / 0004";
        nombreProyecto = "Prueba cojonuda 4 - Con método incrustado";
        pais = "España";
        localizacion = "Baleares";
        fechaInicio = FFStringToDate("2020-01-25");
        fechaFin = FFStringToDate("2020-07-08");

        System.out.println(FFDateToString(fechaInicio));

        List<Proyecto> proyectosSocioLocal1 = Collections.emptyList();
        List<Proyecto> proyectosSocioLocal2 = Collections.emptyList();

        SocioLocal socioLocal1 = new SocioLocal(1L,"Socio Local Atemporal 1", "Marruecos","Tánger", "C/ de su casa, 1", "TangerSL", "C123456789L", "0034123456789", "tanger@tangersl.com", 1L,proyectosSocioLocal1 );
        SocioLocal socioLocal2 = new SocioLocal(2L,"Socio Local Temporal 2", "Marruecos","Tánger", "C/ de su casa, 2", "Tanger2SL", "C987456321L", "0034987456321", "info@tanger2sl.com", 2L,proyectosSocioLocal2 );

        socioLocalList = new ArrayList<SocioLocal>();
        socioLocalList.add(socioLocal1);
        socioLocalList.add(socioLocal2);

        LineaAccion lineaAccion = new LineaAccion(1L, "Apoyo internacional");
        Proyecto proyecto1 = new Proyecto(idProyecto, codigoProyecto, nombreProyecto, pais, localizacion, socioLocalList, trabajadorList, financiadorList, accionList, fechaInicio, fechaFin, lineaAccion, subLineaAccionList);

        XmlProyectoDAO nuevoXML = new XmlProyectoDAO();
        nuevoXML.save(proyecto1);

         /***
          * Cargamos listado de Proyectos
          * ***/
        /*XmlProyectoDAO getProyecto = new XmlProyectoDAO();
        Proyecto dameProyecto = getProyecto.get(2L);
        System.out.println("Proyecto a eliminar: "+dameProyecto.toString());

        getProyecto.delete(dameProyecto);
        System.out.println("Proyecto eliminado");
        */

        List<Proyecto> listadoProyectos;
        XmlProyectoDAO factoryProyectos = new XmlProyectoDAO();

        listadoProyectos = factoryProyectos.getAll();

        System.out.println(listadoProyectos.toString());


        /***
         * Creando Líneas de Acción
         *
        LineaAccion lineaPrueba = new LineaAccion(2L,"Linea de prueba 2 - Editada");
        XmlLineaAccionDAO lineaPruebaXml = new XmlLineaAccionDAO();
        LineaAccion lineaAEliminar = lineaPruebaXml.get(2L);
        System.out.println("Linea de Acción a eliminar: "+lineaAEliminar.toString());
        lineaPruebaXml.delete(lineaAEliminar);

        List<LineaAccion> lineaAccionList = lineaPruebaXml.getAll();
        System.out.println(lineaAccionList.toString());*/


        /***
         * Creando Sub Líneas de Acción
         */
         SubLineaAccion subLineaPrueba = new SubLineaAccion(2L,"Sub línea de Acción de prueba 2");
         XmlSubLineaAccionDAO subLineaPruebaXml = new XmlSubLineaAccionDAO();
         //LineaAccion subLineaAEliminar = subLineaPruebaXml.get(2L);
         //System.out.println("Linea de Acción a eliminar: "+subLineaAEliminar.toString());
         //subLineaPruebaXml.delete(subLineaAEliminar);

         subLineaPruebaXml.save(subLineaPrueba);

         List<SubLineaAccion> subLineaAccionList = subLineaPruebaXml.getAll();
         System.out.println(subLineaAccionList.toString());

    }
}
