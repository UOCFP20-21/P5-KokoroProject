package es.kokoro;

import es.kokoro.dao.hibernate.JPASocioDAO;
import es.kokoro.dao.mysql.MySQLParticularDAO;
import es.kokoro.dao.xml.*;
import es.kokoro.enums.Periodo;
import es.kokoro.model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static es.kokoro.commons.FormatFecha.FFDateToString;
import static es.kokoro.commons.FormatFecha.FFStringToDate;
import static es.kokoro.commons.sqlConection.*;

public class Main {

    public static void main(String[] args) throws Exception {

        JPASocioDAO jpaSocioDAO = new JPASocioDAO("KokoroTests");
        Socio socio = jpaSocioDAO.get(48L);
        System.out.println(socio.toString());
    }
}
