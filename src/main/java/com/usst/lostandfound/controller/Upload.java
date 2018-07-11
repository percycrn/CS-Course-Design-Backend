package com.usst.lostandfound.controller;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.util.Calendar;

class Upload {
    static String upLoad(String path, String phone) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // 生成上传凭证，然后准备上传
        String accessKey = "rw7a4ekcvSuEc70rLEQG9Ik_5AHG6kDAj4GDgeRh";
        String secretKey = "qfuGzLv85iuMsiOxuiZ9avVPQLWH7Lr8mlG7fOOq";
        String bucket = "lostandfound";
        String baseUrl = "http://p8pjrhsrh.bkt.clouddn.com/";
        Calendar now = Calendar.getInstance();
        String key = phone + "-" + now.get(Calendar.YEAR) + "-"
                + now.get(Calendar.MONTH) + "-"
                + now.get(Calendar.DAY_OF_MONTH) + "-"
                + now.get(Calendar.HOUR_OF_DAY) + "-"
                + now.get(Calendar.MINUTE);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(path, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(baseUrl + putRet.key);
            baseUrl = baseUrl + putRet.key;
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return baseUrl;
    }
}
