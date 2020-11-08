package es.kokoro.model;

import es.kokoro.model.interfaces.IIngreso;

import java.util.Date;

public class Herencia extends Persona implements IIngreso {
    private Long idHerencia;

    /***
     * @see Persona
     * @param idPersona -
     * @param nombre -
     * @param apellidos -
     * @param identificador -
     * @param nacionalidad -
     * @param direccion -
     * @param poblacion -
     * @param telefono -
     * @param email -
     * @param idHerencia Índice de Herencia
     */
    public Herencia(Long idPersona, String nombre, String apellidos, String identificador, String nacionalidad, String direccion, String poblacion, String telefono, String email, Long idHerencia, Date fechaNac) {
        super(idPersona, nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email,fechaNac);
        this.idHerencia = idHerencia;
    }

    /***
     *
     * @return idHerencia
     */
    public Long getIdHerencia() {
        return idHerencia;
    }

    /***
     *
     * @param idHerencia
     */
    public void setIdHerencia(Long idHerencia) {
        this.idHerencia = idHerencia;
    }

    @Override
    public String toString() {
        return "Herencia{" +
                "idHerencia=" + idHerencia +
                "} " + super.toString();
    }

    public boolean isPublico() {
        return false;
    }
}
