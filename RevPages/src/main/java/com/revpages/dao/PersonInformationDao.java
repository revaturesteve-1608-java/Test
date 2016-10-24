package com.revpages.dao;

import com.revpages.dto.Person;

/**
 * Access the database for person
 */
public interface PersonInformationDao {

	/**
	 * Get a person by id
	 * @param id The id of the person
	 * @return A person based on id
	 */
	public Person getPersonById(int id);
	
	/**
	 * Get a person by user name
	 * @param username The user name of the person
	 * @return A person based on user name
	 */
	public Person getPersonByUsername(String username);
	
	/**
	 * Get a person by email
	 * @param email The email of the person
	 * @return A person based on id
	 */
	public Person getPersonByEmail(String email);
	
	/**
	 * Create a new user in the database
	 * @param person The person to be put in database
	 * @return The person that had been created
	 */
	public Person createUser(Person person);
	
	/**
	 * Update the person's profile picture
	 * @param person The person to be updated
	 */
	public void updatePersonPic(Person person);
	
	/**
	 * Update the person when they are first login
	 * @param username The old user name of the person 
	 * @param pass The new password of the person 
	 * @param newUsername The new user name of the person 
	 */
	public void updateTempPerson(String username, String pass, String newUsername);
	
	/**
	 * Update the user information
	 * @param person The new person's information
	 */
	public void updateUserInfo(Person person);
}
