package es.kokoro.model;

import java.util.List;

/**
 * Clase Delegacion
 * Contiene información de la delegación
 * Extiende de emprea
 */

public class Delegacion extends Empresa{

    //Atributos
    /**
     * Listado de trabajador
     */
    private List<Trabajador> trabajadorList;
    /**
     * Ong
     * */
    private Ong ong;
    /**
     * Area Operativa
     */
    private String areaOperativa;

    //Constructor
    public Delegacion(String areaOperativa, Ong ong){
       super();
       this.areaOperativa=areaOperativa;
       this.ong=ong;
    }

    // Metodos Publicos

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
     * Devuelve el area Operativa
     *
     * @return area Operativa
     */
    public String getAreaOperativa() {

        return areaOperativa;
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
    /**
     * Modifica area Operativa
     * @param areaOperativa
     */
    public void setAreaOperativa(String areaOperativa) {
        this.areaOperativa = areaOperativa;
    }

}
