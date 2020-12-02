package es.kokoro.model;

import es.kokoro.enums.Periodo;
import es.kokoro.model.interfaces.IIngreso;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

/***
 * @author Kokoro
 */

@Entity
@Table(name = "socios")
public class Socio /*extends Persona*/ implements IIngreso, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idSocio")
	private Long idSocio;
    @Column(name = "periodo", nullable = false)
    @Enumerated(EnumType.STRING)
    private Periodo periodo;
    @Column(name = "cuota")
    private double cuota;
    @Column(name = "estado")
    @Type(type="boolean")
    private boolean estado = false;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "idPersona")
    private Persona persona;

    /***
     * Constructor de la clase Socio
     * @param idPersona ID de Persona
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

    public Socio(Long idPersona, String nombre, String apellidos, String identificador, String nacionalidad, String direccion, String poblacion, String telefono, String email, Long idSocio, Periodo periodo, double cuota, boolean estado, Date fechaNac) {
        super(idPersona, nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email, fechaNac);
        this.idSocio = idSocio;
        this.periodo = periodo;
        this.cuota = cuota;
        this.estado = estado;
    }*/
    /***
     *
     */
    public Socio(){

    }
    /***
     *
     * @param persona
     * @param idSocio
     * @param periodo
     * @param cuota
     * @param estado
     */
    public Socio(Persona persona, Long idSocio, Periodo periodo, double cuota, boolean estado) {

        this.idSocio = idSocio;
        this.periodo = periodo;
        this.cuota = cuota;
        this.estado = estado;
        this.persona = persona;
    }
    /***
     *
     * @return idSocio
     */
    public Long getIdSocio() {
        return idSocio;
    }

    /***
     * Definimos el valor de idSocio
     * @param idSocio
     */
    public void setIdSocio(Long idSocio) {
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
                "} " +  persona.toString();
    }

    // Métodos de Interface
    public boolean isPublico() {
        return false;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
