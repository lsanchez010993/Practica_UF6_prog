package controlador;

import modelo.Player;
import modelo.dao.PlayerDAO;

import java.util.List;

public class PlayerController {
    private PlayerDAO playerDAO;

    public PlayerController() {
        this.playerDAO = new PlayerDAO();
    }

    public Player getPlayer(int jugadorId) {
        return playerDAO.findById(jugadorId);
    }

    public List<Player> getAllPlayers() {
        return playerDAO.findAll();
    }

    public boolean createPlayer(int jugadorId, String nom, String cognom, java.util.Date dataNaixement, String alcada, String pes, String posicio, int equipId) {
        Player player = new Player(jugadorId, nom, cognom, dataNaixement, alcada, pes, posicio, equipId);
        return playerDAO.insert(player);
    }

    public boolean updatePlayer(int jugadorId, String nom, String cognom, java.util.Date dataNaixement, String alcada, String pes, String posicio, int equipId) {
        Player player = new Player(jugadorId, nom, cognom, dataNaixement, alcada, pes, posicio, equipId);
        return playerDAO.update(player);
    }

    public boolean deletePlayer(int jugadorId) {
        return playerDAO.delete(jugadorId);
    }

    public List<Player> getPlayersByTeamName(String teamName) {
        return playerDAO.findPlayersByTeamName(teamName);
    }

    public List<Player> getPlayersByTeam(int equipId) {
        return playerDAO.findByTeamId(equipId);
    }

    public List<Player> getPlayersByName(String name) {
        return playerDAO.findPlayersByName(name);
    }
}
