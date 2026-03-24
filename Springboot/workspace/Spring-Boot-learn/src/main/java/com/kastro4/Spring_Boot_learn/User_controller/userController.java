package com.kastro4.Spring_Boot_learn.User_controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kastro4.Spring_Boot_learn.exceptionthrow.runtimeerror;
import com.kastro4.Spring_Boot_learn.user_entity.UserEntity;
import com.kastro4.Spring_Boot_learn.user_repository.userrepository;


@RestController
@RequestMapping("/api/users")
public class userController {

	@Autowired
	private userrepository kastro;
	
	
//    @GetMapping
//    public String users(){
//        return "Hello S";
//    }
	
	//Get usersList from the Mysql via Jparepository interface
	@GetMapping
    public List<UserEntity> getusers(){
     return kastro.findAll();
    } 

	//passes data to the Mysql (without frontend) input data via postman @Requestbody wait to receive
	@PostMapping
	public UserEntity CreateUser(@RequestBody UserEntity kas) {
		return kastro.save(kas);
	}
	
	
	@GetMapping("/id/{id}")
	public Optional<UserEntity> getSingleUser(@PathVariable Long id){
	    return kastro.findById(id);
	}	
	
	@GetMapping("/user/{name}")
	public UserEntity getSingleUser(@PathVariable String name){
	    return kastro.findByName(name).orElseThrow( ()-> new runtimeerror("User with the name not exist  "+ name));
	}
	
	@PutMapping("/{id}")
	public UserEntity updatename(@PathVariable Long id,@RequestBody UserEntity user) {
		UserEntity usersdata = kastro.findById(id).orElseThrow(() -> new runtimeerror("User with the id not exist"+"name"));
		usersdata.setName(user.getName());
		usersdata.setEmail(user.getEmail());
		return kastro.save(usersdata);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		UserEntity usersda = kastro.findById(id).orElseThrow(() -> new runtimeerror("User with the id not exist"+"name"));
		kastro.delete(usersda);
		return ResponseEntity.ok().build();
	}
	
	
	
	
	
}