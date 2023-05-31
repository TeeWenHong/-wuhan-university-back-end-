package com.qingshuge.dao;


import com.qingshuge.bean.Chapters;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ChaptersMapper {

    Chapters showChapters(@Param("book_id") int book_id,@Param("chapters") int chapters);

}
