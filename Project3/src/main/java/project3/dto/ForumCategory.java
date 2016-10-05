package project3.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ForumCategory")
public class ForumCategory {
	
	@Id
	@Column(name="fc_id")
	@SequenceGenerator(name="forumCatSeq", sequenceName="forumCat_Seq", allocationSize=1)
	@GeneratedValue(generator="forumCatSeq", strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Column(name="fc_categoryName")
	private String categoryName;
	
	@ManyToMany(mappedBy="category", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<ForumPost> forumPost;
	
	public ForumCategory() {
		forumPost = new ArrayList<>();
	}

	public ForumCategory(int id, String categoryName) {
		this();
		this.id = id;
		this.categoryName = categoryName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<ForumPost> getForumPost() {
		return forumPost;
	}

	public void setForumPost(List<ForumPost> forumPost) {
		this.forumPost = forumPost;
	}

	@Override
	public String toString() {
		return "ForumCategory [id=" + id + ", categoryName=" + categoryName 
				+ ", forumPost=" + forumPost + "]";
	}

}
