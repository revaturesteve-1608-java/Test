package project3.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project3.dao.SimpleDao;
import project3.dto.AwsKey;
import project3.dto.Complex;
import project3.dto.ForumCategory;
import project3.dto.Role;

/**
 * The implementation of the SimpleDao
 */
@Repository
@Transactional
public class SimpleDaoImpl implements SimpleDao{
	
	/**
	 * This is used for getting a session to make a connection to the database
	 */
	@Autowired
	SessionFactory session;
	
	/**
	 * Get the key to access the AWS S3
	 * @return The key to AWS S3
	 */
	@Override
	public AwsKey getAWSKey() {
		return (AwsKey) session.getCurrentSession().get(AwsKey.class, 1);
	}
	
	/**
	 * Get all the roles from the database
	 * @return A list of roles
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoles() {
		return (List<Role>) session.getCurrentSession().createCriteria(Role.class).list();
	}
	
	/**
	 * Get a list of category from the database
	 * @return A list of category
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ForumCategory> getForumCategory() {
		return (List<ForumCategory>) session.getCurrentSession().createCriteria(
				ForumCategory.class).list();
	}

	/**
	 * Get a category by its name
	 * @param catName The name of the category
	 * @return A category
	 */
	@Override
	public ForumCategory getCategoryByName(String catName) {
		Criteria criteria = session.getCurrentSession().createCriteria(
				ForumCategory.class);
		criteria.add(Restrictions.eq("categoryName", catName));
		return (ForumCategory) criteria.list().get(0);
	}
	
	/**
	 * Get a list of complex
	 * @return A list of complex
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Complex> getComplex() {
		return session.getCurrentSession().createCriteria(Complex.class).list();
	}
	
	/**
	 * Get a complex by name
	 * @param name The name of the complex
	 * @return A complex
	 */
	@Override
	public Complex getComplexByName(String name) {
		Criteria criteria = session.getCurrentSession().createCriteria(Complex.class);
		return (Complex) criteria.add(Restrictions.eq("complexName", name)).list().get(0);
	}
	
	/**
	 * Create a new complex
	 * (Feature had not been implemented)
	 * @param complexName
	 */
	@Override
	public void createForumCategory(String categoryName) {
		ForumCategory newCategory = new ForumCategory(categoryName);
		session.getCurrentSession().save(newCategory);
	}

	/**
	 * Create a new category
	 * (Feature had not been implemented)
	 * @param categoryName The name of the category
	 */
	@Override
	public void createComplex(String complexName) {
		Complex newComplex = new Complex(complexName);
		session.getCurrentSession().save(newComplex);
	}

}
