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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import project3.dto.Person;
import project3.simpledao.LoginDao;

@RestController
@SessionAttributes("person")
public class RestControllerJ {
    
    @Autowired
    LoginDao dao;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Person handleTodo(@RequestBody Person person, ModelMap map) {
		
		Person persons = dao.loginUser(person.getUsername(), person.getPassword());
		
		map.addAttribute("person", persons);
		
		return persons;
	}
	
	
	@RequestMapping(value = "/user",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> getUser(@ModelAttribute("person") Person person){
		
		System.out.println(person.getUsername());
		
		System.out.println("------------------Here-----------------------------------");
		
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}
}
