package es.kokoro.model;

import es.kokoro.model.interfaces.IFinanciador;
import es.kokoro.model.interfaces.IIngreso;

/**
 * Clase Ente Público
 */

public abstract class EntePublico implements IIngreso, IFinanciador {
    private Long idEntePublico;
    private String nombre;
    private String subvencion;

    /***
     *
     * @param idEntePublico índice del Ente Público
     * @param nombre Nombre del Ente público
     * @param subvencion Nombre de la Subvención
     */
    public EntePublico(Long idEntePublico, String nombre, String subvencion) {
        this.idEntePublico = idEntePublico;
        this.nombre = nombre;
        this.subvencion = subvencion;
    }

    public Long getIdEntePublico() {
        return idEntePublico;
    }

    public void setIdEntePublico(Long idEntePublico) {
        this.idEntePublico = idEntePublico;
    }

    /***
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre Nombre del Ente público
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /***
     *
     * @return subvencion
     */
    public String getSubvencion() {
        return subvencion;
    }

    /***
     *
     * @param subvencion Nombre de la Subvención
     */
    public void setSubvencion(String subvencion) {
        this.subvencion = subvencion;
    }

    @Override
    public String toString() {
        return "EntePublico{" +
                "idEntePublico=" + idEntePublico +
                ", nombre='" + nombre + '\'' +
                ", subvencion='" + subvencion + '\'' +
                '}';
    }
    // Métodos de Interface
    public boolean isPublico() {
        return true;
    }
}
