package project3.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import project3.dao.LoginDao;
import project3.dao.impl.SimpleDaoImpl;
import project3.dto.Person;
import project3.service.ServiceInterface;

@RestController
@SessionAttributes("person")
public class LoginController {
	
	@Autowired 	
	ServiceInterface service; 

	@RequestMapping(value = "/login", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Person  checkLogin(@RequestBody Person person, ModelMap map) {
		Person persons = service.loginUser(person.getUsername(), person.getPassword());
		if(persons == null) {
			persons = new Person();
		}
		map.addAttribute("person", persons);
//		System.out.println(persons.getUsername());
		return persons;
	}
	
	
	@RequestMapping(value = "/user",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> getUser(@ModelAttribute("person") Person person, ModelMap map){
		
		System.out.println("------------------Here-----------------------------------");
		
		if(person == null) {
			person = new Person();
		} else {
			person = service.getPersonByUsername(person.getUsername());
			if(person != null) {
				map.addAttribute("person", person);
			} else {
				person = new Person();
			}
//			System.out.println(person.getUsername());
//			System.out.println(person.isVaildated());
//			map.addAttribute("person", person);
		}
		
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}
	
	@RequestMapping(value="/logout")
	@ResponseBody
	public Person logout(WebRequest request, HttpSession session, SessionStatus status, ModelMap map){
		
		System.out.println("in logout!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		status.setComplete();
		Person person = new Person();
		map.addAttribute("user", person);
        session.removeAttribute("user");

		map.addAttribute("person", person);
		return person;
	}
	
	@ExceptionHandler(HttpSessionRequiredException.class)
	public ResponseEntity<Person> handleException() {

		Person person = new Person();

		return new ResponseEntity<Person>(person, HttpStatus.OK);

	}

}
