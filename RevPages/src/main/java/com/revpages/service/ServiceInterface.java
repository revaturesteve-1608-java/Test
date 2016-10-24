package com.revpages.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.revpages.dto.Complex;
import com.revpages.dto.Person;
import com.revpages.dto.Role;

/**
 * A Service method for person information
 */
public interface ServiceInterface {
	
	/**
	 * Creating a new person
	 * @param person A person to be created
	 * @return
	 */
	public String createUser(Person person);
	
	public List<Role> getRoles();
	
	public Person updateProfilePic(Person person, MultipartFile picture);
	
	public Person getPersonByUsername(String username);
	
	public List<Complex> getComplex();
	
	public Person loginUser(String username, String password);

}
