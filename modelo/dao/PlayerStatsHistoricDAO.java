package modelo.dao;

import modelo.DatabaseConnection;
import modelo.PlayerStatsHistoric;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerStatsHistoricDAO implements GenericDAO<PlayerStatsHistoric, Integer> {

    @Override
    public PlayerStatsHistoric findById(Integer id) {
        throw new UnsupportedOperationException("Método no soportado para PlayerStatsHistoric");
    }

    @Override
    public List<PlayerStatsHistoric> findAll() {
        List<PlayerStatsHistoric> stats = new ArrayList<>();
        String sql = "SELECT * FROM hist_estadistiques_jugadors";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                PlayerStatsHistoric stat = new PlayerStatsHistoric(
                        rs.getInt("jugador_id"),
                        rs.getInt("partit_id"),
                        rs.getString("nom"),
                        rs.getString("cognom"),
                        rs.getDouble("minuts_jugats"),
                        rs.getInt("punts"),
                        rs.getInt("tirs_anotats"),
                        rs.getInt("tirs_tirats"),
                        rs.getInt("tirs_triples_anotats"),
                        rs.getInt("tirs_triples_tirats"),
                        rs.getInt("tirs_lliures_anotats"),
                        rs.getInt("tirs_lliures_tirats"),
                        rs.getInt("rebots_ofensius"),
                        rs.getInt("rebots_defensius"),
                        rs.getInt("assistencies"),
                        rs.getInt("robades"),
                        rs.getInt("bloqueigs")
                );
                stats.add(stat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stats;
    }

    @Override
    public boolean insert(PlayerStatsHistoric stats) {
        String sql = "INSERT INTO hist_estadistiques_jugadors (jugador_id, partit_id, nom, cognom, minuts_jugats, punts, tirs_anotats, tirs_tirats, tirs_triples_anotats, tirs_triples_tirats, tirs_lliures_anotats, tirs_lliures_tirats, rebots_ofensius, rebots_defensius, assistencies, robades, bloqueigs, fecha_baja) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, stats.getJugador_id());
            pstmt.setInt(2, stats.getPartit_id());
            pstmt.setString(3, stats.getNom());
            pstmt.setString(4, stats.getCognom());
            pstmt.setDouble(5, stats.getMinutsJugats());
            pstmt.setInt(6, stats.getPunts());
            pstmt.setInt(7, stats.getTirsAnotats());
            pstmt.setInt(8, stats.getTirsTirats());
            pstmt.setInt(9, stats.getTirsTriplesAnotats());
            pstmt.setInt(10, stats.getTirsTriplesTirats());
            pstmt.setInt(11, stats.getTirsLliuresAnotats());
            pstmt.setInt(12, stats.getTirsLliuresTirats());
            pstmt.setInt(13, stats.getRebotsOfensius());
            pstmt.setInt(14, stats.getRebotsDefensius());
            pstmt.setInt(15, stats.getAssistencies());
            pstmt.setInt(16, stats.getRobades());
            pstmt.setInt(17, stats.getBloqueigs());
            pstmt.setDate(18, new java.sql.Date(System.currentTimeMillis())); // Agregamos la fecha de baja
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(PlayerStatsHistoric stats) {
        throw new UnsupportedOperationException("Método no soportado para PlayerStatsHistoric");
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Método no soportado para PlayerStatsHistoric");
    }
}
