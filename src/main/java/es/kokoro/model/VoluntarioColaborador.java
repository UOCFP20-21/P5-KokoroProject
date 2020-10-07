package es.kokoro.model;

import java.util.Date;

public class VoluntarioColaborador extends Trabajador {

    private Long idVoluntarioColaborador;
    private Date inicioVoluntario;

    /***
     * Constructor de la clase VoluntarioColaborador.
     * Hereda de Trabajador que a su vez hereda de Persona
     * @see Trabajador
     * @param idPersona @see Trabajador
     * @param nombre @see Trabajador
     * @param apellidos @see Trabajador
     * @param identificador @see Trabajador
     * @param nacionalidad @see Trabajador
     * @param direccion @see Trabajador
     * @param poblacion @see Trabajador
     * @param telefono @see Trabajador
     * @param email @see Trabajador
     * @param idTrabajador @see Trabajador
     * @param delegacion @see Trabajador
     * @param fechaNac @see Trabajador
     * @param activo @see Trabajador
     * @param idVoluntarioColaborador Índice de VoluntarioColaborador
     * @param inicioVoluntario Fecha de inicio del Voluntariado.
     */
    public VoluntarioColaborador(Long idPersona, String nombre, String apellidos, String identificador, String nacionalidad, String direccion, String poblacion, String telefono, String email, Long idTrabajador, Delegacion delegacion, Date fechaNac, boolean activo, Long idVoluntarioColaborador, Date inicioVoluntario) {
        super(idPersona, nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email, idTrabajador, delegacion, fechaNac, activo);
        this.idVoluntarioColaborador = idVoluntarioColaborador;
        this.inicioVoluntario = inicioVoluntario;
    }

    /***
     *
     * @return idVoluntarioColaborador
     */
    public Long getIdVoluntarioColaborador() {
        return idVoluntarioColaborador;
    }

    /***
     *
     * @param idVoluntarioColaborador
     */
    public void setIdVoluntarioColaborador(Long idVoluntarioColaborador) {
        this.idVoluntarioColaborador = idVoluntarioColaborador;
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
        return "VoluntarioColaborador{" +
                "idVoluntarioColaborador=" + idVoluntarioColaborador +
                ", inicioVoluntario=" + inicioVoluntario +
                "} " + super.toString();
    }
}
