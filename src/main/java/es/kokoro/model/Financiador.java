package es.kokoro.model;

import es.kokoro.model.interfaces.iFinanciador;

public class Financiador implements iFinanciador {
    private Long idFinanciador;
    private Object tipoFinanciador;
    private double financiaciónAportada = 0.0;
    private String moneda;

    /***
     *
     * @param idFinanciador Índice de financiador
     * @param tipoFinanciador
     * @param financiaciónAportada
     * @param moneda
     */
    public Financiador(Long idFinanciador, Object tipoFinanciador, double financiaciónAportada, String moneda) {
        this.idFinanciador = idFinanciador;
        this.tipoFinanciador = tipoFinanciador;
        this.financiaciónAportada = financiaciónAportada;
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
     * @return financiaciónAportada
     */
    public double getFinanciaciónAportada() {
        return financiaciónAportada;
    }

    /***
     *
     * @param financiaciónAportada
     */
    public void setFinanciaciónAportada(double financiaciónAportada) {
        this.financiaciónAportada = financiaciónAportada;
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
                ", financiaciónAportada=" + financiaciónAportada +
                ", moneda='" + moneda + '\'' +
                '}';
    }
}
