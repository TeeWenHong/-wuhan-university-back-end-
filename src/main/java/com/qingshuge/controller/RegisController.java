package com.qingshuge.controller;

import com.qingshuge.bean.User;
import com.qingshuge.dao.UserMapper;
import com.qingshuge.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegisController {
    @Autowired
    UserService userService;

//    // 登录
//    @RequestMapping("/login")
//    public String login(User user) {
//        return userService.login(user);
//    }

    // 注册
    @RequestMapping("/reg")
    public String register(@RequestBody User user) {
        return userService.register(user);
    }

    // 解决查询数据库中文出现乱码问题
    @RequestMapping(value = "/alluser", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<User> findAll() {
        return userService.findAll();
    }

}

