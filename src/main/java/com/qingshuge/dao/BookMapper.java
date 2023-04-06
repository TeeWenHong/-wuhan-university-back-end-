package com.qingshuge.dao;


import com.qingshuge.bean.Book;
import com.qingshuge.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
@Mapper
public interface BookMapper {


//    List<Book> SearchBook(@Param("bookname") List<Book> bookname);
    List<Book> SearchBook(@Param("bookname") String bookname);

//    @Insert("INSERT INTO file_upload(file_id, file_name, file_type, file_size, file_url) VALUES(#{fileId}, #{fileName}, #{fileType}, #{fileSize}, #{fileUrl})")


//    上传文件
    void insertFile(@Param("book_id") String book_id ,@Param("user_id") int user_id,@Param("bookname") String bookname,@Param("book_path") String book_path,@Param("fileType") String fileType);
//void insertFile(@Param("book_id") String book_id ,@Param("user_id") int user_id,@Param("bookname") String bookname,@Param("book_path") String book_path);
//    @Select("SELECT * FROM file_upload WHERE file_id = #{fileId}")

//    用书本号查找书籍
    Book selectFileById(@Param("book_id") String book_id);



}

