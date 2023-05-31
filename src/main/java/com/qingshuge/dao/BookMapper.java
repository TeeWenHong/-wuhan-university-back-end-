package com.qingshuge.dao;


import com.qingshuge.bean.Book;
import com.qingshuge.bean.ShowAllInfo;
import com.qingshuge.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
@Mapper
public interface BookMapper {


//    List<Book> SearchBook(@Param("bookname") List<Book> bookname);
    //书名搜索书籍
    List<Book> SearchBook(@Param("bookname") String bookname);

    //书名搜索书籍
    List<Book> searchTag(@Param("tag") String tag);
    List<ShowAllInfo> searchId(@Param("user_id") int user_id);

    List<ShowAllInfo> searchBookId(@Param("book_id") int book_id);
//    用书名搜索全部信息
    List<ShowAllInfo> searchAll(@Param("bookname") String bookname);

//    @Insert("INSERT INTO file_upload(file_id, file_name, file_type, file_size, file_url) VALUES(#{fileId}, #{fileName}, #{fileType}, #{fileSize}, #{fileUrl})")


//    上传文件
    void insertFile(@Param("user_id") int user_id,@Param("bookname") String bookname,@Param("book_path") String book_path,@Param("fileType") String fileType,@Param("tag") String tag);
//    void insertFile(@Param("user_id") int user_id,@Param("bookname") String bookname,@Param("book_path") String book_path,@Param("fileType") String fileType);
//    @Select("SELECT * FROM file_upload WHERE file_id = #{fileId}")

//    用书本号查找书籍
    Book selectFileById(@Param("book_id") String book_id);

    int deleteBook(@Param("book_id") int book_id);


//    @RequestParam("brief_book") String brief_book,@RequestParam("bookname") String bookname, @RequestParam("id") int id,@RequestParam("tag") String tag
//    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "book_id", before = false, resultType = Book.class)
    int uploadBook(@Param("brief_book") String brief_book, @Param("bookname") String bookname, @Param("user_id") int user_id, @Param("tag") String tag);


//    @Insert("INSERT INTO book (user_id, bookname, brief_book, tag, pic_id) VALUES (#{user_id}, #{bookname}, #{brief_book}, #{tag}, 0)")
//    @Options(useGeneratedKeys = true, keyProperty = "book_id")
//    int uploadBook(Book book);
    int updateBookPic(@Param("book_id") int book_id,@Param("pic_id") int pic_id);


}

