package es.kokoro;

import es.kokoro.enums.Periodos;
import es.kokoro.model.Socio;

public class Main {

    public static void main(String[] args) {
        Socio socio1 = new Socio(1L, "Jaume", "Calafat", "43000000A", "Española", "C/ Mi Casa, 1", "Lloseta", "686000000", "info@info.com", 1L, Periodos.ANU, 150.00, true);
        System.out.println(socio1.toString());
    }
}
