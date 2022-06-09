package com.UserApplication.demo.repository;

import com.UserApplication.demo.entity.ReferralEntity;
import com.UserApplication.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferralRepository extends JpaRepository<ReferralEntity,Long > {

}
