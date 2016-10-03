package week7.dao;

import java.util.List;

import week7.dto.Player;
import week7.dto.Team;

public interface Dao {
	
	public void addPlayer(Player player);
	
	public void addTeam(Team team);
	
	public List<Player> getPlayer();
	
	public List<Team> getTeam();

}
