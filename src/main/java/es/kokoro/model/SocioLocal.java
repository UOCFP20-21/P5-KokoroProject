package es.kokoro.model;

import java.util.List;

/**
 * Clase de Socio Local
 * Contiene información del socio local
 */
public class SocioLocal extends Empresa {

    //Atributos
    /**
     * Listado de proyectos
     */
    private Long idSocioLocal;
    private List<Proyecto> proyectoList;


    //Constructor

    /***
     * Constructor de la Clase SocioLocal
     * Esta Clase va vinculada a los proyectos.
     * Hereda de Empresa.
     * @param idEmpresa @see Empresa
     * @param nombre @see Empresa
     * @param pais @see Empresa
     * @param poblacion @see Empresa
     * @param direccionSocial @see Empresa
     * @param razonSocial @see Empresa
     * @param identificacionSocial @see Empresa
     * @param telefono @see Empresa
     * @param email @see Empresa
     * @param idSocioLocal Índice de SocioLocal.
     * @param proyectoList Listado de Proyectos en los que ha participado
     */
    public SocioLocal(Long idEmpresa, String nombre, String pais, String poblacion, String direccionSocial, String razonSocial, String identificacionSocial, String telefono, String email, Long idSocioLocal, List<Proyecto> proyectoList) {
        super(idEmpresa, nombre, pais, poblacion, direccionSocial, razonSocial, identificacionSocial, telefono, email);
        this.idSocioLocal = idSocioLocal;
        this.proyectoList = proyectoList;
    }


    // Metodos Publicos

    /***
     *
     * @return idsocioLocal
     */
    public Long getIdSocioLocal() {
        return idSocioLocal;
    }

    /***
     *
     * @param idSocioLocal Identificado del Socio Local
     */
    public void setIdSocioLocal(Long idSocioLocal) {
        this.idSocioLocal = idSocioLocal;
    }

    /**
     * Devuelve el listado de proyectos
     *
     * @return listado proyectos
     */
    public List<Proyecto> getProyectoList() {

        return proyectoList;
    }


    /**
     * Modifica listado Proyectos
     * @param proyectoList Listado de Proyectos
     */
    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

    @Override
    public String toString() {
        return "SocioLocal{" +
                "idSocioLocal=" + idSocioLocal +
                ", proyectoList=" + proyectoList +
                '}';
    }
}
