package com.revpages.dao;

import java.util.List;

import com.revpages.dto.AwsKey;
import com.revpages.dto.Complex;
import com.revpages.dto.ForumCategory;
import com.revpages.dto.Role;

/**
 * Access the database for simple object
 */
public interface SimpleDao {
	
	/**
	 * Get the key to access the AWS S3
	 * @return The key to AWS S3
	 */
	public AwsKey getAWSKey();
	
	/**
	 * Get all the roles from the database
	 * @return A list of roles
	 */
	public List<Role> getRoles();
	
	/**
	 * Get a list of category from the database
	 * @return A list of category
	 */
	public List<ForumCategory> getForumCategory();
	
	/**
	 * Get a category by its name
	 * @param catName The name of the category
	 * @return A category
	 */
	public ForumCategory getCategoryByName(String catName);
	
	/**
	 * Get a list of complex
	 * @return A list of complex
	 */
	public List<Complex> getComplex();
	
	/**
	 * Get a complex by name
	 * @param name The name of the complex
	 * @return A complex
	 */
	public Complex getComplexByName(String name);
	
	/**
	 * Create a new complex
	 * (Feature had not been implemented)
	 * @param complexName
	 */
	public void createComplex(String complexName);
	
	/**
	 * Create a new category
	 * (Feature had not been implemented)
	 * @param categoryName The name of the category
	 */
	public void createForumCategory(String categoryName);
}
