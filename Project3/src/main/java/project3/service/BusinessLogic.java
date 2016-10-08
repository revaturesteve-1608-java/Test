package project3.service;

import project3.dto.Person;

public interface BusinessLogic {
	
	public Person getPersonByUsername(String username);
	
	public void updateTempPerson(String username, String pass, String newUsername);
	
	public void updateUserInfo(String currentUser, String newPassword, String username, String newEmail, 
			String newPhone, String newUniversity, String newLinkedIn);
}
