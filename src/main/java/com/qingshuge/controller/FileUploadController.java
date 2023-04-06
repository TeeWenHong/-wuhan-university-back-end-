package com.qingshuge.controller;
import com.qingshuge.bean.Book;
import com.qingshuge.dao.BookMapper;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.apache.tika.Tika;

@RestController
public class FileUploadController {

//    @Autowired
//    private BookMapper fileUploadMapper;

    @Autowired
    BookMapper bookMapper;

//    @PostMapping("/upload")
//    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") int id) {
//        // 获取文件原始名
//        String originalFilename = file.getOriginalFilename();
//        // 获取文件后缀名
//        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
//        // 生成新的文件名
//        String newFileName = UUID.randomUUID().toString() + suffixName;
//        // 构建文件存储路径
//        File destFile = new File("C:\\Users\\Huawei\\Desktop\\img\\" + newFileName);
//        try {
//            // 将文件保存到指定路径
//            file.transferTo(destFile);
//            // 将文件信息插入到数据库
//            Book book = new Book();
//            book.setBook_id(UUID.randomUUID().toString());
//            book.setBookname(originalFilename);
//            book.setBook_path(destFile.getAbsolutePath());
//            book.setUser_id(id);
//            bookMapper.insertFile(book.getBook_id(),book.getUser_id(),book.getBookname(),book.getBook_path());
//            return "上传成功";
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "上传失败";
//    }



// ...

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") int id) {
        // 获取文件原始名
        String originalFilename = file.getOriginalFilename();
        // 获取文件后缀名
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 生成新的文件名
        String newFileName = UUID.randomUUID().toString() + suffixName;
        // 构建文件存储路径
        File destFile = new File("C:\\Users\\Huawei\\Desktop\\img\\" + newFileName);
        try {
            // 将文件保存到指定路径
            file.transferTo(destFile);
            // 将文件信息插入到数据库
            Book book = new Book();
            book.setBook_id(UUID.randomUUID().toString());
            book.setBookname(originalFilename);
            book.setBook_path(destFile.getAbsolutePath());
            book.setUser_id(id);
            Tika tika = new Tika();
            String fileType = tika.detect(destFile);
            book.setFileType(fileType);
            bookMapper.insertFile(book.getBook_id(), book.getUser_id(), book.getBookname(), book.getBook_path(), book.getFileType());
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }


    @GetMapping("/file/{book_id}")
    public Book getFileById(@PathVariable("book_id") String book_id) {
        return bookMapper.selectFileById(book_id);
    }



}