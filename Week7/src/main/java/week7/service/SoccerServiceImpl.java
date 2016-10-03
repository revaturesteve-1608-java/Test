package week7.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import week7.dao.Dao;
import week7.dto.Player;
import week7.dto.Team;

@Service
public class SoccerServiceImpl implements SoccerService{
	
	@Autowired
	Dao dao;

	@Transactional
	@Override
	public void savePlayer(Player player) {
		dao.addPlayer(player);
	}

	@Transactional
	@Override
	public void saveTeam(Team team) {
		dao.addTeam(team);
	}

	@Transactional
	@Override
	public List<Player> getPlayer() {
		return dao.getPlayer();
	}

	@Transactional
	@Override
	public List<Team> getTeam() {
		return dao.getTeam();
	}
	

}
