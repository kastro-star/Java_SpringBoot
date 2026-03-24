package com.kastro4.Spring_Boot_learn.models;

public class user {
		private long id;
		public user(long id, String name, String email) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
		}
		private String name;
		private String email;
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
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
		
		
}
