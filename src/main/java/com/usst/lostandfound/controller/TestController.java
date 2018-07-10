package com.usst.lostandfound.controller;

import com.usst.lostandfound.entity.Found;
import com.usst.lostandfound.entity.Lost;
import com.usst.lostandfound.repository.FoundRepository;
import com.usst.lostandfound.repository.LostRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final FoundRepository foundRepo;
    private final LostRepository lostRepo;

    public TestController(FoundRepository foundRepo, LostRepository lostRepo) {
        this.foundRepo = foundRepo;
        this.lostRepo = lostRepo;
    }

    @PostMapping(value = "/found")
    public void addFound(){
        foundRepo.save(new Found());
    }

    @PostMapping(value = "/lost")
    public void addLost(){
        lostRepo.save(new Lost());
    }
}
