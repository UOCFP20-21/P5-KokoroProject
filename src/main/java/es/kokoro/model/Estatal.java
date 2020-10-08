package es.kokoro.model;

import es.kokoro.enums.Organismo;

/**
 * Clase Estatales
 */

public class Estatal extends EntePublico {
    private Long idEstatal;
    private Organismo organismo;
    private String nombreOrganismo;

    /***
     *
     * @param idEntePublico Herencia EntePublico
     * @param nombre Herencia EntePublico
     * @param subvencion Herencia EntePublico
     * @param idEstatal Índice Estatal
     * @param organismo Tipo de Organismo estatal
     * @param nombreOrganismo Nombre del Tipo de Organismo
     */
    public Estatal(Long idEntePublico, String nombre, String subvencion, Long idEstatal, Organismo organismo, String nombreOrganismo) {
        super(idEntePublico, nombre, subvencion);
        this.idEstatal = idEstatal;
        this.organismo = organismo;
        this.nombreOrganismo = nombreOrganismo;
    }

    /***
     *
     * @return idEstatal
     */
    public Long getIdEstatal() {
        return idEstatal;
    }

    /***
     *
     * @param idEstatal
     */
    public void setIdEstatal(Long idEstatal) {
        this.idEstatal = idEstatal;
    }

    /***
     *
     * @return organismo
     */
    public Organismo getOrganismo() {
        return organismo;
    }

    /***
     *
     * @param organismo
     */
    public void setOrganismo(Organismo organismo) {
        this.organismo = organismo;
    }

    /***
     *
     * @return nombreOrganismo
     */
    public String getNombreOrganismo() {
        return nombreOrganismo;
    }

    /***
     *
     * @param nombreOrganismo
     */
    public void setNombreOrganismo(String nombreOrganismo) {
        this.nombreOrganismo = nombreOrganismo;
    }

    @Override
    public String toString() {
        return "Estatal{" +
                "organismo=" + organismo.getNombreOrganismo() + " ("+ organismo.name() +")" +
                ", nombreOrganismo='" + nombreOrganismo + '\'' +
                "} " + super.toString();
    }

}
