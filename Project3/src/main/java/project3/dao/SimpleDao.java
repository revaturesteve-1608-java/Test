package project3.dao;

import java.util.List;

import project3.dto.AwsKey;
import project3.dto.Complex;
import project3.dto.ForumCategory;
import project3.dto.Role;

public interface SimpleDao {
	
	public AwsKey getAWSKey();
	
	public List<Role> getRoles();
	
	public List<ForumCategory> getForumCategory();
	
	public ForumCategory getForumCategoryById(int id);
	
	public ForumCategory getCategoryByName(String catName);
	
	public List<Complex> getComplex();
	
	public Complex getComplexByName(String name);
	
	public void createComplex(String complexName);
	
	public void createForumCategory(String categoryName);
}
