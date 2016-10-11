package project3.objectcontainer;

public class PostContainer {
	
	private String authorName;
	private String postTitle;
	private String postContent;
	private int postId;
	
	public PostContainer(){}

	public PostContainer(String authorName, String postTitle, String postContent, int postId) {
		super();
		this.authorName = authorName;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.setPostId(postId);
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

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	@Override
	public String toString() {
		return "PostContainer [authorName=" + authorName + ", postTitle=" + postTitle + ", postContent=" + postContent
				+ ", postId=" + postId + "]";
	}
}
