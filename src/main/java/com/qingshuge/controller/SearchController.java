package com.qingshuge.controller;

import com.qingshuge.bean.SearchResult;
import com.qingshuge.service.test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
public class SearchController {

    @PostMapping("/search")
    public ResponseEntity search(MultipartFile file) {
//        file="C:\\Users\\Huawei\\Desktop\\img\\download.jpg";
        try {
            List<SearchResult> list = test.search(file.getInputStream());

            return ResponseEntity.ok(list);
        } catch (Throwable e) {
            return ResponseEntity.status(400).body(null);
        }
    }

//    @PostMapping("/upload")
//    public void upload(){
//    }
}