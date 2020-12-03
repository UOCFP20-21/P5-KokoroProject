package es.kokoro;


import es.kokoro.dao.DAO;
import es.kokoro.dao.mysql.MySqlFactoryDAO;
import es.kokoro.model.Socio;
import es.kokoro.model.SubLineaAccion;
import es.kokoro.util.HibernateUtil;
import org.hibernate.Session;

import java.text.ParseException;

import static es.kokoro.commons.FormatFecha.FFStringToDate;
import static es.kokoro.enums.Periodo.ANU;
import static es.kokoro.enums.Periodo.MES;
import static java.lang.Boolean.TRUE;

public class App {
    public static void main(String[] args) throws Exception {

        Socio socio = new Socio("Sergio", "Garcia Lopez", "12345678A", "Español",
                "tu casa", "BCN", "963258741", "sergio@gmail.com", null, MES, 30.0,
                TRUE, FFStringToDate("2020-01-01"));
        Socio socio1 = new Socio("Manuel", "Robles Garcia", "987654321B", "Español",
                "mi casa", "JAEN", "654987321", "manu@gmail.com", null, ANU, 100.0,
                TRUE, FFStringToDate("2020-03-25"));
        SubLineaAccion subLineaAccion = new SubLineaAccion(null, "Accion tercer mundo");
        SubLineaAccion subLineaAccion1 = new SubLineaAccion(null, "Agua en Africa");
/*
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.save(socio);
            session.save(socio1);
            session.save(subLineaAccion);
            session.save(subLineaAccion1);
        } catch (Exception e) {

            e.printStackTrace();
        }
*/
        DAO<Socio> mySqlSocioFactoryDAO = MySqlFactoryDAO.getMySqlFactoryDAO(Socio.class);

        mySqlSocioFactoryDAO.save(socio);
        mySqlSocioFactoryDAO.save(socio1);
        Socio socioRecuperado = mySqlSocioFactoryDAO.get(1);
        socioRecuperado.setCuota(20);
        mySqlSocioFactoryDAO.update(socioRecuperado);
       // mySqlSocioFactoryDAO.delete(socioRecuperado);
    }
}