package project3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project3.dto.Person;
import project3.service.BusinessLogic;

@RestController
public class ProfileController {
	
	@Autowired 	
	BusinessLogic service; 
	
	@RequestMapping(value="/getPerson", method=RequestMethod.POST, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> getUser() {
		Person person = service.getPersonByUsername("zhouzhaoxu@yahoo.com");
		System.out.println(person.getUsername());
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

}
