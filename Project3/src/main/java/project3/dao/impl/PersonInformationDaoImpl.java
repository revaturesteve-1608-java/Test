package project3.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project3.dao.PersonInformationDao;
import project3.dto.Person;
import project3.util.JoinColumn;

/**
 * The implementation of the PersonInformationDao
 */
@Repository
@Transactional
public class PersonInformationDaoImpl implements PersonInformationDao{

	/**
	 * This is used for getting a session to make a connection to the database
	 */
	@Autowired
	SessionFactory session;
	
	/**
	 * Get a person by id
	 * @param id The id of the person
	 * @return A person based on id
	 */
	@Override
	public Person getPersonById(int id) {
		return (Person) session.getCurrentSession().get(Person.class, id);
	}
	
	/**
	 * Get a person by user name
	 * @param username The user name of the person
	 * @return A person based on user name
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Person getPersonByUsername(String username) {
		Session currentSession = session.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(Person.class);
		JoinColumn.addColumnByJoin("role", criteria);
		JoinColumn.addColumnByJoin("complex", criteria);
		List<Person> persons = (List<Person>) criteria.add(Restrictions.eq("username", 
				username)).list();
		if(persons.size() == 0) {
			return null;
		} else {
			return persons.get(0);
		}
	}
	
	/**
	 * Get a person by email
	 * @param email The email of the person
	 * @return A person based on email
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Person getPersonByEmail(String email) {
		List<Person> persons = session.getCurrentSession().createCriteria(Person.class).
				add(Restrictions.eq("email", email)).list();
		if(persons.size() == 0) {
			return null;
		} else {
			return persons.get(0);
		}
	}
	
	/**
	 * Create a new user in the database
	 * @param person The person to be put in database
	 * @return The person that had been created
	 */
	@Override
	public Person createUser(Person person) {
		session.getCurrentSession().save(person);
		return (Person) session.getCurrentSession().merge(person);
	}
	
	/**
	 * Update the person's profile picture
	 * @param person The person to be updated
	 */
	@Override
	public void updatePersonPic(Person person) {
		Session currentSession = session.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(Person.class);
		Person tempPerson = (Person) criteria.add(Restrictions.eq("username", 
				person.getUsername())).list().get(0);
		tempPerson.setProfilePic(person.getProfilePic());
	}
}
