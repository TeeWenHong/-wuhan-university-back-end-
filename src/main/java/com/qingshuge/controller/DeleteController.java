package com.qingshuge.controller;

import com.qingshuge.bean.ShowAllInfo;
import com.qingshuge.dao.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeleteController {

    @Autowired
    BookMapper bookMapper;

    @RequestMapping("/deleteBook")
    public String deleteBook(@RequestParam("book_id") int book_id ) {
        int ShowAllInfo = bookMapper.deleteBook(book_id);
//        System.out.println(books);
        if(ShowAllInfo == 1){
            return "成功删除";
        }else {
            return "删除失败";
        }
    }

}
