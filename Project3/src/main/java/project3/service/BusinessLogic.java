package project3.service;

import project3.dto.Person;

public interface BusinessLogic {
	
	public Person getPersonByUsername(String username);
	
	public void updateTempPerson(String username, String pass, String newUsername);
}
