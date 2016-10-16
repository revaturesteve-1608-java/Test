package project3.service;

import java.util.List;

import project3.dto.DisLikeablePost;
import project3.dto.ForumCategory;
import project3.dto.ForumPost;
import project3.dto.LikeablePost;
import project3.dto.Person;
import project3.dto.PostReply;

public interface BusinessLogic {
	
	public boolean checkUserPassword(String username, String password, String curpassword);
	
	public Person getPersonById(int id);
	
	public Person getPersonByUsername(String username);
	
	public String updateTempPerson(String username, String pass, String oldPass, String newUsername);
	
	public String updateUserInfo(Person person, String oldPassword, String newPassword, String username, String newEmail, 
			String newPhone, String newUniversity, String newLinkedIn);
	
	public int createForumPost(String content, String title, Person author, List<ForumCategory> categories);
	
	public List<ForumPost> getAllPosts();
	
	public void createReply(String replyContent, int postId, String username);
	
	public ForumPost getPostById(int id, boolean like, boolean dislike);
	
	public List<ForumPost> getMorePosts(int firstResult);
	
	public void addDislike(ForumPost post, Person person);
	
	public List<DisLikeablePost> getAllDislikebyPost(ForumPost post);

	public void addLike(ForumPost post, Person person);
	
	public void checkForLike(ForumPost post, Person person);

	public ForumPost getPostForDislike(int id);

	public ForumPost getPostForLike(int id);

	public List<LikeablePost> getAllLikesbyPost(ForumPost post);

	void checkForDislike(ForumPost post, Person person);
	
	public List<PostReply> getRepliesByPost(ForumPost post);
}
