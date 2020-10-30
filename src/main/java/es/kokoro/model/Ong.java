package es.kokoro.model;

import java.util.List;

public class Ong {
    private Long idOng;
    private String nombreOng;
    private List<Delegacion> delegacionList;
    private List<Ingreso> ingresoList;
    private List<Socio> socioList;
    private List<Proyecto> proyectoList;
   // private Sede sede;


    public Ong(Long idOng, String nombreOng){
        this.idOng = idOng;
        this.nombreOng = nombreOng;
    }

    /***
     *
     * @param nombreOng Nombre de la ONG
     * @param delegacionList Listado de Delegaciones
     * @param ingresoList Listado de Ingresos
     * @param socioList Listado de socios
     * @param proyectoList Listado de Proyectos
      */
    public Ong(Long idOng, String nombreOng, List<Delegacion> delegacionList, List<Ingreso> ingresoList, List<Socio> socioList, List<Proyecto> proyectoList) {
        this.idOng = idOng;
        this.nombreOng = nombreOng;
        this.delegacionList = delegacionList;
        this.ingresoList = ingresoList;
        this.socioList = socioList;
        this.proyectoList = proyectoList;
        //this.sede = sede;
    }

    /***
     *
     * @return idOng
     */
    public Long getIdOng() {
        return idOng;
    }

    /***
     *
     * @param idOng
     */
    public void setIdOng(Long idOng) {
        this.idOng = idOng;
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
    public List<Ingreso> getIngresoList() {
        return ingresoList;
    }

    /***
     *
     * @param ingresoList ingresosList
     */
    public void setIngresoList(List<Ingreso> ingresoList) {
        this.ingresoList = ingresoList;
    }

    /***
     *
     * @return sociosList
     */
    public List<Socio> getSocioList() {
        return socioList;
    }

    /***
     *
     * @param socioList sociosList
     */
    public void setSocioList(List<Socio> socioList) {
        this.socioList = socioList;
    }

    /***
     *
     * @return proyectosList
     */
    public List<Proyecto> getProyectoList() {
        return proyectoList;
    }

    /***
     *
     * @param proyectoList
     */
    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }


    @Override
    public String toString() {
        return "Ong{" +
                "nombreOng='" + nombreOng + '\'' +
                ", delegacionList=" + delegacionList.toString() +
                ", ingresoList=" + ingresoList.toString() +
                ", socioList=" + socioList.toString() +
                ", proyectoList=" + proyectoList.toString() +
               '}';
    }
}