package com.TodoList.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

import com.TodoList.demo.model.todo;



@Controller
public interface repo  extends JpaRepository<todo, Long >{
	
	
}
