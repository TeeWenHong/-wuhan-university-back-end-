package com.qingshuge.service;

import com.qingshuge.bean.User;
import com.qingshuge.dao.UserMapper;
import org.python.bouncycastle.asn1.ocsp.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class UploadService {

    @Autowired
    UserMapper userMapper;


    // 注册操作
    public String Upload( User user,int id) {
        System.out.println(user.getUsername());
        try {
            User userExist = userMapper.findByName(user.getUsername());

            if (user.getUsername()==null) {
                return "用户名不能为空";
            }else if (user.getPassword()==null) {
                return "密码不能为空";
            }else if (user.getEmail()==null) {
                return "邮箱不能为空";
            }else if (user.getSex()==null) {
                return "性别不能为空";
            }else if (user.getPhone()==null) {
                return "手机号码不能为空";
            }else if (userExist != null) {
                return "账号已经存在";
            }else {
                userMapper.upload(user,id);
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
