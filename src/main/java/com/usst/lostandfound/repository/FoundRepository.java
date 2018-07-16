package com.usst.lostandfound.repository;

import com.usst.lostandfound.entity.Found;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoundRepository extends JpaRepository<Found, Integer> {
    Found findByFoundId(Integer foundId);

    int deleteByFoundId(Integer foundId);

    List<Found> findByFoundPhone(String foundPhone);

    List<Found> findByFoundPhoneNotAndLostPhoneNull(String foundPhone);
}
