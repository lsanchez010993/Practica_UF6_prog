package controlador;

import modelo.Match;
import modelo.dao.MatchDAO;

import java.util.List;

public class MatchController {
    private MatchDAO matchDAO;
    public MatchController(){
        this.matchDAO=new MatchDAO();
    }
    public List<Match> getAllTMatchForTeam(int equip_id){ return matchDAO.findAll();}


}
