package controlador;

import dao.PlayerDAO;
import modelo.Player;

import java.util.List;

public class PlayerController {
    private PlayerDAO playerDAO;

    public PlayerController() {
        this.playerDAO = new PlayerDAO();
    }

    public Player getPlayer(int id) {
        return playerDAO.getPlayerById(id);
    }

    public List<Player> getAllPlayers() {
        return playerDAO.getAllPlayers();
    }

    public void createPlayer(int id, String nombre, String apellido, java.util.Date fechaNacimiento, String altura, String peso, String posicion, int idEquipo) {
        Player player = new Player(id, nombre, apellido, fechaNacimiento, altura, peso, posicion, idEquipo);
        playerDAO.insertPlayer(player);
    }

    public void updatePlayer(int id, String nombre, String apellido, java.util.Date fechaNacimiento, String altura, String peso, String posicion, int idEquipo) {
        Player player = new Player(id, nombre, apellido, fechaNacimiento, altura, peso, posicion, idEquipo);
        playerDAO.updatePlayer(player);
    }

    public void deletePlayer(int id) {
        playerDAO.deletePlayer(id);
    }
}
