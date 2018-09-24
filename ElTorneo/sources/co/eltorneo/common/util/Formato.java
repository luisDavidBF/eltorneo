 /*
 * Formato.java
 *
 * Proyecto: Doctor Pin
 * Cliente: 
 * Copyright 2016 by Mobiltech SAS 
 * All rights reserved
 */
package co.eltorneo.common.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

/**
 *
 * @author Sys. E. Diego Armando Hernandez
 *
 */
public class Formato {

    public static String formatoFechaMostrar(String fecha) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//yyyy-MM-dd'T'HH:mm:ss
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
        Date data = sdf.parse(fecha);
        String formattedTime = output.format(data);

       return formattedTime;
    }

    public Formato() {
    }

    public static String printString(String str) {
        String res = "";
        if (!ValidaString.isNullOrEmptyString(str)) {
            res = str.trim();
        }
        return res;
    }

    public static String formatToMoney(String str) {
        DecimalFormat fmt = new DecimalFormat("###,###,###,##0.00");
        String res = " ";
        if (!ValidaString.isNullOrEmptyString(str)) {
            res = fmt.format(Double.parseDouble(str));
        }
        return res;
    }

    public static String formatCalendar(Calendar cal, int format) {
        String fechaRetorno = "";
        Date fecha;
        SimpleDateFormat sdf;
        if (cal != null) {
            fecha = new Date(cal.getTimeInMillis());
            String formato = "dd-MM-yyyy HH:mm:ss";
            if (format == Generales.DATE_FECHA) {
                formato = "dd-MM-yyyy";
            } else if (format == Generales.DATE_FECHA_YY_MM_DD) {
                formato = "yyyy-MM-dd";
            } else if (format == Generales.DATE_FECHA_HORA_SQL) {
                formato = "yyyy-MM-dd HH:mm:ss";
            }
            sdf = new SimpleDateFormat(formato);
            fechaRetorno = sdf.format(fecha);
        }

        return fechaRetorno;
    }

    public static Calendar parseCalendar(String fecha, int format) {
        Calendar cal = null;
        if (!ValidaString.isNullOrEmptyString(fecha)) {
            fecha = fecha.trim();
            switch (format) {
                case Generales.DATE_FECHA:
                    if (fecha.length() <= 10 && fecha.length() >= 8) {
                        StringTokenizer fechaStr = new StringTokenizer(fecha, "-");
                        int day = Integer.parseInt(fechaStr.nextToken());
                        int month = Integer.parseInt(fechaStr.nextToken()) - 1;
                        int year = Integer.parseInt(fechaStr.nextToken());
                        cal = Calendar.getInstance();
                        cal.set(year, month, day);
                    }
                    break;
                case Generales.DATE_FECHA_HORA:
                    if (fecha.length() <= 19 && fecha.length() >= 13) {
                        StringTokenizer fechaStr = new StringTokenizer(fecha, "-");
                        int day = Integer.parseInt(fechaStr.nextToken());
                        int month = Integer.parseInt(fechaStr.nextToken()) - 1;
                        String horaStr = fechaStr.nextToken();
                        int year = Integer.parseInt(horaStr.substring(0, 4));
                        horaStr = horaStr.substring(5, horaStr.length());
                        StringTokenizer horaToken = new StringTokenizer(horaStr,
                                ":");
                        int hour = Integer.parseInt(horaToken.nextToken());
                        int min = Integer.parseInt(horaToken.nextToken());
                        int seg = Integer.parseInt(horaToken.nextToken());

                        cal = Calendar.getInstance();
                        cal.set(year, month, day, hour, min, seg);
                    }
                    break;
            }
        }
        return cal;
    }

    public static java.sql.Timestamp parseSqlDate(Calendar cal) {
        return new java.sql.Timestamp(cal.getTimeInMillis());
    }

    public static java.sql.Timestamp parseSqlTimestamp(Calendar cal) {
        return new java.sql.Timestamp(cal.getTimeInMillis());
    }

    public static java.sql.Date parseSqlDate(String str, int format) {
        java.sql.Date date = null;
        if (!ValidaString.isNullOrEmptyString(str)) {
            if (str.length() <= 10 && (format == Generales.DATE_FECHA)) {
                StringTokenizer fecha = new StringTokenizer(str, "-");
                int day = Integer.parseInt(fecha.nextToken());
                int month = Integer.parseInt(fecha.nextToken()) - 1;
                int year = Integer.parseInt(fecha.nextToken());
                Calendar cal = Calendar.getInstance();
                cal.set(year, month, day);
                date = new java.sql.Date(cal.getTimeInMillis());
            } else if (str.length() <= 19) {
                StringTokenizer fecha = new StringTokenizer(str, "-");
                int day = Integer.parseInt(fecha.nextToken());
                int month = Integer.parseInt(fecha.nextToken()) - 1;
                int year = Integer.parseInt(fecha.nextToken());

                Calendar cal = Calendar.getInstance();
                cal.set(year, month, day);
                date = new java.sql.Date(cal.getTimeInMillis());
            }
        }
        return date;
    }

    public static Calendar parseCalendarConSlash(String fecha, int format) {
        Calendar cal = null;
        if (!ValidaString.isNullOrEmptyString(fecha)) {
            fecha = fecha.trim();
            switch (format) {
                case Generales.DATE_FECHA:
                    if (fecha.length() <= 10 && fecha.length() >= 8) {
                        StringTokenizer fechaStr = new StringTokenizer(fecha, "/");
                        int day = Integer.parseInt(fechaStr.nextToken());
                        int month = Integer.parseInt(fechaStr.nextToken()) - 1;
                        int year = Integer.parseInt(fechaStr.nextToken());
                        cal = Calendar.getInstance();
                        cal.set(year, month, day);
                    }
                    break;
                case Generales.DATE_FECHA_HORA:
                    if (fecha.length() <= 19 && fecha.length() >= 13) {
                        StringTokenizer fechaStr = new StringTokenizer(fecha, "-");
                        int day = Integer.parseInt(fechaStr.nextToken());
                        int month = Integer.parseInt(fechaStr.nextToken()) - 1;
                        String horaStr = fechaStr.nextToken();
                        int year = Integer.parseInt(horaStr.substring(0, 4));
                        horaStr = horaStr.substring(5, horaStr.length());
                        StringTokenizer horaToken = new StringTokenizer(horaStr, ":");
                        int hour = Integer.parseInt(horaToken.nextToken());
                        int min = Integer.parseInt(horaToken.nextToken());
                        int seg = Integer.parseInt(horaToken.nextToken());

                        cal = Calendar.getInstance();
                        cal.set(year, month, day, hour, min, seg);
                    }
                    break;
            }
        }
        return cal;
    }
    
    public static String formatoFecha(String fecha) throws ParseException {
       SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");//yyyy-MM-dd'T'HH:mm:ss
           SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
           Date data = sdf.parse(fecha);
           String formattedTime = output.format(data);
         
       
       return formattedTime;
   }
}
