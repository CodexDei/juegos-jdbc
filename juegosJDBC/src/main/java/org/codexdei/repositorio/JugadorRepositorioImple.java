package org.codexdei.repositorio;

import org.codexdei.modelo.Jugador;
import org.codexdei.util.ConexionBaseDatos;

import org.codexdei.util.ConexionBaseDatos;

import java.sql.*;
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

        String sql;

        if (jugador.getIdJugador() != null && jugador.getIdJugador() > 30){

            sql = "UPDATE puntuacion SET alias=?, puntaje=?, fecha_registro=? WHERE idjugador=30";

        }else {
            sql = "INSERT INTO puntuacion(alias,puntaje,fecha_registro) VALUES(?,?,?)";
        }

        try(PreparedStatement stmt = ConexionBaseDatos.generarConexion().prepareStatement(sql)){

                stmt.setString(1, jugador.getAlias());
                stmt.setLong(2,jugador.getPuntaje());
                stmt.setDate(3,new Date(jugador.getFechaRegistro().getTime()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

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
