package project3.objectcontainer;

import java.util.List;

import project3.dto.PostReply;

public class PostContainer {

	private String authorName;
	private String postTitle;
	private String postContent;
	private int postId;
	private List<List<String>> replyContent;
	//	private List<PostReply> replies;

	public PostContainer(){}

	public PostContainer(String authorName, String postTitle, String postContent, int postId, List<List<String>> replyContent) {
		super();
		this.authorName = authorName;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.postId = postId;
		this.setReplyContent(replyContent);
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}



	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}



	public List<List<String>> getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(List<List<String>> replyContent2) {
		this.replyContent = replyContent2;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	@Override
	public String toString() {
		return "PostContainer [authorName=" + authorName + ", postTitle=" + postTitle + ", postContent=" + postContent
				+ ", postId=" + postId + ", replyContent=" + replyContent + "]";
	}

	
}
