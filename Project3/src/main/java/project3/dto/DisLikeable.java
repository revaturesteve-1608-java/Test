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
@Table(name="DisLikeable")
public class DisLikeable {

	@Id
	@Column(name="dislike_id")
	@SequenceGenerator(name="dislikeableSeq", sequenceName="disLikeable_Seq", allocationSize=1)
	@GeneratedValue(generator="dislikeableSeq", strategy=GenerationType.SEQUENCE)
	private int id;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)  
	@JoinColumn(name="u_id")
	private Person author;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="pr_id")
	private PostReply dislike_reply;
	
	public DisLikeable() {
		
	}

	public DisLikeable(int id, Person author, PostReply dislike_reply) {
		super();
		this.id = id;
		this.author = author;
		this.dislike_reply = dislike_reply;
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

	public PostReply getDislike_reply() {
		return dislike_reply;
	}

	public void setDislike_reply(PostReply dislike_reply) {
		this.dislike_reply = dislike_reply;
	}

	@Override
	public String toString() {
		return "DisLikeable [id=" + id + ", author=" + author + ", dislike_reply=" + dislike_reply + "]";
	}
}
