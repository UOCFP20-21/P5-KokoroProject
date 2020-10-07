package es.kokoro.model;

/***
 * Clase Accion
 * Contiene informacion de Accion
 */
public class Accion {
    //Atributos
    private Long idAccion;
    private String nombre;
    private String descripcion;
    private Double coste;

    //Constructor
    /***
     *
     * @param idAccion Id de accion
     * @param nombre Nombre de accion
     * @param descripcion Descripcion Accion
     * @param coste Coste accion
     */
    public Accion(Long idAccion, String nombre, String descripcion, Double coste) {
        this.idAccion = idAccion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.coste = coste;
    }

    //Metodos Publicos
    /**
     * Devuelve el numero de la accion
     * @return idAccion proyecto
     */
    public Long getIdAccion() {
        return idAccion;
    }
    /**
     * Modifica el idAccion de la accion
     * @param idAccion
     */
    public void setIdAccion(Long idAccion) {
        this.idAccion = idAccion;
    }
    /**
     * Devuelve el nombre de la accion
     * @return nombre proyecto
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Modifica el nombre de la accion
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Devuelve la descripcion de la accion
     * @return descripcion de la accion
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Modifica la descripcion de la accion
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Devuelve el cosste de la accion
     * @return coste accion
     */
    public Double getCoste() {
        return coste;
    }
    /**
     * Modifica el coste de la accion
     * @param coste
     */
    public void setCoste(Double coste) {
        this.coste = coste;
    }

    @Override
    public String toString() {
        return "Accion{" +
                "idAccion=" + idAccion +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", coste=" + coste +
                '}';
    }
}
