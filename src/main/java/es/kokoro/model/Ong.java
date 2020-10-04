package es.kokoro.model;

import java.util.List;

public class Ong {

    /**
     * Nombre Ong
     */
    private String nombreOng;
    /**
     * Listado Delegaciones
     */
    private List<Delegacion> delegacionList;
    /**
     * Listado Ingresos
     */
    private List<Ingresos> ingresosList;
    /**
     * Listado Socios
     */
    private List<Socio> sociosList;
    /**
     * Listado Proyectos
     */
    private List<Proyecto> proyectosList;
    /**
     * Nombre Sede
     */
    private Sede sede;

    public Ong(String nombreOng, List<Delegacion> delegacionList, List<Ingresos> ingresosList, List<Socio> sociosList, List<Proyecto> proyectosList, Sede sede) {
        this.nombreOng = nombreOng;
        this.delegacionList = delegacionList;
        this.ingresosList = ingresosList;
        this.sociosList = sociosList;
        this.proyectosList = proyectosList;
        this.sede = sede;
    }

    public String getNombreOng() {
        return nombreOng;
    }

    public void setNombreOng(String nombreOng) {
        this.nombreOng = nombreOng;
    }

    public List<Delegacion> getDelegacionList() {
        return delegacionList;
    }

    public void setDelegacionList(List<Delegacion> delegacionList) {
        this.delegacionList = delegacionList;
    }

    public List<Ingresos> getIngresosList() {
        return ingresosList;
    }

    public void setIngresosList(List<Ingresos> ingresosList) {
        this.ingresosList = ingresosList;
    }

    public List<Socio> getSociosList() {
        return sociosList;
    }

    public void setSociosList(List<Socio> sociosList) {
        this.sociosList = sociosList;
    }

    public List<Proyecto> getProyectosList() {
        return proyectosList;
    }

    public void setProyectosList(List<Proyecto> proyectosList) {
        this.proyectosList = proyectosList;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }
}