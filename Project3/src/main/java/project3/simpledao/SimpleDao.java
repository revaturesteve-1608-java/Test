package project3.simpledao;

import java.sql.Timestamp;
import java.util.List;

import project3.dto.AwsKey;
import project3.dto.Complex;
import project3.dto.DisLikeablePost;
import project3.dto.DisLikeableReply;
import project3.dto.ForumCategory;
import project3.dto.ForumPost;
import project3.dto.LikeablePost;
import project3.dto.LikeableReply;
import project3.dto.Person;
import project3.dto.PostReply;
import project3.dto.Role;

public interface SimpleDao {
	
	public List<ForumPost> getAllPosts();
	
	public ForumPost getPostById(int id, boolean like, boolean dislike);
	
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
	
	public void createPostReply(int post, Person author, List<LikeableReply> likes, List<DisLikeableReply> dislikes, boolean approval, 
			String content, Timestamp timestamp);
	
	public void createComplex(String complexName);
	
	public void deleteForumPost(int id);
	
	public void deletePostReply(int id);
	
	public int createForumPost(ForumPost post);
	
	public AwsKey getAWSKey();
	
	public void updatePersonPic(Person person);
	
	public List<ForumPost> getMorePosts(int firstResult);
	
	public List<ForumPost> getPostsByCategory();
	
	public List<ForumPost> getPostsByCategoryProf(Person author);

	public void updatePost(ForumPost post);

	public void saveDislike(DisLikeablePost dislike);

	public ForumPost getPostForDislike(int id);

	public ForumPost getPostForLike(int id);

	public void saveLike(LikeablePost like);
	
	public List<ForumPost> getMorePostsByUsername(int firstResult, Person author);

	public void removeLike(ForumPost post, LikeablePost like);
	
	public LikeablePost getLikesByPerson(Person person, int i);

	public LikeablePost getLikeById(int id);

	public DisLikeablePost getDislikesById(int id);

	public void removeDislike(DisLikeablePost dislike);
	
	public List<PostReply> getRepliesByPost(ForumPost post);
	
	public ForumCategory getCategoryByName(String catName);

	public PostReply getReplyForDislike(int id);

	public void saveDislikeReply(DisLikeableReply dislike);

	public void updateReply(PostReply reply);

	public PostReply getReplyForLike(int id);

	public DisLikeableReply getDislikesReplyById(int id);

	public void removeDislikeReply(DisLikeableReply dislikeable);

	public LikeableReply getLikesReplyById(int id);

	public void removeLikeReply(LikeableReply likeable);

	public void saveLikeReply(LikeableReply like);

	public LikeableReply getLikesReplyByPerson(Person person);

	public DisLikeableReply getDislikesByPerson(Person person);
	
	public Complex getComplexByName(String name);
	
	public List<Complex> getComplex();
}
