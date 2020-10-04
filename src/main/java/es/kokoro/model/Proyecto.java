package es.kokoro.model;

import java.util.Date;
import java.util.List;

/**
 * Clase Proyecto
 * Contiene informacion del Proyecto
 */

public class Proyecto {

    //Atributos
    /**
     * Acumulador para darle un num. de proyecto
     */
    private static int cuentaProyecto =0;
    /**
     * Numero de proyecto
     */
    private int idProyecto;
    /**
     * Pais Proyecto
     */
    private String pais;
    /**
     * Localizacion Proyecto
     */
    private String localizacion;
    /**
     *Listado Socio Local
     */
    private List<SocioLocal> socioLocalList;
    /**
     *Listado Personal
     */
    private List<Persona> personaList;

    /**
     * Financiador
     */
    private Financiador financiador;

    /**
     * Acciones a realizar
     */
    private List<Accion> accionList;

    /**
     * Fecha de inicio Proyecto
     */
    private Date FechaInicio;

    /**
     * Fecha de fin Proyecto
     */
    private Date Fechafin;

    /**
     * Linea de accion
     */
    private LineaAccion LineaAccion;


    //Constructor

    public Proyecto(int idProyecto, String pais, String localizacion, List<SocioLocal> socioLocalList, List<Persona> personaList, Financiador financiador, List<Accion> accionList, Date fechaInicio, Date fechafin, es.kokoro.model.LineaAccion lineaAccion) {
        this.idProyecto = cuentaProyecto++;
        this.pais = pais;
        this.localizacion = localizacion;
        this.socioLocalList = socioLocalList;
        this.personaList = personaList;
        this.financiador = financiador;
        this.accionList = accionList;
        this.FechaInicio = fechaInicio;
        this.Fechafin = fechafin;
        this.LineaAccion = lineaAccion;
    }


    //Metodos publicos

    /**
     * Devuelve el acumulador del proyecto
     * @return acumulador proyecto
     */
    public static int getCuentaProyecto() {
        return cuentaProyecto;
    }
    /**
     * Modifica el acumulador de proyecto
     * @param cuentaProyecto
     */
    public static void setCuentaProyecto(int cuentaProyecto) {
        Proyecto.cuentaProyecto = cuentaProyecto;
    }
    /**
     * Devuelve el pais del proyecto
     * @return pais proyecto
     */
    public String getPais() {
        return pais;
    }
    /**
     * Modifica el pais de proyecto
     * @param pais
     */
    public void setPais(String pais) {
        this.pais = pais;
    }
    /**
     * Devuelve la localizacion del proyecto
     * @return localizacion proyecto
     */
    public String getLocalizacion() {
        return localizacion;
    }
    /**
     * Modifica localizacion de proyecto
     * @param localizacion
     */
    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    /**
     * Devuelve listado de socioLocal del proyecto
     * @return socioLocalList  proyecto
     */
    public List<SocioLocal> getSocioLocalList() {
        return socioLocalList;
    }
    /**
     * Modifica el listado de SocioLocal de proyecto
     * @param socioLocalList
     */
    public void setSocioLocalList(List<SocioLocal> socioLocalList) {
        this.socioLocalList = socioLocalList;
    }
    /**
     * Devuelve listado de persona del proyecto
     * @return personaList proyecto
     */
    public List<Persona> getPersonaList() {
        return personaList;
    }
    /**
     * Modifica el listado de Personal de proyecto
     * @param personaList
     */
    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }
    /**
     * Devuelve financiador del proyecto
     * @return financiador proyecto
     */
    public Financiador getFinanciador() {
        return financiador;
    }
    /**
     * Modifica el financiador de proyecto
     * @param financiador
     */
    public void setFinanciador(Financiador financiador) {
        this.financiador = financiador;
    }
    /**
     * Devuelve listado AccionaRealizar del proyecto
     * @return accionList proyecto
     */
    public List<Accion> getAccionList() {
        return accionList;
    }
    /**
     * Modifica listado de Accion a REalizar de proyecto
     * @param accionList
     */
    public void setAccionList(List<Accion> accionList) {
        this.accionList = accionList;
    }
    /**
     * Devuelve fecha inicio del proyecto
     * @return fechaInicio proyecto
     */
    public Date getFechaInicio() {
        return FechaInicio;
    }
    /**
     * Modifica fecha inicio de proyecto
     * @param fechaInicio
     */
    public void setFechaInicio(Date fechaInicio) {
        FechaInicio = fechaInicio;
    }
    /**
     * Devuelve fecha fin del proyecto
     * @return fechafin proyecto
     */
    public Date getFechafin() {
        return Fechafin;
    }
    /**
     * Modifica fecha fin de proyecto
     * @param fechafin
     */
    public void setFechafin(Date fechafin) {
        Fechafin = fechafin;
    }
    /**
     * Devuelve linea de accion del proyecto
     * @return lineaAccion proyecto
     */
    public  LineaAccion getLineaAccion() {
        return LineaAccion;
    }
    /**
     * Modifica linea de Accion de proyecto
     * @param lineaAccion
     */
    public void setLineaAccion( LineaAccion lineaAccion) {
        LineaAccion = lineaAccion;
    }
}
