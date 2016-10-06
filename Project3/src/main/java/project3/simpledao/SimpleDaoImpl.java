package project3.simpledao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project3.dto.Complex;
import project3.dto.ForumCategory;
import project3.dto.ForumPost;
import project3.dto.Person;
import project3.dto.PostReply;
import project3.dto.Role;

@Repository
public class SimpleDaoImpl implements SimpleDao{
	
	@Autowired
	SessionFactory session;

	@SuppressWarnings("unchecked")
	@Override
	public List<ForumPost> getAllPosts() {
		Criteria criteria = session.getCurrentSession().createCriteria(ForumPost.class);
		return criteria.list();
	}

	@Override
	public ForumPost getPostById(int id) {
		ForumPost post = (ForumPost) session.getCurrentSession().get(ForumPost.class, id);
		return post;
	}

	@Override
	public Person getPersonById(int id) {
		Person person = (Person) session.getCurrentSession().get(Person.class, id);
		return person;
	}

	@Override
	public void createPerson(String first_name, String last_name, String username, String password, String email, Role role,
			byte[] profilePic, Complex complex, String phoneNumber, String bio, String unviersity, boolean vaildated,
			String linkedin) {
		Person newPerson = new Person(first_name, last_name, username, password, email, role, profilePic, 
				complex, phoneNumber, bio, unviersity, vaildated, linkedin);
		session.getCurrentSession().save(newPerson);
	}

	@Override
	public void createForumCategory(String categoryName) {
		ForumCategory newCategory = new ForumCategory(categoryName);
		session.getCurrentSession().save(newCategory);
	}

	@Override
	public void createPostReply(ForumPost post, int likes, int dislikes, boolean approval, 
			String content, Timestamp timestamp) {
		PostReply newReply = new PostReply(post, likes, dislikes, approval, content, timestamp);
		session.getCurrentSession().save(newReply);
	}

	@Override
	public void createComplex(String complexName) {
		Complex newComplex = new Complex(complexName);
		session.getCurrentSession().save(newComplex);
	}

	@Override
	public void deleteForumPost(int id) {
		Session currentSession = session.getCurrentSession();
		ForumPost post = (ForumPost) currentSession.get(ForumPost.class, id);
		currentSession.delete(post);
	}

	@Override
	public void deletePostReply(int id) {
		Session currentSession = session.getCurrentSession();
		PostReply reply = (PostReply) currentSession.get(PostReply.class, id);
		currentSession.delete(reply);
	}
}
