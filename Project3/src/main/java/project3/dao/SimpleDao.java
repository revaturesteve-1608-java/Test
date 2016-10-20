package project3.dao;

import java.util.List;

import project3.dto.AwsKey;
import project3.dto.Complex;
import project3.dto.ForumCategory;
import project3.dto.Person;
import project3.dto.Role;

public interface SimpleDao {
	
	public Person getPersonById(int id);
	
	public Person getPersonByEmail(String email);

	public Person getPersonByUsername(String username);
	
	public ForumCategory getForumCategoryById(int id);
	
	public List<Role> getRoles();
	
	public List<ForumCategory> getForumCategory();
	
	public void updateTempPerson(String username, String pass, String newUsername);
	
	public void updateUserInfo(Person person);
	
	public void createPerson(String first_name, String last_name, String username, String password, String email, Role role,
			String profilePic, Complex complex, String phoneNumber, String bio, String unviersity, boolean vaildated,
			String linkedin);
	
	public void createForumCategory(String categoryName);
	
	public Person createUser(Person person);
	
	public void createComplex(String complexName);
	
	public AwsKey getAWSKey();
	
	public void updatePersonPic(Person person);
	
	public ForumCategory getCategoryByName(String catName);
	
	public Complex getComplexByName(String name);
	
	public List<Complex> getComplex();
}
