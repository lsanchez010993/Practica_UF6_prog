
package modelo.dao;

import modelo.DatabaseConnection;
import modelo.Match;
import modelo.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchDAO implements GenericDAO<Match, Integer> {
    /*
    3.- Llistar tots els partits jugats per un equip amb el seu resultat.
    Demanarem el nom d’un equip en concret i posteriorment llistarem tots els partits i resultats que ha obtingut,
    mostrant la informació amb una estructura semblant a aquestes:
    */
    public List<Match> getMatchesOfTeam(String teamName) {
        List<Match> matches = new ArrayList<>();
        String sql = "SELECT p.partit_id, p.data_partit, p.matx, p.resultat, e.ciutat, e.nom AS nom_equip " +
                "FROM partits p " +
                "JOIN equips e ON p.equip_id = e.equip_id " +
                "WHERE e.nom = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, teamName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Match match = new Match(
                        rs.getString("partit_id"),
                        rs.getString("data_Partit"),
                        rs.getString("matx"),
                        rs.getString("resultat"),
                        rs.getString("ciutat"),
                        rs.getString("nom_equip")
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
        String sql = "INSERT INTO partits (partit_id, equip_id, data_partit, matx, resultat) VALUES (?, ?, ?, ?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, match.getPartit_id());
            pstmt.setInt(2, match.getEquip_id());
            pstmt.setDate(4, new java.sql.Date(match.getData_partit().getTime()));
            pstmt.setString(5, match.getMatx());
            pstmt.setString(6, match.getResultat());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Match match) {
        String sql = "UPDATE partits SET equip_id = ?, data_partit = ?, matx = ?, resultat = ?, WHERE partit_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(2, match.getEquip_id());
            pstmt.setDate(4, new java.sql.Date(match.getData_partit().getTime()));
            pstmt.setString(5, match.getMatx());
            pstmt.setString(6, match.getResultat());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Integer equip_id) {
        String sql = "DELETE FROM partits WHERE equip_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, equip_id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Match> getAllTMatchForTeam(int equip_id) {
        List<Match> matches = new ArrayList<>();
        String sql = "SELECT " +
                "  DISTINCT CONCAT(e1.nom, ' - ', e2.nom, ': ', e2.guanyades, ' - ', e2.perdudes) AS resultado " +
                "FROM " +
                "    partits p " +
                "JOIN " +
                "    equips e1 ON p.equip_id = e1.equip_id " +
                "JOIN " +
                "    equips e2 ON SUBSTRING(p.matx, LENGTH(p.matx) - 2, 3) = e2.acronim " +
                "WHERE " +
                "    e1.equip_id = ? ";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, equip_id);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Match match = new Match(rs.getString("resultado"));
                    matches.add(match);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }

}


