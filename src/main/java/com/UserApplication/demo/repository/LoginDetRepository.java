package com.UserApplication.demo.repository;

import com.UserApplication.demo.entity.LoginDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDetRepository extends JpaRepository<LoginDetailsEntity,Long> {

}
