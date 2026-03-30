package com.TodoList.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//For creating RESTAPI'S use RestController
@RestController 
//for whole separate Mapping the web url
@RequestMapping("/api/v1") 
public class controller {
	
		@GetMapping("/users")
		public String todo(){
			return "kastrfvvo";
		}
		
	
	   //use {} to get the input search from the DB and the return the massive data like show the ecommerce web products the inputs data and return it 
		@GetMapping("{id}")
		public String getinformationbyId(@PathVariable Long id) {
			return "Goodbyehnnnng" + id;
		}
	
	
	   //Request parameter users id for search filters like api/v1/id/21/?status=delivered&year=2026
		@GetMapping
		public String getinformationbyIdparam(@RequestParam int ids) {
			return "Goodbye understand" + ids;
		}
		
		//
		@PostMapping("/create")
		public String createuser(@RequestBody String name) {
			return name;
		}
}
