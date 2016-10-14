package project3.dto;

import javax.persistence.CascadeType;
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
@Table(name="DisLikeablePost")
public class DisLikeablePost {

	@Id
	@Column(name="dislikepost_id")
	@SequenceGenerator(name="dislikeablepostSeq", sequenceName="disLikeablepost_Seq", allocationSize=1)
	@GeneratedValue(generator="dislikeablepostSeq", strategy=GenerationType.SEQUENCE)
	private int id;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)  
	@JoinColumn(name="u_id")
	private Person author;
	
	@ManyToOne
	@JoinColumn(name="fp_id")
	private ForumPost post;
	
	public DisLikeablePost() {
		
	}
	
	

	public DisLikeablePost(Person author, ForumPost post) {
		super();
		this.author = author;
		this.post = post;
	}



	public DisLikeablePost(int id, Person author, ForumPost post) {
		super();
		this.id = id;
		this.author = author;
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
		return "DisLikeablePost [id=" + id + ", author=" + author + ", post=" + post + "]";
	}
}
