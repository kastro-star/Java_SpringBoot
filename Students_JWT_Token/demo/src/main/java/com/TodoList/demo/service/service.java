package com.TodoList.demo.service;


import java.util.List;

import com.TodoList.demo.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.TodoList.demo.model.todo;
import com.TodoList.demo.repository.repo;

@Service
public class service {

	@Autowired
	private repo Repo;
	
//	display all students
	public List<todo> liststudents() {
		return Repo.findAll();
	}
	
	//add users to the database
	public todo adduser(todo Todo) {
		Repo.save(Todo);
		return Todo;
	}
	
	
	//returns the students list  as like fixed size in the webpage
	public Page<todo> getstudentslistbypage(int pagenumber, int size){
		Pageable pagel = PageRequest.of(pagenumber, size);
		return Repo.findAll(pagel);
	}
    public  todo getuserby(Long id){
        return Repo.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
    }


    public void deleteuser(Long id){
        Repo.delete(getuserby(id));
    }


    public void createuser(todo Todo){
        Repo.save(Todo);
    }


    public todo updateus(todo Todo){
        return Repo.save(Todo);
    }



	
	
}
