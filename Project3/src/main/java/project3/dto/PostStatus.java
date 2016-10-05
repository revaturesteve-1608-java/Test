package project3.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PostStatus")
public class PostStatus {
	
	@Id
	@Column(name="ps_id")
	@SequenceGenerator(name="psSeq", sequenceName="ps_Seq", allocationSize=1)
	@GeneratedValue(generator="psSeq", strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Column(name="ps_status")
	private String status;
	
	public PostStatus() {
		
	}

	public PostStatus(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "postStatus [id=" + id + ", status=" + status + "]";
	}

}
