package project3.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)  
	@JoinColumn(name="u_id")
	private Person author;
	
	public LikeablePost() {
		
	}

	public LikeablePost(int id, Person author) {
		super();
		this.id = id;
		this.author = author;
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

	@Override
	public String toString() {
		return "Likeable [id=" + id + ", author=" + author + "]";
	}
}
