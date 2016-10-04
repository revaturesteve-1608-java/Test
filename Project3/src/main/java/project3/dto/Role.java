package project3.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Role")
public class Role {
	
	@Id
	@Column(name="r_id")
	@SequenceGenerator(name="roleSeq", sequenceName="role_Seq", allocationSize=1)
	@GeneratedValue(generator="roleSeq", strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Column(name="r_roleName")
	private String roleName;
	
	public Role() {
		
	}

	public Role(int id, String roleName) {
		super();
		this.id = id;
		this.roleName = roleName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + "]";
	}

}
