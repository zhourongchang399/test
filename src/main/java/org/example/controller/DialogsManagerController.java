package org.example.controller;

import org.example.entity.Dialog;
import org.example.entity.Message;
import org.example.entity.UserAccount;
import org.example.entity.UserInfo;
import org.example.service.DialogsManagerService;
import org.example.service.MessageManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.example.controller.messageManagerController.imageToBase64String;

@RestController
@RequestMapping("/dialogsManager")
public class DialogsManagerController {
    //对话框管理
    @Autowired
    DialogsManagerService dialogsManagerService;
    //消息管理
    @Autowired
    MessageManagerService messageManagerServices;
    //对话框读取
    @PostMapping("/searchDialogs")
    public List<Dialog> searchDialgos(@RequestBody UserAccount userAccount){
        List<Dialog> dialogs = new ArrayList<>();
        if ((dialogs = dialogsManagerService.searchDialogs(userAccount.getUserid())) != null) {
            for (int i = 0;i < dialogs.size();i++) {
                if (dialogs.get(i).getType().equals("people")) {
                    dialogs.get(i).setLastMsg(messageManagerServices.searchLastMessage(userAccount.getUserid(), dialogs.get(i).getUserInfo().getUserId()));
                    int count = messageManagerServices.unreadMessageForDialog(userAccount.getUserid(), dialogs.get(i).getUserInfo().getUserId());
                    dialogs.get(i).setUnreadCount(count);
//                    String base64 = imageToBase64String(messageManagerServices.selectImagePath(dialogs.get(i).getUserInfo().getImgId()), "jpg");
                    String base64 = messageManagerServices.selectImagePath(dialogs.get(i).getUserInfo().getImgId());
                    dialogs.get(i).getUserInfo().setImg(base64);
                }
                else {
                    dialogs.get(i).setLastMsg(messageManagerServices.lastHistoryMessageGroup(dialogs.get(i).getId(),userAccount.getUserid()));
                    dialogs.get(i).setUnreadCount(messageManagerServices.searchDialogUnreadMessageGroup(dialogs.get(i).getId(),userAccount.getUserid()));
                    for (int j = 0; j < dialogs.get(i).getUserInfos().size(); j++) {
//                        String base64 = imageToBase64String(messageManagerServices.selectImagePath(dialogs.get(i).getUserInfos().get(j).getImgId()), "jpg");
                        String base64 = messageManagerServices.selectImagePath(dialogs.get(i).getUserInfos().get(j).getImgId());
                        dialogs.get(i).getUserInfos().get(j).setImg(base64);
                    }
                }
            }
            return dialogs;
        }
        else
            return null;
    }
    //删除对话框
    @PostMapping("/deleteDialog")
    public void deleteDialog(@RequestParam int host,@RequestParam int other){
        dialogsManagerService.deleteDialog(host, other);
        messageManagerServices.deleteMsg(host,other);
    }
    //删除群组对话框
    @PostMapping("/deleteDialogGroup")
    public void deleteDialogGroup(@RequestParam int userId,@RequestParam int group){
        dialogsManagerService.deleteDialogGroup(userId, group);
        messageManagerServices.deleteMsgAffiliationGroup(userId, group);
    }
}
