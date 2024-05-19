package controlador;

import modelo.Player;
import modelo.dao.PlayerDAO;

import java.util.List;

public class PlayerController {
    private PlayerDAO playerDAO;

    public PlayerController() {
        this.playerDAO = new PlayerDAO();
    }

    public Player getPlayer(int jugador_id) {
        return playerDAO.findById(jugador_id);
    }

    public List<Player> getAllPlayers() {
        return playerDAO.findAll();
    }

    public boolean createPlayer(int jugador_id, String nom, String cognom, java.util.Date dataNaixement, String alcada, String pes, String posicio, int equip_id) {
        Player player = new Player(jugador_id, nom, cognom, dataNaixement, alcada, pes, posicio, equip_id);
        return playerDAO.insert(player);
    }

    public boolean updatePlayer(int jugador_id, String nom, String cognom, java.util.Date dataNaixement, String alcada, String pes, String posicio, int equip_id) {
        Player player = new Player(jugador_id, nom, cognom, dataNaixement, alcada, pes, posicio, equip_id);
        return playerDAO.update(player);
    }

    public boolean deletePlayer(int jugador_id) {
        return playerDAO.delete(jugador_id);
    }

    public List<Player> getPlayersByTeamName(String teamName) {
        return playerDAO.findPlayersByTeamName(teamName);
    }

    public List<Player> getPlayersByTeam(int equip_id) {
        return playerDAO.findByTeamId(equip_id);
    }

    public List<Player> getPlayersByName(String name) {
        return playerDAO.findPlayersByName(name);
    }
    public boolean existPlayerName (String namePlayer){
        List<String> playerNames = playerDAO.findPlayersNameString();
        if (playerNames.contains(namePlayer)) {
            return true;
        }
        return false;
    }

        public boolean changeNameTeamOfPlayer(String playerName, int teamId) {
            // Listamos los nombres de los jugadores y comprobamos que el jugador existe
            List<String> playerNames = playerDAO.findPlayersNameString();
            if (playerNames.contains(playerName)) {
                // Utilizamos la siguiente función para actualizar
                return playerDAO.updateTeamPlayerForName(playerName, teamId);
            } else {
                return false; // El jugador no existe
            }
        }
    }



