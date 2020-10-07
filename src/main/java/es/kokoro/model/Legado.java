package es.kokoro.model;

/**
 * Clase legados
 * Extiende de Empresa
 */
public class Legado extends Empresa {
    //Atributos
    private Long idLegado;

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
     * @param idLegado Índice Legado
     */
    public Legado(Long idEmpresa, String nombre, String pais, String poblacion, String direccionSocial, String razonSocial, String identificacionSocial, int telefono, String email, Long idLegado) {
        super(idEmpresa, nombre, pais, poblacion, direccionSocial, razonSocial, identificacionSocial, telefono, email);
        this.idLegado = idLegado;
    }

    /***
     *
     * @return idLegado
     */
    public Long getIdLegado() {
        return idLegado;
    }

    /***
     *
     * @param idLegado
     */
    public void setIdLegado(Long idLegado) {
        this.idLegado = idLegado;
    }

    @Override
    public String toString() {
        return "Legado{" +
                "idLegado=" + idLegado +
                "} " + super.toString();
    }
}

