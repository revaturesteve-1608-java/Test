package project3.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project3.dto.DisLikeableReply;
import project3.dto.ForumCategory;
import project3.dto.ForumPost;
import project3.dto.LikeableReply;
import project3.dto.DisLikeablePost;
import project3.dto.ForumCategory;
import project3.dto.ForumPost;
import project3.dto.LikeablePost;
import project3.dto.Person;
import project3.simpledao.SimpleDao;
import project3.util.GetTimestamp;

@Service
public class BusinessLogicImpl implements BusinessLogic{
	
	@Autowired
	Crypt crypt;
	
	@Autowired
	SimpleDao dao;
	
	public boolean checkUserPassword(String username, String password, String curpassword) {
		return crypt.validate(password, curpassword);
	}
	
	@Override
	public Person getPersonById(int id) {
		return dao.getPersonById(id);
	}
	
	@Override
	public Person getPersonByUsername(String username){
		return dao.getPersonByUsername(username);
	}
	
	@Override
	@Transactional
	public String updateTempPerson(String username, String pass, String oldPass, String newUsername){
		Person person = dao.getPersonByUsername(username);
//		System.out.println("ere" + username);
		Person checkUsername = dao.getPersonByUsername(newUsername);
		if(checkUsername == null) {
			if(crypt.validate(oldPass, person.getPassword())){
				String encryptPass = crypt.encrypt(pass);
				dao.updateTempPerson(username, encryptPass, newUsername);
				return "[\"Updated\"]";
			} else{
				return "[\"Inputed Wrong Password\"]";
			}
		} else {
			return "[\"Username Already Exist\"]";
		}
		
	}
	
	@Override
	@Transactional
	public String updateUserInfo(Person person, String oldPassword, String newPassword, String username, String newEmail, 
			String newPhone, String newUniversity, String newLinkedIn){
		//password
		if(oldPassword != null && newPassword != null && !("".equals(newPassword)
				&& !("".equals(newPassword)))) {
			if(checkUserPassword(
					person.getUsername(), oldPassword, person.getPassword())) {
				person.setPassword(crypt.encrypt(newPassword));
			} else {
				return "[\"Inputed Wrong Password\"]";
			}
		}
		//username
		if(username!=null && !("".equals(username))){
			Person checkUsername = dao.getPersonByUsername(username);
			if(checkUsername == null) {
				person.setUsername(username);
			} else {
				return "[\"Username Already Exist\"]";
			}
		}
		//email
		if(newEmail!=null && !("".equals(newEmail))){
			person.setEmail(newEmail);
		}
		//phone
		if(newPhone==null ||!("".equals(newPhone))) {
			person.setPhoneNumber(newPhone);
		}
		//university
		if(newUniversity==null || !("".equals(newUniversity))){
			person.setUniversity(newUniversity);
		}
		//Linkedin
		if(newLinkedIn==null || !("".equals(newLinkedIn))){
			person.setLinkedin(newLinkedIn);
		}
		dao.updateUserInfo(person);
		return "[\"Updated\"]";
	}

	@Override
	public int createForumPost(String content, String title, Person author, List<ForumCategory> categories) {
		// TODO Auto-generated method stub
		ForumPost post = new ForumPost(author, title, content, GetTimestamp.getCurrentTime(), false);
//		post.setCategory(categories);
		return dao.createForumPost(post);
	}

	@Override
	public List<ForumPost> getAllPosts() {
		return getRidOfDupes(dao.getAllPosts());
	}

	@Override
	public void createReply(String replyContent, int postId, String username) {
		// TODO Auto-generated method stub
//		ForumPost post = dao.getPostById(postId);
//		System.out.println("postId in the service method: " + post.getId());
		Person author = dao.getPersonByUsername(username);
		dao.createPostReply(postId, author, new ArrayList<LikeableReply>(), new ArrayList<DisLikeableReply>(), false, replyContent, GetTimestamp.getCurrentTime());
	}
	
	private List<ForumPost> getRidOfDupes(List<ForumPost> posts){
		List<ForumPost> filteredList = new ArrayList<>();
		for (ForumPost post: posts) {
			if (!filteredList.contains(post))
				filteredList.add(post);
		}
		return filteredList;
	}

	@Override
	@Transactional
	public ForumPost getPostById(int id) {
		return dao.getPostById(id);
	}

	@Override
	public List<ForumPost> getMorePosts(int firstResult) {
		// TODO Auto-generated method stub
		return getRidOfDupes(dao.getMorePosts(firstResult));
		}
	@Transactional
	public void addDislike(ForumPost post, Person person) {
		
		System.out.println(post.getDislikes().size());
		System.out.println(" old--------------------------- ");
		DisLikeablePost dislike = new DisLikeablePost(person, post);
		dao.saveDislike(dislike);
		System.out.println(" new -----------------------------------  " + dislike.getId() );
		post.getDislikes().add(dislike);
		System.out.println(post.getDislikes().size());
		dao.addDislike(post, dislike);
		
	}

	@Override
	@Transactional
	public List<DisLikeablePost> getAllDislikebyPost(ForumPost post) {
		
		return post.getDislikes();
	}

	@Override
	@Transactional
	public void addLike(ForumPost post, Person person) {
		
		//LikeablePost like = new LikeablePost(person);
	/*	post.getLikes().add(like);
		dao.addLike(post, like);*/
	}
	
}
