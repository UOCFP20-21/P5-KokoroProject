package es.kokoro.model;

import es.kokoro.model.interfaces.IFinanciador;
import es.kokoro.model.interfaces.IIngreso;

import java.util.Date;

public class Particular extends Persona implements IIngreso, IFinanciador {
    private Long idParticular;

    /***
     *
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
     * @param idParticular Índice de Particular
     */
    public Particular(Long idPersona, String nombre, String apellidos, String identificador, String nacionalidad, String direccion, String poblacion, String telefono, String email, Long idParticular, Date fechaNac) {
        super(idPersona, nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email, fechaNac);
        this.idParticular = idParticular;
    }

    /***
     *
     * @return idParticular
     */
    public Long getIdParticular() {
        return idParticular;
    }

    /***
     *
     * @param idParticular
     */
    public void setIdParticular(Long idParticular) {
        this.idParticular = idParticular;
    }

    @Override
    public String toString() {
        return "Particular{" +
                "idParticular=" + idParticular +
                "} " + super.toString();
    }

    public boolean isPublico() {
        return false;
    }
}
