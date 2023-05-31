package com.qingshuge.dao;

import com.qingshuge.bean.Markdown;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MarkdownMapper {
    void saveMarkdown(Markdown markdown);
}
