package es.kokoro;

import es.kokoro.enums.Organismo;
import es.kokoro.enums.Periodo;
import es.kokoro.model.Estatal;
import es.kokoro.model.Ingreso;
import es.kokoro.model.Socio;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Socio socio1 = new Socio(1L, "hjkg", "jgg",
                "12345678Z", "SP", "test",
                "test", "test", "test@test.com", 1L,
                Periodo.ANU, 20.0, true);
        Estatal estatal1 = new Estatal(1L, "espa", "bcn", 1L, Organismo.COM, "Ayun");


        Ingreso ingreso = new Ingreso(1L, socio1, 20.0, new Date());
        Ingreso ingreso1 = new Ingreso(2L, estatal1, 40.0, new Date());

        System.out.println("El ingreso es publico: " + ingreso.getIngreso().isPublico());
        System.out.println("El ingreso es publico: " + ingreso1.getIngreso().isPublico());
    }
}
