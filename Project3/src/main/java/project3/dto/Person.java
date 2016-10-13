package project3.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="Person")
public class Person {
	
	@Id
	@Column(name="u_id")
	@SequenceGenerator(name="personSeq", sequenceName="person_Seq", allocationSize=1)
	@GeneratedValue(generator="personSeq", strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Column(name="u_firstName", nullable=false)
	private String first_name;
	
	@Column(name="u_lastName", nullable=false)
	private String last_name;

	@Column(name="u_username", nullable=false, unique=true)
	private String username;
	
	@Column(name="u_password", nullable=false, unique=true)
	@Size(min=6)
	private String password;
	
	@Column(name="u_email", nullable= false, unique=true)
	private String email;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="r_id")
	private Role role;
	
	@Column(name="u_picture")
	private String profilePic;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="c_id")
	private Complex complex;
	
	@Column(name="u_phoneNumber")
	private String phoneNumber;
	
	@Column(name="u_bio")
	private String bio;
	
	@Column(name="u_university")
	private String university;
	
	@Column(name="u_vaildated")
	private boolean vaildated;
	
	@Column(name="u_linkedin")
	private String linkedin;
	
	@Column(name="u_totalLikes")
	private int totalLikes;
	
	public Person() {
		
	}
	
	public Person(int id, String first_name, String last_name, String username, String password, String email,
			Role role, String profilePic, Complex complex, String phoneNumber, String bio, String university,
			boolean vaildated, String linkedin, int totalLikes) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.profilePic = profilePic;
		this.complex = complex;
		this.phoneNumber = phoneNumber;
		this.bio = bio;
		this.university = unviersity;
		this.vaildated = vaildated;
		this.linkedin = linkedin;
		this.totalLikes = totalLikes;
	}

	public Person(String first_name, String last_name, String username, String password, String email, Role role,
			String profilePic, Complex complex, String phoneNumber, String bio, String university, boolean vaildated,
			String linkedin) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.profilePic = profilePic;
		this.complex = complex;
		this.phoneNumber = phoneNumber;
		this.bio = bio;
		this.university = unviersity;
		this.vaildated = vaildated;
		this.linkedin = linkedin;
	}

	public Person(String first_name, String last_name, String username, String password, String email, Role role,
			boolean vaildated) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.vaildated = vaildated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public Complex getComplex() {
		return complex;
	}

	public void setComplex(Complex complex) {
		this.complex = complex;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getUnviersity() {
		return university;
	}

	public void setUnviersity(String unviersity) {
		this.university = unviersity;
	}

	public boolean isVaildated() {
		return vaildated;
	}

	public void setVaildated(boolean vaildated) {
		this.vaildated = vaildated;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public int getTotalLikes() {
		return totalLikes;
	}

	public void setTotalLikes(int totalLikes) {
		this.totalLikes = totalLikes;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", role=" + role + ", profilePic=" + profilePic
				+ ", complex=" + complex + ", phoneNumber=" + phoneNumber + ", bio=" + bio + ", unviersity="
				+ university + ", vaildated=" + vaildated + ", linkedin=" + linkedin + ", totalLikes=" + totalLikes
				+ "]";
	}
}
