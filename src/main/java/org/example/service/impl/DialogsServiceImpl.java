package org.example.service.impl;

import org.example.entity.Dialog;
import org.example.entity.UserInfo;
import org.example.mapper.DialogsManagerMapper;
import org.example.service.DialogsManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DialogsServiceImpl implements DialogsManagerService {
    @Autowired
    DialogsManagerMapper dialogsManagerMapper;

    @Override
    public List<Dialog> searchDialogs(int userId) {
        List<Dialog> dialogs = dialogsManagerMapper.searchGroupDialogs(userId);
        List<UserInfo> userInfoList;
        for (int i = 0; i < dialogs.size(); i++) {
            userInfoList = dialogsManagerMapper.searchGroupUserInfo(Integer.parseInt(dialogs.get(i).getId()));
            dialogs.get(i).setUserInfos(userInfoList);
        }
        dialogs.addAll(dialogsManagerMapper.searchDialogs(userId));
        return dialogs;
    }

    @Override
    public Dialog selectDialog(int hostUserId, int userId) {
        return dialogsManagerMapper.selectDialog(hostUserId, userId);
    }

    @Override
    public void insertDialog(int userId, int group) {
        String name = dialogsManagerMapper.selectGroupName(group);
        if (dialogsManagerMapper.searchDialogGroup(userId,group) == null) {
            dialogsManagerMapper.insertDialogGroup(userId, group, name);
        }
    }

    @Override
    public void deleteDialogGroup(int userId, int group) {
        dialogsManagerMapper.deleteDialogGroup(userId, group);
    }

    @Override
    public void deleteDialog(int hostUserId, int userId) {
        dialogsManagerMapper.deleteDialog(hostUserId, userId);
    }
}
