package es.kokoro.model;

/**
 * Clase Linea de Accion
 * Contiene informacion de la Linea de Accion
 */
public class LineaAccion {

    private Long idLineaAccion;
    private String linea;

    /***
     *
     * @param idLineaAccion Índice Línea de Acción
     * @param linea Nombre de la línea de Acción
     */
    public LineaAccion(Long idLineaAccion, String linea) {
        this.idLineaAccion = idLineaAccion;
        this.linea = linea;
    }

    /***
     *
     * @return idLineaAccion
     */
    public Long getIdLineaAccion() {
        return idLineaAccion;
    }

    /***
     *
     * @param idLineaAccion
     */
    public void setIdLineaAccion(Long idLineaAccion) {
        this.idLineaAccion = idLineaAccion;
    }

    /***
     *
     * @return linea
     */
    public String getLinea() {
        return linea;
    }

    /***
     *
     * @param linea
     */
    public void setLinea(String linea) {
        this.linea = linea;
    }

    @Override
    public String toString() {
        return "LineaAccion{" +
                "idLineaAccion=" + idLineaAccion +
                ", linea='" + linea + '\'' +
                '}';
    }
}