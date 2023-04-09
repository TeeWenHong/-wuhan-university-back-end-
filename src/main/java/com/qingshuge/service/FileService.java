package com.qingshuge.service;


import lombok.Value;
import org.springframework.stereotype.Service;

@Service
public class FileService {
//    @Value("${file.server.url}")
    private String fileServerUrl; // 文件服务器URL


    public String getFileUrl(String fileId) {
        // TODO: 实现获取文件URL的逻辑，这里只是示例代码
        return fileServerUrl + "/files/" + fileId;
    }
}
