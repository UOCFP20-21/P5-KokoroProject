package es.kokoro.model;

import es.kokoro.enums.Periodo;
import es.kokoro.model.interfaces.IIngreso;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "socios")

/***
 * @author Kokoro
 */
public class Socio extends Persona implements IIngreso, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idSocio")
    private Integer idSocio;
    @Column(name = "periodo")
    private Periodo periodo;
    @Column(name = "cuota")
    private Double cuota;
    @Column(name = "estado")
    private boolean estado = false;

    public Socio(){

    }

    /***
     * Constructor de la clase Socio
     * @param nombre Nombre de Persona
     * @param apellidos Apellidos de Persona
     * @param identificador DNI/NIE de Persona
     * @param nacionalidad país de procedencia
     * @param direccion de residencia
     * @param poblacion de residencia
     * @param telefono de contacto
     * @param email de contacto
     * @param idSocio Número de socio
     * @param periodo de pago
     * @param cuota Cantidad a pagar
     * @param estado Estado del Socio (activo o no)
     */
    public Socio(String nombre, String apellidos, String identificador, String nacionalidad,
                 String direccion, String poblacion, String telefono, String email, Integer idSocio, Periodo periodo,
                 double cuota, boolean estado, Date fechaNac) {
        super(nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email, fechaNac);
        this.idSocio = idSocio;
        this.periodo = periodo;
        this.cuota = cuota;
        this.estado = estado;
    }

    /***
     *
     * @return idSocio
     */
    public Integer getIdSocio() {
        return idSocio;
    }

    /***
     * Definimos el valor de idSocio
     * @param idSocio
     */
    public void setIdSocio(Integer idSocio) {
        this.idSocio = idSocio;
    }

    /***
     *
     * @return periodo
     */
    public Periodo getPeriodo() {
        return periodo;
    }

    /***
     * Definimos el valor de periodo
     * @param periodo
     */
    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    /***
     *
     * @return cuota
     */
    public double getCuota() {
        return cuota;
    }

    /***
     * Definimos el valor de cuota
     * @param cuota
     */
    public void setCuota(double cuota) {
        this.cuota = cuota;
    }

    /***
     *
     * @return estado
     */
    public boolean isEstado() {
        return estado;
    }

    /***
     * Definimos el valor de estado
     * @param estado
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Socio {" +
                "idSocio=" + idSocio +
                ", periodo=" + periodo.getNombrePeriodo() +
                ", cuota=" + cuota +
                ", estado=" + estado +
                "} " + super.toString();
    }

    // Métodos de Interface
    public boolean isPublico() {
        return false;
    }
}
