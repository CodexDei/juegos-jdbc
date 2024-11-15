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

        // Comandos SQL
        String sql_crearTablaTemporal = "CREATE TEMPORARY TABLE temp_puntuacion AS " +
                "SELECT alias, puntaje, fecha_registro " +
                "FROM puntuacion ORDER BY puntaje DESC;";
        String sql_vaciarTablaPuntuacion = "TRUNCATE TABLE puntuacion;";
        String sql_reinsertarDatos = "INSERT INTO puntuacion (alias, puntaje, fecha_registro) " +
                "SELECT alias, puntaje, fecha_registro FROM temp_puntuacion;";
        String sql_borrarTablaTemporal = "DROP TEMPORARY TABLE temp_puntuacion;";
        String sql_obtenerTablaOrdenada = "SELECT * FROM puntuacion ORDER BY idpuntuacion;";

        try (Connection connection = generarConexionBaseDatos()) {
            connection.setAutoCommit(false); // Desactivar autocommit

            try (PreparedStatement stmt_crearTablaTemporal = connection.prepareStatement(sql_crearTablaTemporal);
                 PreparedStatement stmt_vaciarTablaPuntuacion = connection.prepareStatement(sql_vaciarTablaPuntuacion);
                 PreparedStatement stmt_reinsertarDatos = connection.prepareStatement(sql_reinsertarDatos);
                 PreparedStatement stmt_borrarTablaTemporal = connection.prepareStatement(sql_borrarTablaTemporal);
                 PreparedStatement stmt_obtenerTablaOrdenada = connection.prepareStatement(sql_obtenerTablaOrdenada)) {

                // Crear tabla temporal
               // System.out.println("Creando tabla temporal...");
                stmt_crearTablaTemporal.execute();

                // Vaciar la tabla original
               // System.out.println("Vaciando tabla puntuacion...");
                stmt_vaciarTablaPuntuacion.execute();

                // Reinsertar datos ordenados
               // System.out.println("Reinsertando datos en tabla puntuacion...");
                stmt_reinsertarDatos.execute();

                // Borrar tabla temporal
               // System.out.println("Eliminando tabla temporal...");
                stmt_borrarTablaTemporal.execute();

                // Confirmar cambios
                connection.commit();
              //  System.out.println("Transacción completada.");

                // Obtener datos ordenados
                try (ResultSet rs = stmt_obtenerTablaOrdenada.executeQuery()) {
                    while (rs.next()) {
                        Jugador jugador = crearJugador(rs);
                        listaPuntaje.add(jugador);
                    }
                }

            } catch (SQLException e) {
                connection.rollback(); // Rollback en caso de error
                System.err.println("Error durante la transacción: " + e.getMessage());
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true); // Reactivar autocommit
            }

        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
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
