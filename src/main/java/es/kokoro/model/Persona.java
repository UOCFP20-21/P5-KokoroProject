package es.kokoro.model;

import java.util.Date;

/***
 * @author Kokoro
 */
public class Persona {
    private Long idPersona;
    private String nombre;
    private String apellidos;
    private String identificador;
    private String nacionalidad;
    private String direccion;
    private String poblacion;
    private String telefono;
    private String email;
    private Date fechaNac;

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

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
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
        Persona that = (Persona) o;
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
        if (email != null ? !email.equals(persona.email) : persona.email != null) return false;
        return fechaNac != null ? fechaNac.equals(that.fechaNac) : that.fechaNac == null;
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
        result = 31 * result + (fechaNac != null ? fechaNac.hashCode() : 0);
        return result;
    }
}

