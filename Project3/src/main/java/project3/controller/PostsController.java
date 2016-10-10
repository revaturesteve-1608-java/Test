package project3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project3.dto.ForumPost;
import project3.dto.Person;
import project3.service.BusinessLogic;

@RestController
public class PostsController {

	@Autowired
	BusinessLogic service;
	
	@RequestMapping(value="/createPost", method=RequestMethod.POST)
	public void createPost(@RequestBody String[] postInfo){
		System.out.println("title: " + postInfo[0]);
		String title = postInfo[0];
		String content = postInfo[1];
		System.out.println("content: " + postInfo[1]);
		Person author = service.getPersonByUsername("123@email.com");
		System.out.println(author.getUsername());
		service.createForumPost(content, title, author, null);
	}
	
	@RequestMapping(value="/getPosts", method=RequestMethod.GET)
	public void getPosts(){
		List<ForumPost> posts = service.getAllPosts();
		System.out.println("id: " + posts.get(0).getId() + "\ttitle: " + posts.get(0).getTitle() + "\tcontent: " + posts.get(0).getContent());
		
	}
}
