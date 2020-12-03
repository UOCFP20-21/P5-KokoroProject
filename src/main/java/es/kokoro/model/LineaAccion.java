package es.kokoro.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Clase Linea de Accion
 * Contiene informacion de la Linea de Accion
 */
@Entity
@Table(name = "LineasAccion")
public class LineaAccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "idLineaAccion", unique = true)
    private Long idLineaAccion;
    @Column(name = "linea", unique = true, nullable = false)
    private String linea;

    public LineaAccion()
    {

    }
    /***
     *
     * @param idLineaAccion Índice Línea de Acción
     * @param linea Nombre de la línea de Acción
     */
    public LineaAccion(Long idLineaAccion, String linea) {
        this.idLineaAccion = idLineaAccion;
        this.linea = linea;
    }

    /***
     *
     * @return idLineaAccion
     */
    public Long getIdLineaAccion() {
        return idLineaAccion;
    }

    /***
     *
     * @param idLineaAccion
     */
    public void setIdLineaAccion(Long idLineaAccion) {
        this.idLineaAccion = idLineaAccion;
    }

    /***
     *
     * @return linea
     */
    public String getLinea() {
        return linea;
    }

    /***
     *
     * @param linea
     */
    public void setLinea(String linea) {
        this.linea = linea;
    }

    @Override
    public String toString() {
        return "LineaAccion{" +
                "idLineaAccion=" + idLineaAccion +
                ", linea='" + linea + '\'' +
                '}';
    }
}