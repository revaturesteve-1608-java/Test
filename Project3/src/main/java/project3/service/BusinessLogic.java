package project3.service;

import java.util.List;

import project3.dto.ForumCategory;
import project3.dto.ForumPost;
import project3.dto.Person;

public interface BusinessLogic {
	
	public Person getPersonByUsername(String username);
	
	public String updateTempPerson(String username, String pass, String oldPass, String newUsername);
	
	public void updateUserInfo(String currentUser, String newPassword, String username, String newEmail, 
			String newPhone, String newUniversity, String newLinkedIn);
	
	public int createForumPost(String content, String title, Person author, List<ForumCategory> categories);
	
	public List<ForumPost> getAllPosts();
	
	public void createReply(String replyContent, int postId);
}
