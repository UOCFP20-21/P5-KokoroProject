package es.kokoro.model;

import java.util.List;

/**
 * Clase de Sede
 * Contiene información de la sede
 * Extiende de empresa
 */

public class Sede extends Empresa {

    //Atributos
    /**
     * Listado de trabajadores
     */
    private List<Trabajador> trabajadorList;
    /**
     *
     * Ong
     * */
    private Ong ong;


    //Constructor
    public Sede(Ong ong){
        super();
        this.ong=ong;
    }

    //Metodos publicos

    /**
     * Devuelve el listado de trabajadores
     *
     * @return listado Trabajador
     */
    public List<Trabajador> getTrabajadoresList() {

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
    public void setTrabajadoresList(List<Trabajador> trabajadorList) {
        this.trabajadorList = trabajadorList;
    }


    /**
     * Modifica Ong
     * @param  ong
     */
    public void setOng(Ong ong) {
        this.ong = ong;
    }


    }

