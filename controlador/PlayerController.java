package controlador;

import modelo.Player;
import modelo.PlayerHistoric;
import modelo.PlayerStats;
import modelo.PlayerStatsHistoric;
import modelo.dao.PlayerDAO;
import modelo.dao.PlayerStatsDAO;

import java.util.List;

public class PlayerController {
    private PlayerDAO playerDAO;
    private PlayerStatsDAO playerStatsDAO;
    private PlayerHistoricController playerHistoricController;
    private PlayerStatsHistoricController playerStatsHistoricController;

    public PlayerController() {
        this.playerDAO = new PlayerDAO();
        this.playerStatsDAO = new PlayerStatsDAO();
        this.playerHistoricController = new PlayerHistoricController();
        this.playerStatsHistoricController = new PlayerStatsHistoricController();
    }

    public Player getPlayer(int jugador_id) {
        return playerDAO.findById(jugador_id);
    }

    public List<Player> getAllPlayers() {
        return playerDAO.findAll();
    }

    public boolean createPlayer(int jugador_id, String nom, String cognom, java.util.Date dataNaixement, String alcada, String pes, String dorsal, String posicio, int equip_id) {
        Player player = new Player(jugador_id, nom, cognom, dataNaixement, alcada, pes, dorsal, posicio, equip_id);
        return playerDAO.insert(player);
    }

    public boolean updatePlayer(int jugador_id, String nom, String cognom, java.util.Date dataNaixement, String alcada, String pes, String dorsal, String posicio, int equip_id) {
        Player player = new Player(jugador_id, nom, cognom, dataNaixement, alcada, pes, dorsal, posicio, equip_id);
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

    public boolean existPlayerName(String namePlayer) {
        List<String> playerNames = playerDAO.findPlayersNameString();
        return playerNames.contains(namePlayer);
    }

    public boolean changeNameTeamOfPlayer(int jugador_id, int teamId) {

        return playerDAO.updateTeamPlayerForName(jugador_id, teamId);

    }

    public boolean retirarJugador(int jugador_id) {
        Player player = playerDAO.findById(jugador_id);
        if (player == null) {
            return false;
        }

        PlayerHistoric playerHistoric = new PlayerHistoric(
                player.getJugador_id(),
                player.getNom(),
                player.getCognom(),
                player.getDataNaixement(),
                player.getAlcada(),
                player.getPes(),
                player.getDorsal(),
                player.getPosicio(),
                player.getEquip_id()
        );

        if (!playerHistoricController.insertPlayerHistoric(playerHistoric)) {
            return false;
        }

        List<PlayerStats> statsList = playerStatsDAO.getPlayerStatsByPlayerId(jugador_id);
        for (PlayerStats stats : statsList) {
            PlayerStatsHistoric playerStatsHistoric = new PlayerStatsHistoric(
                    stats.getJugador_id(),
                    stats.getPartit_id(),
                    player.getNom(),
                    player.getCognom(),
                    stats.getMinutsJugats(),
                    stats.getPunts(),
                    stats.getTirsAnotats(),
                    stats.getTirsTirats(),
                    stats.getTirsTriplesAnotats(),
                    stats.getTirsTriplesTirats(),
                    stats.getTirsLliuresAnotats(),
                    stats.getTirsLliuresTirats(),
                    stats.getRebotsOfensius(),
                    stats.getRebotsDefensius(),
                    stats.getAssistencies(),
                    stats.getRobades(),
                    stats.getBloqueigs()
            );

            if (!playerStatsHistoricController.insertPlayerStatsHistoric(playerStatsHistoric)) {
                return false;
            }
        }

        for (PlayerStats stats : statsList) {
            if (!playerStatsDAO.deletePlayerStats(stats.getJugador_id(), stats.getPartit_id())) {
                return false;
            }
        }

        return playerDAO.delete(jugador_id);
    }
    public List<Player> getPlayersByFullName(String nombre, String apellido) {
        return playerDAO.findPlayersByFullName(nombre, apellido);
    }

}