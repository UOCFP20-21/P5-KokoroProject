package es.kokoro.enums;

public enum Periodo {
    MES("Mensualidad"),
    TRI("Trimestral"),
    ANU("Anual");

    private String nombrePeriodo;

    Periodo(String nombrePeriodo) {
        this.nombrePeriodo = nombrePeriodo;
    }

    public String getNombrePeriodo() {
        return nombrePeriodo;
    }

    public void setNombrePeriodo(String nombrePeriodo) {
        this.nombrePeriodo = nombrePeriodo;
    }

}
