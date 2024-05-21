package controlador;

import modelo.PlayerHistoric;
import modelo.dao.PlayerHistoricDAO;

public class PlayerHistoricController {
    private PlayerHistoricDAO playerHistoricDAO;

    public PlayerHistoricController() {
        this.playerHistoricDAO = new PlayerHistoricDAO();
    }

    public boolean insertPlayerHistoric(PlayerHistoric playerHistoric) {
        return playerHistoricDAO.insert(playerHistoric);
    }
}
