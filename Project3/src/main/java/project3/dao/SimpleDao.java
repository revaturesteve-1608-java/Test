package project3.dao;

import java.util.List;

import project3.dto.AwsKey;
import project3.dto.Complex;
import project3.dto.ForumCategory;
import project3.dto.Person;
import project3.dto.Role;

public interface SimpleDao {
	
	public ForumCategory getForumCategoryById(int id);
	
	public List<Role> getRoles();
	
	public List<ForumCategory> getForumCategory();
	
	public void updateTempPerson(String username, String pass, String newUsername);
	
	public void updateUserInfo(Person person);
	
	public void createForumCategory(String categoryName);
	
	public void createComplex(String complexName);
	
	public AwsKey getAWSKey();
	
	public ForumCategory getCategoryByName(String catName);
	
	public Complex getComplexByName(String name);
	
	public List<Complex> getComplex();
}
