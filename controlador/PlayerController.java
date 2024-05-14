package controlador;

import modelo.dao.PlayerDAO;
import modelo.Player;

import java.util.List;

public class PlayerController {
    private PlayerDAO playerDAO;

    public PlayerController() {
        this.playerDAO = new PlayerDAO();
    }

    public Player getPlayer(int jugadorId) {
        return playerDAO.getPlayerById(jugadorId);
    }

    public List<Player> getAllPlayers() {
        return playerDAO.getAllPlayers();
    }

    public void createPlayer(int jugadorId, String nom, String cognom, java.util.Date dataNaixement, String alcada, String pes, String posicio, int equipId) {
        Player player = new Player(jugadorId, nom, cognom, dataNaixement, alcada, pes, posicio, equipId);
        playerDAO.insertPlayer(player);
    }

    public void updatePlayer(int jugadorId, String nom, String cognom, java.util.Date dataNaixement, String alcada, String pes, String posicio, int equipId) {
        Player player = new Player(jugadorId, nom, cognom, dataNaixement, alcada, pes, posicio, equipId);
        playerDAO.updatePlayer(player);
    }

    public void deletePlayer(int jugadorId) {
        playerDAO.deletePlayer(jugadorId);
    }

    public List<Player> getPlayersByTeam(int equipId) {
        return playerDAO.getPlayersByTeam(equipId);
    }
}
