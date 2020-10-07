package es.kokoro.model;

import es.kokoro.model.interfaces.iFinanciador;

public class Financiador implements iFinanciador {
    private Long idFinanciador;
    private Object tipoFinanciador;
    private double financiacionAportada = 0.0;
    private String moneda;

    /***
     *
     * @param idFinanciador Índice de financiador
     * @param tipoFinanciador
     * @param financiacionAportada
     * @param moneda
     */
    public Financiador(Long idFinanciador, Object tipoFinanciador, double financiacionAportada, String moneda) {
        this.idFinanciador = idFinanciador;
        this.tipoFinanciador = tipoFinanciador;
        this.financiacionAportada = financiacionAportada;
        this.moneda = moneda;
    }

    /***
     *
     * @return idFinanciador
     */
    public Long getIdFinanciador() {
        return idFinanciador;
    }

    /***
     *
     * @param idFinanciador
     */
    public void setIdFinanciador(Long idFinanciador) {
        this.idFinanciador = idFinanciador;
    }

    /***
     *
     * @return tipoFinanciador
     */
    public Object getTipoFinanciador() {
        return tipoFinanciador;
    }

    /***
     *
     * @param tipoFinanciador
     */
    public void setTipoFinanciador(Object tipoFinanciador) {
        this.tipoFinanciador = tipoFinanciador;
        // Revisar si mejor usar try/catch (o si se puede....)
        if(tipoFinanciador instanceof iFinanciador)
        {
            this.tipoFinanciador = tipoFinanciador;
        }
        else{
            System.out.println("Fuente de financiación no válida");
        }
    }

    /***
     *
     * @return financiacionAportada
     */
    public double getFinanciacionAportada() {
        return financiacionAportada;
    }

    /***
     *
     * @param financiacionAportada
     */
    public void setFinanciacionAportada(double financiacionAportada) {
        this.financiacionAportada = financiacionAportada;
    }

    /***
     *
     * @return moneda
     */
    public String getMoneda() {
        return moneda;
    }

    /***
     *
     * @param moneda
     */
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    @Override
    public String toString() {
        return "Financiador{" +
                "idFinanciador=" + idFinanciador +
                ", tipoFinanciador=" + tipoFinanciador.toString() +
                ", financiacionAportada=" + financiacionAportada +
                ", moneda='" + moneda + '\'' +
                '}';
    }
}
