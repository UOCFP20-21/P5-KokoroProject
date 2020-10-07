package es.kokoro.model;

/**
 * Clase Extraordinarios
 * Extiende de Empresa
 */
public class Extraordinario extends Empresa {
    //Atributos
    private Long idExtraordinario;

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
    public Extraordinario(Long idEmpresa, String nombre, String pais, String poblacion, String direccionSocial, String razonSocial, String identificacionSocial, String telefono, String email, Long idExtraordinario) {
        super(idEmpresa, nombre, pais, poblacion, direccionSocial, razonSocial, identificacionSocial, telefono, email);
        this.idExtraordinario = idExtraordinario;
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
     * @param idExtraordinario
     */
    public void setIdExtraordinario(Long idExtraordinario) {
        this.idExtraordinario = idExtraordinario;
    }

    @Override
    public String toString() {
        return "Extraordinario{" +
                "idExtraordinario=" + idExtraordinario +
                "} " + super.toString();
    }
}