package com.kastro4.Spring_Boot_learn.user_repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kastro4.Spring_Boot_learn.user_entity.UserEntity;

public interface userrepository extends JpaRepository<UserEntity,Long>{

	Optional<UserEntity> findByName(String name);
	
}
