package com.TodoList.demo.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class todo {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		@Nonnull
		
		private String name;
		@NotBlank
		private String Rollno;
		@Size(min=2,max=6)
		private String Dep;
		private int latecount;
		@Email
		private String email;
		public todo() {
	    }
		
		
		
		//if adds Phone no use @Pattern(^[0-9]{10}$)
//		use the annotation to valids its an phone no
		
		
		public Long getId() {
			return id;
		}
		
		public void setId(Long id) {
			this.id = id;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getRollno() {
			return Rollno;
		}
		
		public void setRollno(String rollno) {
			Rollno = rollno;
		}
		
		public String getDep() {
			return Dep;
		}
		
		public void setDep(String dep) {
			Dep = dep;
		}
		
		public int getLatecount() {
			return latecount;
		}
	
		public void setLatecount(int latecount) {
			this.latecount = latecount;
		}



		public String getEmail() {
			return email;
		}



		public void setEmail(String email) {
			this.email = email;
		}
	
	
		
}
