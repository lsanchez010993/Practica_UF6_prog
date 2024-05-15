
package modelo.dao;

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
                        rs.getString("dataPartit"),
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
}


