package es.kokoro.model;

import java.util.Date;

public class VoluntarioInternacional extends Trabajador {
    private Long idVoluntarioInternacional;
    private Date inicioVoluntario;

    /***
     * Constructor de la clase VoluntarioInternacional
     * Hereda de Trabajador y a su vez hereda de Persona
     * @see Trabajador
     * @see Persona
     * @param idPersona @see Persona
     * @param nombre @see Persona
     * @param apellidos @see Persona
     * @param identificador @see Persona
     * @param nacionalidad @see Persona
     * @param direccion @see Persona
     * @param poblacion @see Persona
     * @param telefono @see Persona
     * @param email @see Persona
     * @param idTrabajador @see Trabajador
     * @param delegacion @see Trabajador
     * @param fechaNac @see Trabajador
     * @param activo @see Trabajador
     * @param idVoluntarioInternacional Índice de VoluntarioInternacional
     * @param inicioVoluntario Fecha de inicio del voluntariado
     */
    public VoluntarioInternacional(Long idPersona, String nombre, String apellidos, String identificador, String nacionalidad, String direccion, String poblacion, String telefono, String email, Long idTrabajador, Delegacion delegacion, Date fechaNac, boolean activo, Long idVoluntarioInternacional, Date inicioVoluntario) {
        super(idPersona, nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email, idTrabajador, delegacion, fechaNac, activo);
        this.idVoluntarioInternacional = idVoluntarioInternacional;
        this.inicioVoluntario = inicioVoluntario;
    }

    /***
     *
     * @return idVoluntarioInternacional
     */
    public Long getIdVoluntarioInternacional() {
        return idVoluntarioInternacional;
    }

    /***
     *
     * @param idVoluntarioInternacional
     */
    public void setIdVoluntarioInternacional(Long idVoluntarioInternacional) {
        this.idVoluntarioInternacional = idVoluntarioInternacional;
    }

    /***
     *
     * @return inicioVoluntario
     */
    public Date getInicioVoluntario() {
        return inicioVoluntario;
    }

    /***
     *
     * @param inicioVoluntario
     */
    public void setInicioVoluntario(Date inicioVoluntario) {
        this.inicioVoluntario = inicioVoluntario;
    }

    @Override
    public String toString() {
        return "VoluntarioInternacional{" +
                "idVoluntarioInternacional=" + idVoluntarioInternacional +
                ", inicioVoluntario=" + inicioVoluntario +
                "} " + super.toString();
    }
}
