package com.TodoList.demo.service;


import com.TodoList.demo.model.user;
import com.TodoList.demo.repository.userrepo;


import org.springframework.stereotype.Service;


@Service
public class user_service{
    private userrepo users;

    public user_service(userrepo users){
        this.users = users;
    }

    public void createuser(user User){
        users.save(User);
    }

    public user getuserbyId(Long id){
        return users.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    //delete
    public void deleteuser(Long id){
        users.delete(getuserbyId(id));
    }

    //

}
