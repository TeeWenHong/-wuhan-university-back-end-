package com.qingshuge.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qingshuge.bean.Book;
import com.qingshuge.bean.Image;
import com.qingshuge.bean.Product;
import com.qingshuge.bean.ShowAllInfo;
import com.qingshuge.dao.BookMapper;
import com.qingshuge.service.SearchService;
//import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
        System.out.println(books);
        return books;
    }
    //tag搜索
    @RequestMapping("/searchTag")
    public List<Book> searchTag(@RequestParam("tag") String tag) {
        List<Book> books = bookMapper.searchTag(tag);
//        System.out.println(books);
        return books;
    }

    @RequestMapping("/searchId")
    public List<ShowAllInfo> searchId(@RequestParam("user_id") int user_id) {
        List<ShowAllInfo> books = bookMapper.searchId(user_id);
//        System.out.println(books);
        return books;
    }

    @RequestMapping("/searchBookId")
    public List<ShowAllInfo> searchBookId(@RequestParam("book_id") int book_id) {
        List<ShowAllInfo> books = bookMapper.searchBookId(book_id);
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

    private final RestHighLevelClient restHighLevelClient;

    @Autowired
    public SearchController1(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }



    public List<Book> query(QueryBuilder QueryBuilder) throws IOException {
//        Product product = new Product();
        SearchRequest searchRequest = new SearchRequest("book");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.query(QueryBuilder);
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.
                search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse.getHits().getTotalHits().value);
        System.out.println(searchResponse.getHits().getMaxScore());

//        List<SearchHit> hits = List.of(searchResponse.getHits().getHits());
        SearchHit[] hits = searchResponse.getHits().getHits();
        List<Book> bookList = new ArrayList<>();

        System.out.println(hits);
        for (SearchHit hit :hits){
//            String[1,2,3,4] = hit.getSourceAsString();
            System.out.println("id"+hit.getId()+"source"+hit.getSourceAsString());
            Book book = new ObjectMapper().readValue(hit.getSourceAsString(),Book.class);
            bookList.add(book);
        }
        System.out.println(bookList);
//
        return bookList;
    }

    @RequestMapping("/searchBookNamees")
    public List<Book> testQuery(String val) throws IOException{
        return query(QueryBuilders.
                multiMatchQuery(val,"tag","bookname","brief_book"));
    }



    public List<Image> querypic(QueryBuilder QueryBuilder) throws IOException {
        SearchRequest searchRequest = new SearchRequest("pic");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.query(QueryBuilder).postFilter(QueryBuilders.termQuery("buy_flag","true"));
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.
                search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse.getHits().getTotalHits().value);
        System.out.println(searchResponse.getHits().getMaxScore());

        SearchHit[] hits = searchResponse.getHits().getHits();
        List<Image> picList = new ArrayList<>();

        System.out.println(hits);
        for (SearchHit hit :hits){
            System.out.println("id"+hit.getId()+"source"+hit.getSourceAsString());
            Image image = new ObjectMapper().readValue(hit.getSourceAsString(),Image.class);
            picList.add(image);
        }
        System.out.println(picList);
        return picList;
    }




    @RequestMapping("/searchPicNamees")
    public List<Image> Query(String val) throws IOException{
        return querypic(QueryBuilders.
                multiMatchQuery(val,"picname","brief_pic"));
    }

}
