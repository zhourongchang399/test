package org.example.controller;

import org.example.entity.Group;
import org.example.entity.UserInfo;
import org.example.service.BlackListManagerService;
import org.example.service.GroupChatManagerService;
import org.example.service.MessageManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.example.controller.messageManagerController.imageToBase64String;

@RestController
@RequestMapping("/GroupChatManager")
public class GroupChatManagerController {
    //群组管理
    @Autowired
    GroupChatManagerService groupChatManagerService;
    //消息管理
    @Autowired
    MessageManagerService messageManagerService;
    //插入群组
    @PostMapping("/insertGroupChat")
    public void insertGroupChat(int group,String userIds) {
        Group group1 = new Group();
        System.out.println(userIds);
        if (group == 0) {
            group1.setGroupName("新的群聊");
            groupChatManagerService.insertNewGroupChat(group1);
            group = group1.getId();
        }
        String[] strings = userIds.split("\\|");
        for(String a :strings)
            groupChatManagerService.insertGroupChat(group, Integer.parseInt(a));
    }
    //删除群员
    @PostMapping("/deleteGroupChatSomeone")
    public String deleteGroupChatSomeone(int group, int userId) {
        groupChatManagerService.deleteGroupChatSomeone(group, userId);
        return "succeed";
    }
    //查找群组成员
    @PostMapping("/selectGroupChat")
    public List<UserInfo> selectGroupChat(int group) {
        List<UserInfo> userInfoList = groupChatManagerService.selectGroupChat(group);
        for (int i = 0; i < userInfoList.size(); i++) {
//            String base64 = imageToBase64String(messageManagerService.selectImagePath(userInfoList.get(i).getImgId()), "jpg");
            String base64 = messageManagerService.selectImagePath(userInfoList.get(i).getImgId());
            userInfoList.get(i).setImg(base64);
        }
        return userInfoList;
    }
    //查找群组聊天成员
    @PostMapping("/selectHsimsgMenber")
    public List<UserInfo> selectHsimsgMenber(int group) {
        List<UserInfo> userInfoList = groupChatManagerService.selectHismsgMenber(Integer.toString(group));
        for (int i = 0; i < userInfoList.size(); i++) {
//            String base64 = imageToBase64String(messageManagerService.selectImagePath(userInfoList.get(i).getImgId()), "jpg");
            String base64 = messageManagerService.selectImagePath(userInfoList.get(i).getImgId());
            userInfoList.get(i).setImg(base64);
        }
        return userInfoList;
    }
    //重命名群组
    @PostMapping("/changeGroupChat")
    public void changeGroupChat(String group,String name) {
        groupChatManagerService.changeGroupChatName(group, name);
    }

}
