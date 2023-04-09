package com.qingshuge.controller;

import com.qingshuge.bean.ShowAllInfo;
import com.qingshuge.dao.PicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class PicController {

    @Autowired
    PicMapper picMapper;

    @RequestMapping("/searchAllPic")
    public List<ShowAllInfo> searchAllPic(@RequestParam("pic_id") String pic_id) {
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

    @RequestMapping("/deletePic")
    public String deleteBook(@RequestParam("pic_id") int pic_id ) {
        int ShowAllInfo = picMapper.deletePic(pic_id);
//        System.out.println(books);
        if(ShowAllInfo == 1){
            return "成功删除";
        }else {
            return "删除失败";
        }
    }

}
