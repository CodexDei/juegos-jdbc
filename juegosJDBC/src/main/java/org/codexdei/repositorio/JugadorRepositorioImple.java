package org.codexdei.repositorio;

import org.codexdei.modelo.Jugador;
import org.codexdei.util.ConexionBaseDatos;

import org.codexdei.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JugadorRepositorioImple implements Repositorio<Jugador> {

    private Connection generarConexionBaseDatos() throws SQLException {

        return ConexionBaseDatos.generarConexion();
    }

    @Override
    public List<Jugador> mostrarTabla() {

        List<Jugador> listaPuntaje = new ArrayList<>();

        try(Statement st = generarConexionBaseDatos().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM puntuacion ORDER BY puntaje DESC");){

            while (rs.next()){

                Jugador j = crearJugador(rs);

                listaPuntaje.add(j);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaPuntaje;
    }

    @Override
    public void guardar(Jugador jugador) {



    }

    private static Jugador crearJugador(ResultSet rs) throws SQLException {

        Jugador j = new Jugador();
        j.setIdJugador(rs.getInt("idpuntuacion"));
        j.setAlias(rs.getString("alias"));
        j.setPuntaje(rs.getLong("puntaje"));
        j.setFechaRegistro(rs.getDate("fecha_registro"));

        return j;
    }
}
