package project3.simpledao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project3.dto.ForumPost;
import project3.dto.Person;

@Repository
@Transactional
public class LoginDaoImp implements LoginDao {

	@Autowired
	SessionFactory session;
	
	@SuppressWarnings("unchecked")
	@Override
	public Person loginUser(String username, String password) {
		
		System.out.println("here");
		Criteria criteria = session.getCurrentSession().createCriteria(Person.class);
		List<Person> list = criteria.list();
		
		Person user = null;
		System.out.println(list.size());
		for(Person person: list) {
			System.out.println(person.toString());
			if(person.getUsername().equals(username)&& person.getPassword().equals(password)){
				user = person;
			}
		}
		
		return user;
	}

}
