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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import project3.dto.DisLikeablePost;
import project3.dto.ForumPost;
import project3.dto.LikeablePost;
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
		List<ForumPost> posts = service.getMorePosts(0);
//		System.out.println("id: " + posts.get(0).getId() + "\ttitle: " + posts.get(0).getTitle() + "\tcontent: " + posts.get(0).getContent());
//		System.out.println("author: " + posts.get(0).getAuthor().getRole().getRoleName());
		List<PostContainer> allPosts = new ArrayList<>();
		for(ForumPost post: posts){
			List<String> postContent = new ArrayList<>();
			for(PostReply reply: post.getReplys())
				postContent.add(reply.getContent());
			System.out.println("postId: " + post.getId() + "\tpostContent: " + postContent);
			PostContainer p = new PostContainer(post.getAuthor().getUsername(), post.getTitle(), post.getContent(), post.getId(), postContent);
			allPosts.add(p);
		//	System.out.println(p.getPostContent());
		}
		return new ResponseEntity<List<PostContainer>>(allPosts, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getMorePosts", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PostContainer>> getMorePosts(@RequestBody String[] getPostResult){
		int firstResult = Integer.parseInt(getPostResult[0]);
		String username = getPostResult[1];
		List<ForumPost> posts = service.getMorePosts(firstResult);
//		System.out.println("id: " + posts.get(0).getId() + "\ttitle: " + posts.get(0).getTitle() + "\tcontent: " + posts.get(0).getContent());
//		System.out.println("author: " + posts.gset(0).getAuthor().getRole().getRoleName());
		List<PostContainer> allPosts = new ArrayList<>();
		for(ForumPost post: posts){
			List<String> postContent = new ArrayList<>();
			for(PostReply reply: post.getReplys())
				postContent.add(reply.getContent());
			System.out.println("postId: " + post.getId() + "\tpostContent: " + postContent);
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
		System.out.println("post id in the controller: " + postId);
		service.createReply(replyContent, postId, username);
	}

	@RequestMapping(value="/getPostById", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PostContainer> getPost(@RequestParam("id") int id){
		
		System.out.println("id is  " + id);
		
		ForumPost post = service.getPostById(id, false, false);
		//System.out.println(forumPost.toString());
		//System.out.println(post.toString());
		List<String> content = new ArrayList<>();
		for(PostReply reply: post.getReplys())
			content.add(reply.getContent());
		PostContainer pos = new PostContainer(post.getAuthor().getUsername(), post.getTitle(), post.getContent(), post.getId(), content);
		//ForumPost forumPost = new ForumPost();
		//PostContainer pos = new PostContainer();
		return new ResponseEntity<PostContainer>(pos, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dislike", method=RequestMethod.POST)
	@ResponseBody
	public String dislikePost(@RequestParam("username") String username, @RequestParam("id") int id) {
		
		System.out.println(username + "    " + id);
		ForumPost post = service.getPostForDislike(id);
		Person person = service.getPersonByUsername(username);
		service.addDislike(post, person);
		
		
		
		return "yo";
	}
	
	@RequestMapping(value="/like", method=RequestMethod.POST)
	@ResponseBody
	public String likePost(@RequestParam("username") String username, @RequestParam("id") int id) {
		
		System.out.println(username + "    " + id);
		ForumPost post = service.getPostForLike(id);
		Person person = service.getPersonByUsername(username);
		service.addLike(post, person);
		
		
		
		return "ok";
	}
	
	@RequestMapping(value="/getDislike", method=RequestMethod.POST)
	@ResponseBody
	public String getAllDislikes(@RequestParam("id") int id) {
		
		ForumPost post = service.getPostForDislike(id);
		List<DisLikeablePost> dislikes = service.getAllDislikebyPost(post);
		System.out.println("-----------------------------------------------here");
		return Integer.toString(dislikes.size());
	}
	
	
	@RequestMapping(value="/getLike", method=RequestMethod.POST)
	@ResponseBody
	public String getAllLikes(@RequestParam("id") int id) {
		
		ForumPost post = service.getPostForLike(id);
		List<LikeablePost> likes = service.getAllLikesbyPost(post);
		System.out.println("-----------------------------------------------here");
		return Integer.toString(likes.size());
	}
}
