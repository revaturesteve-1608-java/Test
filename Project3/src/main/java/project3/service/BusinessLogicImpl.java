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
import project3.dto.PostReply;
import project3.simpledao.SimpleDao;
import project3.util.GetTimestamp;

@Service
public class BusinessLogicImpl implements BusinessLogic {

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
	public Person getPersonByUsername(String username) {
		return dao.getPersonByUsername(username);
	}

	@Override
	@Transactional
	public String updateTempPerson(String username, String pass, String oldPass, String newUsername) {
		Person person = dao.getPersonByUsername(username);
		// System.out.println("ere" + username);
		Person checkUsername = dao.getPersonByUsername(newUsername);
		if (checkUsername == null) {
			if (crypt.validate(oldPass, person.getPassword())) {
				String encryptPass = crypt.encrypt(pass);
				dao.updateTempPerson(username, encryptPass, newUsername);
				return "[\"Updated\"]";
			} else {
				return "[\"Inputed Wrong Password\"]";
			}
		} else {
			return "[\"Username Already Exist\"]";
		}

	}

	@Override
	@Transactional
	public String updateUserInfo(Person person, String oldPassword, String newPassword, String username,
			String newEmail, String newPhone, String newUniversity, String newLinkedIn) {
		// password
		if (oldPassword != null && newPassword != null && !("".equals(newPassword) && !("".equals(newPassword)))) {
			if (checkUserPassword(person.getUsername(), oldPassword, person.getPassword())) {
				person.setPassword(crypt.encrypt(newPassword));
			} else {
				return "[\"Inputed Wrong Password\"]";
			}
		}
		// username
		if (username != null && !("".equals(username))) {
			Person checkUsername = dao.getPersonByUsername(username);
			if (checkUsername == null) {
				person.setUsername(username);
			} else {
				return "[\"Username Already Exist\"]";
			}
		}
		// email
		if (newEmail != null && !("".equals(newEmail))) {
			person.setEmail(newEmail);
		}
		// phone
		if (newPhone == null || !("".equals(newPhone))) {
			person.setPhoneNumber(newPhone);
		}
		// university
		if (newUniversity == null || !("".equals(newUniversity))) {
			person.setUniversity(newUniversity);
		}
		// Linkedin
		if (newLinkedIn == null || !("".equals(newLinkedIn))) {
			person.setLinkedin(newLinkedIn);
		}
		dao.updateUserInfo(person);
		return "[\"Updated\"]";
	}

	@Override
	public int createForumPost(String content, String title, Person author, List<ForumCategory> categories) {
		// TODO Auto-generated method stub
		ForumPost post = new ForumPost(author, title, content, GetTimestamp.getCurrentTime(), false);
		// post.setCategory(categories);
		return dao.createForumPost(post);
	}

	@Override
	public List<ForumPost> getAllPosts() {
		return getRidOfDupes(dao.getAllPosts());
	}

	@Override
	public void createReply(String replyContent, int postId, String username) {
		// TODO Auto-generated method stub
		// ForumPost post = dao.getPostById(postId);
		// System.out.println("postId in the service method: " + post.getId());
		Person author = dao.getPersonByUsername(username);
		dao.createPostReply(postId, author, new ArrayList<LikeableReply>(), new ArrayList<DisLikeableReply>(), false,
				replyContent, GetTimestamp.getCurrentTime());
	}

	private List<ForumPost> getRidOfDupes(List<ForumPost> posts) {
		List<ForumPost> filteredList = new ArrayList<>();
		for (ForumPost post : posts) {
			if (!filteredList.contains(post))
				filteredList.add(post);
		}
		return filteredList;
	}

	@Override
	@Transactional
	public ForumPost getPostById(int id, boolean like, boolean dislike) {
		return dao.getPostById(id, like, dislike);
	}

	@Override
	public List<ForumPost> getMorePosts(int firstResult) {
		// TODO Auto-generated method stub
		return getRidOfDupes(dao.getMorePosts(firstResult));
	}

	@Transactional
	@Override
	public void addDislike(ForumPost post, Person person) {

		DisLikeablePost dislike = new DisLikeablePost(person, post);

		boolean exist = false;

		for (DisLikeablePost dis : post.getDislikes()) {
			if (dis.getAuthor().getUsername().equals(person.getUsername())) {
				exist = true;
			}
		}

		System.out.println("in add dislike part 2");
		if (exist) {
			System.out.println("in add dislike part 3");
		} else {
			dao.saveDislike(dislike);
			post.getDislikes().add(dislike);
			System.out.println(post.getDislikes().size());
			dao.updatePost(post);
		}

	}

	@Transactional
	@Override
	public void checkForLike(ForumPost post, Person person) {
		// LikeablePost likeable = null;

		System.out.println("in check like");
		// likeable = dao.getLikesByPerson(person);
		for (LikeablePost like : post.getLikes()) {
			if (like.getAuthor().getUsername().equals(person.getUsername())) {
				LikeablePost likeable = dao.getLikesByPerson(person, like.getId());
				// LikeablePost likes = dao.getLikeById(like.getId());
				// post.getLikes().remove(likeable);
				// dao.updatePost(post);
				dao.removeLike(post, likeable);
				// System.out.println(likeable.toString());
				// dao.removeLike(post, likeable);

			}
		}

	}

	@Transactional
	@Override
	public void checkForDislike(ForumPost post, Person person) {
		// LikeablePost likeable = null;

		System.out.println("in check like");
		// likeable = dao.getLikesByPerson(person);
		for (DisLikeablePost dislike : post.getDislikes()) {
			if (dislike.getAuthor().getUsername().equals(person.getUsername())) {
				DisLikeablePost dislikeable = dao.getDislikesById(dislike.getId());
				// LikeablePost likes = dao.getLikeById(like.getId());
				// post.getLikes().remove(likeable);
				// dao.updatePost(post);
				dao.removeDislike(dislikeable);
				// System.out.println(likeable.toString());
				// dao.removeLike(post, likeable);

			}
		}

	}

	@Override
	@Transactional
	public List<DisLikeablePost> getAllDislikebyPost(ForumPost post) {

		return post.getDislikes();
	}

	@Override
	@Transactional
	public void addLike(ForumPost post, Person person) {

		LikeablePost like = new LikeablePost(person, post);

		boolean exist = false;

		for (LikeablePost likeable : post.getLikes()) {
			if (likeable.getAuthor().getUsername().equals(person.getUsername())) {
				exist = true;
			}
		}

		if (exist) {

		} else {
			dao.saveLike(like);
			post.getLikes().add(like);
			dao.updatePost(post);
		}
	}

	@Override
	public ForumPost getPostForDislike(int id) {
		return dao.getPostForDislike(id);
	}

	@Override
	public ForumPost getPostForLike(int id) {
		return dao.getPostForLike(id);
	}

	@Override
	public List<LikeablePost> getAllLikesbyPost(ForumPost post) {
		return post.getLikes();
	}

	@Override
	public List<PostReply> getRepliesByPost(ForumPost post) {
		return dao.getRepliesByPost(post);
	}

}
