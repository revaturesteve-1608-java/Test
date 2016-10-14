package project3.simpledao;

import java.sql.Timestamp;
import java.util.List;

import project3.dto.AwsKey;
import project3.dto.Complex;
import project3.dto.DisLikeablePost;
import project3.dto.ForumCategory;
import project3.dto.ForumPost;
import project3.dto.LikeablePost;
import project3.dto.Person;
import project3.dto.Role;

public interface SimpleDao {
	
	public List<ForumPost> getAllPosts();
	
	public ForumPost getPostById(int id);
	
	public Person getPersonById(int id);
	
	public Person getPersonByEmail(String email);

	public Person getPersonByUsername(String username);
	
	public ForumCategory getForumCategoryById(int id);
	
	public List<Role> getRoles();
	
	public List<ForumCategory> getForumCategory();
	
	public void updateTempPerson(String username, String pass, String newUsername);
	
	public void updateUserInfo(Person person);
	
	public void createPerson(String first_name, String last_name, String username, String password, String email, Role role,
			String profilePic, Complex complex, String phoneNumber, String bio, String unviersity, boolean vaildated,
			String linkedin);
	
	public void createForumCategory(String categoryName);
	
	public Person createUser(Person person);
	
	public void createPostReply(ForumPost post, Person author, int likes, int dislikes, boolean approval, 
			String content, Timestamp timestamp);
	
	public void createComplex(String complexName);
	
	public void deleteForumPost(int id);
	
	public void deletePostReply(int id);
	
	public int createForumPost(ForumPost post);
	
	public AwsKey getAWSKey();
	
	public void updatePersonPic(Person person);
	
	public void addDislike(ForumPost post, DisLikeablePost dislike);

	public void addLike(ForumPost post, LikeablePost like);

	public void saveDislike(DisLikeablePost dislike);
}
