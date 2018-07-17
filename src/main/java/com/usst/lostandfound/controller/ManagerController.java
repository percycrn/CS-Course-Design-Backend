package com.usst.lostandfound.controller;

import com.usst.lostandfound.entity.Application;
import com.usst.lostandfound.entity.Found;
import com.usst.lostandfound.repository.*;
import com.usst.lostandfound.response.Audit;
import com.usst.lostandfound.response.Response;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerController {
    private final ApplicationRepository appRepo;
    private final FoundRepository foundRepo;

    public ManagerController(ApplicationRepository appRepo, FoundRepository foundRepo) {
        this.appRepo = appRepo;
        this.foundRepo = foundRepo;
    }

    /**
     * 管理员审核申请
     *
     * @param appId 申请ID
     * @param audit 审核结果
     * @return Response
     */
    @PatchMapping(value = "/apps/{appId}")
    public Response audit(@PathVariable("appId") Integer appId, @RequestBody Audit audit) {
        Application app = appRepo.findByAppId(appId);
        if (app == null) {
            return new Response("application does not exist", 400);
        }
        app.setState(audit.getState());
        if (audit.getState() == 1) {
            Found found = foundRepo.findByFoundId(app.getFoundId());
            found.setLostPhone(app.getPhone());
            foundRepo.save(found);
        }
        appRepo.save(app);
        return new Response("success to audit", 200);
    }
}
