package project3.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project3.dao.LoginDao;
import project3.dto.Person;

@Repository
@Transactional
public class LoginDaoImp implements LoginDao {

	@Autowired
	SessionFactory session;
	
	/**
	 * A private method to join the table together
	 * 
	 * @param column the column to join
	 * @param criteria where it joining to
	 */
	private void addColumn(String column, Criteria criteria) {
		criteria.setFetchMode(column, FetchMode.JOIN);
	}
	
	@Override
	public Person loginUser(String username, String password) {
		
		System.out.println("here");
		Criteria criteria = session.getCurrentSession().createCriteria(Person.class)
				.add(Restrictions.eq("username", username));
		addColumn("role", criteria);
		addColumn("complex", criteria);
		return (Person) criteria.list().get(0);
	}

}
