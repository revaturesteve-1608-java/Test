package project3.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project3.dao.SimpleDao;
import project3.dto.AwsKey;
import project3.dto.Complex;
import project3.dto.DisLikeableReply;
import project3.dto.ForumCategory;
import project3.dto.ForumPost;
import project3.dto.LikeableReply;
import project3.dto.DisLikeablePost;
import project3.dto.ForumCategory;
import project3.dto.ForumPost;
import project3.dto.LikeablePost;
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
		criteria.setFetchMode("replys", FetchMode.JOIN);
		
//		criteria.setFetchMode("role", FetchMode.EAGER);
//		criteria.setFetchMode("complex", FetchMode.EAGER);
		System.out.println("=============================here====================================");
		List<ForumPost> posts = (List<ForumPost>) criteria.list();
//		System.out.println("length of posts list: " + posts.size() + "\tpostId1: " + posts.get(0).getId() + "\tpostId2: " + posts.get(1).getId());
		return criteria.list();
	}

	@Override
	public ForumPost getPostById(int id, boolean like, boolean dislike) {
		
		Criteria criteria = session.getCurrentSession().createCriteria(ForumPost.class);
        criteria.setFetchMode("author", FetchMode.JOIN);
        criteria.setFetchMode("replys", FetchMode.JOIN);
        
        criteria.add(Restrictions.eq("id", id));
        ForumPost post = (ForumPost) criteria.list().get(0);
        
        System.out.println("end----------------------------");
        
        return post;
	}
	
	@Override
	public ForumPost getPostForDislike(int id) {
		
		Criteria criteria = session.getCurrentSession().createCriteria(ForumPost.class);
        criteria.setFetchMode("author", FetchMode.JOIN);
     //   criteria.setFetchMode("replys", FetchMode.JOIN);
        criteria.setFetchMode("dislikes", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", id));
        ForumPost post = (ForumPost) criteria.list().get(0);
        System.out.println("--------here---");

        return post;
	}
	
	@Override
	public PostReply getReplyForDislike(int id) {
		
		Criteria criteria = session.getCurrentSession().createCriteria(PostReply.class);
        criteria.setFetchMode("author", FetchMode.JOIN);
     //   criteria.setFetchMode("replys", FetchMode.JOIN);
        criteria.setFetchMode("dislikes", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", id));
        PostReply reply = (PostReply) criteria.list().get(0);
        //System.out.println("--------here---");

        return reply;
	}
	
	@Override
	public PostReply getReplyForLike(int id) {
		
		Criteria criteria = session.getCurrentSession().createCriteria(PostReply.class);
        criteria.setFetchMode("author", FetchMode.JOIN);
     //   criteria.setFetchMode("replys", FetchMode.JOIN);
        criteria.setFetchMode("likes", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", id));
        PostReply reply = (PostReply) criteria.list().get(0);
        //System.out.println("--------here---");

        return reply;
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
		PostReply newReply = new PostReply(post, getAuthorAgain, likes, dislikes, approval, content, timestamp);
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
//		for(PostReply reply: post.getReplys())
//			currentSession.delete(reply);
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
		tempPerson.setComplex(person.getComplex());
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
		criteria.setFetchMode("replys", FetchMode.JOIN);
		criteria.addOrder(Order.desc("timestamp"));
		
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
		
	

	@Override
	public void updatePost(ForumPost post) {
		session.getCurrentSession().update(post);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ForumPost> getPostsByCategory() {
		// TODO Auto-generated method stub
		Session currentSession = session.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(ForumPost.class);
		criteria.setFetchMode("author", FetchMode.JOIN);
		criteria.setFetchMode("category", FetchMode.JOIN);
		criteria.addOrder(Order.desc("timestamp"));
		List<ForumPost> posts = (List<ForumPost>) criteria.list();
		return posts;
	}
		
	public void saveDislike(DisLikeablePost dislike) {
		System.out.println("-------------------------------saving----------------------------------");
		session.getCurrentSession().save(dislike);
		
	}

	@Override
	public ForumPost getPostForLike(int id) {
		
		Criteria criteria = session.getCurrentSession().createCriteria(ForumPost.class);
        criteria.setFetchMode("author", FetchMode.JOIN);
     //   criteria.setFetchMode("replys", FetchMode.JOIN);
        criteria.setFetchMode("likes", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", id));
        ForumPost post = (ForumPost) criteria.list().get(0);
        System.out.println("--------here---");
		
		return post;
	}

	@Override
	public void saveLike(LikeablePost like) {
		session.getCurrentSession().save(like);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ForumPost> getMorePostsByUsername(int firstResult, Person author) {
		// TODO Auto-generated method stub
		
		Session currentSession = session.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(ForumPost.class);
		criteria.setFetchMode("author", FetchMode.JOIN);
		criteria.setFetchMode("replys", FetchMode.JOIN);
		criteria.addOrder(Order.desc("timestamp"));
		System.out.println("username inside of the dao: " + author.getUsername());
		criteria.add(Restrictions.eq("author", author));
//		criteria.setFirstResult(firstResult);
//		criteria.setMaxResults(firstResult + 2);
		List<ForumPost> posts = (List<ForumPost>) criteria.list();
		System.out.println("size of posts: " + posts.size());
		
		return posts;
	}

	@Override
	public void removeLike(ForumPost post, LikeablePost like) {
		session.getCurrentSession().delete(like);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public LikeablePost getLikesByPerson(Person person, int id) {
		
		Criteria criteria = session.getCurrentSession().createCriteria(LikeablePost.class);
		criteria.setFetchMode("post", FetchMode.JOIN);
		criteria.setFetchMode("author", FetchMode.JOIN);
		criteria.add(Restrictions.eq("id", id));
		List<LikeablePost> likes = (List<LikeablePost>) criteria.list();
		
		return likes.get(0);
	}

	@Override
	public LikeablePost getLikeById(int id) {
		
		LikeablePost like = (LikeablePost) session.getCurrentSession().get(LikeablePost.class, id);
		
		return like;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DisLikeablePost getDislikesById(int id) {
		
		Criteria criteria = session.getCurrentSession().createCriteria(DisLikeablePost.class);
		criteria.setFetchMode("post", FetchMode.JOIN);
		criteria.setFetchMode("author", FetchMode.JOIN);
		criteria.add(Restrictions.eq("id", id));
		List<DisLikeablePost> likes = (List<DisLikeablePost>) criteria.list();
		return likes.get(0);
	}

	@Override
	public void removeDislike(DisLikeablePost dislike) {
		session.getCurrentSession().delete(dislike);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostReply> getRepliesByPost(ForumPost post) {
		
		Criteria criteria = session.getCurrentSession().createCriteria(PostReply.class);
		criteria.setFetchMode("author", FetchMode.JOIN);
		criteria.setFetchMode("post", FetchMode.JOIN);
		criteria.add(Restrictions.eq("post", post));
		
		List<PostReply> replies = (List<PostReply>) criteria.list();
		
		return replies;
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

	public void saveDislikeReply(DisLikeableReply dislike) {
			session.getCurrentSession().save(dislike);
		
	}

	@Override
	public void updateReply(PostReply reply) {
			session.getCurrentSession().update(reply);
		
	}

	@Override
	public DisLikeableReply getDislikesReplyById(int id) {
		Criteria criteria = session.getCurrentSession().createCriteria(DisLikeableReply.class);
		criteria.setFetchMode("reply", FetchMode.JOIN);
		criteria.setFetchMode("author", FetchMode.JOIN);
		criteria.add(Restrictions.eq("id", id));
		List<DisLikeableReply> likes = (List<DisLikeableReply>) criteria.list();
		return likes.get(0);
	}

	@Override
	public void removeDislikeReply(DisLikeableReply dislikeable) {
		session.getCurrentSession().delete(dislikeable);
		
	}

	@Override
	public LikeableReply getLikesReplyById(int id) {
		Criteria criteria = session.getCurrentSession().createCriteria(LikeableReply.class);
		criteria.setFetchMode("reply", FetchMode.JOIN);
		criteria.setFetchMode("author", FetchMode.JOIN);
		criteria.add(Restrictions.eq("id", id));
		List<LikeableReply> likes = (List<LikeableReply>) criteria.list();
		return likes.get(0);
	}

	@Override
	public void removeLikeReply(LikeableReply likeable) {
		session.getCurrentSession().delete(likeable);
		
	}

	@Override
	public void saveLikeReply(LikeableReply like) {
		session.getCurrentSession().save(like);
		
	}

	@Override
	public LikeableReply getLikesReplyByPerson(Person person) {
		Criteria criteria = session.getCurrentSession().createCriteria(LikeableReply.class);
		criteria.setFetchMode("reply", FetchMode.JOIN);
		criteria.setFetchMode("author", FetchMode.JOIN);
		System.out.println("before                                       person");
		criteria.add(Restrictions.eq("author", person));
		System.out.println("after                                       person");
		List<LikeableReply> likes = (List<LikeableReply>) criteria.list();
		if(likes.size() > 0) {
		return likes.get(0);
		} else {
			return null;
		}
	}

	@Override
	public DisLikeableReply getDislikesByPerson(Person person) {
		Criteria criteria = session.getCurrentSession().createCriteria(DisLikeableReply.class);
		criteria.setFetchMode("reply", FetchMode.JOIN);
		criteria.setFetchMode("author", FetchMode.JOIN);
		System.out.println("before                                       person");
		criteria.add(Restrictions.eq("author", person));
		System.out.println("after                                       person");
		List<DisLikeableReply> likes = (List<DisLikeableReply>) criteria.list();
		if(likes.size() > 0) {
		return likes.get(0);
		} else {
			return null;
		}
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

	@SuppressWarnings("unchecked")
	@Override
	public List<ForumPost> getPostsByCategoryProf(Person author) {
		// TODO Auto-generated method stub
		Session currentSession = session.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(ForumPost.class);
		criteria.add(Restrictions.eq("author", author));
		criteria.setFetchMode("author", FetchMode.JOIN);
		criteria.setFetchMode("category", FetchMode.JOIN);
		criteria.addOrder(Order.desc("timestamp"));
		List<ForumPost> posts = (List<ForumPost>) criteria.list();
		return posts;
	}
}
