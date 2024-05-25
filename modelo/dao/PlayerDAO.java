package modelo.dao;

import modelo.DatabaseConnection;
import modelo.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO implements GenericDAO<Player, Integer> {

    @Override
    public Player findById(Integer jugador_id) {
        String sql = "SELECT * FROM jugadors WHERE jugador_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, jugador_id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Player(
                        rs.getInt("jugador_id"),
                        rs.getString("nom"),
                        rs.getString("cognom"),
                        rs.getDate("data_naixement"),
                        rs.getString("alcada"),
                        rs.getString("pes"),
                        rs.getString("dorsal"), // Añadido campo dorsal
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
    public List<Player> findAll() {
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
                        rs.getString("dorsal"), // Añadido campo dorsal
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
    public boolean insert(Player player) {
        String sql = "INSERT INTO jugadors (jugador_id, nom, cognom, data_naixement, alcada, pes, dorsal, posicio, equip_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, player.getJugador_id());
            pstmt.setString(2, player.getNom());
            pstmt.setString(3, player.getCognom());
            pstmt.setDate(4, new java.sql.Date(player.getDataNaixement().getTime()));
            pstmt.setString(5, player.getAlcada());
            pstmt.setString(6, player.getPes());
            pstmt.setString(7, player.getDorsal()); // Añadido campo dorsal
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
    public boolean update(Player player) {
        String sql = "UPDATE jugadors SET nom = ?, cognom = ?, data_naixement = ?, alcada = ?, pes = ?, dorsal = ?, posicio = ?, equip_id = ? WHERE jugador_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, player.getNom());
            pstmt.setString(2, player.getCognom());
            pstmt.setDate(3, new java.sql.Date(player.getDataNaixement().getTime()));
            pstmt.setString(4, player.getAlcada());
            pstmt.setString(5, player.getPes());
            pstmt.setString(6, player.getDorsal()); // Añadido campo dorsal
            pstmt.setString(7, player.getPosicio());
            pstmt.setInt(8, player.getEquip_id());
            pstmt.setInt(9, player.getJugador_id());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Integer jugador_id) {
        String sql = "DELETE FROM jugadors WHERE jugador_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, jugador_id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Player> findByTeamId(int equip_id) {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM jugadors WHERE equip_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, equip_id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Player player = new Player(
                        rs.getInt("jugador_id"),
                        rs.getString("nom"),
                        rs.getString("cognom"),
                        rs.getDate("data_naixement"),
                        rs.getString("alcada"),
                        rs.getString("pes"),
                        rs.getString("dorsal"), // Añadido campo dorsal
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

    public List<Player> findPlayersByTeamName(String teamName) {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT p.* FROM jugadors p JOIN equips e ON p.equip_id = e.equip_id WHERE e.nom = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, teamName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Player player = new Player(
                        rs.getInt("jugador_id"),
                        rs.getString("nom"),
                        rs.getString("cognom"),
                        rs.getDate("data_naixement"),
                        rs.getString("alcada"),
                        rs.getString("pes"),
                        rs.getString("dorsal"), // Añadido campo dorsal
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

    public List<Player> findPlayersByName(String name) {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM jugadors WHERE LOWER(nom) LIKE ? OR LOWER(cognom) LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + name.toLowerCase() + "%");
            pstmt.setString(2, "%" + name.toLowerCase() + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Player player = new Player(
                        rs.getInt("jugador_id"),
                        rs.getString("nom"),
                        rs.getString("cognom"),
                        rs.getDate("data_naixement"),
                        rs.getString("alcada"),
                        rs.getString("pes"),
                        rs.getString("dorsal"), // Añadido campo dorsal
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

    public List<Player> findPlayersByFullName(String nombre, String apellido) {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM jugadors WHERE LOWER(nom) = ? AND LOWER(cognom) = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre.toLowerCase());
            pstmt.setString(2, apellido.toLowerCase());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Player player = new Player(
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


    public List<String> findPlayersNameString() {
        List<String> playersName = new ArrayList<>();
        String sql = "SELECT nom FROM jugadors";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String teamName = rs.getString("nom");
                playersName.add(teamName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playersName;
    }

    public int getIdMax() {
        int id_max = 0; // Inicializamos el valor máximo del ID

        // Consulta SQL para obtener el máximo jugador_id
        String sql = "SELECT MAX(jugador_id) FROM jugadors";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet resultSet = pstmt.executeQuery()) {

            // Si la consulta devuelve un resultado
            if (resultSet.next()) {
                id_max = resultSet.getInt(1); // Obtenemos el máximo jugador_id
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id_max;


}

    public boolean updateTeamPlayerForName(int jugador_id, int teamId) {
        String sql = "UPDATE jugadors SET equip_id = ? WHERE jugador_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, teamId);
            pstmt.setInt(2, jugador_id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
