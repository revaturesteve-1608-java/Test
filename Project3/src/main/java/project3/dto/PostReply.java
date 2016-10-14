package project3.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	@JoinColumn(name="u_id")
	private Person author;

	@ManyToOne
	@JoinColumn(name="fp_id")
	private ForumPost post;
	
	@OneToMany(mappedBy="reply", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<LikeableReply> likes;
	
	@OneToMany(mappedBy="reply", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<DisLikeableReply> dislikes;
	
	@Column(name="pr_approval")
	private boolean approval;
	
	@Column(name="pr_content")
	private String content;
	
	@Column(name="pr_timestamp")
	private Timestamp timestamp;
	
	public PostReply() {
		
	}

	public PostReply(int id, Person author, ForumPost post, List<LikeableReply> likes, List<DisLikeableReply> dislikes,
			boolean approval, String content, Timestamp timestamp) {
		super();
		this.id = id;
		this.author = author;
		this.post = post;
		this.likes = likes;
		this.dislikes = dislikes;
		this.approval = approval;
		this.content = content;
		this.timestamp = timestamp;
	}

	public PostReply(ForumPost post, Person author, List<LikeableReply> likes, List<DisLikeableReply> dislikes, boolean approval, String content,
			Timestamp timestamp) {
		super();
		this.author = author;
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

	public List<LikeableReply> getLikes() {
		return likes;
	}

	public List<DisLikeableReply> getDislikes() {
		return dislikes;
	}

	public void setLikes(List<LikeableReply> likes) {
		this.likes = likes;
	}

	public void setDislikes(List<DisLikeableReply> dislikes) {
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

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	public ForumPost getPost() {
		return post;
	}

	public void setPost(ForumPost post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "PostReply [id=" + id + ", author=" + author + ", post=" + post + ", likes=" + likes + ", dislikes="
				+ dislikes + ", approval=" + approval + ", content=" + content + ", timestamp=" + timestamp + "]";
	}
}
