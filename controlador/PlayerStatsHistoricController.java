package controlador;

import modelo.PlayerStatsHistoric;
import modelo.dao.PlayerStatsHistoricDAO;

public class PlayerStatsHistoricController {
    private PlayerStatsHistoricDAO playerStatsHistoricDAO;

    public PlayerStatsHistoricController() {
        this.playerStatsHistoricDAO = new PlayerStatsHistoricDAO();
    }

    public boolean insertPlayerStatsHistoric(PlayerStatsHistoric playerStatsHistoric) {
        return playerStatsHistoricDAO.insert(playerStatsHistoric);
    }
}
