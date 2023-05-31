package com.qingshuge.controller;

import com.alibaba.fastjson.JSON;
import com.qingshuge.bean.Book;
import com.qingshuge.bean.User;

import com.qingshuge.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class LoginController {

    @Autowired
    UserMapper userMapper;


//  登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestBody  User user){
        String flag = "error";
//        System.out.println("user"+ user.getUsername());
        User us = userMapper.getUserByMassage(user.getUsername(),user.getPassword());
        int fans = userMapper.bookNumber(us.getId());

        System.out.println("nooknumber  "+ fans);
//        System.out.println("id:"+ user.getId());
//        System.out.println("emai:"+ user.getId());
        HashMap<String,Object> res = new HashMap<>();
        if (us != null){
            flag ="ok";
        }
        res.put("user",us);
        res.put("flag",flag);
        res.put("bookNumber",fans);

        String res_json = JSON.toJSONString(res);
        System.out.println(res_json);
        
        return res_json;
    }

//    有多少本书
    @RequestMapping(value = "/bookNumber",method = RequestMethod.GET)
    public int bookNumber(@RequestParam("id") int id){
        int booknumber = userMapper.bookNumber(id);
        return booknumber;
    }

    @RequestMapping("/money")
    public User money(@RequestParam("id") int id) {
        User books = userMapper.money(id);
        System.out.println(books);
        return books;
    }

    @RequestMapping("/setMoney")
    public int setMoney(@RequestParam("id") int id,@RequestParam("money") int money) {
        int books = userMapper.setMoney(id,money);

        return books;
    }




//    @RequestMapping("/money")
//    public String money(@RequestParam("id") int id) {
//        User books = userMapper.money(id);
//        HashMap<String,Object> res = new HashMap<>();
//        res.put("money",books.getMoney());
//        String res_json = JSON.toJSONString(res);
////        System.out.println(books);
//        return res_json;
//    }

}
