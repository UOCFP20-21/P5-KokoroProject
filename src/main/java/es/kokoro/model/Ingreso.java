package es.kokoro.model;

import es.kokoro.model.interfaces.iIngreso;

import java.util.Date;

public class Ingreso {
    private Long idIngreso;
    private iIngreso ingreso;
    private double importe;
    private Date fecha;


    /***
     *
     * @param idIngreso Índice de ingreso
     * @param ingreso Objeto de tipo varios
     * @param importe Cantidad del ingreso
     * @param fecha Fecha del ingreso
     * @param publico Es un ingreso de una fuente pública [true|false]
     */
    public Ingreso(Long idIngreso, iIngreso ingreso, double importe, Date fecha, boolean publico) {
        this.idIngreso = idIngreso;
        this.setIngreso(ingreso);
        this.importe = importe;
        this.fecha = fecha;
    }

    /***
     *
     * @return idIngreso
     */
    public Long getIdIngreso() {
        return idIngreso;
    }

    /***
     *
     * @param idIngreso
     */
    public void setIdIngreso(Long idIngreso) {
        this.idIngreso = idIngreso;
    }

    /***
     *
     * @return fuente
     */
    public iIngreso getIngreso() {
        return ingreso;
    }

    /***
     *
     * @param ingreso
     */
    public void setIngreso(iIngreso ingreso) {

        this.ingreso = ingreso;

    }

    /***
     *
     * @return importe
     */
    public double getImporte() {
        return importe;
    }

    /***
     *
     * @param importe
     */
    public void setImporte(double importe) {
        this.importe = importe;
    }

    /***
     *
     * @return fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /***
     *
     * @param fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    @Override
    public String toString() {
        return "Ingreso{" +
                "fuente=" + ingreso.toString() +
                ", importe=" + importe +
                ", fecha=" + fecha +
                '}';
    }
}
