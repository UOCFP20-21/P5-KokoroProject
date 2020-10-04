package es.kokoro.model;

import java.util.Date;

public class Trabajador extends Persona{
    private Long idTrabajador;
    private Delegacion delegacion;
    private Date fechaNac;
    private boolean activo;

    public Trabajador(Long idPersona, String nombre, String apellidos, String identificador, String nacionalidad, String direccion, String poblacion, String telefono, String email, Long idTrabajador, Delegacion delegacion, Date fechaNac, boolean activo) {
        super(idPersona, nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email);
        this.idTrabajador = idTrabajador;
        this.delegacion = delegacion;
        this.fechaNac = fechaNac;
        this.activo = activo;
    }

    public Long getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Long idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public Delegacion getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(Delegacion delegacion) {
        this.delegacion = delegacion;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public boolean isActivo() {
        return activo;
    }

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
