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
import javax.persistence.JoinTable;
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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ps_id")
	private PostStatus status;
	
	@Column(name="fp_title")
	private String title;
	
	@Column(name="fp_content")
	private String content;
	
	@Column(name="fp_timestamp")
	private Timestamp timestamp;
	
	@Column(name="fp_resolved")
	private boolean resolved;
	
	@OneToMany(mappedBy="post", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<PostReply> replys;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="Post_Cat_JT", joinColumns=@JoinColumn(name="fp_id"),
	inverseJoinColumns=@JoinColumn(name="fc_id"))
	private List<ForumCategory> category;
	
	public ForumPost() {
		replys = new ArrayList<>();
		category = new ArrayList<>();
	}

	public ForumPost(int id, Person author, PostStatus status, String title, 
			String content, Timestamp timestamp, boolean resolved) {
		this();
		this.id = id;
		this.author = author;
		this.status = status;
		this.title = title;
		this.content = content;
		this.timestamp = timestamp;
		this.resolved = resolved;
	}
	
	public ForumPost(Person author, String title, String content, Timestamp timestamp, boolean resolved) {
		this();
		this.author = author;
		this.title = title;
		this.content = content;
		this.timestamp = timestamp;
		this.resolved = resolved;
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

	public PostStatus getStatus() {
		return status;
	}

	public void setStatus(PostStatus status) {
		this.status = status;
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

	@Override
	public String toString() {
		return "ForumPost [id=" + id + ", author=" + author + ", status=" + status 
				+ ", title=" + title + ", content="
				+ content + ", timestamp=" + timestamp + ", resolved=" + resolved 
				+ ", replys=" + replys + ", category="
				+ category + "]";
	}

	
}
