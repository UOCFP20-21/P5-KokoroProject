package es.kokoro.model;

import java.util.List;

/**
 * Clase de Sede
 * Contiene información de la sede
 * Extiende de empresa
 */

public class Sede extends Empresa {

    //Atributos
    private List<Trabajador> trabajadorList;
    private Ong ong;

    /***
     * Constructor de la Clase Sede.
     * Esta clase no tiene Id porque la sede es única por lo que los valores que no pertenezcan a Empresa se guardarán probablemente en otra tabla junto a ONG (u otro sitio)
     * @param idEmpresa @see Empresa
     * @param nombre @see Empresa
     * @param pais @see Empresa
     * @param poblacion @see Empresa
     * @param direccionSocial @see Empresa
     * @param razonSocial @see Empresa
     * @param identificacionSocial @see Empresa
     * @param telefono @see Empresa
     * @param email @see Empresa
     * @param trabajadorList Listado de Trabajadores de la ONG
     * @param ong Objeto ONG con todas sus características.
     */
    public Sede(Long idEmpresa, String nombre, String pais, String poblacion, String direccionSocial, String razonSocial, String identificacionSocial, String telefono, String email, List<Trabajador> trabajadorList, Ong ong) {
        super(idEmpresa, nombre, pais, poblacion, direccionSocial, razonSocial, identificacionSocial, telefono, email);
        this.trabajadorList = trabajadorList;
        this.ong = ong;
    }

//Metodos publicos

    /**
     * Devuelve el listado de trabajadores
     *
     * @return listado Trabajador
     */
    public List<Trabajador> getTrabajadorList() {

        return trabajadorList;
    }

    /**
     * Devuelve Ong
     *
     * @return ong
     */
    public Ong getOng() {

        return ong;
    }

    /**
     * Modifica listado Trabajador
     * @param trabajadorList
     */
    public void setTrabajadorList(List<Trabajador> trabajadorList) {
        this.trabajadorList = trabajadorList;
    }


    /**
     * Modifica Ong
     * @param  ong
     */
    public void setOng(Ong ong) {
        this.ong = ong;
    }

    @Override
    public String toString() {
        return "Sede{" +
                "trabajadorList=" + trabajadorList.toString() +
                ", ong=" + ong +
                '}';
    }
}

