package org.example.controller;

import org.example.Utils.DateUtils;
import org.example.entity.Info;
import org.example.service.*;
import org.example.socketServer.SocketManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.example.controller.messageManagerController.imageToBase64String;

@RestController
@RequestMapping("/infoManager")
public class InfoManagerController {
    //信息管理
    @Autowired
    InfoManagerService infoManagerService;
    //朋友管理
    @Autowired
    FriendManagerService friendManagerService;
    //消息管理
    @Autowired
    MessageManagerService messageManagerService;
    //黑名单管理
    @Autowired
    BlackListManagerService blackListManagerService;
    //用户账号管理
    @Autowired
    UserAccountService userAccountService;
    //发送信息
    @PostMapping("/sendInfo")
    public void sendInfo(@RequestParam int userId,@RequestParam int userId2,@RequestParam String text){
        if (blackListManagerService.searchBlackListByOne(userId2,userId) == null) {
            if (userAccountService.selectOnlineList(userId2))
                SocketManager.getSocketManager().outputMessage(Integer.toString(userId2), 1, null);
            infoManagerService.sendInfo(userId, userId2, text);
        }
    };
    //改变信息状态
    @PostMapping("/changeInfoStatus")
    public String changeInfoStatus(@RequestParam int userId,@RequestParam int userId2,@RequestParam String status){
        infoManagerService.changeInfoStatus(userId, userId2, status);
        if (status.equals("true")) {
            if (friendManagerService.addFriend(userId, userId2).equals("succeed")) {
                messageManagerService.sendMessage(userId,"我们已经是朋友了!",userId2, DateUtils.getNowDateString(),"text");
                System.out.println("添加好友成功！");
                infoManagerService.changeInfoStatus(userId2, userId, status);
                return "succeed";
            } else {
                System.out.println("添加好友失败！");
                return "defeat";
            }
        }
        return null;
    }
    //删除信息
    @PostMapping("/deleteInfo")
    public void deleteInfo(@RequestParam int userId,@RequestParam int userId2){
        infoManagerService.deleteInfo(userId, userId2);
    }
    //删除所有信息
    @PostMapping("/deleteInfoForAll")
    public void deleteInfo(@RequestParam int userId){
        System.out.println(userId);
        infoManagerService.deleteInfoForAll(userId);
    }
    //查找信息
    @PostMapping("/searchInfo")
    public Info searchInfo(@RequestParam int userId,@RequestParam int userId2){
        return infoManagerService.searchInfo(userId,userId2);
    }
    //查找所有信息
    @PostMapping("/searchInfos")
    public List<Info> searchInfos(@RequestParam int userId){
        List<Info> infoList;
        if ((infoList = infoManagerService.searchInfos(userId)) != null) {
            for (int i = 0; i < infoList.size(); i++) {
//                String base64 = imageToBase64String(messageManagerService.selectImagePath(infoList.get(i).getUserInfo().getImgId()), "jpg");
                String base64 = messageManagerService.selectImagePath(infoList.get(i).getUserInfo().getImgId());
                infoList.get(i).getUserInfo().setImg(base64);
            }
        }
        return infoList;
    }
}
