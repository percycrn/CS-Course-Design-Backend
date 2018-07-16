package com.usst.lostandfound.controller;

import com.usst.lostandfound.entity.User;
import com.usst.lostandfound.repository.*;
import com.usst.lostandfound.response.Response;
import com.usst.lostandfound.response.SignIn;
import com.usst.lostandfound.response.Users;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServiceController {
    private final UserRepository userRepo;

    public ServiceController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * 用户注册
     *
     * @param user 用户注册信息
     * @return Response
     */
    @PostMapping(value = "/signup")
    public Response signUp(@RequestBody User user) {
        if (user.getPassword() == null || user.getPhone() == null || user.getId() == null || user.getName() == null) {
            return new Response("missing some information", 400);
        }
        if (userRepo.findByPhone(user.getPhone()) != null) {
            return new Response("The phone has already been used", 400);
        }
        user.setCredit(0);
        userRepo.save(user);
        return new Response("success to sign up", 200);
    }

    /**
     * 用户管理员登录
     * TODO redis session
     *
     * @param userSignIn 登录的账号密码保存在这个对象中
     * @return 成功登录则返回一个
     */
    @PostMapping(value = "/signin")
    public SignIn signIn(@RequestBody User userSignIn) {
        User user = userRepo.findByPhone(userSignIn.getPhone());
        if (user != null && user.getPassword().equals(userSignIn.getPassword())) {
            return new SignIn("success to sign in", 200, new Users(user.getUserId(), user.getPhone()));
        } else {
            return new SignIn("account or password is incorrect", 400, null);
        }
    }

    /**
     * 用户修改个人信息
     *
     * @param userId   路径参数 用户Id
     * @param userInfo 用户信息修改的内容
     * @return Response
     */
    @PutMapping(value = "/users/{userId}")
    public Response updateUserInfo(@PathVariable("userId") Integer userId, @RequestBody User userInfo) {
        User user = userRepo.findByUserId(userId);
        if (user == null) {
            return new Response("account doesn't exist", 400);
        }
        String password = userInfo.getPassword();
        if (password != null && !password.equals("")) {
            user.setPassword(password);
        }
        user.setName(userInfo.getName());
        user.setEmail(userInfo.getEmail());
        user.setId(userInfo.getId());
        user.setAddress(userInfo.getAddress());
        userRepo.save(user);
        return new Response("success to update user information", 200);
    }

    /**
     * 用户获得个人信息
     *
     * @param userId 用户ID
     * @return 用户对象
     */
    @GetMapping(value = "/users/{userId}")
    public User getUserInfo(@PathVariable("userId") Integer userId) {
        User user = userRepo.findByUserId(userId);
        user.setPassword("");
        return user;
    }

    /**
     * 用户获得个人信息
     *
     * @param phone 用户手机号
     * @return 用户对象
     */
    @GetMapping(value = "/user")
    public User getUserInfoByPhone(@RequestParam("phone") String phone) {
        return userRepo.findByPhone(phone);
    }

}
