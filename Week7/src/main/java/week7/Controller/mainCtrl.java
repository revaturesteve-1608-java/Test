package week7.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import week7.dto.Player;
import week7.dto.Team;
import week7.service.SoccerService;

@RestController
public class mainCtrl {
	
	@Autowired
	SoccerService soccerService;

	@RequestMapping(value="/saveTeam", method=RequestMethod.POST, 
			consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Team> saveTeam(@RequestBody Team team) {
		System.out.println("save post " + team); 
		soccerService.saveTeam(team);
		return new ResponseEntity<Team>(team, HttpStatus.OK);
	}
	
	@RequestMapping(value="/savePlayer", method=RequestMethod.POST, 
			consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Player> savePlayer(@RequestBody Player player) {
		System.out.println("save post " + player); 
		soccerService.savePlayer(player);
		return new ResponseEntity<Player>(player, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getTeam", method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Team>> getTeam() {
		List<Team> teams = soccerService.getTeam();
		for(Team team : teams) {
			team.setPlayers(null);
		}
		return new ResponseEntity<List<Team>>(teams, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getPlayer", method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Player>> getPlayer() {
		List<Player> players = soccerService.getPlayer();
		for(Player player : players) {
			player.setTeam(null);
		}
		return new ResponseEntity<List<Player>>(players, HttpStatus.OK);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Exception> handleException(Exception e) {
		System.out.println("lol spring...wow catches exceptions");
		e.printStackTrace();
		return new ResponseEntity<Exception>(e, HttpStatus.CONFLICT);
	}
}
