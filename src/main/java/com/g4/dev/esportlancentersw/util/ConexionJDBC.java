package com.g4.dev.esportlancentersw.util;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que proporsionara un objeto conexion para pasarle al objeto que
 * generara  reportes
 * @author Luis DEV
 * @since 1.0
 */
public class ConexionJDBC {

    private static String conxString = "jdbc:sqlserver://localhost;databaseName=LANCENTER;TrustServerCertificate=True";
    private static String username = "sa";

    private  static String password = "hazard60";

    private static  Connection conx;

    private ConexionJDBC(){}

    public static Connection getConx() throws SQLException {
        if (conx == null){
            conx = DriverManager.getConnection(conxString, username, password);
        }

        return  conx;

    }

}
