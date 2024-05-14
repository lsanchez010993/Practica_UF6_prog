package dao;

import modelo.DatabaseConnection;
import modelo.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    public Player getPlayerById(int jugadorId) {
        String sql = "SELECT * FROM jugadors WHERE jugador_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, jugadorId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Player(
                        rs.getInt("jugador_id"),
                        rs.getString("nom"),
                        rs.getString("cognom"),
                        rs.getDate("data_naixement"),
                        rs.getString("alcada"),
                        rs.getString("pes"),
                        rs.getString("posicio"),
                        rs.getInt("equip_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM jugadors";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Player player = new Player(
                        rs.getInt("jugador_id"),
                        rs.getString("nom"),
                        rs.getString("cognom"),
                        rs.getDate("data_naixement"),
                        rs.getString("alcada"),
                        rs.getString("pes"),
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

    public void insertPlayer(Player player) {
        String sql = "INSERT INTO jugadors (jugador_id, nom, cognom, data_naixement, alcada, pes, posicio, equip_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, player.getJugadorId());
            pstmt.setString(2, player.getNom());
            pstmt.setString(3, player.getCognom());
            pstmt.setDate(4, new java.sql.Date(player.getDataNaixement().getTime()));
            pstmt.setString(5, player.getAlcada());
            pstmt.setString(6, player.getPes());
            pstmt.setString(7, player.getPosicio());
            pstmt.setInt(8, player.getEquipId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePlayer(Player player) {
        String sql = "UPDATE jugadors SET nom = ?, cognom = ?, data_naixement = ?, alcada = ?, pes = ?, posicio = ?, equip_id = ? WHERE jugador_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, player.getNom());
            pstmt.setString(2, player.getCognom());
            pstmt.setDate(3, new java.sql.Date(player.getDataNaixement().getTime()));
            pstmt.setString(4, player.getAlcada());
            pstmt.setString(5, player.getPes());
            pstmt.setString(6, player.getPosicio());
            pstmt.setInt(7, player.getEquipId());
            pstmt.setInt(8, player.getJugadorId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePlayer(int jugadorId) {
        String sql = "DELETE FROM jugadors WHERE jugador_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, jugadorId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Player> getPlayersByTeam(int equipId) {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM jugadors WHERE equip_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, equipId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Player player = new Player(
                        rs.getInt("jugador_id"),
                        rs.getString("nom"),
                        rs.getString("cognom"),
                        rs.getDate("data_naixement"),
                        rs.getString("alcada"),
                        rs.getString("pes"),
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
}
