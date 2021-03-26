package org.example.controller;

import org.example.mapper.DialogsManagerMapper;
import org.example.service.DialogsManagerService;
import org.example.service.FriendManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friendManager")
public class FriendManagerController {
    //好友管理
    @Autowired
    FriendManagerService friendManagerService;
    //对话框管理
    @Autowired
    DialogsManagerService dialogsManagerService;
    //添加朋友
    @GetMapping("/addFriend")
    public String addFriend(@RequestParam int hostUserId,@RequestParam int userId){
        if (friendManagerService.addFriend(hostUserId, userId).equals("succeed")){
            System.out.println("添加好友成功！");
            return "succeed";
        }
        else {
            System.out.println("添加好友失败！");
            return "defeat";
        }
    }
    //删除朋友
    @GetMapping("/deleteFriend")
    public String deleteFriend(@RequestParam int hostUserId,@RequestParam int userId){
        if (friendManagerService.deleteFriend(hostUserId, userId).equals("succeed")){
            System.out.println("删除好友成功！");
            if (dialogsManagerService.selectDialog(hostUserId, userId) != null) {
                dialogsManagerService.deleteDialog(hostUserId, userId);
            }
            return "succeed";
        }
        else {
            System.out.println("删除好友失败！");
            return "defeat";
        }
    }
    //查找朋友
    @PostMapping("/searchFriend")
    public String searchFriend(@RequestParam int hostUserId,@RequestParam int userId){
        if (friendManagerService.searchFriend(userId, hostUserId) != null){
            System.out.println("添加好友成功！");
            return "succeed";
        }
        else {
            System.out.println("添加好友失败！");
            return "defeat";
        }
    }
}
