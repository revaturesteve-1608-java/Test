package project3.main;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import project3.dto.ForumPost;

public class TestClass {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration().configure().
				buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		Criteria criteria = session.createCriteria(ForumPost.class);
		List<ForumPost> posts = criteria.list();
	}

}
