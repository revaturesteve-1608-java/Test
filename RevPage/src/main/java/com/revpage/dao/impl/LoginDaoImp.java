package com.revpage.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revpage.dao.LoginDao;
import com.revpage.dto.Person;
import com.revpage.util.JoinColumn;

/**
 * The implementation of the loginDao
 */
@Repository
@Transactional
public class LoginDaoImp implements LoginDao {

	/**
	 * This is used for getting a session to make a connection to the database
	 */
	@Autowired
	SessionFactory session;
	
	/**
	 * Getting information about the user base on the user name and password.
	 * @param username The user name of the person
	 * @param password The password of the person
	 * @return The person's information
	 */
	@Override
	public Person loginUser(String username, String password) {
		Criteria criteria = session.getCurrentSession().createCriteria(Person.class)
				.add(Restrictions.eq("username", username));
		JoinColumn.addColumnByJoin("role", criteria);
		JoinColumn.addColumnByJoin("complex", criteria);
		return (Person) criteria.list().get(0);
	}

}
