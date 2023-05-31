package com.qingshuge.controller;

import com.qingshuge.bean.Msg;
import com.qingshuge.bean.ShowAllInfo;
import com.qingshuge.dao.MsgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MsgController {

    @Autowired
    MsgMapper msgMapper;



    @RequestMapping("/toMsgId")
    public List<Msg> toMsgId(@RequestParam("to_user_id") int to_user_id) {
        List<Msg> msg1 = msgMapper.showMsg(to_user_id);
//        System.out.println(books);
//        int test = msg.getMoney(to_user_id);
//        System.out.println(test);
        return msg1;
    }

    @RequestMapping("/reject")
    public int reject(@RequestParam("user_id") int user_id,@RequestParam("pic_id") int pic_id) {
        int msg = msgMapper.reject(user_id,pic_id);
//        System.out.println(books);
        return msg;
    }

    @RequestMapping("/rejectAll")
    public int rejectAll(@RequestParam("pic_id") int pic_id) {
        int msg = msgMapper.rejectAll(pic_id);
//        System.out.println(books);
        return msg;
    }

    @RequestMapping("/buy")
    public int buy(@RequestParam("user_id") int user_id,@RequestParam("to_user_id") int to_user_id,@RequestParam("pic_id") int pic_id) {
        int msg = msgMapper.buy(user_id,to_user_id,pic_id);
//        System.out.println(books);
        return msg;
    }


//    @RequestMapping("/trade")
//    public int trade(@RequestParam("user_id") int user_id,@RequestParam("pic_id") int pic_id) {
//        int msg = msgMapper.agree(user_id,pic_id);
//
////        System.out.println(books);
//        return msg;
//    }

}
