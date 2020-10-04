package es.kokoro.model;

import java.util.List;

/**
 * Clase de Socio Local
 * Contiene información del socio local
 */
public class SocioLocal extends Empresa {

    //Atributos
    /**
     * Listado de proyectos
     */
    private List<Proyecto> proyectosList;


    //Constructor
    public SocioLocal(){
        super();

    }

    // Metodos Publicos

    /**
     * Devuelve el listado de proyectos
     *
     * @return listado proyectos
     */
    public List<Proyecto> getProyectosList() {

        return proyectosList;
    }


    /**
     * Modifica listado Proyectos
     * @param proyectosList
     */
    public void setProyectosList(List<Proyecto> proyectosList) {
        this.proyectosList = proyectosList;
    }


}
