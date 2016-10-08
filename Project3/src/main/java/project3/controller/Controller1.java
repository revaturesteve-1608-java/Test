package project3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import project3.dto.Person;
import project3.service.BusinessLogic;

@RestController
//@SessionAttributes("user")
public class Controller1 {
	
	@Autowired
	BusinessLogic service;
	
	@RequestMapping(value="/updateTemp", method=RequestMethod.POST)
	public void updateTempUser(@RequestBody String[] usernamePass){
//		System.out.println("HEY IT GOT INTO CONTROLLER");
		String testUsername = usernamePass[3];
		System.out.println("inside controller oldPass: " + usernamePass[0]);
		System.out.println("inside controller newPass: " + usernamePass[1]);
//		System.out.println("inside controller firstname: " + person.getFirst_name());
//		System.out.println("inside controller lastname: " + person.getLast_name());
//		System.out.println("inside controller email: " + person.getEmail());
		
		service.updateTempPerson(testUsername, usernamePass[1], usernamePass[2]);
		
//		Person person = service.getPersonByUsername(username);
//		service.updateTempPerson(person);
	}
	
	@RequestMapping(value="/updateInfo", method=RequestMethod.POST)
	public void updateUserInfo(@RequestBody String[] information){
//		System.out.println("HEY IT GOT INTO CONTROLLER");
//		String testUsername = person.getUsername();
		System.out.println("inside controller oldPass: " + information[0]);
		System.out.println("inside controller newPass: " + information[1]);
		System.out.println("inside controller newPass: " + information[2]);
		System.out.println("inside controller newPass: " + information[3]);
		System.out.println("inside controller newPass: " + information[4]);
		System.out.println("inside controller newPass: " + information[5]);
		System.out.println("inside controller newPass: " + information[6]);
		// information[7] is the current user
		Person currentUser = service.getPersonByUsername("Joey");
		String newPassword = information[1];
		String newUsername = information[2];
		String newEmail = information[3];
		String newPhone = information[4];
		String newUniversity = information[5];
		String newLinkedIn = information[6];
		
		
		if(information[1]==null || "".equals(information[1])) {
			newPassword = currentUser.getPassword();
		}
		if(information[2]==null || "".equals(information[1])){
			newUsername = currentUser.getUsername();
		}
		if(information[3]==null || "".equals(information[1])) {
			newEmail = currentUser.getEmail();
		}
		if(information[4]==null || "".equals(information[1])){
			newPhone = currentUser.getPhoneNumber();
		}
		if(information[5]==null || "".equals(information[1])){
			newUniversity = currentUser.getUnviersity();
		}
		if(information[6]==null || "".equals(information[1])){
			newLinkedIn = currentUser.getLinkedin();
		}
		
		System.out.println("current user: " + currentUser);
		System.out.println("email: " + newEmail + "\tsize: " + newEmail.length());
		
		if(information[0] == null)
			System.out.println("CAN CHECK FOR NULLS");
//		System.out.println("inside controller firstname: " + person.getFirst_name());
//		System.out.println("inside controller lastname: " + person.getLast_name());
//		System.out.println("inside controller email: " + person.getEmail());
		
//		service.updateTempPerson(testUsername, usernamePass[1], usernamePass[2]);
		
//		Person person = service.getPersonByUsername(username);
		service.updateUserInfo(currentUser.getUsername(), newPassword, newUsername, newEmail, newPhone, newUniversity, newLinkedIn);
		System.out.println("REALLY SHOULD NOT GET HERE");
	}
}
