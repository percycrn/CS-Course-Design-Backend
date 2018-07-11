package com.usst.lostandfound.repository;

import com.usst.lostandfound.entity.Lost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LostRepository extends JpaRepository<Lost, Integer> {
    Lost findByLostId(Integer lostId);

    int deleteByLostId(Integer lostId);

    List<Lost> findByLostPhone(String lostPhone);

    List<Lost> findByLostPhoneNot(String lostPhone);
}
