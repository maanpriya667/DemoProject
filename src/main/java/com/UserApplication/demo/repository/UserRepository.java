package com.UserApplication.demo.repository;

import com.UserApplication.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long >{
	
	UserEntity findByEmail(String email);

	@Query(value = "Select * from user_entity where user_ref = ?1", nativeQuery = true)
	UserEntity findByRefCode(String code);


}
