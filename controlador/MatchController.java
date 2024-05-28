package controlador;

import modelo.Match;
import modelo.dao.MatchDAO;

import java.util.Date;
import java.util.List;

public class MatchController {
    private MatchDAO matchDAO;
    public MatchController(){
        this.matchDAO=new MatchDAO();
    }
    public List<Match> getAllTMatchForTeam(int equip_id){
        return matchDAO.getAllTMatchForTeam(equip_id);
    }
    public boolean updateMatch(int partit_id, int equip_id, Date data_partit, String matx, String resultat){
        Match match = new Match(partit_id,equip_id,data_partit,matx,resultat);
        return matchDAO.update(match);
    }

}
