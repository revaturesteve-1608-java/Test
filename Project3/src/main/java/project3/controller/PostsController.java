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
import project3.dto.PostReply;
import project3.objectcontainer.PostContainer;
import project3.service.BusinessLogic;

@RestController
public class PostsController {

	@Autowired
	BusinessLogic service;
	
	@RequestMapping(value="/createPost", method=RequestMethod.POST)
	public ResponseEntity<Integer> createPost(@RequestBody String[] postInfo){
		System.out.println("title: " + postInfo[0]);
		String title = postInfo[0];
		String content = postInfo[1];
		String username = postInfo[2];
		System.out.println("content: " + postInfo[1]);
		System.out.println("username: " + postInfo[2]);
		Person author = service.getPersonByUsername(username);
		System.out.println(author.getUsername());
		int postId = service.createForumPost(content, title, author, null);
		return new ResponseEntity<Integer>(postId, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getPosts", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PostContainer>> getPosts(){
		List<ForumPost> posts = service.getAllPosts();
//		System.out.println("id: " + posts.get(0).getId() + "\ttitle: " + posts.get(0).getTitle() + "\tcontent: " + posts.get(0).getContent());
//		System.out.println("author: " + posts.get(0).getAuthor().getRole().getRoleName());
		List<PostContainer> allPosts = new ArrayList<>();
		for(ForumPost post: posts){
			List<String> postContent = new ArrayList<>();
			for(PostReply reply: post.getReplys())
				postContent.add(reply.getContent());
			PostContainer p = new PostContainer(post.getAuthor().getUsername(), post.getTitle(), post.getContent(), post.getId(), postContent);
			allPosts.add(p);
			System.out.println(p.getPostContent());
		}
		return new ResponseEntity<List<PostContainer>>(allPosts, HttpStatus.OK);
	}
	
	@RequestMapping(value="/createReply", method=RequestMethod.POST)
	public void createReply(@RequestBody String[] postInfo){
		System.out.println("GOT INTO CREATEREPLY");
		System.out.println(postInfo[0]);
		System.out.println(postInfo[1]);
		String replyContent = postInfo[0];
		String username = postInfo[2];
		System.out.println("third param: " + postInfo[2]);
		int postId = Integer.parseInt(postInfo[1]);
		service.createReply(replyContent, postId, username);
		
//		List<ForumPost> posts = service.getAllPosts();
//		System.out.println("id: " + posts.get(0).getId() + "\ttitle: " + posts.get(0).getTitle() + "\tcontent: " + posts.get(0).getContent());
//		System.out.println("author: " + posts.get(0).getAuthor().getRole().getRoleName());
//		List<PostContainer> allPosts = new ArrayList<>();
//		for(ForumPost post: posts){
//			PostContainer p = new PostContainer(post.getAuthor().getUsername(), post.getTitle(), post.getContent(), post.getId());
//			allPosts.add(p);
//			System.out.println(p.getPostContent());
//		new ResponseEntity<List<PostContainer>>(allPosts, HttpStatus.OK)
//		}
	}
}
