package com.usst.lostandfound.controller;

import com.usst.lostandfound.entity.Found;
import com.usst.lostandfound.entity.Lost;
import com.usst.lostandfound.entity.User;
import com.usst.lostandfound.repository.*;
import com.usst.lostandfound.response.Response;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class ReleaseController {
    private final ApplicationRepository appRepo;
    private final FoundRepository foundRepo;
    private final LostRepository lostRepo;
    private final PromptRepository promptRepo;
    private final UserRepository userRepo;

    public ReleaseController(ApplicationRepository appRepo, FoundRepository foundRepo, LostRepository lostRepo,
                             PromptRepository promptRepo, UserRepository userRepo) {
        this.appRepo = appRepo;
        this.foundRepo = foundRepo;
        this.lostRepo = lostRepo;
        this.promptRepo = promptRepo;
        this.userRepo = userRepo;
    }

    /**
     * 发布found信息
     *
     * @param pic   上传的图片文件
     * @param found 发布的found信息
     * @return Response
     */
    @PostMapping(value = "/found")
    public Response createFound(@RequestParam("pic") MultipartFile pic, @RequestBody Found found) {
        found.setPic(getPicUrl(pic, found.getFoundPhone()));
        foundRepo.save(found);
        return new Response("success to create new found", 200);
    }

    /**
     * 发布Lost信息
     *
     * @param pic  上传的图片文件
     * @param lost 发布的lost信息
     * @return Response
     */
    @PostMapping(value = "/lost")
    public Response createLost(@RequestParam("pic") MultipartFile pic, @RequestBody Lost lost) {
        lost.setFound(0);
        lost.setPic(getPicUrl(pic, lost.getLostPhone()));
        return new Response("success to create new lost", 200);
    }

    /**
     * 删除found信息
     *
     * @param foundId found对象的ID
     * @return Response
     */
    @DeleteMapping(value = "/found/{foundId}")
    public Response deleteFound(@PathVariable("foundId") Integer foundId) {
        if (foundRepo.deleteByFoundId(foundId) == 1) {
            return new Response("success to delete found", 200);
        } else {
            return new Response("fail to delete found", 400);
        }
    }

    /**
     * 删除Lost信息
     *
     * @param lostId lost对象的ID
     * @return Response
     */
    @DeleteMapping(value = "/lost/{lostId}")
    public Response deleteLost(@PathVariable("lostId") Integer lostId) {
        if (lostRepo.deleteByLostId(lostId) == 1) {
            return new Response("success to delete lost", 200);
        } else {
            return new Response("fail to delete lost", 400);
        }
    }

    /**
     * 修改found信息
     *
     * @param pic       上传的图片文件
     * @param foundId   found对象的ID
     * @param foundInfo 包含修改内容的found对象
     * @return Response
     */
    @PutMapping(value = "/found/{foundId}")
    public Response updateFound(@RequestParam("pic") MultipartFile pic, @PathVariable("foundId") Integer foundId,
                                @RequestBody Found foundInfo) {
        Found found = foundRepo.findByFoundId(foundId);
        found.setName(foundInfo.getName());
        found.setLocation(foundInfo.getLocation());
        found.setTime(foundInfo.getTime());
        found.setOutline(foundInfo.getOutline());
        found.setStorage(foundInfo.getStorage());
        String url = getPicUrl(pic, found.getFoundPhone());
        if (url != null) {
            found.setPic(url);
        }
        foundRepo.save(found);
        return new Response("success to update found", 200);
    }

    /**
     * 修改lost信息
     *
     * @param pic      上传的图片文件
     * @param lostId   lost对象的ID
     * @param lostInfo 包含修改内容的found对象
     * @return Response
     */
    @PutMapping(value = "/lost/{lostId}")
    public Response updateLost(@RequestParam("pic") MultipartFile pic, @PathVariable("lostId") Integer lostId,
                               @RequestBody Lost lostInfo) {
        Lost lost = lostRepo.findByLostId(lostId);
        lost.setName(lostInfo.getName());
        lost.setLocation(lostInfo.getLocation());
        lost.setTime(lostInfo.getTime());
        lost.setOutline(lostInfo.getOutline());
        String url = getPicUrl(pic, lost.getLostPhone());
        if (url != null) {
            lost.setPic(url);
        }
        lostRepo.save(lost);
        return new Response("success to update lost", 200);
    }

    /**
     * 查看found信息
     *
     * @param userId 用户ID
     * @return 所有用户发布的found信息
     */
    @GetMapping(value = "/users/{userId}/found")
    public List<Found> getUserFound(@PathVariable("userId") Integer userId) {
        User user = userRepo.findByUserId(userId);
        return foundRepo.findByFoundPhone(user.getPhone());
    }

    /**
     * 查看lost信息
     *
     * @param userId 用户ID
     * @return 所有用户发布的lost信息
     */
    @GetMapping(value = "/users/{userId}/lost")
    public List<Lost> getUserLost(@PathVariable("userId") Integer userId) {
        User user = userRepo.findByUserId(userId);
        return lostRepo.findByLostPhone(user.getPhone());
    }

    /**
     * 查看他人发布的found信息
     *
     * @param userId 用户ID
     * @return 所有其他用户发布的found信息
     */
    @GetMapping(value = "/users/{userId}/found/other")
    public List<Found> getOtherFound(@PathVariable("userId") Integer userId) {
        User user = userRepo.findByUserId(userId);
        return foundRepo.findByFoundPhoneNot(user.getPhone());
    }

    /**
     * 查看他人发布的lost信息
     *
     * @param userId 用户ID
     * @return 所有其他用户发布的lost信息
     */
    @GetMapping(value = "/users/{userId}/lost/other")
    public List<Lost> getOtherLost(@PathVariable("userId") Integer userId) {
        User user = userRepo.findByUserId(userId);
        return lostRepo.findByLostPhoneNot(user.getPhone());
    }

    private static String getPicUrl(MultipartFile pic, String phone) {
        String url = null;
        if (!pic.isEmpty()) {
            try {
                File path = new File(ResourceUtils.getURL("path:").getPath());
                if (!path.exists()) path = new File("");
                System.out.println("path:" + path.getAbsolutePath());
                File upload = new File(path.getAbsolutePath(), "src/img/");
                if (!upload.exists()) upload.mkdirs();
                System.out.println("upload url:" + upload.getAbsolutePath());

                File uploadFile = new File(upload.getAbsolutePath() + pic.getOriginalFilename());
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(uploadFile));
                System.out.println(pic.getOriginalFilename());
                String args[] = pic.getOriginalFilename().split("\\.");
                System.out.println(args[1]);
                out.write(pic.getBytes());
                out.flush();
                out.close();
                url = Upload.upLoad(uploadFile.getAbsolutePath(), phone);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return url;
    }
}
