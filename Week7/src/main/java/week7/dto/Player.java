package week7.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Player")
public class Player {
	
	@Id
	@Column(name="p_id")
	@SequenceGenerator(name="playerSeq", sequenceName="player_Seq", allocationSize=1)
	@GeneratedValue(generator="playerSeq", strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Column(name="p_firstName")
	private String first_name;
	
	@Column(name="p_lastName")
	private String last_name;
	
	@Column
	private String position;
	
	@Column
	private String country;
	
	@ManyToOne
	@JoinColumn(name="t_id")
	private Team team;
	
	public Player() {
		
	}

	public Player(int id, String first_name, String last_name, String position, 
			String country, Team team) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.position = position;
		this.country = country;
		this.team = team;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", first_name=" + first_name + ", last_name=" 
				+ last_name + ", position=" + position
				+ ", country=" + country + ", team=" + team + "]";
	}

}
