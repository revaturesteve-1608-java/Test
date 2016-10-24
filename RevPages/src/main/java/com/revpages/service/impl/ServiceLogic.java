package com.revpages.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.revpages.dao.LoginDao;
import com.revpages.dao.PersonInformationDao;
import com.revpages.dao.SimpleDao;
import com.revpages.dto.AwsKey;
import com.revpages.dto.Complex;
import com.revpages.dto.Person;
import com.revpages.dto.Role;
import com.revpages.service.Crypt;
import com.revpages.service.Jets3;
import com.revpages.service.ServiceInterface;
import com.revpages.util.Email;

/**
 * The logic behind user information
 */
@Service
public class ServiceLogic implements ServiceInterface{
	
	/**
	 * Used for encrypting the password
	 */
	@Autowired
	Crypt crypt;
	
	/**
	 * Used for uploading a picture
	 */
	@Autowired
	Jets3 jetS3;
	
	/**
	 * Using to access the database
	 */
	@Autowired // mapped to the bean
	SimpleDao dao;
	
	/**
	 * Using to login User
	 */
	@Autowired
	LoginDao loginDao;
	
	/**
	 * Using to updated user information
	 */
	@Autowired
	PersonInformationDao PersonDao;
	
	/**
	 * Get a random password
	 * @param length The length of the password
	 * @return A random password
	 */
	private String getRandom(int length){
		return crypt.getRandom(length);
	}
	
	/**
	 * Encrypt the password
	 * @param target The password to be encrypt
	 * @return An encrypt password
	 */
	private String maskElement(String target){			
		return crypt.encrypt(target);
	}
	
	/**
	 * A method to send the email to user 
	 * @param email The address of the email
	 * @param message The message of the email
	 * @param subject The subject of the email
	 */
	private void email(String email, String message, String subject){
		//Email sent by another thread
		class myRunnable implements Runnable {
			private String threadEmail = email;
			private String threadMessage = message;
			private String threadSubject = subject;
			@Override
			public void run() {
				Email.sendEmail(threadEmail, threadMessage, threadSubject);
			}
		}
		Thread t = new Thread(new myRunnable());
		t.start();
	}

	/**
	 * Get all the roles
	 * @return A list of roles
	 */
	@Override
	public List<Role> getRoles() {
		return dao.getRoles();
	}

	/**
	 * Update profile picture
	 * @param person The person that the picture is associate with
	 * @param picture The picture to be uploaded to S3
	 * @return A person with new profile picture link
	 */
	@Override
	public Person updateProfilePic(Person person, MultipartFile picture) {
		AwsKey key = dao.getAWSKey();
		person.setProfilePic(jetS3.uploadProfileItem(person.getId() + "", person.getId() + "", picture,
				key.getAccessKey(), key.getSecretAccessKey()));
		PersonDao.updatePersonPic(person);
		return person;
	}

	/**
	 * Get A person by user name
	 * @param username The user name to found the person 
	 * @return The person
	 */
	@Override
	public Person getPersonByUsername(String username) {
		return PersonDao.getPersonByUsername(username);
	}

	/**
	 * Get a list of complex
	 * @return A list of complex
	 */
	@Override
	public List<Complex> getComplex() {
		return dao.getComplex();
	}
	
	/**
	 * Login the user by checking if the user have input the right information
	 * @param username The user name user had input
	 * @param password The password user had input
	 * @return A person if login true else null if false
	 */
	@Override
	public Person loginUser(String username, String password) {
		Person person = PersonDao.getPersonByUsername(username);
		if(person != null && crypt.validate(password, person.getPassword())) {
			return person;
		}
		return null;
	}
	
	/**
	 * Creating a new person
	 * @param person A person to be created
	 * @return A message on whether the person had created
	 */
	@Override
	public String createUser(Person person) {
		person.setEmail(person.getEmail().toLowerCase());
		// Check if email exists
		if(PersonDao.getPersonByEmail(person.getEmail()) == null) {
			//make new user name 
			person.setUsername(person.getEmail());
			//set it as new user
			person.setVaildated(false);
			// Generate a Temporary Password
			String password = getRandom(20);
			person.setPassword(maskElement(password));
			// Save in Database
			Person user = PersonDao.createUser(person);
			// set a default profile picture
			AwsKey key = dao.getAWSKey();
			person.setProfilePic(jetS3.uploadProfileItem(user.getId() + "", user.getId() + "", 
					key.getAccessKey(), key.getSecretAccessKey()));
			PersonDao.updatePersonPic(person);
			// Send Email to Account
			String subject = "Welcome to Revature";
			String message = 
			"<table style=\"min-width:100%;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">"
			    + "<tbody>"
					+ "<tr>"
						+ "<td style=\"min-width:100%;padding:1px 18px;\">"
							+ "<table style=\"min-width:100%;border-top-width:5px;border-top-style:solid;border-top-color:#F26925;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">"
								+ "<tbody>"
									+ "<tr>"
			                        	+ "<td>"
			                            	+ "<span></span>"
			                            + "</td>"
			                        + "</tr>"
			                    + "</tbody>"
			                + "</table>"
			            + "</td>"
			        + "</tr>"
			    + "</tbody>"
			+ "</table>"
			+ "<h1>Welcome to RevPages !!</h1>"
			+ "<table style=\"overflow-x:auto;max-width:100%;min-width:100%;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"left\">"
            	+ "<tbody>"
            		+ "<tr>"
            			+ "<td style=\"padding-top:0;padding-right:18px;padding-bottom:9px;padding-left:18px;\" valign=\"top\">"
            				+ "<h3 style=\"text-align:left;\">Hello " + person.getFirstName() + " " + person.getLastName() + "</h3>"
            				+ "<p style=\"text-align:left;\"> Your account had been approve<br><br>"
            				
            				+ "Here is your login information <br><br>" 
        					+ "Temporary Username: " + person.getUsername() + "<br>" 
        					+ "Temporary Password: " + password
        					+ "<br>"
        					+ "<br>"
        					+ "Click the link below to login: "
        					+ "<a rel=\"nofollow\" target=\"_blank\" href=\"http://ec2-52-72-127-66.compute-1.amazonaws.com:8080/Project3/\">Login</a></p>"

            				+ "<br>"
            				+ "<br>"
            				+ "<br>Regards<br>"
            				+ "<strong>RevPages</strong><br>"
            				+ "<a rel=\"nofollow\" target=\"_blank\" href=\"https://revature.com/\">https://www.revature.com</a></p>"

            			+ "</td>"
            		+ "</tr>"
            	+ "</tbody>"
            + "</table>";
			email(person.getEmail(), message, subject);
			return "[\"User had been created\"]";
		} else {
			//tell that the email already exist
			return "[\"Email already exist\"]";
		}		
	}
	

}
