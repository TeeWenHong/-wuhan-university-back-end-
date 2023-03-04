package com.qingshuge.dao;

import com.qingshuge.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    public User getUserByMassage(@Param("username") String username,@Param("password") String password);



}
