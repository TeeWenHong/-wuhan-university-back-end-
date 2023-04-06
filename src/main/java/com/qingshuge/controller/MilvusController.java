//package com.qingshuge.controller;
//
//import com.qingshuge.service.MilvusService;
//import io.milvus.grpc.FloatArray;
//import io.milvus.param.IndexType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/milvus")
//public class MilvusController {
//
//    @Autowired
//    private MilvusService milvusService;
//
//    /**
//     * 创建集合
//     * @param collectionName 集合名称
//     * @param dimension 向量维度
//     * @param indexType 索引类型
//     * @return 是否创建成功
//     */
//    @PostMapping("/collection")
//    public boolean createCollection(String collectionName, int dimension, IndexType indexType) {
//        return milvusService.createCollection(collectionName, dimension, indexType);
//    }
//
//    /**
//     * 插入向量
//     * @param collectionName 集合名称
//     * @param vectors 向量列表
//     * @return 是否插入成功
//     */
//    @PostMapping("/insert")
//    public boolean insert(String collectionName, List<FloatArray> vectors) {
//        return milvusService.insert(collectionName, vectors);
//    }
//
///**
// * 搜索向量
// * @param collectionName 集合名称
// * @param queryVector 待查询向量
// * @param topK 返回结果的数量
// * @return 搜索结果
// */
