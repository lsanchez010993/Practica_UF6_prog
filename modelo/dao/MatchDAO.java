package modelo.dao;

import modelo.DatabaseConnection;
import modelo.Match;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchDAO implements GenericDAO<Match, Integer> {

    public List<Match> getMatchesOfTeam(String teamName) {
        List<Match> matches = new ArrayList<>();
        String sql = "SELECT p.partit_id, p.data_partit, p.matx, p.resultat, e.equip_id, e.ciutat, e.nom AS nom_equip " +
                "FROM partits p " +
                "JOIN equips e ON p.equip_id = e.equip_id " +
                "WHERE e.nom = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, teamName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Match match = new Match(
                        rs.getInt("partit_id"),
                        rs.getInt("equip_id"),
                        rs.getDate("data_partit"),
                        rs.getString("matx"),
                        rs.getString("resultat")
                );
                matches.add(match);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }

    @Override
    public Match findById(Integer partitId) {
        String sql = "SELECT * FROM partits WHERE partit_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, partitId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Match(
                        rs.getInt("partit_id"),
                        rs.getInt("equip_id"),
                        rs.getDate("data_partit"),
                        rs.getString("matx"),
                        rs.getString("resultat")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Match> findAll() {
        List<Match> matches = new ArrayList<>();
        String sql = "SELECT * FROM partits";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Match match = new Match(
                        rs.getInt("partit_id"),
                        rs.getInt("equip_id"),
                        rs.getDate("data_partit"),
                        rs.getString("matx"),
                        rs.getString("resultat")
                );
                matches.add(match);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }

    @Override
    public boolean insert(Match match) {
        String sql = "INSERT INTO partits (partit_id, equip_id, data_partit, matx, resultat) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, match.getPartit_id());
            pstmt.setInt(2, match.getEquip_id());
            pstmt.setDate(3, new java.sql.Date(match.getData_partit().getTime()));
            pstmt.setString(4, match.getMatx());
            pstmt.setString(5, match.getResultat());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Match match) {
        String sql = "UPDATE partits SET equip_id = ?, data_partit = ?, matx = ?, resultat = ? WHERE partit_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, match.getEquip_id());
            pstmt.setDate(2, new java.sql.Date(match.getData_partit().getTime()));
            pstmt.setString(3, match.getMatx());
            pstmt.setString(4, match.getResultat());
            pstmt.setInt(5, match.getPartit_id());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Integer partit_id) {
        String sql = "DELETE FROM partits WHERE partit_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, partit_id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Match> getAllTMatchForTeam(int equip_id) {
        List<Match> matches = new ArrayList<>();
        String sql = "SELECT p.partit_id, p.data_partit, p.equip_id, CONCAT(e1.nom, ' - ', e2.nom, ': ', p.resultat) AS resultado " +
                "FROM partits p " +
                "JOIN equips e1 ON p.equip_id = e1.equip_id " +
                "JOIN equips e2 ON SUBSTRING(p.matx, LENGTH(p.matx) - 2, 3) = e2.acronim " +
                "WHERE e1.equip_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, equip_id);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Match match = new Match(
                            rs.getInt("partit_id"),
                            rs.getInt("equip_id"),
                            rs.getDate("data_partit"),
                            rs.getString("resultado")
                    );
                    matches.add(match);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }
}
