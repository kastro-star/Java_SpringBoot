package com.TodoList.demo.controllers;

import com.TodoList.demo.model.todo;
import com.TodoList.demo.model.user;
import com.TodoList.demo.repository.userrepo;

import com.TodoList.demo.security.Jwttoken;
import com.TodoList.demo.service.service;
import com.TodoList.demo.service.user_service;
import lombok.Data;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@Data
@RequestMapping("/auth")
public class usercollage{
    //IF I USE @RequiredArgsConstructor dont need to put autowired for object creation

    private final user_service User;
    private final userrepo Userrepo;
    private final Jwttoken jwttoken;
    private final PasswordEncoder passwordEncoder;
    private final service Service;

    public usercollage(user_service user, userrepo userrepo,service service ,Jwttoken jwttoken, PasswordEncoder passwordEncoder) {
        User = user;
        Userrepo = userrepo;
        Service = service;
        this.jwttoken = jwttoken;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody  Map<String,String>body){
            String email = body.get("email");
            String password = passwordEncoder.encode(body.get("password"));
            if(Userrepo.findByEmail(email).isPresent()) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("message","User already exist"));
            }
            User.createuser(new user(email,password));

        return ResponseEntity.ok(Map.of("message","Created successfully"));

    }
    @GetMapping("/test")
    public String test() {
        return "working";
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String ,String>body){
        String email = body.get("email");
        String password = body.get("password");

        var usersearch = Userrepo.findByEmail(email);
        if(usersearch.isEmpty()){
            return new ResponseEntity<>("USER NOT REGISTERED", HttpStatus.UNAUTHORIZED);
        }
        user User  =usersearch.get();
        if(!passwordEncoder.matches(password,User.getPassword())){
            return new ResponseEntity<>("Invalid User",HttpStatus.UNAUTHORIZED);
        }
        String token = jwttoken.tokengeneration(email);
        return  ResponseEntity.ok( Map.of("token",token));
    }




    //for todo's deletion
    @DeleteMapping("/delete/{id}")
    public void deletetodo(Long id){
        Service.deleteuser(id);
    }

    @PutMapping("/update")
    public todo updatetodo(@RequestBody todo Todo){
       return Service.updateus(Todo);
    }


}
