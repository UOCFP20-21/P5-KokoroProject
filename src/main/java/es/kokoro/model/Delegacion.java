package es.kokoro.model;

import java.util.List;

/**
 * Clase Delegacion
 * Contiene información de la delegación
 * Extiende de empresa
 */

public class Delegacion extends Empresa{

    //Atributos
    private Long idDelegacion;
    private List<Trabajador> trabajadorList;
    private Ong ong;
    private String areaOperativa;

    //Constructor
    /***
     *
     * @param idEmpresa Herencia de Empresa
     * @param nombre Herencia de Empresa
     * @param pais Herencia de Empresa
     * @param poblacion Herencia de Empresa
     * @param direccionSocial Herencia de Empresa
     * @param razonSocial Herencia de Empresa
     * @param identificacionSocial Herencia de Empresa
     * @param telefono Herencia de Empresa
     * @param email Herencia de Empresa
     * @param idDelegacion Índice de la Delegación
     * @param trabajadorList Listado de trabajadores
     * @param ong Ong
     * @param areaOperativa Area Operativa
     */
    public Delegacion(Long idEmpresa, String nombre, String pais, String poblacion, String direccionSocial, String razonSocial, String identificacionSocial, String telefono, String email, Long idDelegacion, List<Trabajador> trabajadorList, Ong ong, String areaOperativa) {
        super(idEmpresa, nombre, pais, poblacion, direccionSocial, razonSocial, identificacionSocial, telefono, email);
        this.idDelegacion = idDelegacion;
        this.trabajadorList = trabajadorList;
        this.ong = ong;
        this.areaOperativa = areaOperativa;
    }

    // Metodos Publicos
    /***
     *
     * @return idDelegacion
     */
    public Long getIdDelegacion() {
        return idDelegacion;
    }

    /***
     *
     * @param idDelegacion
     */
    public void setIdDelegacion(Long idDelegacion) {
        this.idDelegacion = idDelegacion;
    }

    /***
     * Devuelve el listado de trabajadores
     * @return listado Trabajador
     */
    public List<Trabajador> getTrabajadorList() {
        return trabajadorList;
    }
    /***
     * Modifica listado Trabajador
     * @param trabajadorList
     */
    public void setTrabajadorList(List<Trabajador> trabajadorList) {
        this.trabajadorList = trabajadorList;
    }

    /**
     * Devuelve Ong
     * @return ong
     */
    public Ong getOng() {
        return ong;
    }
    /**
     * Modifica Ong
     * @param  ong
     */
    public void setOng(Ong ong) {
        this.ong = ong;
    }

    /**
     * Devuelve el area Operativa
     * @return area Operativa
     */
    public String getAreaOperativa() {
        return areaOperativa;
    }
    /**
     * Modifica area Operativa
     * @param areaOperativa
     */
    public void setAreaOperativa(String areaOperativa) {
        this.areaOperativa = areaOperativa;
    }

    @Override
    public String toString() {
        return "Delegacion{" +
                "idDelegacion=" + idDelegacion +
                ", trabajadorList=" + trabajadorList.toString() +
                ", ong=" + ong.toString() +
                ", areaOperativa='" + areaOperativa + '\'' +
                "} " + super.toString();
    }
}
