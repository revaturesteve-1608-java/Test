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
		if(persons.isEmpty()) {
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
		if(persons.isEmpty()) {
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
	
	/**
	 * Update the person when they are first login
	 * @param username The old user name of the person 
	 * @param pass The new password of the person 
	 * @param newUsername The new user name of the person 
	 */
	@Override
	public void updateTempPerson(String username, String pass, String newUsername) {
		Session currentSession = session.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(Person.class);
		Person person = (Person) criteria.add(Restrictions.eq("username", username)).
				list().get(0);
		person.setPassword(pass);
		person.setUsername(newUsername);
		person.setVaildated(true);
	}
	
	/**
	 * Update the user information
	 * @param person The new person's information
	 */
	@Override
	public void updateUserInfo(Person person) {
		Session currentSession = session.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(Person.class);
		Person tempPerson = (Person) criteria.add(Restrictions.eq("id", person.getId())).
				list().get(0);
		tempPerson.setPassword(person.getPassword());
		tempPerson.setUsername(person.getUsername());
		tempPerson.setEmail(person.getEmail());
		tempPerson.setPhoneNumber(person.getPhoneNumber());
		tempPerson.setUniversity(person.getUniversity());
		tempPerson.setLinkedin(person.getLinkedin());
		tempPerson.setUsername(person.getUsername());
		tempPerson.setComplex(person.getComplex());
	}
}
