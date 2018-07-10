package com.usst.lostandfound.repository;

import com.usst.lostandfound.entity.Found;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoundRepository extends JpaRepository<Found, Integer> {
}
