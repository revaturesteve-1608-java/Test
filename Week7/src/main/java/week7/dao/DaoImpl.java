package week7.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import week7.dto.Player;
import week7.dto.Team;

@Repository
public class DaoImpl implements Dao{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void addPlayer(Player player) {
		sessionFactory.getCurrentSession().save(player);
	}

	@Override
	public void addTeam(Team team) {
		sessionFactory.getCurrentSession().save(team);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Player> getPlayer() {
		return sessionFactory.getCurrentSession().createCriteria(Player.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Team> getTeam() {
		return sessionFactory.getCurrentSession().createCriteria(Team.class).list();
	}

}
