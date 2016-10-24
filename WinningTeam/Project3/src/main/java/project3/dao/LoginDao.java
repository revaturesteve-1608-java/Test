package project3.dao;

import project3.dto.Person;

/**
 * Access the database for login a User
 */
@FunctionalInterface
public interface LoginDao {
	
	/**
	 * Getting information about the user base on the user name and password.
	 * @param username The user name of the person
	 * @param password The password of the person
	 * @return The person's information
	 */
	public Person loginUser(String username, String password);

}
