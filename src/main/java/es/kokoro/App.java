package es.kokoro;


import java.text.ParseException;
import java.util.List;


import es.kokoro.model.SubLineaAccion;
import org.hibernate.Session;
import org.hibernate.Transaction;

import es.kokoro.model.Socio;
import es.kokoro.model.Persona;
import es.kokoro.util.HibernateUtil;

import static es.kokoro.commons.FormatFecha.FFStringToDate;
import static es.kokoro.enums.Periodo.*;
import static java.lang.Boolean.*;
import static es.kokoro.commons.FormatFecha.*;

public class App {
    public static void main(String[] args) throws ParseException {

        Socio socio = new Socio(1L, "Sergio", "Garcia Lopez", "12345678A","Español",
                "tu casa","BCN","963258741","sergio@gmail.com",1,MES,30.0,
                TRUE,FFStringToDate("2020-01-01"));
        Socio socio1 = new Socio(2L, "Manuel", "Robles Garcia", "987654321B","Español",
                "mi casa","JAEN","654987321","manu@gmail.com",2,ANU,100.0,
                TRUE,FFStringToDate("2020-03-25"));
        SubLineaAccion subLineaAccion = new SubLineaAccion(1L,"Accion tercer mundo");
        SubLineaAccion subLineaAccion1 = new SubLineaAccion(2L,"Agua en Africa");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.save(socio);
            session.save(socio1);
            session.save(subLineaAccion);
            session.save(subLineaAccion1);
        } catch (Exception e) {

            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}