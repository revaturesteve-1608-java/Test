package project3.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="LikeablePost")
public class LikeablePost {

	@Id
	@Column(name="likepost_id")
	@SequenceGenerator(name="likeablepostSeq", sequenceName="Likeablepost_Seq", allocationSize=1)
	@GeneratedValue(generator="likeablepostSeq", strategy=GenerationType.SEQUENCE)
	private int id;
	
	@OneToOne(fetch=FetchType.EAGER)  
	@JoinColumn(name="u_id")
	private Person author;
	
	@ManyToOne
	@JoinColumn(name="fp_id")
	private ForumPost post;
	
	public LikeablePost() {
		
	}
	
	public LikeablePost(int id, Person author, ForumPost post) {
		super();
		this.id = id;
		this.author = author;
		this.post = post;
	}

	public LikeablePost(Person person, ForumPost post) {
		this.author = person;
		this.post = post;
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

	public ForumPost getPost() {
		return post;
	}

	public void setPost(ForumPost post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "LikeablePost [id=" + id + ", author=" + author + ", post=" + post + "]";
	}
}
