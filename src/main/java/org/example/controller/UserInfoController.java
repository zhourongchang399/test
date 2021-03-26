package org.example.controller;

import org.example.Utils.ImageUtils;
import org.example.entity.UserAccount;
import org.example.entity.UserInfo;
import org.example.service.MessageManagerService;
import org.example.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.controller.messageManagerController.imageToBase64String;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    //用户信息管理
    @Autowired
    UserInfoService userInfoService;
    //消息管理
    @Autowired
    MessageManagerService messageManagerServices;
    //查找用户信息
    @GetMapping("/searchUserInfo")
    public UserInfo searchUserInfo(@RequestParam("userId") int userId){
        UserInfo userInfo;

        if ((userInfo = userInfoService.searchUserInfo(userId)) != null) {
            System.out.println(userId + "用户查找信息成功！");
//            String base64 = imageToBase64String(messageManagerServices.selectImagePath(userInfo.getImgId()),"jpg");
//            userInfo.setImg(base64);
            userInfo.setImg(messageManagerServices.selectImagePath(userInfo.getImgId()));
            return userInfo;
        }
        else
            return null;
    }
    //更新用户信息
    @PostMapping("/updateUserInfo")
    public UserInfo updateUserInfo(@RequestBody UserInfo userInfo){
        if (userInfo != null) {
            System.out.println(userInfo.toString());
            userInfoService.updateUserInfo(userInfo);
            System.out.println(userInfo.getUserId() + "用户信息修改成功！");
        }
        return userInfoService.searchUserInfo(userInfo.getUserId());
    }
    //查找用户朋友列表
    @GetMapping("/searchFriendList")
    public List<UserInfo> searchFriendList(@RequestParam int userId){
        List<UserInfo> userInfoList;
        if ((userInfoList = userInfoService.searchFriendList(userId)) != null){
            for (int i = 0;i < userInfoList.size();i++) {
//                String base64 = ImageUtils.imageToBase64String(messageManagerServices.selectImagePath(userInfoList.get(i).getImgId()), "jpg");
                userInfoList.get(i).setImg(messageManagerServices.selectImagePath(userInfoList.get(i).getImgId()));
            }
            System.out.println("朋友列表查找成功！");
            return userInfoList;
        }
        else {
            System.out.println("朋友列表为空！");
            return null;
        }
    }
}
