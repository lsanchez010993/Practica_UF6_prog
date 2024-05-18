package controlador;

import modelo.Player;
import modelo.Team;
import modelo.dao.TeamDAO;

import java.util.List;

public class TeamController {
    private TeamDAO teamDAO;
    public TeamController(){
        this.teamDAO = new TeamDAO();
    }
    public Team getTeam(int equip_id){
        return teamDAO.findById(equip_id);
    }
    public List<Team> getAllTeams(){ return teamDAO.findAll();}

    public boolean updateTeam(int equip_id, String nom, String ciutat, String acronim, String divisio, int guanyades, int perdudes) {
        Team team = new Team(equip_id, nom, ciutat, acronim, divisio, guanyades, perdudes);
        return teamDAO.update(team);
    }




    public boolean changeNameTeam(String nameActual, String nameNuevo) {
        // listamos los equipos y comprobamos que el equipo exista:
        List<String> teamNames = teamDAO.getAllTeams();
        if (teamNames.contains(nameActual)) {
            // Utilizamos la siguiente función para actualizar
            teamDAO.updateTeamNameDAO(nameActual, nameNuevo);
            return true; // Actualización exitosa
        } else {
            return false; // El equipo no existe
        }
    }

}
