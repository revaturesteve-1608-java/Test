package project3.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import project3.dto.Person;
import project3.dto.Role;

public interface ServiceInterface {
	
	public String createUser(Person person);
	
	public Person loginUser(String username, String password);
	
	public List<Role> getRoles();
	
	public Person updateProfilePic(Person person, MultipartFile picture);

}
