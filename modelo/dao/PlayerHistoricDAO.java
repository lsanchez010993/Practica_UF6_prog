package modelo.dao;

import modelo.DatabaseConnection;
import modelo.PlayerHistoric;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerHistoricDAO implements GenericDAO<PlayerHistoric, Integer> {

    @Override
    public PlayerHistoric findById(Integer id) {
        String sql = "SELECT * FROM jugadors_historic WHERE jugador_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new PlayerHistoric(
                        rs.getInt("jugador_id"),
                        rs.getString("nom"),
                        rs.getString("cognom"),
                        rs.getDate("data_naixement"),
                        rs.getString("alcada"),
                        rs.getString("pes"),
                        rs.getString("dorsal"),
                        rs.getString("posicio"),
                        rs.getInt("equip_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PlayerHistoric> findAll() {
        List<PlayerHistoric> players = new ArrayList<>();
        String sql = "SELECT * FROM jugadors_historic";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                PlayerHistoric player = new PlayerHistoric(
                        rs.getInt("jugador_id"),
                        rs.getString("nom"),
                        rs.getString("cognom"),
                        rs.getDate("data_naixement"),
                        rs.getString("alcada"),
                        rs.getString("pes"),
                        rs.getString("dorsal"),
                        rs.getString("posicio"),
                        rs.getInt("equip_id")
                );
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    @Override
    public boolean insert(PlayerHistoric player) {
        String sql = "INSERT INTO jugadors_historic (jugador_id, nom, cognom, data_naixement, alcada, pes, dorsal, posicio, equip_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, player.getJugador_id());
            pstmt.setString(2, player.getNom());
            pstmt.setString(3, player.getCognom());
            pstmt.setDate(4, new java.sql.Date(player.getDataNaixement().getTime()));
            pstmt.setString(5, player.getAlcada());
            pstmt.setString(6, player.getPes());
            pstmt.setString(7, player.getDorsal());
            pstmt.setString(8, player.getPosicio());
            pstmt.setInt(9, player.getEquip_id());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(PlayerHistoric player) {
        throw new UnsupportedOperationException("Método no soportado para PlayerHistoric");
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Método no soportado para PlayerHistoric");
    }
}
