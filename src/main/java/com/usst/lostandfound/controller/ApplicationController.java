package com.usst.lostandfound.controller;

import com.usst.lostandfound.entity.Application;
import com.usst.lostandfound.repository.*;
import com.usst.lostandfound.response.Response;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {
    private final ApplicationRepository appRepo;

    public ApplicationController(ApplicationRepository appRepo) {
        this.appRepo = appRepo;
    }

    /**
     * 用户申请认领失物
     *
     * @param app 一个包含foundId和phone的application对象
     * @return Response
     */
    @PostMapping(value = "/apps")
    public Response apply(@RequestBody Application app) {
        Application appSaved = new Application();
        appSaved.setFoundId(app.getFoundId());
        appSaved.setPhone(app.getPhone());
        appSaved.setTime(System.currentTimeMillis());
        appSaved.setState(0);
        appRepo.save(appSaved);
        return new Response("success to create new application", 200);
    }

    /**
     * 用户取消申请
     *
     * @param appId 一个application对象的ID
     * @return Response
     */
    @DeleteMapping(value = "/apps/{appId}")
    public Response deleteApp(@PathVariable("appId") Integer appId) {
        if (appRepo.deleteByAppId(appId) == 1) {
            return new Response("success to delete application", 20);
        } else {
            return new Response("fail to delete application", 400);
        }
    }

}
