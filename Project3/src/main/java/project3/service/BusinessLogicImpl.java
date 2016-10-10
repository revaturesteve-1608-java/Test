package project3.service;

import java.sql.Timestamp;
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
	SimpleDao dao;
	
	@Override
	public Person getPersonByUsername(String username){
		return dao.getPersonByUsername(username);
	}
	
	@Override
	@Transactional
	public void updateTempPerson(String username, String pass, String newUsername){
		dao.updateTempPerson(username, pass, newUsername);
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
}
