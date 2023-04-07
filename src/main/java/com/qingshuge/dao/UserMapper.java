package com.qingshuge.dao;

import com.qingshuge.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
//    @Insert("INSERT INTO users(username, password) VALUES(#{username}, #{password})")
//    @Select("SELECT * FROM users WHERE username = #{username}")
//    User findByUsername(String username);

     User getUserByMassage(@Param("username") String username, @Param("password") String password);

//    //通过账号密码来查询信息
//    List<User> queryUserByUp(@Param("username") String username, @Param("password") String password);
//    //查询所有用户
//    List<User> queryUserList();
//    //增加用户
//    int addUser(@Param("user") User user);
//    //通过用户名来进行查询
//    List<User> queryUserByUserName(@Param("username") String username);
//
//    void addUser(@Param("username") String username, @Param("password") String password);

    int bookNumber(@Param("id") int id);

    List<User> findAll();
    User findByName(String username);
//    String findPswByName(String userName);
    void save(User user);

    void upload(@Param("user")User user,@Param("id")int id);
}

