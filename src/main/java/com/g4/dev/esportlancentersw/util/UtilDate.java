package com.g4.dev.esportlancentersw.util;

import org.apache.commons.lang.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UtilDate {
    private final static  Logger log = LogManager.getLogger(UtilDate.class);
    /**
     * @return El momento exacto de la accion ocurrida
     * @author Luis DEV
     * @since 1.0
     */
    public static String getExactlyDate(){
       return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }

    /**
     * Método encargado de dar un objeto calendario del momento en el que se
     * le llama
     * @return Calendar ojeto calendar
     * @author Luis DEV
     * @since 1.0
     */
    public  static Calendar getExactlyCalendar(){
        return  Calendar.getInstance();
    }

    /**
     * Método encargado de verificar si la fecha dada se encuentra en el rango de una fecha
     * de inicio y otra de fin
     * @param calInicio fecha inicio
     * @param  calFin fecha Fin
     * @param  calToCompare fecha a comparar en el rango
     * @return boolean corresponse true si esta dentro del rango, false sino lo esta
     * @author Luis DEV
     * @since 1.0
     */
    public  static  boolean isCalendarBetween(Calendar calInicio, Calendar calFin, Calendar calToCompare){
        int hourOfDay = calToCompare.get(Calendar.HOUR_OF_DAY);
        calToCompare.set(Calendar.HOUR_OF_DAY , hourOfDay);
        /*Calendar caltoCheck = new GregorianCalendar(
                calToCompare.get(Calendar.YEAR),
                calToCompare.get(Calendar.MONTH),
                calToCompare.get(Calendar.DAY_OF_MONTH),
                calToCompare.get(Calendar.HOUR_OF_DAY),
                calToCompare.get(Calendar.MINUTE)
        );*/
        return  calToCompare.compareTo(calInicio) >= 0 &&  calToCompare.compareTo(calFin) <= 0;
    }

    public static String getDateToStringForPdf(){
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
}
