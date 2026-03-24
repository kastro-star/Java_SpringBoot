package com.kastro4.Spring_Boot_learn.user_entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "Users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name ="Roll_no")
	Long student_id;
	
	@Column(name = "name")
	String name;
	
	@Column(name ="email")
	String email;
	
	public UserEntity() {
		
	}
	
	public Long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Long student_id) {
		this.student_id = student_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	UserEntity(Long Student_id,String Name,String email){
		this.student_id =Student_id;
		this.name = Name;
		this.email = email;
	}
	
}
