package es.kokoro.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/***
 * @author Kokoro
 */

@Entity
@Table(name = "personas")
public class Persona implements Serializable {
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idPersona", unique = true, nullable = false)
    private Long idPersona;
	@Column(name = "nombre", nullable = false)
    private String nombre;
	@Column (name = "apellidos", nullable = false)
    private String apellidos;
	@Column (name = "identificador", nullable = false)//, unique = true
    private String identificador;
	@Column (name = "nacionalidad", nullable = false)
    private String nacionalidad;
	@Column (name = "direccion", nullable = false)
    private String direccion;
	@Column (name = "poblacion", nullable = false)
    private String poblacion;
	@Column (name = "telefono", nullable = false)
    private String telefono;
	@Column (name = "email", nullable = false)
    private String email;
	@Column (name = "fechaNac", nullable = false)
	@Temporal(TemporalType.DATE)
    private Date fechaNac;
/*
	@OneToOne(optional = true)
	private Socio socio;
*/
    public Persona()
    {
    	
    }
    /***
     * Constructor de la clase Persona
     * @param idPersona
     * @param nombre
     * @param apellidos
     * @param identificador
     * @param nacionalidad
     * @param direccion
     * @param poblacion
     * @param telefono
     * @param email
     */
    public Persona(Long idPersona, String nombre, String apellidos, String identificador, String nacionalidad, String direccion, String poblacion, String telefono, String email, Date fechaNac) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.identificador = identificador;
        this.nacionalidad = nacionalidad;
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.telefono = telefono;
        this.email = email;
        this.fechaNac = fechaNac;
    }

    /***
     *
     * @return Long idPersona
     */
    public Long getIdPersona() {
        return idPersona;
    }

    /***
     * Definimos el valor de idPersona
     * @param idPersona
     */
    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    /***
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /***
     * Definimos el valor de nombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /***
     *
     * @return apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /***
     * Definimos el valor de Apellidos
     * @param apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /***
     *
     * @return identificador
     */
    public String getIdentificador() {
        return identificador;
    }

    /***
     * Definimos el valor del identificador
     * @param identificador
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /***
     *
     * @return nacionalidad
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /***
     * Definimos el valor de nacionalidad
     * @param nacionalidad
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /***
     *
     * @return dirección
     */
    public String getDireccion() {
        return direccion;
    }

    /***
     * Definimos el valor de direccion
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /***
     *
     * @return poblacion
     */
    public String getPoblacion() {
        return poblacion;
    }

    /***
     * Definimos el valor de poblacion
     * @param poblacion
     */
    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    /***
     *
     * @return telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /***
     * Definimos el valor de telefono
     * @param telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /***
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /***
     * Definimos el valor de email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    /***
     *
     * @return String con los datos de persona
     */


    @Override
    public String toString() {
        return "Persona{" +
                "idPersona=" + idPersona +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", identificador='" + identificador + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", direccion='" + direccion + '\'' +
                ", poblacion='" + poblacion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Persona persona = (Persona) o;

        if (idPersona != null ? !idPersona.equals(persona.idPersona) : persona.idPersona != null) return false;
        if (nombre != null ? !nombre.equals(persona.nombre) : persona.nombre != null) return false;
        if (apellidos != null ? !apellidos.equals(persona.apellidos) : persona.apellidos != null) return false;
        if (identificador != null ? !identificador.equals(persona.identificador) : persona.identificador != null)
            return false;
        if (nacionalidad != null ? !nacionalidad.equals(persona.nacionalidad) : persona.nacionalidad != null)
            return false;
        if (direccion != null ? !direccion.equals(persona.direccion) : persona.direccion != null) return false;
        if (poblacion != null ? !poblacion.equals(persona.poblacion) : persona.poblacion != null) return false;
        if (telefono != null ? !telefono.equals(persona.telefono) : persona.telefono != null) return false;
        return email != null ? email.equals(persona.email) : persona.email == null;
    }

    @Override
    public int hashCode() {
        int result = idPersona != null ? idPersona.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (identificador != null ? identificador.hashCode() : 0);
        result = 31 * result + (nacionalidad != null ? nacionalidad.hashCode() : 0);
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + (poblacion != null ? poblacion.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
/*
    public Socio getSocio() {
       return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }*/
}

