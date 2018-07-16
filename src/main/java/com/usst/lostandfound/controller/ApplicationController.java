package com.usst.lostandfound.controller;

import com.usst.lostandfound.entity.Application;
import com.usst.lostandfound.entity.User;
import com.usst.lostandfound.repository.*;
import com.usst.lostandfound.response.Response;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class ApplicationController {
    private final ApplicationRepository appRepo;
    private final UserRepository userRepo;

    public ApplicationController(ApplicationRepository appRepo, UserRepository userRepo) {
        this.appRepo = appRepo;
        this.userRepo = userRepo;
    }

    /**
     * 用户申请认领失物
     *
     * @param app 一个包含foundId和phone的application对象
     * @return Response
     */
    @PostMapping(value = "/apps")
    public Response apply(@RequestBody Application app) {
        if (app.getFoundId() == null || app.getPhone() == null) {
            return new Response("lack necessary parameter", 400);
        }
        if (appRepo.findByFoundIdAndPhone(app.getFoundId(), app.getPhone()) != null) {
            return new Response("cannot apply twice!", 400);
        }
        app.setState(0);
        appRepo.save(app);
        return new Response("success to create new application", 200);
    }

    /**
     * 用户取消申请
     *
     * @param appId 一个application对象的ID
     * @return Response
     */
    @Transactional
    @DeleteMapping(value = "/apps/{appId}")
    public Response deleteApp(@PathVariable("appId") Integer appId) {
        if (appRepo.deleteByAppId(appId) == 1) {
            return new Response("success to delete application", 20);
        } else {
            return new Response("fail to delete application", 400);
        }
    }

    /**
     * 获得用户申请列表
     *
     * @param userId 用户ID
     * @return 申请列表
     */
    @GetMapping(value = "/users/{userId}/apps")
    public List<Application> getUserApps(@PathVariable("userId") Integer userId) {
        User user = userRepo.findByUserId(userId);
        return appRepo.findByPhone(user.getPhone());
    }

    /**
     * 获得所有用户未审核的申请列表
     *
     * @return 申请列表
     */
    @GetMapping(value = "/apps")
    public List<Application> getAppsList() {
        System.out.println(1);
        List<Application> lists = appRepo.findByState(0);
        for (Application app : lists) {
            System.out.println(app);
        }
        System.out.println(2);
        return appRepo.findByState(0);
    }
}
