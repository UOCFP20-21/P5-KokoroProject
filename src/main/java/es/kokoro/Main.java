package es.kokoro;

import es.kokoro.dao.xml.XmlProyectoDAO;
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

        idProyecto = 1L;
        codigoProyecto = "01ESP / 0001";
        nombreProyecto = "Prueba cojonuda";
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
        List<Proyecto> listadoProyectos;
        XmlProyectoDAO factoryProyectos = new XmlProyectoDAO();

        listadoProyectos = factoryProyectos.getAll();

        System.out.println(listadoProyectos.toString());

    }
}
