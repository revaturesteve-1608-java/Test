package project3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import project3.dto.Person;
import project3.simpledao.LoginDao;

@RestController
@SessionAttributes("user")
public class RestControllerJ {
	

	@Autowired
	LoginDao dao;

	@RequestMapping(value = { "/login"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> handleTodo(@RequestBody Person person, ModelMap map) {
		
		System.out.println(person.getUsername() + "          " + person.getPassword());
		
		System.out.println("Here");
		
		Person persons = dao.loginUser(person.getUsername(), person.getPassword());
		
		map.addAttribute("user", person);
		
		System.out.println(persons);
		
		return new ResponseEntity<Person>(persons, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/user",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> getUser(@ModelAttribute("user") Person person){
		
		System.out.println(person.getUsername());
		
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}
}
