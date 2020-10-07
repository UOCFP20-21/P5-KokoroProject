package es.kokoro.model;

import java.util.Date;

public abstract class Trabajador extends Persona{
    private Long idTrabajador;
    private Delegacion delegacion;
    private Date fechaNac;
    private boolean activo;

    /***
     *
     * @param idPersona Herencia de Persona
     * @param nombre Herencia de Persona
     * @param apellidos Herencia de Persona
     * @param identificador Herencia de Persona
     * @param nacionalidad Herencia de Persona
     * @param direccion Herencia de Persona
     * @param poblacion Herencia de Persona
     * @param telefono Herencia de Persona
     * @param email Herencia de Persona
     * @param idTrabajador Índice de Trabajador
     * @param delegacion Delegación asignada
     * @param fechaNac Fecha de Nacimiento
     * @param activo Boolean para definir si el trabajador está activo o no [true|false]
     */
    public Trabajador(Long idPersona, String nombre, String apellidos, String identificador, String nacionalidad, String direccion, String poblacion, String telefono, String email, Long idTrabajador, Delegacion delegacion, Date fechaNac, boolean activo) {
        super(idPersona, nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email);
        this.idTrabajador = idTrabajador;
        this.delegacion = delegacion;
        this.fechaNac = fechaNac;
        this.activo = activo;
    }

    /***
     *
     * @return idTrabajador
     */
    public Long getIdTrabajador() {
        return idTrabajador;
    }

    /***
     *
     * @param idTrabajador
     */
    public void setIdTrabajador(Long idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    /***
     *
     * @return delegacion
     */
    public Delegacion getDelegacion() {
        return delegacion;
    }

    /***
     *
     * @param delegacion
     */
    public void setDelegacion(Delegacion delegacion) {
        this.delegacion = delegacion;
    }

    /***
     *
     * @return fechaNac
     */
    public Date getFechaNac() {
        return fechaNac;
    }

    /***
     *
     * @param fechaNac
     */
    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    /***
     *
     * @return  isActivo
     */
    public boolean isActivo() {
        return activo;
    }

    /***
     *
     * @param activo
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Trabajador{" +
                "idTrabajador=" + idTrabajador +
                ", delegacion=" + delegacion +
                ", fechaNac=" + fechaNac +
                ", activo=" + activo +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Trabajador that = (Trabajador) o;

        if (activo != that.activo) return false;
        if (idTrabajador != null ? !idTrabajador.equals(that.idTrabajador) : that.idTrabajador != null) return false;
        if (delegacion != null ? !delegacion.equals(that.delegacion) : that.delegacion != null) return false;
        return fechaNac != null ? fechaNac.equals(that.fechaNac) : that.fechaNac == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (idTrabajador != null ? idTrabajador.hashCode() : 0);
        result = 31 * result + (delegacion != null ? delegacion.hashCode() : 0);
        result = 31 * result + (fechaNac != null ? fechaNac.hashCode() : 0);
        result = 31 * result + (activo ? 1 : 0);
        return result;
    }
}
