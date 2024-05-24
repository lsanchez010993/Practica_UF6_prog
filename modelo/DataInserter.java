package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataInserter {

    public static void main(String[] args) {
        String directory = "modelo/ArchivosGenerados/";
        insertarDatos(
                directory + "equipos.txt",
                directory + "jugadores.txt",
                directory + "estadisticas.txt",
                directory + "partidos.txt"
        );
    }

    public static void insertarDatos(String equiposFile, String jugadoresFile, String estadisticasFile, String partidosFile) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            insertarEquipos(conn, equiposFile);
            insertarJugadores(conn, jugadoresFile);
            insertarEstadisticas(conn, estadisticasFile);
            insertarPartidos(conn, partidosFile);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertarEquipos(Connection conn, String equiposFile) throws SQLException {
        String sql = "INSERT INTO equips (equip_id, ciutat, nom, acronim, divisio, guanyades, perdudes) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (BufferedReader br = new BufferedReader(new FileReader(equiposFile));
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                pstmt.setInt(1, Integer.parseInt(values[0]));
                pstmt.setString(2, values[1]);
                pstmt.setString(3, values[2]);
                pstmt.setString(4, values[3]);
                pstmt.setString(5, values[4]);
                pstmt.setInt(6, Integer.parseInt(values[5]));
                pstmt.setInt(7, Integer.parseInt(values[6]));
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void insertarJugadores(Connection conn, String jugadoresFile) throws SQLException {
        String sql = "INSERT INTO jugadors (jugador_id, nom, cognom, data_naixement, alcada, pes, dorsal, posicio, equip_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (BufferedReader br = new BufferedReader(new FileReader(jugadoresFile));
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                pstmt.setInt(1, Integer.parseInt(values[0]));
                pstmt.setString(2, values[1]);
                pstmt.setString(3, values[2]);
                pstmt.setDate(4, java.sql.Date.valueOf(values[3]));
                pstmt.setDouble(5, Double.parseDouble(values[4]));
                pstmt.setDouble(6, Double.parseDouble(values[5]));
                pstmt.setInt(7, Integer.parseInt(values[6]));
                pstmt.setString(8, values[7]);
                pstmt.setInt(9, Integer.parseInt(values[8]));
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void insertarEstadisticas(Connection conn, String estadisticasFile) throws SQLException {
        String sql = "INSERT INTO estadistiques_jugadors (jugador_id, partit_id, minuts_jugats, punts, tirs_anotats, tirs_tirats, tirs_triples_anotats, tirs_triples_tirats, tirs_lliures_anotats, tirs_lliures_tirats, rebots_ofensius, rebots_defensius, assistencies, robades, bloqueigs) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (BufferedReader br = new BufferedReader(new FileReader(estadisticasFile));
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                pstmt.setInt(1, Integer.parseInt(values[0]));
                pstmt.setInt(2, Integer.parseInt(values[1]));
                pstmt.setDouble(3, Double.parseDouble(values[2]));
                pstmt.setInt(4, Integer.parseInt(values[3]));
                pstmt.setInt(5, Integer.parseInt(values[4]));
                pstmt.setInt(6, Integer.parseInt(values[5]));
                pstmt.setInt(7, Integer.parseInt(values[6]));
                pstmt.setInt(8, Integer.parseInt(values[7]));
                pstmt.setInt(9, Integer.parseInt(values[8]));
                pstmt.setInt(10, Integer.parseInt(values[9]));
                pstmt.setInt(11, Integer.parseInt(values[10]));
                pstmt.setInt(12, Integer.parseInt(values[11]));
                pstmt.setInt(13, Integer.parseInt(values[12]));
                pstmt.setInt(14, Integer.parseInt(values[13]));
                pstmt.setInt(15, Integer.parseInt(values[14]));
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void insertarPartidos(Connection conn, String partidosFile) throws SQLException {
        String sql = "INSERT INTO partits (partit_id, equip_id, data_partit, matx, resultat) VALUES (?, ?, ?, ?, ?)";
        try (BufferedReader br = new BufferedReader(new FileReader(partidosFile));
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                pstmt.setInt(1, Integer.parseInt(values[0]));
                pstmt.setInt(2, Integer.parseInt(values[1]));
                pstmt.setDate(3, java.sql.Date.valueOf(values[2]));
                pstmt.setString(4, values[3]);
                pstmt.setString(5, values[4]);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
