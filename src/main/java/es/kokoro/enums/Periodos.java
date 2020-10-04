package es.kokoro.enums;

public enum Periodos {
    MES("Mensualidad"),
    TRI("Trimestral"),
    ANU("Anual");

    private String nombrePeriodo;

    Periodos(String nombrePeriodo) {
        this.nombrePeriodo = nombrePeriodo;
    }

    public String getNombrePeriodo() {
        return nombrePeriodo;
    }

    public void setNombrePeriodo(String nombrePeriodo) {
        this.nombrePeriodo = nombrePeriodo;
    }

}
