package com.qingshuge.controller;

import com.qingshuge.bean.Markdown;
import com.qingshuge.dao.MarkdownMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/markdown")
public class MarkdownController {
    private final MarkdownMapper markdownMapper;

    public MarkdownController(MarkdownMapper markdownMapper) {
        this.markdownMapper = markdownMapper;
    }

    @PostMapping
    public void saveMarkdown(@RequestBody Map<String, String> request) {
        String book_path = request.get("book_path");
        int book_id = Integer.parseInt(request.get("book_id"));
        int chapters = Integer.parseInt(request.get("chapters"));
        String title = request.get("title");

        Markdown markdown = new Markdown();
        markdown.setBook_path(book_path);
        markdown.setBook_id(book_id);
        markdown.setChapters(chapters);
        markdown.setTitle(title);

        markdownMapper.saveMarkdown(markdown);
    }
}
