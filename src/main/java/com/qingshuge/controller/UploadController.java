package com.qingshuge.controller;

import com.qingshuge.bean.User;
import com.qingshuge.service.UploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UploadController {

    @Autowired
    UploadService uploadService;

    @RequestMapping("/UpdateForm")
    public String UpdateForm(@RequestParam("id") int id,@RequestBody User user) {
//        User us = userMapper.getUserByMassage(user.getUsername(),user.getPassword());

        return uploadService.Upload(user,id);
    }


}
