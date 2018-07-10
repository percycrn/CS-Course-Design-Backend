package com.usst.lostandfound.repository;

import com.usst.lostandfound.entity.Lost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LostRepository extends JpaRepository<Lost,Integer> {
}
