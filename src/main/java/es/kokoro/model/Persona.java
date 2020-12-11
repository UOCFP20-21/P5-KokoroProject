package es.kokoro.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
/***
 * @author Kokoro
 */
public abstract class Persona {

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "identificador")
    private String identificador;
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "poblacion")
    private String poblacion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "email")
    private String email;
    @Column(name = "fechaNac")
    private Date fechaNac;

    public Persona() {

    }

    /***
     * Constructor de la clase Persona
     * @param nombre
     * @param apellidos
     * @param identificador
     * @param nacionalidad
     * @param direccion
     * @param poblacion
     * @param telefono
     * @param email
     */
    public Persona(String nombre, String apellidos, String identificador, String nacionalidad, String direccion, String poblacion, String telefono, String email, Date fechaNac) {
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
        return Objects.equals(nombre, persona.nombre) &&
                Objects.equals(apellidos, persona.apellidos) &&
                Objects.equals(identificador, persona.identificador) &&
                Objects.equals(nacionalidad, persona.nacionalidad) &&
                Objects.equals(direccion, persona.direccion) &&
                Objects.equals(poblacion, persona.poblacion) &&
                Objects.equals(telefono, persona.telefono) &&
                Objects.equals(email, persona.email) &&
                Objects.equals(fechaNac, persona.fechaNac);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email, fechaNac);
    }
}

