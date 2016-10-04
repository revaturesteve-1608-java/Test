package project3.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PostReply")
public class PostReply {

	@Id
	@Column(name="pr_id")
	@SequenceGenerator(name="postReplySeq", sequenceName="postReply_Seq", allocationSize=1)
	@GeneratedValue(generator="postReplySeq", strategy=GenerationType.SEQUENCE)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fp_id")
	private ForumPost post;
	
	@Column(name="pr_likes")
	private int likes;
	
	@Column(name="pr_dislikes")
	private int dislikes;
	
	@Column(name="pr_approval")
	private boolean approval;
	
	@Column(name="pr_content")
	private String content;
	
	@Column(name="pr_timestamp")
	private Timestamp timestamp;
	
	public PostReply() {
		
	}

	public PostReply(int id, ForumPost post, int likes, int dislikes, boolean approval, 
			String content, Timestamp timestamp) {
		super();
		this.id = id;
		this.post = post;
		this.likes = likes;
		this.dislikes = dislikes;
		this.approval = approval;
		this.content = content;
		this.timestamp = timestamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ForumPost getPost() {
		return post;
	}

	public void setPost(ForumPost post) {
		this.post = post;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "PostReply [id=" + id + ", post=" + post + ", likes=" + likes 
				+ ", dislikes=" + dislikes + ", approval="
				+ approval + ", content=" + content + ", timestamp=" + timestamp + "]";
	}
}
