package project3.dao;

import java.sql.Timestamp;
import java.util.List;

import project3.dto.DisLikeableReply;
import project3.dto.ForumPost;
import project3.dto.LikeableReply;
import project3.dto.Person;
import project3.dto.PostReply;

public interface PostAndReplyDao {
	
	/**
	 * Get all the post with the replies
	 * @return A list of all the post
	 */
	public List<ForumPost> getAllPosts();
	
	/**
	 * Get all the replies from a post
	 * @param post The post where all the replies is
	 * @return A list of replies
	 */
	public List<PostReply> getRepliesByPost(ForumPost post);
	
	/**
	 * Get one post by id
	 * @param id The id of the post
	 * @return The post of the id
	 */
	public ForumPost getPostById(int id);
	
	/**
	 * Getting more posts for infinity scroll (Feature had not been implemented)
	 * @return A list of forumPost
	 */
	public List<ForumPost> getMorePosts();
	
	/**
	 * Getting more posts by the author for infinity scroll 
	 * (Feature had not been implemented)
	 * @return A list of forumPost
	 */
	public List<ForumPost> getMorePostsByAuthor(Person author);
	
	/**
	 * Get all the posts with category and sorted by time
	 * @return A list of posts with category
	 */
	public List<ForumPost> getPostsByCategory();
	
	/**
	 * Get all the post with category base on a person and sorted by time
	 * @param author The author of the post
	 * @return A list of post by an author
	 */
	public List<ForumPost> getPostsByCategoryProf(Person author);

	/**
	 * Get a post with the likes attach
	 * @param id The id of the post
	 * @return A post with likes
	 */
	public ForumPost getPostForLike(int id);
	
	/**
	 * Get a post with the dislikes attach
	 * @param id The id of the post
	 * @return A post with dislikes
	 */
	public ForumPost getPostForDislike(int id);
	
	/**
	 * Get a reply with likes attach
	 * @param id The id of the likes
	 * @return A reply with likes
	 */
	public PostReply getReplyForLike(int id);
	
	/**
	 * Get a reply with dislike attach
	 * @param id The id of the dislike
	 * @return A reply with dislike
	 */
	public PostReply getReplyForDislike(int id);

	/**
	 * Creating a forum post
	 * @param post The object post needed to be created
	 * @return The id of the post
	 */
	public int createForumPost(ForumPost post);
	
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
	public void createPostReply(int post, Person author, List<LikeableReply> likes, 
			List<DisLikeableReply> dislikes, boolean approval, String content, 
			Timestamp timestamp);
	
	/**
	 * Update the post in the database
	 * @param post The post that will be updated
	 */
	public void updatePost(ForumPost post);
	
	/**
	 * Update the reply in the database
	 * @param reply The reply that will be updated
	 */
	public void updateReply(PostReply reply);
	
	/**
	 * Delete the forum post
	 * @param id The id of the post
	 */
	public void deleteForumPost(int id);
	
	/**
	 * Delete the reply of the post (Feature had not been implemented)
	 * @param id The id of the reply
	 */
	public void deletePostReply(int id);


}
