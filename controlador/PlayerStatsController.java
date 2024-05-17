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

    public List<PlayerStats> getPlayerStatsByPlayerId(int jugadorId) {
        return playerStatsDAO.getPlayerStatsByPlayerId(jugadorId);
    }

    public double[] calcularMediasJugador(int jugadorId) {
        List<PlayerStats> stats = getPlayerStatsByPlayerId(jugadorId);
        if (stats.isEmpty()) return null;

        double totalMinutsJugats = 0, totalPunts = 0, totalTirsAnotats = 0, totalTirsTirats = 0;
        double totalTirsTriplesAnotats = 0, totalTirsTriplesTirats = 0, totalTirsLliuresAnotats = 0, totalTirsLliuresTirats = 0;
        double totalRebotsOfensius = 0, totalRebotsDefensius = 0, totalAssistencies = 0, totalRobades = 0, totalBloqueigs = 0;

        // Calcular la suma total de cada estadística
        for (PlayerStats stat : stats) {
            totalMinutsJugats += stat.getMinutsJugats();
            totalPunts += stat.getPunts();
            totalTirsAnotats += stat.getTirsAnotats();
            totalTirsTirats += stat.getTirsTirats();
            totalTirsTriplesAnotats += stat.getTirsTriplesAnotats();
            totalTirsTriplesTirats += stat.getTirsTriplesTirats();
            totalTirsLliuresAnotats += stat.getTirsLliuresAnotats();
            totalTirsLliuresTirats += stat.getTirsLliuresTirats();
            totalRebotsOfensius += stat.getRebotsOfensius();
            totalRebotsDefensius += stat.getRebotsDefensius();
            totalAssistencies += stat.getAssistencies();
            totalRobades += stat.getRobades();
            totalBloqueigs += stat.getBloqueigs();
        }

        int numPartits = stats.size();
        //Usamos size para obtener el número de partidos jugados por el jugador
        return new double[]{
                totalMinutsJugats / numPartits,
                totalPunts / numPartits,
                totalTirsAnotats / numPartits,
                totalTirsTirats / numPartits,
                totalTirsTriplesAnotats / numPartits,
                totalTirsTriplesTirats / numPartits,
                totalTirsLliuresAnotats / numPartits,
                totalTirsLliuresTirats / numPartits,
                totalRebotsOfensius / numPartits,
                totalRebotsDefensius / numPartits,
                totalAssistencies / numPartits,
                totalRobades / numPartits,
                totalBloqueigs / numPartits
        };
    }
}
