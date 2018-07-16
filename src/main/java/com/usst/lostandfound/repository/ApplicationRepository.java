package com.usst.lostandfound.repository;

import com.usst.lostandfound.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Application findByAppId(Integer appId);

    int deleteByAppId(Integer appId);

    List<Application> findByPhone(String phone);

    Application findByFoundIdAndPhone(Integer foundId, String phone);
}
