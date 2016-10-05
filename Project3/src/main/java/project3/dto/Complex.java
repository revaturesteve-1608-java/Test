package project3.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Complex")
public class Complex {
	
	@Id
	@Column(name="c_id")
	@SequenceGenerator(name="complexSeq", sequenceName="complex_Seq", allocationSize=1)
	@GeneratedValue(generator="complexSeq", strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Column(name="c_name")
	private String complexName;
	
	public Complex() {
		
	}

	public Complex(int id, String complexName) {
		super();
		this.id = id;
		this.complexName = complexName;
	}
	
	public Complex(String complexName) {
		super();
		this.complexName = complexName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComplexName() {
		return complexName;
	}

	public void setComplexName(String complexName) {
		this.complexName = complexName;
	}

	@Override
	public String toString() {
		return "Complex [id=" + id + ", complexName=" + complexName + "]";
	}

}
