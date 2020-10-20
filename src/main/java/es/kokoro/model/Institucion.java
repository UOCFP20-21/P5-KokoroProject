package es.kokoro.model;

import es.kokoro.model.interfaces.IIngreso;

/**
 * Clase instituciones
 * Extiende de Empresa
 */
public class Institucion extends Empresa implements IIngreso {

    private Long idInstitucion;
    //Atributos

    /***
     * @see Empresa
     * @param idEmpresa Herencia de Empresa
     * @param nombre Herencia de Empresa
     * @param pais Herencia de Empresa
     * @param poblacion Herencia de Empresa
     * @param direccionSocial Herencia de Empresa
     * @param razonSocial Herencia de Empresa
     * @param identificacionSocial Herencia de Empresa
     * @param telefono Herencia de Empresa
     * @param email Herencia de Empresa
     * @param idInstitucion Índice Institución
     */
    public Institucion(Long idEmpresa, String nombre, String pais, String poblacion, String direccionSocial, String razonSocial, String identificacionSocial, String telefono, String email, Long idInstitucion) {
        super(idEmpresa, nombre, pais, poblacion, direccionSocial, razonSocial, identificacionSocial, telefono, email);
        this.idInstitucion = idInstitucion;
    }

    /***
     *
     * @return idInstitucion
     */
    public Long getIdInstitucion() {
        return idInstitucion;
    }

    /***
     *
     * @param idInstitucion
     */
    public void setIdInstitucion(Long idInstitucion) {
        this.idInstitucion = idInstitucion;
    }
// Metodos Publicos

    @Override
    public String toString() {
        return "Institucion{" +
                "idInstitucion=" + idInstitucion +
                "} "+super.toString();
    }

    // Métodos de Interface
    public boolean isPublico() {
        return false;
    }
}

