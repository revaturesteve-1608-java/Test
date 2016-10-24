package com.revpages.dao;

import com.revpages.dto.DisLikeablePost;
import com.revpages.dto.DisLikeableReply;
import com.revpages.dto.LikeablePost;
import com.revpages.dto.LikeableReply;
import com.revpages.dto.Person;

/**
 * Access the database for like and dislike
 */
public interface LikeAndDislikeDao {
	
	/**
	 * Get a like from the post by id
	 * @param id The id of the like
	 * @return A like from the post
	 */
	public LikeablePost getLikesById(int id);

	/**
	 * Get the dislike of a post by id with post and author it associate with
	 * @param id The id of the dislike
	 * @return The dislike of the post
	 */
	public DisLikeablePost getDislikesPostById(int id);
	
	/**
	 * Get a like from the reply base on id
	 * @param id The id of the like
	 * @return A like from a reply
	 */
	public LikeableReply getLikesReplyById(int id);	
	
	/**
	 * Get a dislike from the reply base on id
	 * @param id The id of the dislike
	 * @return A dislike from a reply
	 */
	public DisLikeableReply getDislikesReplyById(int id);
	
	/**
	 * Get a like from the reply base on a person
	 * (Feature had not been implemented)
	 * @param person The person to get the like from reply
	 * @return A like from the reply base on a person
	 */
	public LikeableReply getLikesReplyByPerson(Person person);

	/**
	 * Get a dislike from the reply base on a person
	 * (Feature had not been implemented)
	 * @param person The person to get the dislike from reply
	 * @return A dislike from the reply base on a person
	 */
	public DisLikeableReply getDislikesByPerson(Person person);
	
	/**
	 * Save a new like post
	 * @param like The new like post to be saved
	 */
	public void saveLikePost(LikeablePost like);
	
	/**
	 * Save a new dislike post
	 * @param dislike The new dislike post to be saved
	 */
	public void saveDislikePost(DisLikeablePost dislike);
	
	/**
	 * Save a new like reply
	 * @param like The new like reply to be saved
	 */
	public void saveLikeReply(LikeableReply like);
	
	/**
	 * Save a new dislike reply
	 * @param dislike The new dislike reply to be saved
	 */
	public void saveDislikeReply(DisLikeableReply dislike);

	/**
	 * Remove a like from post
	 * @param like The like to be deleted
	 */
	public void removeLikePost(LikeablePost like);
	
	/**
	 * Remove a dislike from post
	 * @param dislike The dislike to be deleted
	 */
	public void removeDislikePost(DisLikeablePost dislike);
	
	/**
	 * Remove a like from reply
	 * @param likeable The like to be deleted
	 */
	public void removeLikeReply(LikeableReply likeable);
	
	/**
	 * Remove a dislike from reply
	 * @param dislikeable The dislike to be deleted
	 */
	public void removeDislikeReply(DisLikeableReply dislikeable);

}
