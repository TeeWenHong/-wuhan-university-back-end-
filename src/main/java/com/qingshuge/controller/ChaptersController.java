package com.qingshuge.controller;


import com.qingshuge.bean.Chapters;
import com.qingshuge.bean.ShowAllInfo;
import com.qingshuge.dao.ChaptersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChaptersController {

    @Autowired
    ChaptersMapper chaptersMapper;

    @RequestMapping("/searchchapters")
    public Chapters searchchapters(@RequestParam("book_id") int book_id,@RequestParam("chapters") int chapters) {
        Chapters ShowAllInfo = chaptersMapper.showChapters(book_id,chapters);

        return ShowAllInfo;
    }

}
