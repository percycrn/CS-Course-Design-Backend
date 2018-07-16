package com.usst.lostandfound.controller;

import com.usst.lostandfound.entity.Found;
import com.usst.lostandfound.entity.Prompt;
import com.usst.lostandfound.repository.*;
import com.usst.lostandfound.response.Response;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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
    @GetMapping(value = "/users/{userId}/prompts")
    public List<Prompt> getPrompts(@PathVariable("userId") Integer userId) {
        return promptRepo.findByLostPhone(userRepo.findByUserId(userId).getPhone());
    }

    /**
     * 删除一个认领信息
     *
     * @param promptId 某个认领对象的ID
     * @return Response
     */
    @Transactional
    @DeleteMapping(value = "/prompt/{promptId}")
    public Response deletePrompt(@PathVariable("promptId") Integer promptId) {
        if (promptRepo.deleteByPromptId(promptId) == 1) {
            return new Response("success to delete prompt", 200);
        } else {
            return new Response("fail to delete prompt", 400);
        }
    }

}
