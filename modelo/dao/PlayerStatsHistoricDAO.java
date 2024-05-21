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
        String sql = "SELECT * FROM estadistiques_jugadors_historic";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                PlayerStatsHistoric stat = new PlayerStatsHistoric(
                        rs.getInt("jugador_id"),
                        rs.getInt("partit_id"),
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
        String sql = "INSERT INTO estadistiques_jugadors_historic (jugador_id, partit_id, minuts_jugats, punts, tirs_anotats, tirs_tirats, tirs_triples_anotats, tirs_triples_tirats, tirs_lliures_anotats, tirs_lliures_tirats, rebots_ofensius, rebots_defensius, assistencies, robades, bloqueigs) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, stats.getJugador_id());
            pstmt.setInt(2, stats.getPartit_id());
            pstmt.setDouble(3, stats.getMinutsJugats());
            pstmt.setInt(4, stats.getPunts());
            pstmt.setInt(5, stats.getTirsAnotats());
            pstmt.setInt(6, stats.getTirsTirats());
            pstmt.setInt(7, stats.getTirsTriplesAnotats());
            pstmt.setInt(8, stats.getTirsTriplesTirats());
            pstmt.setInt(9, stats.getTirsLliuresAnotats());
            pstmt.setInt(10, stats.getTirsLliuresTirats());
            pstmt.setInt(11, stats.getRebotsOfensius());
            pstmt.setInt(12, stats.getRebotsDefensius());
            pstmt.setInt(13, stats.getAssistencies());
            pstmt.setInt(14, stats.getRobades());
            pstmt.setInt(15, stats.getBloqueigs());
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
