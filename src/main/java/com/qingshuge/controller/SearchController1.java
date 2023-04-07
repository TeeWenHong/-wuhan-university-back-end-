package com.qingshuge.controller;

import com.alibaba.fastjson.JSON;
import com.qingshuge.bean.Book;
import com.qingshuge.bean.ShowAllInfo;
import com.qingshuge.dao.BookMapper;
import com.qingshuge.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchController1 {

    @Autowired
    SearchService searchService;

    @Autowired
    BookMapper bookMapper;

//    @RequestMapping("/search")
//    public List<Book> search(@RequestBody String bookname) {
//        return searchService.searchBooks(bookname);
//    }
    //书名搜索书籍
    @RequestMapping("/searchBookName")
    public List<Book> search(@RequestParam("bookname") String bookname) {
        List<Book> books = searchService.searchBooks(bookname);
//        System.out.println(books);
        return books;
    }
    //tag搜索
    @RequestMapping("/searchTag")
    public List<Book> searchTag(@RequestParam("tag") String tag) {
        List<Book> books = bookMapper.searchTag(tag);
//        System.out.println(books);
        return books;
    }

    //书名搜索书籍
    @RequestMapping("/searchAll")
    public List<ShowAllInfo> searchAll(@RequestParam("bookname") String bookname) {
        List<ShowAllInfo> ShowAllInfo = bookMapper.searchAll(bookname);
//        System.out.println(books);
        return ShowAllInfo;
    }


//    @RequestMapping(value = "/alluser", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
//    public List<User> findAll() {
//        return userService.findAll();
//    }


}
