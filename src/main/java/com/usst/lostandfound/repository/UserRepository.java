package com.usst.lostandfound.repository;

import com.usst.lostandfound.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
