package dao;

import modelo.DatabaseConnection;
import modelo.Match;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatchDAO {
    /*
    3.- Llistar tots els partits jugats per un equip amb el seu resultat.
Demanarem el nom d’un equip en concret i posteriorment llistarem tots els partits i resultats que ha obtingut,
mostrant la informació amb una estructura semblant a aquestes:
     */
    public List<Match> getMatchesOfTeam(String teamName) {
        List<Match> matches = new ArrayList<>();
        String sql = "SELECT * FROM partits WHERE equip_local = ? UNION SELECT * FROM partits WHERE equip_visitant = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, teamName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Match match = new Match(
                        rs.getString("id"),
                        rs.getString("equip_local"),
                        rs.getString("equip_visitant"),
                        rs.getInt("resultat_local"),
                        rs.getInt("resultat_visitant")
                );
                matches.add(match);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }
}
