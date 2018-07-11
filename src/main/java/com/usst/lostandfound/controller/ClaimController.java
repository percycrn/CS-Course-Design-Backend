package com.usst.lostandfound.controller;

import com.usst.lostandfound.entity.Found;
import com.usst.lostandfound.entity.Prompt;
import com.usst.lostandfound.repository.*;
import com.usst.lostandfound.response.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClaimController {
    private final FoundRepository foundRepo;
    private final PromptRepository promptRepo;
    private final UserRepository userRepo;

    public ClaimController(FoundRepository foundRepo, PromptRepository promptRepo, UserRepository userRepo) {
        this.foundRepo = foundRepo;
        this.promptRepo = promptRepo;
        this.userRepo = userRepo;
    }

    /**
     * 提示他人人领
     *
     * @param prompt 提示信息对象
     * @return Response
     */
    @PostMapping(value = "/prompts")
    public Response prompt(@RequestBody Prompt prompt) {
        promptRepo.save(prompt);
        return new Response("success to create new prompt", 200);
    }

    /**
     * 查看他人提示的认领信息
     *
     * @param userId 用户ID
     * @return 提示信息数组
     */
    @GetMapping(value = "/prompts/{userId}")
    public List<Prompt> getPrompts(@PathVariable("userId") Integer userId) {
        return promptRepo.findByLostPhone(userRepo.findByUserId(userId).getPhone());
    }

    /**
     * 查看他人提示的单个found信息
     *
     * @param foundId 准备查找的found对象的ID
     * @return found对象
     */
    @GetMapping(value = "/founds/{foundId}/prompts")
    public Found getPromptFound(@PathVariable("foundId") Integer foundId) {
        return foundRepo.findByFoundId(foundId);
    }
}
