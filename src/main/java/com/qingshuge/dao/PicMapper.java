package com.qingshuge.dao;

import com.qingshuge.bean.Image;
import com.qingshuge.bean.ShowAllInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface PicMapper {

    List<ShowAllInfo> searchAllPic(@Param("pic_id") int pic_id);

    List<ShowAllInfo> searchMyAllPic(@Param("user_id") int user_id);


    List<ShowAllInfo> searchIdPic(@Param("user_id") int user_id);

    List<ShowAllInfo> searchIdPices(@Param("user_id") int user_id);

//    Image searchPicIdPic(@Param("pic_id") int pic_id);

    int deletePic(@Param("pic_id") int pic_id);

    int setBuyFlag(@Param("user_id") int user_id,@Param("pic_id") int pic_id);

//    (#{user_id},#{picname},#{picture_path},#{HASH},#{price},#{brief_pic})
    int savePic(@Param("user_id") int user_id,@Param("picname") String picname,@Param("picture_path") String picture_path,@Param("price") int price,@Param("brief_pic") String brief_pic);

    int savePicBook(@Param("user_id") int user_id,@Param("picname") String picname,@Param("picture_path") String picture_path,@Param("price") int price,@Param("brief_pic") String brief_pic);

//    (user_id,picname,picture_path,price,brief_pic,buy_flag)
//    int savePic(@Param("user_id") int user_id,@Param("picname") String picname,@Param("price") int price,@Param("brief_pic") String brief_pic);

    int savePicCache(@Param("user_id") int user_id,@Param("picture_path") String picture_path);
//    int setUserId(@Param("user_id") int user_id,@Param("pic_id") int pic_id);

    int deletePicCache(@Param("user_id") int user_id);

    int deletePicName(@Param("picture_path") String picture_path);

    Image findByPath(@Param("pic_id") int pic_id);

    Image findByPathName(@Param("picture_path") String picture_path);

}
