package project3.service;

import java.util.List;

import project3.dto.Person;
import project3.dto.Role;

public interface ServiceInterface {
	
	public void createUser(Person person);
	
	public List<Role> getRoles();

}
