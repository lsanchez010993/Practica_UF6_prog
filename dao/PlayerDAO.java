package dao;

import modelo.DatabaseConnection;
import modelo.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    public Player getPlayerById(int id) {
        String sql = "SELECT * FROM jugadors WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Player(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("cognom"),
                        rs.getDate("data_naixement"),
                        rs.getString("altura"),
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
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("cognom"),
                        rs.getDate("data_naixement"),
                        rs.getString("altura"),
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
        String sql = "INSERT INTO jugadors (id, nom, cognom, data_naixement, altura, pes, posicio, equip_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, player.getId());
            pstmt.setString(2, player.getNombre());
            pstmt.setString(3, player.getApellido());
            pstmt.setDate(4, new java.sql.Date(player.getFechaNacimiento().getTime()));
            pstmt.setString(5, player.getAltura());
            pstmt.setString(6, player.getPeso());
            pstmt.setString(7, player.getPosicion());
            pstmt.setInt(8, player.getIdEquipo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePlayer(Player player) {
        String sql = "UPDATE jugadors SET nom = ?, cognom = ?, data_naixement = ?, altura = ?, pes = ?, posicio = ?, equip_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, player.getNombre());
            pstmt.setString(2, player.getApellido());
            pstmt.setDate(3, new java.sql.Date(player.getFechaNacimiento().getTime()));
            pstmt.setString(4, player.getAltura());
            pstmt.setString(5, player.getPeso());
            pstmt.setString(6, player.getPosicion());
            pstmt.setInt(7, player.getIdEquipo());
            pstmt.setInt(8, player.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePlayer(int id) {
        String sql = "DELETE FROM jugadors WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Player> getPlayersByTeam(int id) {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM jugadors WHERE equip_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Player player = new Player(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("cognom"),
                        rs.getDate("data_naixement"),
                        rs.getString("altura"),
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
