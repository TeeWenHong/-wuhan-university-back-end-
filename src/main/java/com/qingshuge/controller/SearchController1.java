package com.qingshuge.controller;

import com.alibaba.fastjson.JSON;
import com.qingshuge.bean.Book;
import com.qingshuge.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchController1 {

    @Autowired
    SearchService searchService;

//    @RequestMapping("/search")
//    public List<Book> search(@RequestBody String bookname) {
//        return searchService.searchBooks(bookname);
//    }
    @RequestMapping("/search1")
    public String search(@RequestParam("bookname") String bookname) {
        List<Book> books = searchService.searchBooks(bookname);
        return JSON.toJSONString(books);
    }



//    @RequestMapping(value = "/alluser", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
//    public List<User> findAll() {
//        return userService.findAll();
//    }


}
