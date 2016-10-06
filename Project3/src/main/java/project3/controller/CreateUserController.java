package project3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project3.dto.Person;
import project3.service.ServiceInterface;

@RestController
public class CreateUserController {

	@Autowired 	
	ServiceInterface service; 
	
	@RequestMapping(value="/createUser", method=RequestMethod.POST, 
			consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> createUser(@RequestBody Person person) {
		System.out.println("save post " + person); 
		service.createUser(person);
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}
}
