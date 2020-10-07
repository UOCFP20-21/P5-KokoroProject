package es.kokoro.model;

import java.util.Date;
import java.util.List;

/**
 * Clase Proyecto
 * Contiene informacion del Proyecto
 */

public class Proyecto {

    //Atributos
    private Long idProyecto;
    private String codigoProyecto;
    private String nombreProyecto;
    private String pais;
    private String localizacion;
    private List<SocioLocal> socioLocalList;
    private List<Trabajador> trabajadorList;
    private List<Financiador> financiadorList;
    private List<Accion> accionList;
    private Date fechaInicio;
    private Date fechaFin;
    private LineaAccion lineaAccion;
    private List<SubLineaAccion> subLineaAccionList;

    /***
     * Constructor de la clase Proyecto
     * @param idProyecto Índice del Proyecto
     * @param codigoProyecto Código del Proyecto
     * @param nombreProyecto Nombre del Proyecto
     * @param pais País en el que se realizará el proyecto
     * @param localizacion Localización donde tendrá lugar el proyecto
     * @param socioLocalList Listado de socios Locales participantes
     * @param trabajadorList Listado de Trabajadores participantes
     * @param financiadorList Listado de Financiadores
     * @param accionList Listado de Acciones a realizar
     * @param fechaInicio Fecha de Inicio del Proyecto
     * @param fechaFin Fecha de Fin del Proyecto
     * @param lineaAccion Lína de Acción principal del Proyecto
     * @param subLineaAccionList Sublíneas de Acción del proyecto.
     */
    public Proyecto(Long idProyecto, String codigoProyecto, String nombreProyecto, String pais, String localizacion, List<SocioLocal> socioLocalList, List<Trabajador> trabajadorList, List<Financiador> financiadorList, List<Accion> accionList, Date fechaInicio, Date fechaFin, LineaAccion lineaAccion, List<SubLineaAccion> subLineaAccionList) {
        this.idProyecto = idProyecto;
        this.codigoProyecto = codigoProyecto;
        this.nombreProyecto = nombreProyecto;
        this.pais = pais;
        this.localizacion = localizacion;
        this.socioLocalList = socioLocalList;
        this.trabajadorList = trabajadorList;
        this.financiadorList = financiadorList;
        this.accionList = accionList;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.lineaAccion = lineaAccion;
        this.subLineaAccionList = subLineaAccionList;
    }


    //Metodos publicos

    /***
     * Devuelve el ID del Proyecto
     * @return idProyecto
     */
    public Long getIdProyecto() {
        return idProyecto;
    }

    /***
     *
     * @param idProyecto
     */
    public void setIdProyecto(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    /***
     * Devuelve el código del Proyecto
     * @return codigoProyecto
     */
    public String getCodigoProyecto() {
        return codigoProyecto;
    }

    /***
     *
     * @param codigoProyecto
     */
    public void setCodigoProyecto(String codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
    }

    /***
     *
     * @return nombreProyecto
     */
    public String getNombreProyecto() {
        return nombreProyecto;
    }

    /***
     *
     * @param nombreProyecto
     */
    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    /***
     *
     * @return pais
     */
    public String getPais() {
        return pais;
    }

    /***
     *
     * @param pais
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /***
     *
     * @return localizacion
     */
    public String getLocalizacion() {
        return localizacion;
    }

    /***
     *
     * @param localizacion
     */
    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    /***
     *
     * @return socioLocalList
     */
    public List<SocioLocal> getSocioLocalList() {
        return socioLocalList;
    }

    /***
     *
     * @param socioLocalList
     */
    public void setSocioLocalList(List<SocioLocal> socioLocalList) {
        this.socioLocalList = socioLocalList;
    }

    /***
     *
     * @return trabajadorList
     */
    public List<Trabajador> getTrabajadorList() {
        return trabajadorList;
    }

    /***
     *
     * @param trabajadorList
     */
    public void setTrabajadorList(List<Trabajador> trabajadorList) {
        this.trabajadorList = trabajadorList;
    }

    /***
     *
     * @return financiadorList
     */
    public List<Financiador> getFinanciadorList() {
        return financiadorList;
    }

    /***
     *
     * @param financiadorList
     */
    public void setFinanciadorList(List<Financiador> financiadorList) {
        this.financiadorList = financiadorList;
    }

    /***
     *
     * @return accionList
     */
    public List<Accion> getAccionList() {
        return accionList;
    }

    /***
     *
     * @param accionList
     */
    public void setAccionList(List<Accion> accionList) {
        this.accionList = accionList;
    }

    /***
     *
     * @return fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /***
     *
     * @param fechaInicio
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /***
     *
     * @return fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /***
     *
     * @param fechaFin
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /***
     *
     * @return lineaAccion
     */
    public LineaAccion getLineaAccion() {
        return lineaAccion;
    }

    /***
     *
     * @param lineaAccion
     */
    public void setLineaAccion(LineaAccion lineaAccion) {
        this.lineaAccion = lineaAccion;
    }

    /***
     *
     * @return subLineaAccionList
     */
    public List<SubLineaAccion> getSubLineaAccionList() {
        return subLineaAccionList;
    }

    /***
     *
     * @param subLineaAccionList
     */
    public void setSubLineaAccionList(List<SubLineaAccion> subLineaAccionList) {
        this.subLineaAccionList = subLineaAccionList;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "idProyecto=" + idProyecto +
                ", codigoProyecto='" + codigoProyecto + '\'' +
                ", nombreProyecto='" + nombreProyecto + '\'' +
                ", pais='" + pais + '\'' +
                ", localizacion='" + localizacion + '\'' +
                ", socioLocalList=" + socioLocalList +
                ", trabajadorList=" + trabajadorList +
                ", financiadorList=" + financiadorList +
                ", accionList=" + accionList +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", lineaAccion=" + lineaAccion +
                ", subLineaAccionList=" + subLineaAccionList +
                '}';
    }
}
