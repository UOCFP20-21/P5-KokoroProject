package es.kokoro.model;



/**
 * Clase Accion
 * Contiene informacion de Accion
 */
public class Accion {
    //Atributos
    /**
     * Acumulador para darle un num. de accion
     */
    private static int cuentaAccion =0;
    /**
     * Numero de accion
     */
    private int idAccion;
    /**
     * NOmbre de accion
     */
    private String nombre;
    /**
     * Descripcion Accion
     */
    private String descripcion;
    /**
     * Coste accion
     */
    private Double coste;


  //Constructor


    public Accion(String nombre, String descripcion, Double coste) {
        this.idAccion = cuentaAccion++;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.coste = coste;
    }

    //Metodos Publicos

    /**
     * Devuelve el acumulador de la accion
     * @return acumulador acion
     */
    public static int getCuentaAccion() {
        return cuentaAccion;
    }
    /**
     * Modifica el acumulador de la accion
     * @param cuentaAccion
     */
    public static void setCuentaAccion(int cuentaAccion) {
        Accion.cuentaAccion = cuentaAccion;
    }

    /**
     * Devuelve el numero de la accion
     * @return idAccion proyecto
     */
    public int getIdAccion() {
        return idAccion;
    }
    /**
     * Modifica el idAccion de la accion
     * @param idAccion
     */
    public void setIdAccion(int idAccion) {
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
}
