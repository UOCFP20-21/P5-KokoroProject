package es.kokoro.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatFecha {

    public static Date FFStringToDate(String t) throws ParseException {
        SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date r = formatFecha.parse(t);
        return r;
    }
    public static Date FFStringToDate(String t, String formato) throws ParseException {
        SimpleDateFormat formatFecha = new SimpleDateFormat(formato);
        Date r = formatFecha.parse(t);
        return r;
    }
    public static String FFDateToString(Date t) throws ParseException {
        SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        String r = formatFecha.format(t);
        return r;
    }
    public static String FFDateToString(Date t, String formato) throws ParseException {
        SimpleDateFormat formatFecha = new SimpleDateFormat(formato);
        String r = formatFecha.format(t);
        return r;
    }
}
