package project3.simpledao;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project3.dto.Complex;
import project3.dto.ForumCategory;
import project3.dto.ForumPost;
import project3.dto.Person;
import project3.dto.PostReply;
import project3.dto.Role;

@Repository
@Transactional
public class SimpleDaoImpl implements SimpleDao{
	
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
	
	@SuppressWarnings("unchecked")
	@Override
	public Person getPersonByUsername(String username) {
		Session currentSession = session.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(Person.class);
		addColumn("role", criteria);
		addColumn("complex", criteria);
		List<Person> persons = (List<Person>) criteria.add(Restrictions.eq("username", username)).list();
		if(persons.size() == 0) {
			return null;
		} else {
			return persons.get(0);
		}
	}
	
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
	public void createUser(Person person) {
		session.getCurrentSession().save(person);
	}

	//not using this method
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

	@Override
	public void updateTempPerson(String username, String pass, String newUsername) {
		// TODO Auto-generated method stub
		Session currentSession = session.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(Person.class);
		Person person = (Person) criteria.add(Restrictions.eq("username", username)).list().get(0);
		person.setPassword(pass);
		person.setUsername(newUsername);
		person.setVaildated(true);
	}
	
	@Override
	public void updateUserInfo(String currentUser, String newPassword, String username, String newEmail, 
			String newPhone, String newUniversity, String newLinkedIn) {
		// TODO Auto-generated method stub
		Session currentSession = session.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(Person.class);
		Person person = (Person) criteria.add(Restrictions.eq("username", currentUser)).list().get(0);
		System.out.println("inside dao email: " + newEmail + "\tsize: " + newEmail.length());
		person.setPassword(newPassword);
		System.out.println("SHOULD NOT GET HERE");
		person.setUsername(username);
		person.setEmail(newEmail);
		person.setPhoneNumber(newPhone);
		person.setUnviersity(newUniversity);
		person.setLinkedin(newLinkedIn);
		person.setUsername(username);
	}

	@Override
	public void createForumPost(ForumPost post) {
		// TODO Auto-generated method stub
		session.getCurrentSession().save(post);
	}

	
}
