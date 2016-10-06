package project3.simpledao;

import project3.dto.Person;

public interface LoginDao {
	
	public Person loginUser(String username, String password);

}
