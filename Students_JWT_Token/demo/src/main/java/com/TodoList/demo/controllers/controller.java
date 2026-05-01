package com.TodoList.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.TodoList.demo.model.todo;

import com.TodoList.demo.service.service;
@RestController
@RequestMapping("/collage")
@CrossOrigin(origins = "*")
public class controller{
		
		@Autowired
		private service temp;
	
		
		//returns all the studentslist
		@GetMapping("/studentsList")
		public List<todo> Allstudents(){
			return temp.liststudents();
		}
		
		
		//add students one by one
		@PostMapping("/addstudents")
		public todo addstudent(@RequestBody todo Todo) {
			temp.adduser(Todo);
			return Todo;
			
		}
		
		
		//returns the students list as fixed size in the page (dont display all the users) in webpage uses pagable object
		@GetMapping("/page")
		public Page<todo> studentsentry(@RequestParam int page,@RequestParam int size ){
			return temp.getstudentslistbypage(page,size);
		}






        

		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
