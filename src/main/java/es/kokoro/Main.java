package es.kokoro;

import es.kokoro.dao.xml.*;
import es.kokoro.enums.Periodo;
import es.kokoro.model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static es.kokoro.commons.FormatFecha.FFDateToString;
import static es.kokoro.commons.FormatFecha.FFStringToDate;
import static es.kokoro.commons.sqlConection.conectar;
import static es.kokoro.commons.sqlConection.desconectar;

public class Main {

    public static void main(String[] args) throws Exception {
        Connection connection = null;
        try{
            connection = conectar();
            LineaAccion nuevaLineaAccion = new LineaAccion(null, "Cooperación al Desarrollo");


        }catch (SQLException e)
        {
            e.printStackTrace();
        }finally {

            if(connection != null)
            {
                desconectar(connection);
            }
        }




        //XmlProyectoDAO factoryProyectos = new XmlProyectoDAO();
        //XmlLineaAccionDAO lineaPruebaXml = new XmlLineaAccionDAO();
        //XmlSubLineaAccionDAO subLineaPruebaXml = new XmlSubLineaAccionDAO();
        //XmlSocioDAO xmlSocioDao = new XmlSocioDAO();
        //XmlDelegacionDAO xmlDelegacionDAO = new XmlDelegacionDAO();
        /*List<Trabajador> listTrabajador = new ArrayList<>();

        List<Delegacion> delegacionLista = new ArrayList<>();
        List<Socio> socioList =  new ArrayList<>();
        List<Ingreso> ingresoList = new ArrayList<>();

        XmlProyectoDAO proyectoData = new XmlProyectoDAO();
        List<Proyecto> proyectoList = proyectoData.getAll();


        Ong ong = new Ong(
                1L,
                "Kokoro Sin Fronteras",
                delegacionLista,
                ingresoList,
                socioList,
                proyectoList

        );

        Delegacion delegacion25 = new Delegacion(2L,"Amor, SA", "Francia", "Paris", "tttt", "tttt", "tttt",
                "123456789Z", "937998877",2L,listTrabajador,ong,"Barcelona");

      xmlDelegacionDAO.save(delegacion25);
     // Delegacion delegacionBorrar = xmlDelegacionDAO.get(2L);
    //  System.out.println(delegacionBorrar.toString());
       // Delegacion delegacionBorrar = xmlDelegacionDAO.get(1L);
       // System.out.println(delegacionBorrar.toString());
        //xmlDelegacionDAO.delete(delegacionBorrar);
        List<Delegacion> delegacionList = xmlDelegacionDAO.getAll();
        System.out.println(delegacionList.toString());

     /***  Socio socio1 = new Socio(2L, "hjkg", "jgg",
                "677648268", "SP", "test",
                "test", "test", "test@test.com", 2L,
                Periodo.MES, 100.0, true);

       xmlSocioDao.save(socio1);
       System.out.println("creación de socio");
        List<Socio> socioList = xmlSocioDao.getAll();
       System.out.println(socioList.toString()); ***/
          /*  Socio socio1Recuperado = xmlSocioDao.get(socio1.getIdSocio());
            assert socio1Recuperado != null;
            System.out.println(socio1Recuperado.getIdSocio());
            System.out.println("Leer socio");
            List<Socio> socioList = xmlSocioDao.getAll();
            System.out.println(socioList);
            System.out.println("listado de socio");*/
       // socio1.setCuota(40.0);
       /* xmlSocioDao.update(socio1);
        System.out.println(socio1);
        System.out.println("imprime actualización de socio");
        xmlSocioDao.delete(socio1.getIdSocio());
        System.out.println("socio borrado");*/

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
        //Proyecto proyecto1 = factoryProyectos.get(5L);

        //LineaAccion lineaAccion = lineaPruebaXml.get(3L);
        //proyecto1.setLineaAccion(lineaAccion);
        //SubLineaAccion subLineaAccion1 = subLineaPruebaXml.get(2L);
        //SubLineaAccion subLineaAccion2 = subLineaPruebaXml.get(3L);

        //List<SubLineaAccion> subLineaAccionList = new ArrayList<SubLineaAccion>();
        //subLineaAccionList.add(subLineaAccion1);
        //subLineaAccionList.add(subLineaAccion2);
        //proyecto1.setSubLineaAccionList(subLineaAccionList);
        //Proyecto proyecto1 = new Proyecto(idProyecto, codigoProyecto, nombreProyecto, pais, localizacion, socioLocalList, trabajadorList, financiadorList, accionList, fechaInicio, fechaFin, lineaAccion, subLineaAccionList);
        //Date nuevaFechaInicio = FFStringToDate("2019-12-05");
        //proyecto1.setFechaInicio(nuevaFechaInicio);
/*
         /***
          * Cargamos listado de Proyectos
          * ***/
       /* Proyecto proyecto4_v1 = factoryProyectos.get(5L);
        System.out.println(proyecto4_v1.getLineaAccion().getLinea());
        LineaAccion editada = lineaPruebaXml.get(2L);
        editada.setLinea("Editamos la linea 2");
        lineaPruebaXml.save(editada);

        Proyecto proyecto4_v2 = factoryProyectos.get(5L);
        System.out.println(proyecto4_v2.getLineaAccion().getLinea());*/


        //factoryProyectos.save(proyecto1);


        //Proyecto dameProyecto = getProyecto.get(2L);
        //System.out.println("Proyecto a eliminar: "+dameProyecto.toString());

        //getProyecto.delete(dameProyecto);
        //System.out.println("Proyecto eliminado");

        /*
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

        //List<LineaAccion> lineaAccionList = lineaPruebaXml.getAll();
        //System.out.println(lineaAccionList.toString());
/*

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

        //subLineaAccionList = subLineaPruebaXml.getAll();
        //System.out.println(subLineaAccionList.toString());

    }
}
