//package com.qingshuge.service;
//
//import io.grpc.StatusRuntimeException;
//import io.milvus.client.MilvusClient;
//import io.milvus.grpc.FloatArray;
//import io.milvus.param.IndexType;
//import io.milvus.param.MetricType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MilvusService {
//
//    @Autowired
//    private MilvusClient milvusClient;
//
//    /**
//     * 创建集合
//     * @param collectionName 集合名称
//     * @param dimension 向量维度
//     * @param indexType 索引类型
//     * @return 是否创建成功
//     */
//    public boolean createCollection(String collectionName, int dimension, IndexType indexType) {
//        CollectionMapping collectionMapping = CollectionMapping.create(collectionName, dimension, MetricType.IP)
//                .withIndexType(indexType);
//        try {
//            return milvusClient.createCollection(collectionMapping).isOk();
//        } catch (StatusRuntimeException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    /**
//     * 插入向量
//     * @param collectionName 集合名称
//     * @param vectors 向量列表
//     * @return 是否插入成功
//     */
//    public boolean insert(String collectionName, List<FloatArray> vectors) {
//        List<RowRecord> records = vectors.stream().map(v -> RowRecord.create(Arrays.asList(v.getData()))).collect(Collectors.toList());
//        try {
//            return milvusClient.insert(collectionName, records).isOk();
//        } catch (StatusRuntimeException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    /**
//     * 搜索向量
//     * @param collectionName 集合名称
//     * @param queryVector 待查询向量
//     * @param topK 返回结果的数量
//     * @return 搜索结果
//     */
//    public List<SearchResult> search(String collectionName, FloatArray queryVector, int topK) {
//        SearchParam searchParam = SearchParam.create(collectionName)
//                .withQueryRecord(RowRecord.create(Arrays.asList(queryVector.getData())))
//                .withTopK(topK);
//        try {
//            return milvusClient.search(searchParam).getQueryResultsList();
//        } catch (StatusRuntimeException e) {
//            e.printStackTrace();
//            return Collections.emptyList();
//        }
//    }
//
//    /**
//     * 获取集合信息
//     * @param collectionName 集合名称
//     * @return 集合信息
//     */
//    public CollectionMapping describeCollection(String collectionName) {
//        return milvusClient.describeCollection(collectionName);
//    }
//
//    /**
//     * 获取集合中向量数量
//     * @param collectionName 集合名称
//     * @return 向量数量
//     */
//    public long count(String collectionName) {
//        return milvusClient.countEntities(collectionName);
//    }
//}
