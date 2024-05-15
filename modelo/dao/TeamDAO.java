package modelo.dao;

import modelo.DatabaseConnection;
import modelo.Match;
import modelo.Player;
import modelo.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO implements GenericDAO<Team, Integer> {


//  4. Inserir un nou jugador a un equip.


    /*
     //5. Traspassar un judador a un altra equip. TODO: Simplemente cambiar el id del equipo
Les seves dades personals aconseguides a la seva trajectòria no
haurien de variar malgrat canviï d'equip.
    */
    //pasandole el id por parametro hay que cambiar el equip_id con un update.
    public void updateTeamPlayer(Player player, int idEquipo) {
        String sql = "UPDATE jugadors SET id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idEquipo); // Establecemos el nuevo ID de equipo
            pstmt.setInt(2, player.getJugadorId()); // Filtro por el ID del jugador
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Team findById(Integer equip_id) {
        String sql = "SELECT * FROM equips WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, equip_id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Team(
                        rs.getInt("id"),
                        rs.getString("ciutat"),
                        rs.getString("nom"),
                        rs.getString("acronim"),
                        rs.getString("divisio"),
                        rs.getInt("guanyades"),
                        rs.getInt("perdudes")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Team> findAll() {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT * FROM equips";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Team team = new Team(
                        rs.getInt("equip_id"),
                        rs.getString("ciutat"),
                        rs.getString("nom"),
                        rs.getString("acronim"),
                        rs.getString("divisio"),
                        rs.getInt("guanyades"),
                        rs.getInt("perdueds")
                );
                teams.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    @Override
    public boolean insert(Team team) {
        String sql = "INSERT INTO equips (id, ciutat, nom, acronim, divisio, guanyades, perdudes) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, team.getId());
            pstmt.setString(2, team.getCiudad());
            pstmt.setString(3, team.getNombre());
            pstmt.setString(4, team.getAcronimo());
            pstmt.setString(5, team.getDivision());
            pstmt.setInt(6, team.getGuanyades());
            pstmt.setInt(7, team.getPerdudes());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Team team) {
        String sql = "UPDATE equips SET id = ?, ciutat = ?, nom = ?, acronim = ?, divisio = ?, guanyades = ?, perdudes = ? WHERE equip_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, team.getId());
            pstmt.setString(2, team.getCiudad());
            pstmt.setString(3, team.getNombre());
            pstmt.setString(4, team.getAcronimo());
            pstmt.setString(5, team.getDivision());
            pstmt.setInt(6, team.getGuanyades()); // Modifiqué setBoolean a setInt
            pstmt.setInt(7, team.getPerdudes());  // Modifiqué setBoolean a setInt
            pstmt.setInt(8, team.getId()); // Ajusté el índice de los parámetros
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean delete(Integer equipId) {
        String sql = "DELETE FROM equips WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, equipId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    //6. Actualitzar les dades de jugadors o equips després d'un partit.
    /*

    En funció de les dades rebudes (mitjançant API o fitxers), actualitzarem
    els partits de l'equip i les dades de cada jugador aconseguides durant el partit.
     */

    //9. Canviar nom franquícia d’un equip
}
