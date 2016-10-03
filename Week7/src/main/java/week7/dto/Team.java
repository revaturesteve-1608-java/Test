package week7.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Team")
public class Team {
	
	@Id
	@Column(name="t_id")
	@SequenceGenerator(name="teamSeq", sequenceName="team_seq", allocationSize=1)
	@GeneratedValue(generator="teamSeq",strategy=GenerationType.SEQUENCE)
	private int Id;
	
	@Column
	private String name;
	
	@Column
	private String stadium;
	
	@OneToMany(mappedBy="team", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Player> players;
	
	public Team() {
		
	}

	public Team(int id, String name, String stadium, List<Player> players) {
		super();
		Id = id;
		this.name = name;
		this.stadium = stadium;
		this.players = players;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		return "Team [Id=" + Id + ", name=" + name + ", stadium=" + stadium 
				+ ", players=" + players + "]";
	}
	

}
