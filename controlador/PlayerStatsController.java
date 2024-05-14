package controlador;

import modelo.PlayerStats;
import modelo.dao.PlayerStatsDAO;

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
        return playerStatsDAO.findAll();
    }

    public boolean createPlayerStats(int jugadorId, int partitId, double minutsJugats, int punts, int tirsAnotats, int tirsTirats, int tirsTriplesAnotats, int tirsTriplesTirats, int tirsLliuresAnotats, int tirsLliuresTirats, int rebotsOfensius, int rebotsDefensius, int assistencies, int robades, int bloqueigs) {
        PlayerStats stats = new PlayerStats(jugadorId, partitId, minutsJugats, punts, tirsAnotats, tirsTirats, tirsTriplesAnotats, tirsTriplesTirats, tirsLliuresAnotats, tirsLliuresTirats, rebotsOfensius, rebotsDefensius, assistencies, robades, bloqueigs);
        return playerStatsDAO.insert(stats);
    }

    public boolean updatePlayerStats(int jugadorId, int partitId, double minutsJugats, int punts, int tirsAnotats, int tirsTirats, int tirsTriplesAnotats, int tirsTriplesTirats, int tirsLliuresAnotats, int tirsLliuresTirats, int rebotsOfensius, int rebotsDefensius, int assistencies, int robades, int bloqueigs) {
        PlayerStats stats = new PlayerStats(jugadorId, partitId, minutsJugats, punts, tirsAnotats, tirsTirats, tirsTriplesAnotats, tirsTriplesTirats, tirsLliuresAnotats, tirsLliuresTirats, rebotsOfensius, rebotsDefensius, assistencies, robades, bloqueigs);
        return playerStatsDAO.update(stats);
    }

    public boolean deletePlayerStats(int jugadorId, int partitId) {
        return playerStatsDAO.deletePlayerStats(jugadorId, partitId);
    }
}
