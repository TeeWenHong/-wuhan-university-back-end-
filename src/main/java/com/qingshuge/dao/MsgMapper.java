package com.qingshuge.dao;


import com.qingshuge.bean.Msg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
@Mapper
public interface MsgMapper {

    List<Msg> showMsg(@Param("to_user_id") int to_user_id);

    int reject(@Param("user_id") int user_id,@Param("pic_id") int pic_id);
    int rejectAll(@Param("pic_id") int pic_id);
    int buy(@Param("user_id") int user_id,@Param("to_user_id") int to_user_id,@Param("pic_id") int pic_id);
}
