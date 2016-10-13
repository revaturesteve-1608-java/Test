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

import project3.dto.AwsKey;
import project3.dto.Complex;
import project3.dto.DisLikeableReply;
import project3.dto.ForumCategory;
import project3.dto.ForumPost;
import project3.dto.LikeableReply;
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
		criteria.setFetchMode("author", FetchMode.JOIN);
//		criteria.setFetchMode("role", FetchMode.EAGER);
//		criteria.setFetchMode("complex", FetchMode.EAGER);
		System.out.println("=============================here====================================");
		List<ForumPost> posts = (List<ForumPost>) criteria.list();
//		System.out.println("length of posts list: " + posts.size() + "\tpostId1: " + posts.get(0).getId() + "\tpostId2: " + posts.get(1).getId());
		return criteria.list();
	}

	@Override
	public ForumPost getPostById(int id) {
		ForumPost post = (ForumPost) session.getCurrentSession().get(ForumPost.class, id);
		System.out.println(post.toString());
		System.out.println("--------here---");
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
	public Person createUser(Person person) {
		session.getCurrentSession().save(person);
		return (Person) session.getCurrentSession().merge(person);
	}

	//not using this method
	@Override
	public void createPerson(String first_name, String last_name, String username, String password, String email, Role role,
			String profilePic, Complex complex, String phoneNumber, String bio, String unviersity, boolean vaildated,
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
	public void createPostReply(int postId, Person author, List<LikeableReply> likes, List<DisLikeableReply> dislikes, boolean approval, 
			String content, Timestamp timestamp) {
		Session currentSession = session.getCurrentSession();
		
		ForumPost post = (ForumPost) currentSession.get(ForumPost.class, postId);
		System.out.println("forum post id: " + post.getId());
		System.out.println("forum author username: " + post.getAuthor().getId());
		Person getAuthorAgain = (Person) currentSession.get(Person.class, author.getId());
		PostReply newReply = new PostReply(getAuthorAgain, likes, dislikes, approval, content, timestamp);
//		System.out.println("we");
//		session.getCurrentSession().save(newReply);
//		System.out.println("here");
		post.getReplys().add(newReply);
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
	}

	@Override
	public int createForumPost(ForumPost post) {
		session.getCurrentSession().save(post);
		ForumPost newForumPost = (ForumPost) session.getCurrentSession().merge(post);
		System.out.println("id assigned to new post: " + newForumPost.getId());
		return newForumPost.getId();
	}

	@Override
	public AwsKey getAWSKey() {
		return (AwsKey) session.getCurrentSession().get(AwsKey.class, 1);
	}

	@Override
	public void updatePersonPic(Person person) {
		Session currentSession = session.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(Person.class);
		Person tempPerson = (Person) criteria.add(Restrictions.eq("username", person.getUsername())).list().get(0);
		tempPerson.setProfilePic(person.getProfilePic());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ForumPost> getMorePosts(int firstResult) {
		// TODO Auto-generated method stub
		Session currentSession = session.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(ForumPost.class);
		criteria.setFetchMode("author", FetchMode.JOIN);
//		criteria.setFirstResult(firstResult);
//		criteria.setMaxResults(firstResult + 2);
		List<ForumPost> posts = (List<ForumPost>) criteria.list();
		System.out.println("size of posts: " + posts.size());
		
//		Criteria criteria = session.createCriteria(Client.class);
//		criteria.createAlias("address", "address");
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//		criteria.setFirstResult(init);
//		criteria.setMaxResults(max);
//		List<Client> clients = criteria.list();
		return posts;
	}
	
}
