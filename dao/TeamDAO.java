package dao;

import modelo.DatabaseConnection;
import modelo.Match;
import modelo.Player;
import modelo.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {



//  4. Inserir un nou jugador a un equip.


    /*
     //5. Traspassar un judador a un altra equip. TODO: Simplemente cambiar el id del equipo
Les seves dades personals aconseguides a la seva trajectòria no
haurien de variar malgrat canviï d'equip.
    */
    //pandole el id por parametro hay que cambiar el equip_id con un update.
    public void updateTeamPlayer(Player player, int idEquipo) {
        String sql = "UPDATE jugadors SET equip_id = "+idEquipo+" WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idEquipo); // Establecemos el nuevo ID de equipo
            pstmt.setInt(2, player.getId()); // Filtro por el ID del jugador
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //6. Actualitzar les dades de jugadors o equips després d'un partit.
    /*

    En funció de les dades rebudes (mitjançant API o fitxers), actualitzarem
    els partits de l'equip i les dades de cada jugador aconseguides durant el partit.
     */

    //9. Canviar nom franquícia d’un equip
}
