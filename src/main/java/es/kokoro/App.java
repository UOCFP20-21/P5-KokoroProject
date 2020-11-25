package es.kokoro;


import java.text.ParseException;
import java.util.List;

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
                "tu casa","BCN","963258741","sergio@gmail.com",1L,MES,30.0,
                TRUE,FFStringToDate("2020-01-01"));
        Socio socio1 = new Socio(2L, "Manuel", "Robles Garcia", "987654321B","Español",
                "mi casa","JAEN","654987321","manu@gmail.com",2L,ANU,100.0,
                TRUE,FFStringToDate("2020-03-25"));
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(socio);
            session.save(socio1);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List < Socio > socioList = session.createQuery("from Socio", Socio.class).list();
            socioList.forEach(s -> System.out.println(s.getNombre()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}