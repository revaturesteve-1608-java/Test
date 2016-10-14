package project3.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ForumPost")
public class ForumPost {

	@Id
	@Column(name="fp_id")
	@SequenceGenerator(name="ForumPostSeq", sequenceName="ForumPost_Seq", 
		allocationSize=1)
	@GeneratedValue(generator="ForumPostSeq", strategy=GenerationType.SEQUENCE)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="u_id")
	private Person author;
	
	@Column(name="fp_title")
	private String title;
	
	@Column(name="fp_content")
	private String content;
	
	@Column(name="fp_timestamp")
	private Timestamp timestamp;

	@OneToMany(mappedBy="post", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<LikeablePost> likes;

	@OneToMany(mappedBy="post", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<DisLikeablePost> dislikes;
	
	@Column(name="fp_resolved")
	private boolean resolved;
	
	@OneToMany(mappedBy="post", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<PostReply> replys;
	
	@ManyToMany
	private List<ForumCategory> category;
	
	public ForumPost() {
		replys = new ArrayList<>();
		category = new ArrayList<>();
	}
	
	public ForumPost(Person author, String title, String content, Timestamp timestamp, boolean resolved) {
		this();
		this.author = author;
		this.title = title;
		this.content = content;
		this.timestamp = timestamp;
		this.resolved = resolved;
	}

	public ForumPost(int id, Person author, String title, String content, Timestamp timestamp, List<LikeablePost> likes,
			List<DisLikeablePost> dislikes, boolean resolved, List<PostReply> replys, List<ForumCategory> category) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.content = content;
		this.timestamp = timestamp;
		this.likes = likes;
		this.dislikes = dislikes;
		this.resolved = resolved;
		this.replys = replys;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public boolean isResolved() {
		return resolved;
	}

	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}

	public List<PostReply> getReplys() {
		return replys;
	}

	public void setReplys(List<PostReply> replys) {
		this.replys = replys;
	}

	public List<ForumCategory> getCategory() {
		return category;
	}

	public void setCategory(List<ForumCategory> category) {
		this.category = category;
	}

	public List<LikeablePost> getLikes() {
		return likes;
	}

	public void setLikes(List<LikeablePost> likes) {
		this.likes = likes;
	}

	public List<DisLikeablePost> getDislikes() {
		return dislikes;
	}

	public void setDislikes(List<DisLikeablePost> dislikes) {
		this.dislikes = dislikes;
	}

	@Override
	public String toString() {
		return "ForumPost [id=" + id + ", author=" + author + ", title=" + title + ", content=" + content
				+ ", timestamp=" + timestamp + ", likes=" + likes + ", dislikes=" + dislikes + ", resolved=" + resolved
				+ ", replys=" + replys + ", category=" + category + "]";
	}

	
}
