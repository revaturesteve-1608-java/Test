package project3.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project3.dao.SimpleDao;
import project3.dto.AwsKey;
import project3.dto.Complex;
import project3.dto.ForumCategory;
import project3.dto.Person;
import project3.dto.Role;

@Repository
@Transactional
public class SimpleDaoImpl implements SimpleDao{
	
	@Autowired
	SessionFactory session;
	
	@Override
	public ForumCategory getForumCategoryById(int id) {
		return (ForumCategory) session.getCurrentSession().get(ForumCategory.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoles() {
		return (List<Role>) session.getCurrentSession().createCriteria(Role.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ForumCategory> getForumCategory() {
		return (List<ForumCategory>) session.getCurrentSession().createCriteria(ForumCategory.class).list();
	}

	@Override
	public void createForumCategory(String categoryName) {
		ForumCategory newCategory = new ForumCategory(categoryName);
		session.getCurrentSession().save(newCategory);
	}

	@Override
	public void createComplex(String complexName) {
		Complex newComplex = new Complex(complexName);
		session.getCurrentSession().save(newComplex);
	}

	@Override
	public void updateTempPerson(String username, String pass, String newUsername) {
		Session currentSession = session.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(Person.class);
		criteria.setFetchMode("role", FetchMode.JOIN);
		criteria.setFetchMode("complex", FetchMode.JOIN);
		Person person = (Person) criteria.add(Restrictions.eq("username", username)).list().get(0);
		person.setPassword(pass);
		person.setUsername(newUsername);
		person.setVaildated(true);
	}
	
	@Override
	public void updateUserInfo(Person person) {
		Session currentSession = session.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(Person.class);
		Person tempPerson = (Person) criteria.add(Restrictions.eq("id", person.getId())).list().get(0);
		tempPerson.setPassword(person.getPassword());
		tempPerson.setUsername(person.getUsername());
		tempPerson.setEmail(person.getEmail());
		tempPerson.setPhoneNumber(person.getPhoneNumber());
		tempPerson.setUniversity(person.getUniversity());
		tempPerson.setLinkedin(person.getLinkedin());
		tempPerson.setUsername(person.getUsername());
		tempPerson.setComplex(person.getComplex());
	}

	@Override
	public AwsKey getAWSKey() {
		return (AwsKey) session.getCurrentSession().get(AwsKey.class, 1);
	}

	@Override
	public ForumCategory getCategoryByName(String catName) {
		// TODO Auto-generated method stub
		Criteria criteria = session.getCurrentSession().createCriteria(ForumCategory.class);
		System.out.println("categroy name: " + catName);
		criteria.add(Restrictions.eq("categoryName", catName));
		System.out.println("size of category: " + criteria.list().size());
		return (ForumCategory) criteria.list().get(0);
	}
	
	@Override
	public Complex getComplexByName(String name) {
		Criteria criteria = session.getCurrentSession().createCriteria(Complex.class);
		return (Complex) criteria.add(Restrictions.eq("complexName", name)).list().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Complex> getComplex() {
		return session.getCurrentSession().createCriteria(Complex.class).list();
	}
}
