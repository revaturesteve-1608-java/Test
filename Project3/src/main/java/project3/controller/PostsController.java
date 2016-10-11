package project3.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project3.dto.ForumPost;
import project3.dto.Person;
import project3.objectcontainer.PostContainer;
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
	
	@RequestMapping(value="/getPosts", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PostContainer>> getPosts(){
		List<ForumPost> posts = service.getAllPosts();
		System.out.println("id: " + posts.get(0).getId() + "\ttitle: " + posts.get(0).getTitle() + "\tcontent: " + posts.get(0).getContent());
		System.out.println("author: " + posts.get(0).getAuthor().getRole().getRoleName());
		List<PostContainer> allPosts = new ArrayList<>();
		for(ForumPost post: posts){
			PostContainer p = new PostContainer(post.getAuthor().getUsername(), post.getTitle(), post.getContent(), post.getId());
			allPosts.add(p);
		}
		return new ResponseEntity<List<PostContainer>>(allPosts, HttpStatus.OK);
	}
}
