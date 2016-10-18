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
import org.springframework.web.multipart.MultipartFile;

import project3.dto.Complex;
import project3.dto.Person;
import project3.service.BusinessLogic;
import project3.service.ServiceInterface;

@RestController
@SessionAttributes("person")
public class Controller1 {
	
	@Autowired
	BusinessLogic logic;
	
	@Autowired
	ServiceInterface service;
	
	@RequestMapping(value="/updateTemp", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> updateTempUser(@RequestBody String[] usernamePass, ModelMap map){
//		System.out.println("HEY IT GOT INTO CONTROLLER");
		String testUsername = usernamePass[3];
		System.out.println("inside controller oldPass: " + usernamePass[0]);
		System.out.println("inside controller newPass: " + usernamePass[1]);
		System.out.println("inside controller oldPass: " + usernamePass[2]);
		System.out.println("inside controller username: " + usernamePass[3]);
//		System.out.println("inside controller firstname: " + person.getFirst_name());
//		System.out.println("inside controller lastname: " + person.getLast_name());
//		System.out.println("inside controller email: " + person.getEmail());
		
		String updatedStatus = logic.updateTempPerson(testUsername, usernamePass[1], usernamePass[0], usernamePass[2]);
		if(updatedStatus.equals("Updated")){
			Person updatedPerson = logic.getPersonByUsername(usernamePass[2]);
			map.addAttribute("person", updatedPerson);
		}
		System.out.println(updatedStatus);
		return new ResponseEntity<String>(updatedStatus, HttpStatus.OK);
		
		
//		Person person = service.getPersonByUsername(username);
//		service.updateTempPerson(person);
	}
	
	@RequestMapping(value="/updateInfo", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> updateUserInfo(@RequestBody String[] information, 
			@ModelAttribute("person") Person person, ModelMap map){
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
//		Person currentUser = service.getPersonByUsername("Joey");
		String oldPassword = information[0];
		String newPassword = information[1];
		String newUsername = information[2];
		String newEmail = information[3];
		String newPhone = information[4];
		String newUniversity = information[5];
		String newLinkedIn = information[6];
		String complex = information[7];
		
		String updatedStatus = logic.updateUserInfo(person, oldPassword, newPassword, newUsername, newEmail, 
				newPhone, complex, newUniversity, newLinkedIn);
		
		if(updatedStatus.equals("Updated")){
			Person updatedPerson = logic.getPersonById(person.getId());
			map.addAttribute("person", updatedPerson);
		}
		
//		System.out.println("current user: " + currentUser);
//		System.out.println("email: " + newEmail + "\tsize: " + newEmail.length());
		
		if(information[0] == null)
			System.out.println("CAN CHECK FOR NULLS");
//		System.out.println("inside controller firstname: " + person.getFirst_name());
//		System.out.println("inside controller lastname: " + person.getLast_name());
//		System.out.println("inside controller email: " + person.getEmail());
		
//		service.updateTempPerson(testUsername, usernamePass[1], usernamePass[2]);
		
//		Person person = service.getPersonByUsername(username);
		
		System.out.println("REALLY SHOULD NOT GET HERE");
		return new ResponseEntity<String>(updatedStatus, HttpStatus.OK);
	}
	
	@RequestMapping(value="/fileupload", method=RequestMethod.POST,
			consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	public void updateProfilePic(@RequestParam(required = false, value = "file") MultipartFile picture,
			@ModelAttribute("person") Person person, ModelMap map){
		System.out.println("HEY IT GOT INTO CONTROLLER");
		System.out.println(picture);
//		map.addAttribute("person", service.updateProfilePic(person, picture));
		System.out.println("HEY IT GOT INTO CONTROLLER");
	}
}
