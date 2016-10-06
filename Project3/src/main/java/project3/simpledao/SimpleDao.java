package project3.simpledao;

import java.sql.Timestamp;
import java.util.List;

import project3.dto.Complex;
import project3.dto.ForumPost;
import project3.dto.Person;
import project3.dto.Role;

public interface SimpleDao {
	
	public List<ForumPost> getAllPosts();
	
	public ForumPost getPostById(int id);
	
	public Person getPersonById(int id);
	
	public Person getPersonByEmail(String email);
	
	public void createPerson(String first_name, String last_name, String username, String password, String email, Role role,
			byte[] profilePic, Complex complex, String phoneNumber, String bio, String unviersity, boolean vaildated,
			String linkedin);
	
	public void createForumCategory(String categoryName);
	
	public void createUser(Person person);
	
	public void createPostReply(ForumPost post, int likes, int dislikes, boolean approval, 
			String content, Timestamp timestamp);
	
	public void createComplex(String complexName);
	
	public void deleteForumPost(int id);
	
	public void deletePostReply(int id);
}
