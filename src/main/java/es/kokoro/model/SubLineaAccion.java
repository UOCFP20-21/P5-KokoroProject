package es.kokoro.model;

public class SubLineaAccion {
    private Long idSubLinea;
    private String nombreSubLinea;

    /***
     *
     * @param idSubLinea Índice Sublinea de Acción
     * @param nombreSubLinea Nombre Sub Linea de Acción
     */
    public SubLineaAccion(Long idSubLinea, String nombreSubLinea) {
        this.idSubLinea = idSubLinea;
        this.nombreSubLinea = nombreSubLinea;
    }

    /***
     *
     * @return idSubLinea
     */
    public Long getIdSubLinea() {
        return idSubLinea;
    }

    /***
     *
     * @param idSubLinea
     */
    public void setIdSubLinea(Long idSubLinea) {
        this.idSubLinea = idSubLinea;
    }

    /***
     *
     * @return nombreSubLinea
     */
    public String getNombreSubLinea() {
        return nombreSubLinea;
    }

    /***
     *
     * @param nombreSubLinea
     */
    public void setNombreSubLinea(String nombreSubLinea) {
        this.nombreSubLinea = nombreSubLinea;
    }

    @Override
    public String toString() {
        return "SubLineaAccion{" +
                "idSubLinea=" + idSubLinea +
                ", nombreSubLinea='" + nombreSubLinea + '\'' +
                '}';
    }
}
