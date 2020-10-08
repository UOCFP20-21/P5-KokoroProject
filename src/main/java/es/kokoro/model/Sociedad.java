package es.kokoro.model;


import es.kokoro.model.interfaces.iIngreso;

/**
 * Clase sociedades
 * Extiende de Empresa
 */
public class Sociedad extends Empresa implements iIngreso {
    //Atributos
    private Long idSociedad;

    //Constructor
    /***
     * Constructor de la clase Sociedad que realiza "ingresos puntuales" a la ONG.
     * Esta clase Hereda de Empresa
     * @param idEmpresa @see Empresa
     * @param nombre @see Empresa
     * @param pais @see Empresa
     * @param poblacion @see Empresa
     * @param direccionSocial @see Empresa
     * @param razonSocial @see Empresa
     * @param identificacionSocial @see Empresa
     * @param telefono @see Empresa
     * @param email @see Empresa
     * @param idSociedad Índice de Sociedad
     */
    public Sociedad(Long idEmpresa, String nombre, String pais, String poblacion, String direccionSocial, String razonSocial, String identificacionSocial, String telefono, String email, Long idSociedad) {
        super(idEmpresa, nombre, pais, poblacion, direccionSocial, razonSocial, identificacionSocial, telefono, email);
        this.idSociedad = idSociedad;
    }


    // Metodos Publicos

    /***
     *
     * @return idSociedad
     */
    public Long getIdSociedad() {
        return idSociedad;
    }

    /***
     *
     * @param idSociedad
     */
    public void setIdSociedad(Long idSociedad) {
        this.idSociedad = idSociedad;
    }

    @Override
    public String toString() {
        return "Sociedad{" +
                "idSociedad=" + idSociedad +
                "} " + super.toString();
    }

    // Métodos de Interface
    public boolean setPublico() {
        return false;
    }
}
