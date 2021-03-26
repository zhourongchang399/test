package org.example.service.impl;

import org.example.entity.Group;
import org.example.entity.UserInfo;
import org.example.mapper.DialogsManagerMapper;
import org.example.mapper.GroupChatManagerMapper;
import org.example.service.DialogsManagerService;
import org.example.service.GroupChatManagerService;
import org.example.service.MessageManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupChatServiceImpl implements GroupChatManagerService {

    @Autowired
    GroupChatManagerMapper groupChatManagerMapper;

    @Autowired
    MessageManagerService messageManagerService;

    @Autowired
    DialogsManagerMapper dialogsManagerMapper;

    @Override
    public void insertGroupChat(int group, int userId) {
        groupChatManagerMapper.insertGroupChat(group, userId);
        String name = dialogsManagerMapper.selectGroupName(group);
        Integer integer = dialogsManagerMapper.searchDialogGroup(userId, group);
        if (integer == null) {
            dialogsManagerMapper.insertDialogGroup(userId, group, name);
        }
    }



    @Override
    public void deleteGroupChatSomeone(int group, int userId) {
        groupChatManagerMapper.deleteGroupChatSomeone(group, userId);
        messageManagerService.deleteMsgAffiliationGroup(userId,group);
        dialogsManagerMapper.deleteDialogGroup(userId,group);
    }

    @Override
    public List<UserInfo> selectGroupChat(int group) {
        return groupChatManagerMapper.selectGroupChat(group);
    }

    @Override
    public void changeGroupChatName(String group, String name) {
        groupChatManagerMapper.changeGroupChatName(group, name);
        groupChatManagerMapper.updateGroupChatName(group, name);
    }

    @Override
    public void insertNewGroupChat(Group name) {
        groupChatManagerMapper.insertNewGroupChat(name);
    }

    @Override
    public List<UserInfo> selectHismsgMenber(String group) {
        return groupChatManagerMapper.selectHismsgMenber(group);
    }
}
