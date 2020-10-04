package es.kokoro.model;

import java.util.Date;

public class VoluntarioColaborador extends Trabajador {
    private Date inicioVoluntario;

    public VoluntarioColaborador(Long idPersona, String nombre, String apellidos, String identificador, String nacionalidad, String direccion, String poblacion, String telefono, String email, Long idTrabajador, Delegacion delegacion, Date fechaNac, boolean activo, Date inicioVoluntario) {
        super(idPersona, nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email, idTrabajador, delegacion, fechaNac, activo);
        this.inicioVoluntario = inicioVoluntario;
    }

    public Date getInicioVoluntario() {
        return inicioVoluntario;
    }

    public void setInicioVoluntario(Date inicioVoluntario) {
        this.inicioVoluntario = inicioVoluntario;
    }

    @Override
    public String toString() {
        return "VoluntarioColaborador{" +
                "inicioVoluntario=" + inicioVoluntario +
                "} " + super.toString();
    }
}
