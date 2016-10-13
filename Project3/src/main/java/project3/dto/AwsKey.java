package project3.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AwsKey")
public class AwsKey {

	@Id
	@Column(name="aws_id")
	private int id;
	
	@Column(name="aws_key")
	private String accessKey;
	
	@Column(name="aws_pkey")
	private String secretAccessKey;

	public AwsKey() {
		
	}

	public AwsKey(int id, String accessKey, String secretAccessKey) {
		super();
		this.id = id;
		this.accessKey = accessKey;
		this.secretAccessKey = secretAccessKey;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretAccessKey() {
		return secretAccessKey;
	}

	public void setSecretAccessKey(String secretAccessKey) {
		this.secretAccessKey = secretAccessKey;
	}

	@Override
	public String toString() {
		return "AwsKey [id=" + id + ", accessKey=" + accessKey + ", secretAccessKey=" 
				+ secretAccessKey + "]";
	}
}
