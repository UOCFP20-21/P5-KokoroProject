package es.kokoro.model;

import java.util.List;

public class Ong {

    /**
     * Nombre Ong
     */
    private String NombreOng;
    /**
     * Listado Delegaciones
     */
    private List<Delegacion> DelegacionList;
    /**
     * Listado Ingresos
     */
    private List<Ingresos> IngresosList;
    /**
     * Listado Socios
     */
    private List<Socio> SociosList;
    /**
     * Listado Proyectos
     */
    private List<Proyecto> ProyectosList;
    /**
     * Nombre Sede
     */
    private Sede Sede;

    public String getNombreOng() {
        return NombreOng;
    }

    public void setNombreOng(String nombreOng) {
        NombreOng = nombreOng;
    }

    public List<Delegacion> getDelegacionList() {
        return DelegacionList;
    }

    public void setDelegacionList(List<Delegacion> delegacionList) {
        DelegacionList = delegacionList;
    }

    public List<Ingresos> getIngresosList() {
        return IngresosList;
    }

    public void setIngresosList(List<Ingresos> ingresosList) {
        IngresosList = ingresosList;
    }

    public List<Socio> getSociosList() {
        return SociosList;
    }

    public void setSociosList(List<Socio> sociosList) {
        SociosList = sociosList;
    }

    public List<Proyecto> getProyectosList() {
        return ProyectosList;
    }

    public void setProyectosList(List<Proyecto> proyectosList) {
        ProyectosList = proyectosList;
    }

    public es.kokoro.model.Sede getSede() {
        return Sede;
    }

    public void setSede(es.kokoro.model.Sede sede) {
        Sede = sede;
    }

    public Ong(String nombreOng, List<Delegacion> delegacionList, List<Ingresos> ingresosList, List<Socio> sociosList, List<Proyecto> proyectosList, es.kokoro.model.Sede sede) {
        NombreOng = nombreOng;
        DelegacionList = delegacionList;
        IngresosList = ingresosList;
        SociosList = sociosList;
        ProyectosList = proyectosList;
        Sede = sede;
    }
}
