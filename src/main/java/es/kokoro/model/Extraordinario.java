package es.kokoro.model;

import es.kokoro.model.interfaces.iIngreso;

/**
 * Clase Extraordinarios
 * Extiende de Empresa
 */
public class Extraordinario extends Empresa implements iIngreso {
    //Atributos
    private Long idExtraordinario;
    private String concepto;

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
     * @param idExtraordinario Índice de Extraordinario
     */
    public Extraordinario(Long idEmpresa, String nombre, String pais, String poblacion, String direccionSocial, String razonSocial, String identificacionSocial, String telefono, String email, Long idExtraordinario, String concepto) {
        super(idEmpresa, nombre, pais, poblacion, direccionSocial, razonSocial, identificacionSocial, telefono, email);
        this.idExtraordinario = idExtraordinario;
        this.concepto = concepto;
    }

// Metodos Publicos
    /***
     *
     * @return idExtraordinario
     */
    public Long getIdExtraordinario() {
        return idExtraordinario;
    }

    /***
     *
     * @param idExtraordinario Identificador del Donativo Extraoridnario
     */
    public void setIdExtraordinario(Long idExtraordinario) {
        this.idExtraordinario = idExtraordinario;
    }

    /***
     *
     * @return concepto
     */
    public String getConcepto() {
        return concepto;
    }

    /***
     *
     * @param concepto del donativo
     */
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    @Override
    public String toString() {
        return "Extraordinario{" +
                "idExtraordinario=" + idExtraordinario +
                "} " + super.toString();
    }

    // Métodos de Interface
    public boolean setPublico() {
        return false;
    }
}