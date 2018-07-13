package com.usst.lostandfound.repository;

import com.usst.lostandfound.entity.Prompt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromptRepository extends JpaRepository<Prompt, Integer> {
    List<Prompt> findByLostPhone(String lostPhone);

    int deleteByPromptId(Integer promptId);
}
