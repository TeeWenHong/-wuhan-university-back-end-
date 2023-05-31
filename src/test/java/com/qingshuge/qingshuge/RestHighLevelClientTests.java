package com.qingshuge.qingshuge;

import lombok.val;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class RestHighLevelClientTests extends QingshugeApplicationTests{

    private final RestHighLevelClient restHighLevelClient;

    @Autowired
    public RestHighLevelClientTests(RestHighLevelClient restHighLevelClient){
        this.restHighLevelClient = restHighLevelClient;
    }

    @Test
    public void testIndexAndMapping() throws IOException {
        val createIndexRequest = new CreateIndexRequest("products");
        createIndexRequest.mapping("{\n" +
                "    \"properties\": {\n" +
                "      \"title\":{\n" +
                "        \"type\": \"text\"\n" +
                "      },\n" +
                "      \"price\":{\n" +
                "        \"type\": \"double\"\n" +
                "      },\n" +
                "      \"description\":{\n" +
                "        \"type\": \"text\"\n" +
                "      }\n" +
                "    }\n" +
                "  }", XContentType.JSON);
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse.isAcknowledged());
    }

    @Test
    public void testMatchAll() throws IOException{
        SearchRequest searchRequest = new SearchRequest("products");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT);
        System.out.println(searchResponse);
    }

    @Test
    public void testCreate() throws IOException{
        IndexRequest indexRequest = new IndexRequest("products");
        indexRequest.source("{\n" +
                "  \"title\":\"我要加入米哈游\",\n" +
                "  \"price\":\"1000\",\n" +
                "  \"description\":\"小浣熊干吃面真好吃！\"\n" +
                "}",XContentType.JSON);
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest,RequestOptions.DEFAULT);
        System.out.println(indexResponse.status());
    }

    @Test
    public void query(QueryBuilder QueryBuilder) throws IOException{
        SearchRequest searchRequest = new SearchRequest("products");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.query(QueryBuilder);
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT);
        System.out.println(searchResponse.getHits().getTotalHits().value);
        System.out.println(searchResponse.getHits().getMaxScore());

        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit :hits){
            System.out.println("id"+hit.getId()+"source"+hit.getSourceAsString());
        }
    }

    @Test
    public void testQuery() throws IOException{
        query(QueryBuilders.termQuery("title","霸道"));
    }


}
