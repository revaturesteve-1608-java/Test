package project3.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project3.dto.ForumCategory;
import project3.dto.ForumPost;
import project3.dto.Person;
import project3.simpledao.SimpleDao;
import project3.util.GetTimestamp;

@Service
public class BusinessLogicImpl implements BusinessLogic{
	
	@Autowired
	Crypt crypt;
	
	@Autowired
	SimpleDao dao;
	
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
	public void updateUserInfo(String currentUser, String newPassword, String username, String newEmail, 
			String newPhone, String newUniversity, String newLinkedIn){
		dao.updateUserInfo(currentUser, newPassword, username, newEmail, newPhone, newUniversity, newLinkedIn);
	}

	@Override
	public void createForumPost(String content, String title, Person author, List<ForumCategory> categories) {
		// TODO Auto-generated method stub
		ForumPost post = new ForumPost(author, title, content, GetTimestamp.getCurrentTime(), false);
//		post.setCategory(categories);
		dao.createForumPost(post);
	}

	@Override
	public List<ForumPost> getAllPosts() {
		// TODO Auto-generated method stub
		return dao.getAllPosts();
	}
}
