package org.codexdei.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {

    private static String url = "jdbc:mysql://localhost:3306/java_curso";
    private static String usuario = "root";
    private static String password = "admin";
    private static Connection conexion;

    public static Connection generarConexion() throws SQLException {

        if (conexion == null) {

            conexion = DriverManager.getConnection(url,usuario,password);
        }
        return conexion;
    }
}

