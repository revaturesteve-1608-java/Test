package com.revpage.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revpage.dao.LikeAndDislikeDao;
import com.revpage.dto.DisLikeablePost;
import com.revpage.dto.DisLikeableReply;
import com.revpage.dto.LikeablePost;
import com.revpage.dto.LikeableReply;
import com.revpage.dto.Person;
import com.revpage.util.JoinColumn;

/**
 * The implementation of the LikeAndDislikeDao
 */
@Repository
@Transactional
public class LikeAndDislikeDaoImple implements LikeAndDislikeDao{
	
	/**
	 * This is used for getting a session to make a connection to the database
	 */
	@Autowired
	SessionFactory session;
	
	/**
	 * Get a like from the post by id
	 * @param id The id of the like
	 * @return A like from the post
	 */
	@Override
	public LikeablePost getLikesById(int id) {
		Criteria criteria = session.getCurrentSession().createCriteria(LikeablePost.class);
		JoinColumn.addColumnByJoin("post", criteria);
		JoinColumn.addColumnByJoin("author", criteria);
		criteria.add(Restrictions.eq("id", id));
		return (LikeablePost) criteria.list().get(0);
	}

	/**
	 * Get the dislike of a post by id with post and author it associate with
	 * @param id The id of the dislike
	 * @return The dislike of the post
	 */
	@Override
	public DisLikeablePost getDislikesPostById(int id) {
		Criteria criteria = session.getCurrentSession().createCriteria(DisLikeablePost.class);
		JoinColumn.addColumnByJoin("post", criteria);
		JoinColumn.addColumnByJoin("author", criteria);
		criteria.add(Restrictions.eq("id", id));
		return (DisLikeablePost) criteria.list().get(0);
	}
	
	/**
	 * Get a like from the reply base on id
	 * @param id The id of the like
	 * @return A like from a reply
	 */
	@Override
	public LikeableReply getLikesReplyById(int id) {
		Criteria criteria = session.getCurrentSession().createCriteria(LikeableReply.class);
		JoinColumn.addColumnByJoin("reply", criteria);
		JoinColumn.addColumnByJoin("author", criteria);
		criteria.add(Restrictions.eq("id", id));
		return (LikeableReply) criteria.list().get(0);
	}
	
	/**
	 * Get a dislike from the reply base on id
	 * @param id The id of the dislike
	 * @return A dislike from a reply
	 */
	@Override
	public DisLikeableReply getDislikesReplyById(int id) {
		Criteria criteria = session.getCurrentSession().createCriteria(DisLikeableReply.class);
		JoinColumn.addColumnByJoin("reply", criteria);
		JoinColumn.addColumnByJoin("author", criteria);
		criteria.add(Restrictions.eq("id", id));
		return (DisLikeableReply) criteria.list().get(0);
	}
	
	/**
	 * Get a like from the reply base on a person
	 * @param person The person to get the like from reply
	 * @return A like from the reply base on a person
	 */
	@SuppressWarnings("unchecked")
	@Override
	public LikeableReply getLikesReplyByPerson(Person person) {
		Criteria criteria = session.getCurrentSession().createCriteria(LikeableReply.class);
		JoinColumn.addColumnByJoin("reply", criteria);
		JoinColumn.addColumnByJoin("author", criteria);
		criteria.add(Restrictions.eq("author", person));
		List<LikeableReply> likes = (List<LikeableReply>) criteria.list();
		if(!likes.isEmpty()) {
			return likes.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public DisLikeableReply getDislikesByPerson(Person person) {
		Criteria criteria = session.getCurrentSession().createCriteria(DisLikeableReply.class);
		JoinColumn.addColumnByJoin("reply", criteria);
		JoinColumn.addColumnByJoin("author", criteria);
		criteria.add(Restrictions.eq("author", person));
		List<DisLikeableReply> likes = (List<DisLikeableReply>) criteria.list();
		if(!likes.isEmpty()) {
			return likes.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * Save a new like post
	 * @param like The new like post to be saved
	 */
	@Override
	public void saveLikePost(LikeablePost like) {
		session.getCurrentSession().save(like);
	}
	
	/**
	 * Save a new dislike post
	 * @param dislike The new dislike post to be saved
	 */
	@Override
	public void saveDislikePost(DisLikeablePost dislike) {
		session.getCurrentSession().save(dislike);
	}
	
	/**
	 * Save a new like reply
	 * @param like The new like reply to be saved
	 */
	@Override
	public void saveLikeReply(LikeableReply like) {
		session.getCurrentSession().save(like);
		
	}
	
	/**
	 * Save a new dislike reply
	 * @param dislike The new dislike reply to be saved
	 */
	@Override
	public void saveDislikeReply(DisLikeableReply dislike) {
		session.getCurrentSession().save(dislike);
	}

	/**
	 * Remove a like (Feature had not been implemented)
	 * @param post The post where the like associate with
	 * @param like The like to be deleted
	 */
	@Override
	public void removeLikePost(LikeablePost like) {
		session.getCurrentSession().delete(like);
	}
	
	/**
	 * Remove a dislike from the database
	 * @param dislike The dislike to be deleted
	 */
	@Override
	public void removeDislikePost(DisLikeablePost dislike) {
		session.getCurrentSession().delete(dislike);
	}
	
	/**
	 * Remove a like from reply
	 * @param likeable The like to be deleted
	 */
	@Override
	public void removeDislikeReply(DisLikeableReply dislikeable) {
		session.getCurrentSession().delete(dislikeable);
		
	}

	/**
	 * Remove a dislike from reply
	 * @param dislikeable The dislike to be deleted
	 */
	@Override
	public void removeLikeReply(LikeableReply likeable) {
		session.getCurrentSession().delete(likeable);
		
	}

}
