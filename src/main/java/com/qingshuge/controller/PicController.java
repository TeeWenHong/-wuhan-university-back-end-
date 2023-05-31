package com.qingshuge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qingshuge.bean.Image;
import com.qingshuge.bean.ResponseResult;
import com.qingshuge.bean.ShowAllInfo;
import com.qingshuge.dao.PicMapper;
import org.apache.ibatis.annotations.Param;
import org.elasticsearch.ResourceNotFoundException;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@RestController
public class PicController {

    @Autowired
    PicMapper picMapper;

    @Value("${upload.path}") // 读取文件上传路径的配置
    private String uploadPath;

    @RequestMapping("/searchAllPic")
    public List<ShowAllInfo> searchAllPic(@RequestParam("pic_id") int pic_id) {
        List<ShowAllInfo> ShowAllInfo = picMapper.searchAllPic(pic_id);
//        System.out.println(books);
        return ShowAllInfo;
    }

    @RequestMapping("/searchMyAllPic")
    public List<ShowAllInfo> searchMyAllPic(@RequestParam("user_id") int user_id) {
        List<ShowAllInfo> ShowAllInfo = picMapper.searchMyAllPic(user_id);
//        System.out.println(books);
        return ShowAllInfo;
    }

    @RequestMapping("/searchIdPic")
    public List<ShowAllInfo> searchIdPic(@RequestParam("user_id") int user_id ) {
        List<ShowAllInfo> ShowAllInfo = picMapper.searchIdPic(user_id);
//        System.out.println(books);
        return ShowAllInfo;
    }

    @RequestMapping("/searchIdPices")
    public List<ShowAllInfo> searchIdPices(@RequestParam("user_id") int user_id ) {
        List<ShowAllInfo> ShowAllInfo = picMapper.searchIdPic(user_id);
//        System.out.println(books);
        return ShowAllInfo;
    }

//    根据pic_id搜索
    @RequestMapping("/searchPicnamePic")
    public Image searchPicnamePic(@RequestParam("picture_path") String picture_path ) {

        Image searchPicIdPic = picMapper.findByPathName(picture_path);
        System.out.println(searchPicIdPic);
        return searchPicIdPic;
    }

    @RequestMapping("/deletePic")
    public String deleteBook(@RequestParam("picture_path") String picture_path ) {
        int ShowAllInfo = picMapper.deletePicName(picture_path);
//        System.out.println(books);
        if(ShowAllInfo == 1){
            return "成功删除";
        }else {
            return "删除失败";
        }
    }

    @RequestMapping("/setBuyFlag")
    public int setBuyFlag(@RequestParam("user_id") int user_id,@RequestParam("pic_id") int pic_id) {
        int pic = picMapper.setBuyFlag(user_id,pic_id);
//        System.out.println(books);
        return pic;
    }

//    @RequestMapping("/setUserId")
//    public int setUserId(@RequestParam("user_id") int user_id,@RequestParam("pic_id") int pic_id) {
//        int pic = picMapper.setUserId(user_id,pic_id);
////        System.out.println(books);
//        return pic;
//    }
//int savePicCache(@Param("user_id") int user_id,@Param("picture_path") int picture_path);
    @RequestMapping("/savePicCache")
    public String savePicCache(@RequestParam("user_id") int user_id,@RequestParam("picture_path") String picture_path) {
        String full_path = "C:\\Users\\Huawei\\Desktop\\img\\"+picture_path;
        System.out.println(full_path);
        int pic = picMapper.savePicCache(user_id,full_path);
        System.out.println(pic);
        return full_path;
    }

    @PostMapping("/picupload")
    public ResponseResult uploadFile(@RequestParam("file") MultipartFile file){
        // 判断文件是否为空
        if(file.isEmpty()){
            return ResponseResult.fail();
        }
//        System.out.printf("原始名字"+file.getName());
        // 获取传过来的文件名字
        String OriginalFilename=file.getOriginalFilename();
        System.out.println("原始名字"+OriginalFilename);
        // 为了防止重名覆盖，获取系统时间戳+原始文件的后缀名
        String fileName=System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        // 设置保存地址（这里是转义字符）
        //1.后台保存位置
        System.out.println("记录名字"+fileName);
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

    @PostMapping("/uploadFile1")
    public ResponseResult uploadFile1(@RequestParam("file") MultipartFile file)throws Exception {
        // 判断文件是否为空
        if(file.isEmpty()){
            return ResponseResult.fail();
        }
//        System.out.printf("原始名字"+file.getName());
        // 获取传过来的文件名字
        String OriginalFilename=file.getOriginalFilename();
        System.out.println("原始名字"+OriginalFilename);


        // 为了防止重名覆盖，获取系统时间戳+原始文件的后缀名
        String fileName=OriginalFilename;
        // 设置保存地址（这里是转义字符）
        //1.后台保存位置
        System.out.println("记录名字"+fileName);
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

    @RequestMapping("/picupload1")
    public ResponseResult uploadFile1(@RequestParam("file") MultipartFile file,
                                      @RequestParam("user_id") int user_id,
                                      @RequestParam("picname") String picname,
                                      @RequestParam("picture_path") String picture_path,
                                      @RequestParam("price") int price,
                                      @RequestParam("brief_pic") String brief_pic
                                        ){
        // 判断文件是否为空
        if(file.isEmpty()){
            return ResponseResult.fail();
        }
        System.out.printf(file.getName());
        // 获取传过来的文件名字
        String OriginalFilename=file.getOriginalFilename();
        System.out.println("图片名字"+OriginalFilename);
//        int savePic(@Param("user_id") int user_id,@Param("picname") String picname,@Param("price") int price,@Param("brief_pic") String brief_pic);
//        int ShowAllInfo = picMapper.savePic(user_id,picname,OriginalFilename,price,brief_pic);

        // 为了防止重名覆盖，获取系统时间戳+原始文件的后缀名
        String fileName=System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        // 设置保存地址（这里是转义字符）
        //1.后台保存位置
        int pic = picMapper.savePic(user_id,picname,picture_path,price,brief_pic);
        System.out.println("这是数据成功没"+pic);
//        System.out.println(fileName);
        String path = "C:\\Users\\Huawei\\Desktop\\qinshuge\\src\\userimg\\";
        File dest=new File(path+picture_path);
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

    @PostMapping("/picupload2")
    public ResponseResult uploadFile2(@RequestParam("user_id") int user_id,
                                      @RequestParam("file") MultipartFile file


    ){
        // 判断文件是否为空
        if(file.isEmpty()){
            return ResponseResult.fail();
        }
        System.out.printf(file.getName());
        // 获取传过来的文件名字
        String OriginalFilename=file.getOriginalFilename();
        System.out.println("图片名字"+OriginalFilename);
//        int savePic(@Param("user_id") int user_id,@Param("picname") String picname,@Param("price") int price,@Param("brief_pic") String brief_pic);
//        int ShowAllInfo = picMapper.savePic(user_id,picname,OriginalFilename,price,brief_pic);

        // 为了防止重名覆盖，获取系统时间戳+原始文件的后缀名
//        String fileName=System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        // 设置保存地址（这里是转义字符）
        //1.后台保存位置
        int pic = picMapper.savePicBook(user_id,OriginalFilename,OriginalFilename,1,"1");
        System.out.println("这是数据成功没"+pic);
//        System.out.println(fileName);
        String path = "C:\\Users\\Huawei\\Desktop\\qinshuge\\src\\userimg\\";
        File dest=new File(path+OriginalFilename);
        // 判断文件是否存在
        if(!dest.getParentFile().exists()){
            // 不存在就创建一个
            dest.getParentFile().mkdirs();
        }
        try {
            // 后台上传
            file.transferTo(dest);
            return new ResponseResult(200, "文件上传成功", OriginalFilename);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.fail();
        }


    }



    @RequestMapping("deletePicName")
    public int deleteImage(@RequestParam("pic_id") int pic_id) {
        try {
            // 从数据库中获取图片记录
//            Image image = picMapper.deletePicName(picture_path);
            Image image = picMapper.findByPath(pic_id);
            System.out.println(image);
//            // 从文件系统中删除对应的图片文件
            Path imagePath = Paths.get(uploadPath, image.getPicture_path());
            System.out.println(imagePath);
            Files.deleteIfExists(imagePath);

            int test = (int)image.getPic_id();
            System.out.println(test);
            // 从数据库中删除图片记录
            picMapper.deletePicName(image.getPicture_path());
//            return ResponseEntity.ok().build();
            return 1;
        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("删除图片失败！");
            return 0;
        }
    }

    @RequestMapping("deletePicByPath")
    public int deletePicByPath(@RequestParam("picture_path") String picture_path) {
        try {
            // 从数据库中获取图片记录
//            // 从文件系统中删除对应的图片文件
            Path imagePath = Paths.get(uploadPath, picture_path);
            System.out.println(imagePath);
            Files.deleteIfExists(imagePath);

            // 从数据库中删除图片记录
            picMapper.deletePicName(picture_path);
//            return ResponseEntity.ok().build();
            return 1;
        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("删除图片失败！");
            return 0;
        }
    }

    private final RestHighLevelClient restHighLevelClient;

    @Autowired
    public PicController(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @RequestMapping("indexPices")
    public int indexPices(@RequestParam("pic_id") int pic_id,
                          @RequestParam("user_id") int user_id,
                          @RequestParam("picname") String picname,
                          @RequestParam("picture_path") String picture_path,
                          @RequestParam("HASH") String HASH,
                          @RequestParam("price") int price,
                          @RequestParam("buy_flag") boolean buy_flag,
                          @RequestParam("brief_pic") String brief_pic) throws Exception{
        Image image = new Image();
        image.setPic_id(pic_id);
        image.setUser_id(user_id);
        image.setPicname(picname);
        image.setPicture_path(picture_path);
        image.setHASH(HASH);
        image.setPrice(price);
        image.setBuy_flag(buy_flag);
        image.setBrief_pic(brief_pic);

        IndexRequest indexRequest = new IndexRequest("pic");
        indexRequest.id(Integer.toString(pic_id))
                .source(new ObjectMapper().writeValueAsString(image), XContentType.JSON);
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(indexResponse.status());

        return 1;
    }

    @RequestMapping("deletePic1")
    public int deletePic1(@RequestParam("pic_id") int pic_id) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("pic", "_doc", Integer.toString(pic_id));
        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(deleteResponse.status());
        return deleteResponse.status().getStatus();
    }

    @RequestMapping("/downloadPhoto")
    public ResponseEntity<Resource> downloadPhoto(@RequestParam("pic_id") int pic_id) throws IOException {
        // 根据pic_id查询图片路径
//        Image image = new Image();

        Image image = picMapper.findByPath(pic_id);
        System.out.println(image.getPicture_path());
        String photoPath ="C:\\Users\\Huawei\\Desktop\\qinshuge\\src\\userimg\\"+image.getPicture_path();

        // 创建一个Resource对象，用于包装照片内容
        Resource resource = new FileSystemResource(photoPath);

        // 设置响应头，指定下载文件名和内容类型
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=photo.jpg");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE);

        // 返回带有照片内容的响应实体
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }


}
