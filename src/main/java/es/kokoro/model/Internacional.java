package es.kokoro.model;

public class Internacional extends EntePublico {

    private Long idInternacional;
    private String pais;


    /***
     *
     * @see EntePublico
     * @param idEntePublico Herencia EntePublico
     * @param nombre Herencia EntePublico
     * @param subvencion Herencia EntePublico
     * @param idInternacional Índice Internacional
     * @param pais Nombre del País
     */
    public Internacional(Long idEntePublico, String nombre, String subvencion, Long idInternacional, String pais) {
        super(idEntePublico, nombre, subvencion);
        this.idInternacional = idInternacional;
        this.pais = pais;
    }

    /***
     *
     * @return idInternacional
     */
    public Long getIdInternacional() {
        return idInternacional;
    }

    /***
     *
     * @param idInternacional
     */
    public void setIdInternacional(Long idInternacional) {
        this.idInternacional = idInternacional;
    }

    /***
     *
     * @return pais
     */
    public String getPais() {
        return pais;
    }

    /***
     *
     * @param pais
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Internacional{" +
                "idInternacional=" + idInternacional +
                ", pais='" + pais + '\'' +
                "} " + super.toString();
    }
}
