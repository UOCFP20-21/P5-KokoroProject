/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.kokoro.entidades;

import es.kokoro.model.Socio;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "personas")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Personas.findAll", query = "SELECT * FROM Personas")
        , @NamedQuery(name = "Personas.findByIdpersona", query = "SELECT * FROM personas WHERE idpersona = :idPersona")
        , @NamedQuery(name = "Personas.findByNombre", query = "SELECT * FROM personas WHERE nombre = :nombre")
        , @NamedQuery(name = "Personas.findByApellidos", query = "SELECT * FROM personas  WHERE apellidos = :apellidos")
        , @NamedQuery(name = "Personas.findByDni", query = "SELECT * FROM personas WHERE indentificador = :indentificador")
        , @NamedQuery(name = "Personas.findByNacionalidad", query = "SELECT * FROM nacionalidad WHERE nacionalidad = :nacionalidad")
        , @NamedQuery(name = "Personas.findByDireccion", query = "SELECT * FROM direccion WHERE direccion = :direccion")
        , @NamedQuery(name = "Personas.findByPoblacion", query = "SELECT * FROM poblacion WHERE poblacion = :poblacion")
        , @NamedQuery(name = "Personas.findByTelefono", query = "SELECT * FROM telefono WHERE telefono = :telefono")
        , @NamedQuery(name = "Personas.findEmail", query = "SELECT * FROM email WHERE email = :email")
        , @NamedQuery(name = "Personas.findByFechaNac", query = "SELECT * FROM fechaNac WHERE fechaNac = :fechaNac")})

public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPersona")
    private Integer idpersona;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "indetificador")
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
    //@OneToOne(cascade = CascadeType.ALL, mappedBy = "persona")
    //private Socios socios;

    public Persona() {
    }

    public Persona(Integer idpersona) {
        this.idpersona = idpersona;
    }

    public Integer getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Integer idpersona) {
        this.idpersona = idpersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getIndentidicador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String Direccion) {
        this.direccion = direccion;
    }
    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String Poblacion) {
        this.poblacion = poblacion;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;

    }public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

   /*** public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    } ***/


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpersona != null ? idpersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.idpersona == null && other.idpersona != null) || (this.idpersona != null && !this.idpersona.equals(other.idpersona))) {
            return false;
        }
        return true;
    }

    /*** @Override
    public String toString() {
        return "Entidades.Persona [idpersona=" + idpersona + ", nombre=" + nombre + ", apellidos=" + apellidos + ", identificador=" + identificador + "]";
    }
    @Override
    public String toString() {
        return "Entidades.Persona[ idpersona=" + idpersona + " ]";
    } ***/

}