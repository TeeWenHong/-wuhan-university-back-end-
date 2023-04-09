package com.qingshuge.dao;

import com.qingshuge.bean.ShowAllInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface PicMapper {

    List<ShowAllInfo> searchAllPic(@Param("pic_id") String pic_id);

    List<ShowAllInfo> searchMyAllPic(@Param("user_id") int user_id);


    List<ShowAllInfo> searchIdPic(@Param("user_id") int user_id);

    int deletePic(@Param("pic_id") int pic_id);
}
