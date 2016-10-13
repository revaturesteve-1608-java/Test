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
@Table(name="LikeableReply")
public class LikeableReply {

	@Id
	@Column(name="likereply_id")
	@SequenceGenerator(name="likeablereplySeq", sequenceName="Likeablereply_Seq", allocationSize=1)
	@GeneratedValue(generator="likeablereplySeq", strategy=GenerationType.SEQUENCE)
	private int id;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)  
	@JoinColumn(name="u_id")
	private Person author;
	
	@ManyToOne
	@JoinColumn(name="pr_id")
	private PostReply reply;
	
	public LikeableReply() {
		
	}

	public LikeableReply(int id, Person author, PostReply reply) {
		super();
		this.id = id;
		this.author = author;
		this.reply = reply;
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

	public PostReply getReply() {
		return reply;
	}

	public void setReply(PostReply reply) {
		this.reply = reply;
	}

	@Override
	public String toString() {
		return "LikeableReply [id=" + id + ", author=" + author + ", reply=" + reply + "]";
	}
}
