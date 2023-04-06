//package com.qingshuge.service;
//
//import com.qingshuge.bean.User;
//import com.qingshuge.mapper.UserMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//    @Autowired
//    private UserMapper userMapper;
//
//    public void register(User user) {
//        User existingUser = userMapper.findByUsername(user.getUsername());
//        if (existingUser != null) {
//            throw new RuntimeException("Username already exists");
//        }
//        userMapper.save(user);
//    }
//}

package com.qingshuge.service;
import com.qingshuge.bean.User;
import com.qingshuge.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {

    //autowire:可以对类成员变量、方法以及构造函数进行标注，完成自动装配工作
    @Autowired
    UserMapper userMapper;


    // 注册操作
    public String register( User user) {
        System.out.println(user.getUsername());
        try {
            User userExist = userMapper.findByName(user.getUsername());

            if (user.getUsername()==null) {
                return "用户名不能为空";
            }else if (user.getPassword()==null) {
                return "密码不能为空";
            }else if (userExist != null) {
                return "账号已经存在";
            }else {
                userMapper.save(user);
                return "ok";
            }
        }catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    // 获取所有用户
    public List<User> findAll() {
        List<User> list = userMapper.findAll();
        return list;
    }
}
