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
        String sql_tablaTemporal = "CREATE TEMPORARY TABLE temp_puntuacion (alias VARCHAR(255), puntaje INT, fecha_registro DATE);";
        String sql_copiarDatosATemporal = "INSERT INTO temp_puntuacion (alias, puntaje, fecha_registro) " +
                "SELECT alias, puntaje, fecha_registro FROM puntuacion ORDER BY puntaje DESC;";
        String sql_vaciarPuntuacion = "TRUNCATE TABLE puntuacion;";
        String sql_reinsertarDatosPuntuacion = "INSERT INTO puntuacion (alias, puntaje, fecha_registro) " +
                "SELECT alias, puntaje, fecha_registro FROM temp_puntuacion;";
        String sql_borrarTablaTemporal = "DROP TEMPORARY TABLE temp_puntuacion;";
        String sql_tablaPuntacionMayorAMenor = "SELECT * FROM puntuacion ORDER BY puntaje DESC;";

        try (Connection connection = generarConexionBaseDatos()) {
            // Desactivar el autocommit para manejar la transacción manualmente
            connection.setAutoCommit(false);

            try (PreparedStatement stmt_tablaTemporal = connection.prepareStatement(sql_tablaTemporal);
                 PreparedStatement stmt_copiarDatosATemporal = connection.prepareStatement(sql_copiarDatosATemporal);
                 PreparedStatement stmt_vaciarPuntuacion = connection.prepareStatement(sql_vaciarPuntuacion);
                 PreparedStatement stmt_reinsertarDatosPuntuacion = connection.prepareStatement(sql_reinsertarDatosPuntuacion);
                 PreparedStatement stmt_borrarTablaTemporal = connection.prepareStatement(sql_borrarTablaTemporal);
                 PreparedStatement stmt_tablaPuntacionMayorAMenor = connection.prepareStatement(sql_tablaPuntacionMayorAMenor)) {

                // Crear la tabla temporal
                stmt_tablaTemporal.execute();

                // Copiar los datos ordenados a la tabla temporal
                stmt_copiarDatosATemporal.execute();

                // Vaciar la tabla original
                stmt_vaciarPuntuacion.execute();

                // Reinsertar los datos en la tabla original
                stmt_reinsertarDatosPuntuacion.execute();

                // Eliminar la tabla temporal
                stmt_borrarTablaTemporal.execute();

                // Hacer commit de la transacción
                connection.commit();

                // Ejecutar la consulta para mostrar la tabla puntuacion ordenada
                ResultSet rs = stmt_tablaPuntacionMayorAMenor.executeQuery();

                while (rs.next()) {
                    Jugador j = crearJugador(rs); // Método hipotético para crear un objeto Jugador desde el ResultSet
                    listaPuntaje.add(j);
                }

                rs.close();

            } catch (SQLException e) {
                // Hacer rollback en caso de error
                connection.rollback();
                System.err.println("Se ha producido un error. Se hizo rollback de la transacción.");
                e.printStackTrace();
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
