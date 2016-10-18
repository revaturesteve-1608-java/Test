package project3.controller;

import java.sql.Date;
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
import project3.dto.ForumCategory;
import project3.dto.DisLikeableReply;
import project3.dto.ForumPost;
import project3.dto.LikeablePost;
import project3.dto.LikeableReply;
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
		String category = postInfo[3];
		System.out.println("contentuksahdaiushdaiadasds: " + postInfo[3]);
		List<ForumCategory> catList = new ArrayList<>();
		catList.add(service.getCategoryByName(category));
		
		System.out.println("username: " + postInfo[2]);
		Person author = service.getPersonByUsername(username);
		System.out.println(author.getUsername());
		int postId = service.createForumPost(content, title, author, catList);
		return new ResponseEntity<Integer>(postId, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getPosts", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PostContainer>> getPosts(){
		List<ForumPost> posts = service.getMorePosts(0);
//		System.out.println("id: " + posts.get(0).getId() + "\ttitle: " + posts.get(0).getTitle() + "\tcontent: " + posts.get(0).getContent());
//		System.out.println("author: " + posts.get(0).getAuthor().getRole().getRoleName());
		List<PostContainer> allPosts = new ArrayList<>();
		
		for(ForumPost post: posts){
			List<List<String>> postContent = new ArrayList<>();
			List<PostReply> replys = service.getRepliesByPost(post);
			for(PostReply reply: replys) {
				List<String> replies = new ArrayList<>();
				replies.add(reply.getContent());
				replies.add(reply.getAuthor().getUsername());
				replies.add(reply.getTimestamp().toString());
				postContent.add(replies);
			}
			
			
			System.out.println("postId: " + post.getId() + "\tpostContent: " + postContent);
			PostContainer p = new PostContainer(post.getAuthor().getUsername(), post.getTitle(), post.getContent(), post.getId(), post.getAuthor().getProfilePic(), postContent);
			allPosts.add(p);
			
		//	System.out.println(p.getPostContent());
		}
		return new ResponseEntity<List<PostContainer>>(allPosts, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getPostsByUsername", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PostContainer>> getPostsByUsername(@RequestBody String username){
		System.out.println("username in the controller: " + username);
		List<ForumPost> posts = service.getPostsByUsername(0, username);
		List<PostContainer> allPosts = new ArrayList<>();
		for(ForumPost post: posts){
			List<PostReply> replys = service.getRepliesByPost(post);
			List<List<String>> postContent = new ArrayList<>();
			for(PostReply reply: replys){
				List<String> replies = new ArrayList<>();
				replies.add(reply.getContent());
				replies.add(reply.getAuthor().getUsername());
				Date day = new Date(reply.getTimestamp().getTime());
				replies.add(day.toString());
				postContent.add(replies);
			}
			System.out.println("postId: " + post.getId() + "\tpostContent: " + postContent);
			PostContainer p = new PostContainer(post.getAuthor().getUsername(), post.getTitle(), post.getContent(), post.getId(), post.getAuthor().getProfilePic(), postContent);
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
			List<List<String>> postContent = new ArrayList<>();
			for(PostReply reply: post.getReplys()) {
				List<String> replies = new ArrayList<>();
				replies.add(reply.getContent());
				replies.add(reply.getAuthor().getUsername());
				replies.add(reply.getTimestamp().toString());
				postContent.add(replies);
			}
			
			System.out.println("postId: " + post.getId() + "\tpostContent: " + postContent);
			PostContainer p = new PostContainer(post.getAuthor().getUsername(), post.getTitle(), post.getContent(), post.getId(), post.getAuthor().getProfilePic(),  postContent);
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

		
//		List<ForumPost> posts = service.getAllPosts();
//		System.out.println("id: " + posts.get(0).getId() + "\ttitle: " + posts.get(0).getTitle() + "\tcontent: " + posts.get(0).getContent());
//		System.out.println("author: " + posts.get(0).getAuthor().getRole().getRoleName());
//		List<PostContainer> allPosts = new ArrayList<>();
//		for(ForumPost post: posts){
//			PostContainer p = new PostContainer(post.getAuthor().getUsername(), post.getTitle(), post.getContent(), post.getId());
//			allPosts.add(p);
//			System.out.println(p.getPostContent());
//		new ResponseEntity<List<PostContainer>>(allPosts, HttpStatus.OK)
		


	@RequestMapping(value="/getPostById", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PostContainer> getPost(@RequestParam("id") int id){
		
		System.out.println("id is  " + id);
		
		ForumPost post = service.getPostById(id, false, false);
		//System.out.println(forumPost.toString());

		//System.out.println(post.toString());
		List<List<String>> content = new ArrayList<>();
		
		List<PostReply> replys = service.getRepliesByPost(post);
		System.out.println("here 1st----------");
		for(PostReply reply: replys) {
			List<String> replies = new ArrayList<>();
			replies.add(reply.getContent());
			replies.add(reply.getAuthor().getUsername());
			Date day = new Date(reply.getTimestamp().getTime());
			replies.add(day.toString());
			replies.add(reply.getId() + "");
			content.add(replies);
		}
		System.out.println("here end reply=================");
		Date day = new Date(post.getTimestamp().getTime());
		PostContainer pos = new PostContainer(post.getAuthor().getUsername(), post.getTitle(), post.getContent(), post.getId(), post.getAuthor().getProfilePic(),  content, day.toString());
		//ForumPost forumPost = new ForumPost();
		//PostContainer pos = new PostContainer();
		System.out.println("here 1");
		return new ResponseEntity<PostContainer>(pos, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dislike", method=RequestMethod.POST)
	@ResponseBody
	public String dislikePost(@RequestParam("username") String username, @RequestParam("id") int id) {
		
		System.out.println(username + "    " + id);
		ForumPost post = service.getPostForDislike(id);
		Person person = service.getPersonByUsername(username);
		ForumPost postLike = service.getPostForLike(id);
		service.addDislike(post, person);
		service.checkForLike(postLike, person);
		List<DisLikeablePost> dislikes = service.getAllDislikebyPost(post);
		
		return Integer.toString(dislikes.size());
	}
	
	@RequestMapping(value="/dislikeReply", method=RequestMethod.POST)
	@ResponseBody
	public String dislikeReply(@RequestParam("username") String username, @RequestParam("id") int id) {
		
		System.out.println(username + "    " + id);
		PostReply reply = service.getReplyForDislike(id);
		Person person = service.getPersonByUsername(username);
		PostReply replyLike = service.getReplyForLike(id);
		service.addDislikeReply(reply, person);
		service.checkReplyLike(replyLike, person);
		List<DisLikeableReply> dislikes = service.getAllDislikebyReply(reply);
		
		return Integer.toString(dislikes.size());
	}
	
	@RequestMapping(value="/likeReply", method=RequestMethod.POST)
	@ResponseBody
	public String likeReply(@RequestParam("username") String username, @RequestParam("id") int id) {
		
		System.out.println(username + "    " + id);
		PostReply reply = service.getReplyForLike(id);
		Person person = service.getPersonByUsername(username);
		System.out.println("------------------before  getting dislike------------------------");
		PostReply disLikeReply = service.getReplyForDislike(id);
		System.out.println("------------------before adding reply------------------------");
		service.addlikeReply(reply, person);
		System.out.println("------------------before removing dislike------------------------");
		service.checkReplyDislike(disLikeReply, person);
		List<LikeableReply> likes = service.getAllLikebyReply(reply);
		
		return Integer.toString(likes.size());
	}
	
	@RequestMapping(value="/like", method=RequestMethod.POST)
	@ResponseBody
	public String likePost(@RequestParam("username") String username, @RequestParam("id") int id) {
		
		System.out.println(username + "    " + id);
		ForumPost post = service.getPostForLike(id);
		Person person = service.getPersonByUsername(username);
		service.addLike(post, person);
		ForumPost postDislikes = service.getPostForDislike(id);
		service.checkForDislike(postDislikes, person);
		List<LikeablePost> likes = service.getAllLikesbyPost(post);
		
		return Integer.toString(likes.size());
	}
	
	@RequestMapping(value="/getDislike", method=RequestMethod.POST)
	@ResponseBody
	public String getAllDislikes(@RequestParam("id") int id) {
		
		ForumPost post = service.getPostForDislike(id);
		List<DisLikeablePost> dislikes = service.getAllDislikebyPost(post);
		System.out.println("-----------------------------------------------here");
		return Integer.toString(dislikes.size());
	}
	
	@RequestMapping(value="/getDislikes", method=RequestMethod.POST)
	@ResponseBody
	public List<Integer> getAllDislikesReply(@RequestParam("id") int id) {
		
		ForumPost post = service.getPostForDislike(id);
		
		List<PostReply> replys = service.getRepliesByPost(post);
		
		List<Integer> dislikes = new ArrayList<>();
		
		for(PostReply item: replys) {
			PostReply reply = service.getReplyForDislike(item.getId());
			List<DisLikeableReply> dislikeReplyList = service.getAllDislikebyReply(reply);
			dislikes.add(dislikeReplyList.size());
		}
		
		return dislikes;
	}
	
	@RequestMapping(value="/getLikes", method=RequestMethod.POST)
	@ResponseBody
	public List<Integer> getAllLikesReply(@RequestParam("id") int id) {
		
		ForumPost post = service.getPostForLike(id);
		
		List<PostReply> replys = service.getRepliesByPost(post);
		
		List<Integer> likes = new ArrayList<>();
		
		for(PostReply item: replys) {
			PostReply reply = service.getReplyForLike(item.getId());
			List<LikeableReply> likeReplyList = service.getAllLikebyReply(reply);
			likes.add(likeReplyList.size());
		}
		
		return likes;
	}
	
	
	@RequestMapping(value="/getLike", method=RequestMethod.POST)
	@ResponseBody
	public String getAllLikes(@RequestParam("id") int id) {
		
		ForumPost post = service.getPostForLike(id);
		List<LikeablePost> likes = service.getAllLikesbyPost(post);
		System.out.println("-----------------------------------------------here");
		return Integer.toString(likes.size());
	}
	
	@RequestMapping(value="/deletePost")
	public void deletePost(@RequestBody String postIdStr){
		int postId = Integer.parseInt(postIdStr);
		service.deletePost(postId);
	}
	
	@RequestMapping(value="/getPostsCat")
	public ResponseEntity<List<PostContainer>> getPostsByCategory(@RequestBody String catName){
		
		List<ForumPost> posts = service.getPostsByCategory(catName);
		
		List<PostContainer> allPosts = new ArrayList<>();
		
		for(ForumPost post: posts){
			List<List<String>> postContent = new ArrayList<>();
			for(PostReply reply: service.getRepliesByPost(post)) {
				List<String> replies = new ArrayList<>();
				replies.add(reply.getContent());
				replies.add(reply.getAuthor().getUsername());
				replies.add(reply.getTimestamp().toString());
				postContent.add(replies);
			}
			
			System.out.println("postId: " + post.getId() + "\tpostContent: " + postContent);
			PostContainer p = new PostContainer(post.getAuthor().getUsername(), post.getTitle(), post.getContent(), post.getId(),post.getAuthor().getProfilePic() , postContent);
			allPosts.add(p);
			System.out.println(p.getPostContent());
		}
		
		for(ForumPost p: posts)
			System.out.println("post title: " + p.getTitle());
		return new ResponseEntity<List<PostContainer>>(allPosts, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getPostsCatProf")
	public ResponseEntity<List<PostContainer>> getPostsByCategoryInProf(@RequestBody String[] info){
		String catName = info[0];
		String username = info[1];
		List<ForumPost> posts = service.getPostsByCategoryProf(catName, username);
		
		List<PostContainer> allPosts = new ArrayList<>();
		
		for(ForumPost post: posts){
			List<List<String>> postContent = new ArrayList<>();
			for(PostReply reply: service.getRepliesByPost(post)) {
				List<String> replies = new ArrayList<>();
				replies.add(reply.getContent());
				replies.add(reply.getAuthor().getUsername());
				replies.add(reply.getTimestamp().toString());
				postContent.add(replies);
			}
			
			System.out.println("postId: " + post.getId() + "\tpostContent: " + postContent);
			PostContainer p = new PostContainer(post.getAuthor().getUsername(), post.getTitle(), post.getContent(), post.getId(),post.getAuthor().getProfilePic() , postContent);
			allPosts.add(p);
			System.out.println(p.getPostContent());
		}
		
		for(ForumPost p: posts)
			System.out.println("post title: " + p.getTitle());
		return new ResponseEntity<List<PostContainer>>(allPosts, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getAllCat")
	public ResponseEntity<List<String>> getAllCategories(){
		
		List<ForumCategory> categories = service.getAllCategories();
		List<String> catNames = new ArrayList<>();
		for(ForumCategory cat: categories){
			catNames.add(cat.getCategoryName());
		}
		return new ResponseEntity<List<String>>(catNames, HttpStatus.OK);
	}
}
