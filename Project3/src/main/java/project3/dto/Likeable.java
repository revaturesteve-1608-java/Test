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
@Table(name="Likeable")
public class Likeable {

	@Id
	@Column(name="like_id")
	@SequenceGenerator(name="likeableSeq", sequenceName="Likeable_Seq", allocationSize=1)
	@GeneratedValue(generator="likeableSeq", strategy=GenerationType.SEQUENCE)
	private int id;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)  
	@JoinColumn(name="u_id")
	private Person author;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="pr_id")
	private PostReply like_reply;
	
	public Likeable() {
		
	}

	public Likeable(int id, Person author, PostReply like_reply) {
		super();
		this.id = id;
		this.author = author;
		this.like_reply = like_reply;
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

	public PostReply getLike_reply() {
		return like_reply;
	}

	public void setLike_reply(PostReply like_reply) {
		this.like_reply = like_reply;
	}

	@Override
	public String toString() {
		return "Likeable [id=" + id + ", author=" + author + ", like_reply=" + like_reply + "]";
	}
}
