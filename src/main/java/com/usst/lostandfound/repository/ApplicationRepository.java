package com.usst.lostandfound.repository;

import com.usst.lostandfound.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application,Integer> {
    Application findByAppId(Integer appId);
    int deleteByAppId(Integer appId);
}
