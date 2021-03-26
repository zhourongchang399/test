package org.example.controller;

import org.example.entity.UserInfo;
import org.example.service.BlackListManagerService;
import org.example.service.MessageManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.example.controller.messageManagerController.imageToBase64String;

@RestController
@RequestMapping("/blackListManager")
public class BlackListManagerController {
    //黑名单管理
    @Autowired
    BlackListManagerService blackListManagerService;
    //消息管理
    @Autowired
    MessageManagerService messageManagerService;
    //插入黑名单
    @PostMapping("/insertBlackList")
    public String insertBlackList(@RequestParam int hostId,@RequestParam int userId){
        return blackListManagerService.insertBlackList(hostId, userId);
    }
    //读取黑名单
    @PostMapping("/searchBlackList")
    public List<UserInfo> searchBlackList(@RequestParam int hostId){
        List<UserInfo> userInfoList;
        if ((userInfoList = blackListManagerService.searchBlackList(hostId)) != null) {
            for (int i = 0; i < userInfoList.size(); i++) {
//                String base64 = imageToBase64String(messageManagerService.selectImagePath(userInfoList.get(i).getImgId()), "jpg");
                String base64 = messageManagerService.selectImagePath(userInfoList.get(i).getImgId());
                userInfoList.get(i).setImg(base64);
            }
        }
        return userInfoList;
    }
    //黑名单删除
    @PostMapping("/deleteBlackList")
    public void deleteBlackList(@RequestParam int hostId,@RequestParam int userId){
        blackListManagerService.deleteBlackList(hostId, userId);
    }

}
