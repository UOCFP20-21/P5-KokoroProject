package es.kokoro.model;

import java.util.List;

public class Ong {

    private String nombreOng;
    private List<Delegacion> delegacionList;
    private List<Ingreso> ingresosList;
    private List<Socio> sociosList;
    private List<Proyecto> proyectosList;
    private Sede sede;

    /***
     *
     * @param nombreOng Nombre de la ONG
     * @param delegacionList Listado de Delegaciones
     * @param ingresosList Listado de Ingresos
     * @param sociosList Listado de socios
     * @param proyectosList Listado de Proyectos
     * @param sede Objeto de Tipo Sede
     */
    public Ong(String nombreOng, List<Delegacion> delegacionList, List<Ingreso> ingresosList, List<Socio> sociosList, List<Proyecto> proyectosList, Sede sede) {
        this.nombreOng = nombreOng;
        this.delegacionList = delegacionList;
        this.ingresosList = ingresosList;
        this.sociosList = sociosList;
        this.proyectosList = proyectosList;
        this.sede = sede;
    }

    /***
     *
     * @return nombre ONG
     */
    public String getNombreOng() {
        return nombreOng;
    }

    /***
     *
     * @param nombreOng
     */
    public void setNombreOng(String nombreOng) {
        this.nombreOng = nombreOng;
    }

    /***
     *
     * @return delegacionList
     */
    public List<Delegacion> getDelegacionList() {
        return delegacionList;
    }

    /***
     *
     * @param delegacionList
     */
    public void setDelegacionList(List<Delegacion> delegacionList) {
        this.delegacionList = delegacionList;
    }

    /***
     *
     * @return ingresosList
     */
    public List<Ingreso> getIngresosList() {
        return ingresosList;
    }

    /***
     *
     * @param ingresosList ingresosList
     */
    public void setIngresosList(List<Ingreso> ingresosList) {
        this.ingresosList = ingresosList;
    }

    /***
     *
     * @return sociosList
     */
    public List<Socio> getSociosList() {
        return sociosList;
    }

    /***
     *
     * @param sociosList sociosList
     */
    public void setSociosList(List<Socio> sociosList) {
        this.sociosList = sociosList;
    }

    /***
     *
     * @return proyectosList
     */
    public List<Proyecto> getProyectosList() {
        return proyectosList;
    }

    /***
     *
     * @param proyectosList
     */
    public void setProyectosList(List<Proyecto> proyectosList) {
        this.proyectosList = proyectosList;
    }

    /***
     *
     * @return sede
     */
    public Sede getSede() {
        return sede;
    }

    /***
     *
     * @param sede
     */
    public void setSede(Sede sede) {
        this.sede = sede;
    }

    @Override
    public String toString() {
        return "Ong{" +
                "nombreOng='" + nombreOng + '\'' +
                ", delegacionList=" + delegacionList.toString() +
                ", ingresosList=" + ingresosList.toString() +
                ", sociosList=" + sociosList.toString() +
                ", proyectosList=" + proyectosList.toString() +
                ", sede=" + sede.toString() +
                '}';
    }
}