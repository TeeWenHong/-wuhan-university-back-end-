package com.qingshuge.service;

import com.qingshuge.bean.Book;
import com.qingshuge.bean.User;
import com.qingshuge.dao.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class SearchService {
    @Autowired
    BookMapper bookMapper;

    public List<Book> searchBooks(String bookname) {
        // 使用MyBatis的模糊查询功能
        List<Book> books = bookMapper.SearchBook(bookname);
        return books;
    }


}
