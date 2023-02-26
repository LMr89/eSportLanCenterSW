package com.g4.dev.esportlancentersw.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilDate {
    /**
     * @return El momento exacto de la accion ocurrida
     * @author Luis DEV
     * @since 1.0
     */
    public static String getExactlyDate(){
       return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }
}
