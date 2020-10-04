package es.kokoro.model;

import es.kokoro.enums.Periodos;

/***
 * @author Kokoro
 */
public class Socio extends Persona{
    private Long idSocio;
    private Periodos periodo;
    private double cuota;
    private boolean estado = false;

    /***
     * Constructor de la clase Socio
     * @param idPersona
     * @param nombre
     * @param apellidos
     * @param identificador
     * @param nacionalidad
     * @param direccion
     * @param poblacion
     * @param telefono
     * @param email
     * @param idSocio
     * @param periodo
     * @param cuota
     * @param estado
     */
    public Socio(Long idPersona, String nombre, String apellidos, String identificador, String nacionalidad, String direccion, String poblacion, String telefono, String email, Long idSocio, Periodos periodo, double cuota, boolean estado) {
        super(idPersona, nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email);
        this.idSocio = idSocio;
        this.periodo = periodo;
        this.cuota = cuota;
        this.estado = estado;
    }

    /***
     *
     * @return idSocio
     */
    public Long getIdSocio() {
        return idSocio;
    }

    /***
     * Definimos el valor de idSocio
     * @param idSocio
     */
    public void setIdSocio(Long idSocio) {
        this.idSocio = idSocio;
    }

    public Periodos getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodos periodo) {
        this.periodo = periodo;
    }

    public double getCuota() {
        return cuota;
    }

    public void setCuota(double cuota) {
        this.cuota = cuota;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Socio {" +
                "idSocio=" + idSocio +
                ", periodo=" + periodo.getNombrePeriodo() +
                ", cuota=" + cuota +
                ", estado=" + estado +
                "} " +  super.toString();
    }
}
