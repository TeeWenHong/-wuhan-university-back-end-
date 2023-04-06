package com.qingshuge.service;

import ai.djl.Device;
import ai.djl.Model;
import ai.djl.inference.Predictor;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import ai.djl.modality.cv.transform.Normalize;
import ai.djl.modality.cv.transform.Resize;
import ai.djl.modality.cv.transform.ToTensor;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDList;
import ai.djl.ndarray.NDManager;
import ai.djl.translate.Transform;
import ai.djl.translate.Translator;
import ai.djl.translate.TranslatorContext;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.gwen.entity.SearchResult;
import com.qingshuge.bean.SearchResult;

//import resources.SearchResult.py;

//import javax.naming.directory.SearchResult;
import java.io.File;
import java.io.InputStream;
import java.util.*;

public class test {

    private static final String INDEX = "isi";
    private static final int IMAGE_SIZE = 224;
    private static Model model; //模型
    private static Predictor<Image, float[]> predictor; //predictor.predict(input)相当于python中model(input)

    static {
        try {
            model = Model.newInstance("model");
            //这里的model.pt是上面代码展示的那种方式保存的
            model.load(test.class.getClassLoader().getResourceAsStream("model.pt"));
            Transform resize = new Resize(IMAGE_SIZE);
            Transform toTensor = new ToTensor();
            Transform normalize = new Normalize(new float[]{0.485f, 0.456f, 0.406f}, new float[]{0.229f, 0.224f, 0.225f});
            //Translator处理输入Image转为tensor、输出转为float[]
            Translator<Image, float[]> translator = new Translator<Image, float[]>() {
                @Override
                public NDList processInput(TranslatorContext ctx, Image input) throws Exception {
                    NDManager ndManager = ctx.getNDManager();
                    System.out.println("input: " + input.getWidth() + ", " + input.getHeight());
                    NDArray transform = normalize.transform(toTensor.transform(resize.transform(input.toNDArray(ndManager))));
                    System.out.println(transform.getShape());
                    NDList list = new NDList();
                    list.add(transform);
                    return list;
                }
                @Override
                public float[] processOutput(TranslatorContext ctx, NDList ndList) throws Exception {
                    return ndList.get(0).toFloatArray();
                }
            };
            predictor = new Predictor<>(model, translator, Device.cpu(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void upload() throws Exception {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
        //批量上传请求
        BulkRequest bulkRequest = new BulkRequest(INDEX);
        File file = new File("C:\\Users\\Huawei\\Desktop\\qingshuge\\src\\main\\resources\\img");
        for (File listFile : file.listFiles()) {
            float[] vector = predictor.predict(ImageFactory.getInstance().fromInputStream(test.class.getClassLoader().getResourceAsStream("img/" + listFile.getName())));
            // 构建文档
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("url", listFile.getAbsolutePath());
            jsonMap.put("vector", vector);
            jsonMap.put("user_id", "user123");
            IndexRequest request = new IndexRequest(INDEX).source(jsonMap, XContentType.JSON);
            bulkRequest.add(request);
        }
        client.bulk(bulkRequest, RequestOptions.DEFAULT);
        client.close();
    }

    //接收待搜索图片的inputstream，搜索与其相似的图片
    public static List<SearchResult> search(InputStream input) throws Throwable {
        float[] vector = predictor.predict(ImageFactory.getInstance().fromInputStream(input));
        System.out.println(Arrays.toString(vector));
//        System.out.println(vector);

        //展示k个结果
        int k = 100;
        // 连接Elasticsearch服务器
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        SearchRequest searchRequest = new SearchRequest(INDEX);
        Script script = new Script(
                ScriptType.INLINE,
                "painless",
                "cosineSimilarity(params.queryVector, doc['vector'])",
                Collections.singletonMap("queryVector", vector));

        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(
                QueryBuilders.matchAllQuery(),
                ScoreFunctionBuilders.scriptFunction(script));

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(functionScoreQueryBuilder)
                .fetchSource(null, "vector") //不返回vector字段，太多了没用还耗时
                .size(k);

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = searchResponse.getHits();

        List<SearchResult> list = new ArrayList<>();
        for (SearchHit hit : hits) {
            // 处理搜索结果
//            System.out.println(hit.toString());
            SearchResult result = new SearchResult((String) hit.getSourceAsMap().get("url"), hit.getScore());
            list.add(result);
        }

        client.close();
        return list;
    }
}