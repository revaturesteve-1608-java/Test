package project3.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User {
	
	@Id
//	@Column(name="u_id",  )
//	@GeneratedValue
//	@SequenceGenerator
	private int u_id;
	private String u_first_name;
	private String u_last_name;
	private String u_username;
	private String u_email;
	
	
//	users - UID(PK), first name(NN), last name(NN), username(NNU), email address(NNU), password(restrictions), role, profile pic img, complex, phone number, bio (strong and weak suits), alma mater, validated boolean, Linkedin URL

//	complex lookup (CID, name of living quarters)
}
