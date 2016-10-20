package project3.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project3.dao.PostAndReplyDao;
import project3.dto.DisLikeableReply;
import project3.dto.ForumPost;
import project3.dto.LikeableReply;
import project3.dto.Person;
import project3.dto.PostReply;
import project3.util.JoinColumn;

/**
 * The implementation of the PostAndReplyDao
 */
@Repository
@Transactional
public class PostAndReplyImpl implements PostAndReplyDao{
	
	/**
	 * This is used for getting a session to make a connection to the database
	 */
	@Autowired
	SessionFactory session;

	/**
	 * Getting all the post with the replies
	 * @return A list of all the post
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ForumPost> getAllPosts() {
		Criteria criteria = session.getCurrentSession().createCriteria(ForumPost.class);
		JoinColumn.addColumnByJoin("author", criteria);
		JoinColumn.addColumnByJoin("replys", criteria);
		return criteria.list();
	}
	
	/**
	 * Get all the replies from a post
	 * @param post The post where all the replies is
	 * @return A list of replies
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PostReply> getRepliesByPost(ForumPost post) {
		Criteria criteria = session.getCurrentSession().createCriteria(PostReply.class);
		JoinColumn.addColumnByJoin("author", criteria);
		JoinColumn.addColumnByJoin("post", criteria);
		criteria.add(Restrictions.eq("post", post));
		return (List<PostReply>) criteria.list();
	}

	/**
	 * Get one post by id
	 * @param id The id of the post
	 * @return The post of the id
	 */
	@Override
	public ForumPost getPostById(int id) {
		Criteria criteria = session.getCurrentSession().createCriteria(ForumPost.class);
		JoinColumn.addColumnByJoin("author", criteria);
		JoinColumn.addColumnByJoin("replys", criteria);
        criteria.add(Restrictions.eq("id", id));
        return (ForumPost) criteria.list().get(0);
	}
	
	/**
	 * Getting more posts for infinity scroll (Feature had not been implemented)
	 * @return A list of forumPost
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ForumPost> getMorePosts() {
		Session currentSession = session.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(ForumPost.class);
		JoinColumn.addColumnByJoin("author", criteria);
		JoinColumn.addColumnByJoin("replys", criteria);
		criteria.addOrder(Order.desc("timestamp"));
		return (List<ForumPost>) criteria.list();
	}
	
	/**
	 * Getting more posts by the author for infinity scroll 
	 * (Feature had not been implemented)
	 * (Feature had not been implemented)
	 * @return A list of forumPost
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ForumPost> getMorePostsByAuthor(Person author) {
		Session currentSession = session.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(ForumPost.class);
		JoinColumn.addColumnByJoin("author", criteria);
		JoinColumn.addColumnByJoin("replys", criteria);
		criteria.addOrder(Order.desc("timestamp"));
		criteria.add(Restrictions.eq("author", author));
		return (List<ForumPost>) criteria.list();
	}
	
	/**
	 * Get all the posts with category and sorted by time
	 * @return A list of posts with category
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ForumPost> getPostsByCategory() {
		Session currentSession = session.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(ForumPost.class);
		JoinColumn.addColumnByJoin("author", criteria);
		JoinColumn.addColumnByJoin("category", criteria);
		criteria.addOrder(Order.desc("timestamp"));
		return (List<ForumPost>) criteria.list();
	}
	
	/**
	 * Get all the post with category base on a person and sorted by time
	 * @param author The author of the post
	 * @return A list of post by an author
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ForumPost> getPostsByCategoryProf(Person author) {
		Session currentSession = session.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(ForumPost.class);
		criteria.add(Restrictions.eq("author", author));
		JoinColumn.addColumnByJoin("author", criteria);
		JoinColumn.addColumnByJoin("category", criteria);
		criteria.addOrder(Order.desc("timestamp"));
		return (List<ForumPost>) criteria.list();
	}
	
	/**
	 * Get a post with the likes attach
	 * @param id The id of the post
	 * @return A post with likes
	 */
	@Override
	public ForumPost getPostForLike(int id) {
		Criteria criteria = session.getCurrentSession().createCriteria(ForumPost.class);
		JoinColumn.addColumnByJoin("author", criteria);
		JoinColumn.addColumnByJoin("likes", criteria);
        criteria.add(Restrictions.eq("id", id));
		return (ForumPost) criteria.list().get(0);
	}
	
	/**
	 * Get a post with the dislikes attach
	 * @param id The id of the post
	 * @return A post with dislikes
	 */
	@Override
	public ForumPost getPostForDislike(int id) {
		Criteria criteria = session.getCurrentSession().createCriteria(ForumPost.class);
		JoinColumn.addColumnByJoin("author", criteria);
		JoinColumn.addColumnByJoin("dislikes", criteria);
        criteria.add(Restrictions.eq("id", id));
        return (ForumPost) criteria.list().get(0);
	}
	
	/**
	 * Get a reply with likes attach
	 * @param id The id of the likes
	 * @return A reply with likes
	 */
	@Override
	public PostReply getReplyForLike(int id) {
		Criteria criteria = session.getCurrentSession().createCriteria(PostReply.class);
		JoinColumn.addColumnByJoin("author", criteria);
		JoinColumn.addColumnByJoin("likes", criteria);
        criteria.add(Restrictions.eq("id", id));
        return (PostReply) criteria.list().get(0);
	}
	
	/**
	 * Get a reply with dislike attach
	 * @param id The id of the dislike
	 * @return A reply with dislike
	 */
	@Override
	public PostReply getReplyForDislike(int id) {
		Criteria criteria = session.getCurrentSession().createCriteria(PostReply.class);
		JoinColumn.addColumnByJoin("author", criteria);
		JoinColumn.addColumnByJoin("dislikes", criteria);
        criteria.add(Restrictions.eq("id", id));
        return (PostReply) criteria.list().get(0);
	}
	
	/**
	 * Creating a forum post
	 * @param post The object post needed to be created
	 * @return The id of the post
	 */
	@Override
	public int createForumPost(ForumPost post) {
		session.getCurrentSession().save(post);
		return ((ForumPost) session.getCurrentSession().merge(post)).getId();
	}
	
	/**
	 * Create a new reply to a post
	 * @param post The id of the post
	 * @param author The author of the reply
	 * @param likes A list of likes from reply
	 * @param dislikes A list of dislikes from reply
	 * @param approval Whether the reply had been approve
	 * @param content The content of the reply
	 * @param timestamp The time of the reply
	 */
	@Override
	public void createPostReply(int postId, Person author, List<LikeableReply> likes, 
			List<DisLikeableReply> dislikes, boolean approval, String content, 
			Timestamp timestamp) {
		Session currentSession = session.getCurrentSession();
		ForumPost post = (ForumPost) currentSession.get(ForumPost.class, postId);
		Person getAuthorAgain = (Person) currentSession.get(Person.class, author.getId());
		PostReply newReply = new PostReply(post, getAuthorAgain, likes, dislikes, 
				approval, content, timestamp);
		post.getReplys().add(newReply);
	}
	
	/**
	 * Update the post in the database
	 * @param post The post that will be updated
	 */
	@Override
	public void updatePost(ForumPost post) {
		session.getCurrentSession().update(post);
	}
	
	/**
	 * Update the reply in the database
	 * @param reply The reply that will be updated
	 */
	@Override
	public void updateReply(PostReply reply) {
		session.getCurrentSession().update(reply);
	}
	
	/**
	 * Delete the forum post
	 * @param id The id of the post
	 */
	@Override
	public void deleteForumPost(int id) {
		Session currentSession = session.getCurrentSession();
		ForumPost post = (ForumPost) currentSession.get(ForumPost.class, id);
		currentSession.delete(post);
	}

	/**
	 * Delete the reply of the post (Feature had not been implemented)
	 * @param id The id of the reply
	 */
	@Override
	public void deletePostReply(int id) {
		Session currentSession = session.getCurrentSession();
		PostReply reply = (PostReply) currentSession.get(PostReply.class, id);
		currentSession.delete(reply);
	}
}
