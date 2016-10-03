package week7.service;

import java.util.List;

import week7.dto.Player;
import week7.dto.Team;

public interface SoccerService {
	
	public void savePlayer(Player player);
	
	public void saveTeam(Team team);
	
	public List<Player> getPlayer();
	
	public List<Team> getTeam();

}
