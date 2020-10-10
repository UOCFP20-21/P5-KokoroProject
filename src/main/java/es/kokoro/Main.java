package es.kokoro;

import es.kokoro.enums.Periodo;
import es.kokoro.model.Ingreso;
import es.kokoro.model.Socio;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Socio socio1 = new Socio(1L, "hjkg", "jgg",
                "12345678Z", "SP", "test",
                "test", "test", "test@test.com", 1L,
                Periodo.ANU, 20.0, true);

        Ingreso ingreso = new Ingreso(1L, socio1, 20.0, new Date());

        System.out.println("El ingreso es publico: " + ingreso.getIngreso().isPublico());
    }
}
