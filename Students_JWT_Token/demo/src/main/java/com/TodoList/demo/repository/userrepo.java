package com.TodoList.demo.repository;

import com.TodoList.demo.model.user;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userrepo  extends JpaRepository <user,Long> {

    Optional<user> findByEmail(String email);
}
