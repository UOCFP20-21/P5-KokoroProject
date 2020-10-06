package es.kokoro.enums;

public enum Organismo {
    EST("Estado"),
    DIP("Diputación"),
    COM("Comarcal"),
    PRO("Provincial"),
    AUT("Autonómico");

    private String nombreOrganismo;

    Organismo(String nombreOrganismo) { this.nombreOrganismo = nombreOrganismo; }

    public String getNombreOrganismo() {
        return nombreOrganismo;
    }

    public void setNombreOrganismo(String nombreOrganismo) {
        this.nombreOrganismo = nombreOrganismo;
    }
}

