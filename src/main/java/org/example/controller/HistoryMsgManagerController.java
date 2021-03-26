package org.example.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.example.Utils.ImageUtils;
import org.example.entity.Message;
import org.example.service.HistoryMsgManagerService;
import org.example.service.MessageManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/historyMsg")
public class HistoryMsgManagerController {
    //历史消息管理
    @Autowired
    HistoryMsgManagerService historyMsgManagerService;
    //消息管理里
    @Autowired
    MessageManagerService messageManagerService;
    //查找历史消息通过搜索框
    @PostMapping("/hisMsgBySearch")
    public List<Message> searchHisMsgBySearch(@RequestParam int send,@RequestParam int receive,@RequestParam String search){
        List<Message> messageList = null;
        if ((messageList = historyMsgManagerService.searchHistoryMsgBySearch(send, receive, search)) != null) {
//            for (int i = 0; i < messageList.size(); i++) {
//                if (messageList.get(i).getImgId() != 0){
//                    String base64 = ImageUtils.imageToBase64String(messageManagerService.selectImagePath(messageList.get(i).getImgId()), messageList.get(i).getType());
//                    messageList.get(i).setText(base64);
//                }
//            }
            return messageList;
        }
        else
            return null;
    }
    //查找历史消息通过类型
    @PostMapping("/hisMsgByType")
    public List<Message> searchHisMsgByType(@RequestParam int send,@RequestParam int receive,@RequestParam String type){
        List<Message> messageList = new ArrayList<>();
        if ((messageList = historyMsgManagerService.searchHistoryMsgByType(send, receive, type)) != null) {
            for (int i = 0; i < messageList.size(); i++) {
//                String base64 = ImageUtils.imageToBase64String(messageManagerService.selectImagePath(messageList.get(i).getImgId()), messageList.get(i).getType());
                String base64 = messageManagerService.selectImagePath(messageList.get(i).getImgId());
                messageList.get(i).setText(base64);
            }
            return messageList;
        }
        else {
            return null;
        }
    }
    //查找历史消息通过发送者
    @PostMapping("/hisMsgByOwner")
    public List<Message> searchHisMsgByOwner(@RequestParam int send,@RequestParam int receive,@RequestParam int owner){
        List<Message> messageList = null;
        if ((messageList = historyMsgManagerService.searchHistoryMsgByOwner(send, receive,owner)) != null) {
//            for (int i = 0; i < messageList.size(); i++) {
//                if (messageList.get(i).getImgId() != 0){
//                    String base64 = ImageUtils.imageToBase64String(messageManagerService.selectImagePath(messageList.get(i).getImgId()), messageList.get(i).getType());
//                    messageList.get(i).setText(base64);
//                }
//            }
            return messageList;
        }
        else
            return null;
    }
    //查找历史消息通过日期
    @PostMapping("/hisMsgByDate")
    public List<Message> searchHisMsgByDate(@RequestParam int send,@RequestParam int receive,@RequestParam String date){
        List<Message> messageList = null;
        if ((messageList = historyMsgManagerService.searchHistoryMsgByDate(send, receive,date)) != null) {
//            for (int i = 0; i < messageList.size(); i++) {
//                if (messageList.get(i).getImgId() != 0){
//                    String base64 = ImageUtils.imageToBase64String(messageManagerService.selectImagePath(messageList.get(i).getImgId()), messageList.get(i).getType());
//                    messageList.get(i).setText(base64);
//                }
//            }
            return messageList;
        }
        else
            return null;
    }
    //删除所有历史消息
    @PostMapping("/deleteHisMsgForAll")
    public void deleteHisMsgForAll(@RequestParam int send,@RequestParam int receive){
        historyMsgManagerService.dedleteHistoryMsgForALL(send, receive);
    }
    //查找历史消息通过搜索框
    @PostMapping("/searchHistoryBySearchGroup")
    public List<Message> searchHistoryBySearchGroup(@RequestParam int group,@RequestParam int userId,@RequestParam String search) {
        return historyMsgManagerService.searchHistoryBySearchGroup(group, userId, search);
    }
    //查找历史消息通过发送者
    @PostMapping("/searchHistoryByOwnerGroup")
    public List<Message> searchHistoryByOwnerGroup(@RequestParam int group,@RequestParam int userId,@RequestParam int owner) {
        return historyMsgManagerService.searchHistoryByOwnerGroup(group, userId,owner);
    }
    //查找历史消息通过类型
    @PostMapping("/searchHistoryByTypeGroup")
    public List<Message> searchHistoryByTypeGroup(@RequestParam int group,@RequestParam int userId) {
        List<Message> messageList = new ArrayList<>();
        if ((messageList = historyMsgManagerService.searchHistoryByTypeGroup(group, userId)) != null) {
            for (int i = 0; i < messageList.size(); i++) {
//                String base64 = ImageUtils.imageToBase64String(messageManagerService.selectImagePath(messageList.get(i).getImgId()), messageList.get(i).getType());
                String base64 = messageManagerService.selectImagePath(messageList.get(i).getImgId());
                messageList.get(i).setText(base64);
            }
            return messageList;
        }
        else {
            return null;
        }
    }
    //查找历史消息通过日期
    @PostMapping("/searchHistoryByDateGroup")
    public List<Message> searchHistoryByDateGroup(@RequestParam int group,@RequestParam int userId,@RequestParam String date) {
        return historyMsgManagerService.searchHistoryByDateGroup(group, userId, date);
    }

}
