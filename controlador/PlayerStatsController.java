package controlador;

import dao.PlayerStatsDAO;
import modelo.PlayerStats;

import java.util.List;

public class PlayerStatsController {
    private PlayerStatsDAO playerStatsDAO;

    public PlayerStatsController() {
        this.playerStatsDAO = new PlayerStatsDAO();
    }

    public PlayerStats getPlayerStats(int jugadorId, int partitId) {
        return playerStatsDAO.getPlayerStats(jugadorId, partitId);
    }

    public List<PlayerStats> getAllPlayerStats() {
        return playerStatsDAO.getAllPlayerStats();
    }

    public void createPlayerStats(int jugadorId, int partitId, double minutsJugats, int punts, int tirsAnotats, int tirsTirats, int tirsTriplesAnotats, int tirsTriplesTirats, int tirsLliuresAnotats, int tirsLliuresTirats, int rebotsOfensius, int rebotsDefensius, int assistencies, int robades, int bloqueigs) {
        PlayerStats stats = new PlayerStats(jugadorId, partitId, minutsJugats, punts, tirsAnotats, tirsTirats, tirsTriplesAnotats, tirsTriplesTirats, tirsLliuresAnotats, tirsLliuresTirats, rebotsOfensius, rebotsDefensius, assistencies, robades, bloqueigs);
        playerStatsDAO.insertPlayerStats(stats);
    }

    public void updatePlayerStats(int jugadorId, int partitId, double minutsJugats, int punts, int tirsAnotats, int tirsTirats, int tirsTriplesAnotats, int tirsTriplesTirats, int tirsLliuresAnotats, int tirsLliuresTirats, int rebotsOfensius, int rebotsDefensius, int assistencies, int robades, int bloqueigs) {
        PlayerStats stats = new PlayerStats(jugadorId, partitId, minutsJugats, punts, tirsAnotats, tirsTirats, tirsTriplesAnotats, tirsTriplesTirats, tirsLliuresAnotats, tirsLliuresTirats, rebotsOfensius, rebotsDefensius, assistencies, robades, bloqueigs);
        playerStatsDAO.updatePlayerStats(stats);
    }

    public void deletePlayerStats(int jugadorId, int partitId) {
        playerStatsDAO.deletePlayerStats(jugadorId, partitId);
    }
}
