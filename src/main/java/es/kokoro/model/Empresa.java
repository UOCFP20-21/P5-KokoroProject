package es.kokoro.model;

import es.kokoro.model.interfaces.iFinanciador;
import es.kokoro.model.interfaces.iIngreso;

/**
 * Clase empresa
 * Contiene información de la empresa
 */
public abstract class Empresa implements iIngreso, iFinanciador {
    //Atributos
    private Long idEmpresa;
    private String nombre;
    private String pais;
    private String poblacion;
    private String direccionSocial;
    private String razonSocial;
    private String identificacionSocial;
    private String telefono;
    private String email;

    /***
     *
     * @param idEmpresa Numero de empresa
     * @param nombre Nombre Empresa
     * @param pais Pais Empresa
     * @param poblacion Poblacion Empresa
     * @param direccionSocial Direccion Social Empresa
     * @param razonSocial Razon social Empresa
     * @param identificacionSocial Identificación Social Empresa
     * @param telefono Telefono de Empresa
     * @param email Email de Empresa
     */
    public Empresa(Long idEmpresa, String nombre, String pais, String poblacion, String direccionSocial, String razonSocial, String identificacionSocial, String telefono, String email) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.pais = pais;
        this.poblacion = poblacion;
        this.direccionSocial = direccionSocial;
        this.razonSocial = razonSocial;
        this.identificacionSocial = identificacionSocial;
        this.telefono = telefono;
        this.email = email;
    }
    //Metodos publicos
    /**
     * Devuelve el numero de empresa
     * @return numero empresa
     */
    public Long getIdEmpresa(){
        return idEmpresa;
    }
    /**
     * Modifica el numero de empresa
     * @param idEmpresa
     */
    public void setIdEmpresa(Long idEmpresa){

        this.idEmpresa = idEmpresa;
    }

    /**
     * Devuelve el nombre de la empresa
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre de la empresa
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el pais de la empresa
     *
     * @return pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * Modifica el pais de la empresa
     *
     * @param pais
     */
    public void setPais(String pais) {
        this.pais = pais;
    }
    /**
     * Devuelve la población de la empresa
     *
     * @return poblacion
     */
    public String getPoblacion() {
        return poblacion;
    }

    /**
     * Modifica la población de la empresa
     *
     * @param poblacion
     */
    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    /**
     * Devuelve la direccion Social de la empresa
     *
     * @return direccionSocial
     */
    public String getDireccionSocial() {
        return direccionSocial;
    }

    /**
     * Modifica la direccion Social de la empresa
     *
     * @param direccionSocial
     */
    public void setDireccionSocial(String direccionSocial) {
        this.direccionSocial = direccionSocial;
    }
    /**
     * Devuelve la razon Social de la empresa
     *
     * @return razonSocial
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * Modifica la Razon Social de la empresa
     *
     * @param razonSocial
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    /**
     * Devuelve la identificacion Social de la empresa
     *
     * @return identificacionSocial
     */
    public String getIdentificacionSocial() {
        return identificacionSocial;
    }

    /**
     * Modifica el identificación Social de la empresa
     *
     * @param identificacionSocial
     */
    public void setIdentificacionSocial(String identificacionSocial) {
        this.identificacionSocial = identificacionSocial;
    }
    /**
     * Devuelve el telefono de la empresa
     *
     * @return telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Modifica el nombre de la empresa
     *
     * @param telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    /**
     * Devuelve el email de la empresa
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Modifica el email de la empresa
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "idEmpresa=" + idEmpresa +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", poblacion='" + poblacion + '\'' +
                ", direccionSocial='" + direccionSocial + '\'' +
                ", razonSocial='" + razonSocial + '\'' +
                ", identificacionSocial='" + identificacionSocial + '\'' +
                ", telefono=" + telefono +
                ", email='" + email + '\'' +
                '}';
    }
}
