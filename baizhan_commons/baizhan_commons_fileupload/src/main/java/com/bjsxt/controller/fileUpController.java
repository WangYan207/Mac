package com.bjsxt.controller;

import com.bjsxt.commons.pojo.BaizhanResult;
import com.bjsxt.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class fileUpController {
    @Autowired
    FileUploadService fileUploadService;
    @RequestMapping("/file/upload")
    public BaizhanResult fileUpload(MultipartFile file){
        BaizhanResult baizhanResult = fileUploadService.fileUpload(file);
        System.out.println("牛啊牛啊");
        return baizhanResult;
    }
}
