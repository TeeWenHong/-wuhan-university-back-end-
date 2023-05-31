package com.qingshuge.controller;

import com.qingshuge.bean.ResponseResult;
import lombok.val;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class testController {

    @PostMapping("/upload")
    public ResponseResult uploadFile(@RequestParam(value = "file",required = false) MultipartFile file){
        // 判断文件是否为空
        if(file.isEmpty()){
            return ResponseResult.fail();
        }
        // 获取传过来的文件名字
        String OriginalFilename=file.getOriginalFilename();
        System.out.println(OriginalFilename);
        // 为了防止重名覆盖，获取系统时间戳+原始文件的后缀名
        String fileName=System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        // 设置保存地址（这里是转义字符）
        //1.后台保存位置
        System.out.println(fileName);
        String path = "C:\\Users\\Huawei\\Desktop\\cahce\\";
        File dest=new File(path+fileName);
        // 判断文件是否存在
        if(!dest.getParentFile().exists()){
            // 不存在就创建一个
            dest.getParentFile().mkdirs();
        }
        try {
            // 后台上传
            file.transferTo(dest);
            return new ResponseResult(200, "文件上传成功", fileName);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.fail();
        }


    }





























//    private final RestHighLevelClient restHighLevelClient;
//
//    @Autowired
//    public testController(RestHighLevelClient restHighLevelClient) {
//        this.restHighLevelClient = restHighLevelClient;
//    }
//
//    @RequestMapping("/test110")
//    public void testIndexAndMapping() throws IOException {
//        val createIndexRequest = new CreateIndexRequest("products");
//        createIndexRequest.mapping("{\n" +
//                "    \"properties\": {\n" +
//                "      \"title\":{\n" +
//                "        \"type\": \"text\"\n" +
//                "      },\n" +
//                "      \"price\":{\n" +
//                "        \"type\": \"double\"\n" +
//                "      },\n" +
//                "      \"description\":{\n" +
//                "        \"type\": \"text\"\n" +
//                "      }\n" +
//                "    }\n" +
//                "  }", XContentType.JSON);
//        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
//        System.out.println(createIndexResponse.isAcknowledged());
//    }
//
//    public void query(QueryBuilder QueryBuilder) throws IOException{
//        SearchRequest searchRequest = new SearchRequest("products");
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//
//        sourceBuilder.query(QueryBuilder);
//        searchRequest.source(sourceBuilder);
//
//        SearchResponse searchResponse = restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT);
//        System.out.println(searchResponse.getHits().getTotalHits().value);
//        System.out.println(searchResponse.getHits().getMaxScore());
//
//        SearchHit[] hits = searchResponse.getHits().getHits();
//        for (SearchHit hit :hits){
//            System.out.println("id"+hit.getId()+"source"+hit.getSourceAsString());
//        }
//    }
//
//    @RequestMapping("/test112")
//    public void testQuery(String val) throws IOException{
//        query(QueryBuilders.multiMatchQuery(val));
//    }

}