package org.codexdei;

import org.codexdei.modelo.Jugador;
import org.codexdei.repositorio.JugadorRepositorioImple;
import org.codexdei.repositorio.Repositorio;
import org.codexdei.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class PruebasJuego {

    public static void main(String[] args) {

        try(Connection conexion = ConexionBaseDatos.generarConexion()){

            Repositorio<Jugador> tablaBD = new JugadorRepositorioImple();

            System.out.println("************ Tabla puntaje **************");

            tablaBD.mostrarTabla().forEach(System.out::println);

            System.out.println("************** GUARDAR *************");

            Jugador jugador1 = new Jugador();

            jugador1.setAlias("Pedro");
            jugador1.setPuntaje(500L);
            jugador1.setFechaRegistro(new Date());

            tablaBD.guardar(jugador1);

            System.out.println("************ Tabla puntaje Actualizada **************");

            tablaBD.mostrarTabla().forEach(System.out::println);




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
