package com.revpages.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import com.revpages.dto.Person;
import com.revpages.service.ServiceInterface;

@RestController
//@SessionAttributes("person")
public class LoginController {
	
	@Autowired 	
	ServiceInterface service; 
	
	/**
	 * URL mapping for logging in
	 * @param person - the user that is logging in
	 * @param map
	 * @return returns the user that is logging in
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> checkLogin(@RequestBody Person person, HttpServletRequest req) {
		Person persons = service.loginUser(person.getUsername(), person.getPassword());
		if(persons == null) {
			persons = new Person();
		}
		HttpSession session = req.getSession();
		session.setAttribute("person", persons);
		return new ResponseEntity<Person>(persons, HttpStatus.OK);
	}
	
	/**
	 * URL to the user of the logged in user
	 * @param person - takes in the user that is aqcuired from the session
	 * @param map
	 * @return response entity of the logged in user
	 */
	@RequestMapping(value = "/user",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> getUser(ModelMap map, HttpServletRequest req){
		Person person = (Person) req.getSession().getAttribute("person");
		if(person == null) {
			person = new Person();
		} else {
			person = service.getPersonByUsername(person.getUsername());
			if(person != null) {
				req.getSession().setAttribute("person", person);
			} else {
				person = new Person();
			}
		}
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}
	
	/**
	 * URL mapping for logging out
	 * @param request
	 * @param session
	 * @param status
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/logout")
	@ResponseBody
	public Person logout(WebRequest request, SessionStatus status, HttpServletRequest req){
		status.setComplete();
		Person person = new Person();
		req.getSession().setAttribute("person", person);
		return person;
	}
	
	@ExceptionHandler(HttpSessionRequiredException.class)
	public ResponseEntity<Person> handleException() {

		Person person = new Person();
		return new ResponseEntity<Person>(person, HttpStatus.OK);

	}

}
