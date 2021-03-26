package org.example.service.impl;

import org.example.Utils.DateUtils;
import org.example.entity.Dialog;
import org.example.entity.Message;
import org.example.entity.UserAccount;
import org.example.entity.UserInfo;
import org.example.mapper.DialogsManagerMapper;
import org.example.mapper.MessageManagerMapper;
import org.example.mapper.UserInfoMapper;
import org.example.service.MessageManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageManagerService {

    @Autowired
    MessageManagerMapper messageManagerMapper;
    @Autowired
    DialogsManagerMapper dialogsManagerMapper;
    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public void sendMessage(int sendId, String text, int receiveId, String createTime,String type) {
        Message message = new Message();
        message.setText(text);
        message.setCreateTime(createTime);
        message.setUserId(sendId);
        message.setReceiveId(receiveId);
        message.setImgId(0);
        message.setType(type);
        messageManagerMapper.sendMessage(message);
        searchDialog(sendId,receiveId);
        messageManagerMapper.insertMsgAffiliation(sendId,receiveId,message.getId());
    }

    @Override
    public List<Message> historyMessage(int userId,int userId2) {
        List<Message> messages;
        if ((messages = messageManagerMapper.historyMessage(userId, userId2))!= null)
            return messages;
        else
            return null;
    }

    @Override
    public Message searchLastMessage(int hostUserId, int userId) {
        Message message;
        if ((message = messageManagerMapper.lastMessage(hostUserId, userId)) != null) {
            return message;
        }
        else {
            return null;
        }
    }

    @Override
    public void insertDialog(int userId, int userId2, String username1,String username2,int i) {
        if (i == 0) {
            dialogsManagerMapper.insertDialog(userId, userId2, username2);
            dialogsManagerMapper.insertDialog(userId2, userId, username1);
        }
        else
            if (i == 1){
                dialogsManagerMapper.insertDialog(userId2, userId, username1);
            }
            else
                if (i == 2){
                    dialogsManagerMapper.insertDialog(userId, userId2, username2);
                }

    }

    @Override
    public void searchDialog(int userId, int userId2) {
        List<Dialog> dialogs;
        String username1 = searchUserInfo(userId).getName();
        String username2 = searchUserInfo(userId2).getName();
        if ((dialogs = dialogsManagerMapper.searchDialog(userId, userId2)) != null) {
            if (dialogs.size() == 1) {
                int i = Integer.parseInt(dialogs.get(0).getId());
                if (i == userId2) {
                    insertDialog(userId, userId2, username1, username2, 1);
                } else if (i == userId) {
                    insertDialog(userId, userId2, username1, username2, 2);
                }
            }
            if (dialogs.size() == 0) {
                insertDialog(userId, userId2, username1, username2, 0);
            }
        }
    }

    @Override
    public UserAccount searchUserAccount(int userId) {
        return messageManagerMapper.searchUserAccount(userId);
    }

    @Override
    public UserInfo searchUserInfo(int userId) {
        return userInfoMapper.searchUserInfo(userId);
    }

    @Override
    public void insertUnreadMessage(int sendId, String text, int imgId,int receiveId, String createTime,String type) {
        messageManagerMapper.insertUnreadMessage(sendId, text, imgId,receiveId, createTime,type);
    }

    @Override
    public boolean searchOnline(int userId, int receiveUserId) {
        if (messageManagerMapper.searchOnline(userId, receiveUserId) != null){
            return true;
        }
        else
            return false;
    }

    @Override
    public List<Message> unreadMessage(int userId) {
        return messageManagerMapper.searchUnreadMessage(userId);
    }

    @Override
    public int unreadMessageForDialog(int userId, int userId2) {
        int count;
        if ((count = (int)messageManagerMapper.searchDialogUnreadMessage(userId, userId2)) == 0)
            return 0;
        else
            return count;
    }

    @Override
    public void resetUnreadMessage(int userId,int userId2) {
        messageManagerMapper.resetUnreadMessage(userId,userId2);
    }

    @Override
    public void sendImageMessage(int sendId, int imgId, int receiveId, String createTime, String type) {
        Message message = new Message();
        message.setText(null);
        message.setCreateTime(createTime);
        message.setUserId(sendId);
        message.setReceiveId(receiveId);
        message.setImgId(imgId);
        message.setType(type);
        messageManagerMapper.sendImageMessage(message);
        searchDialog(sendId,receiveId);
        messageManagerMapper.insertMsgAffiliation(sendId,receiveId,message.getId());
    }

    @Override
    public void sendImage(String path) {
        messageManagerMapper.sendImage(path);
    }

    @Override
    public int selectImageId(String path) {
        return messageManagerMapper.selectImageId(path);
    }

    @Override
    public String selectImagePath(int imgId) {
        return messageManagerMapper.selectImagePath(imgId);
    }

    @Override
    public void deleteMsg(int userId, int userId2) {
        messageManagerMapper.deleteMsg(userId, userId2);
    }

    @Override
    public List<Message> historyMessageGroup(String group,int userId) {
        return messageManagerMapper.historyMessageGroup(group,userId);
    }

    @Override
    public Message lastHistoryMessageGroup(String group,int userId) {
        return messageManagerMapper.lastMessageGroup(group,userId);
    }

    @Override
    public Integer searchDialogUnreadMessageGroup(String group, int hostId) {
        return messageManagerMapper.searchDialogUnreadMessageGroup(group, hostId);
    }

    @Override
    public void resetUnreadMessageGroup(String group, int hostId) {
        messageManagerMapper.resetUnreadMessageGroup(group, hostId);
    }

    @Override
    public void insertUnreadMessageGroup(int msgId, String group, int hostId) {
        messageManagerMapper.insertUnreadMessageGroup(msgId, group, hostId);
    }

    @Override
    public int sendMessageGroup(int userId, String text, int receiveId, String createTime, String type) {
        Message message = new Message();
        message.setText(text);
        message.setCreateTime(createTime);
        message.setUserId(userId);
        message.setReceiveId(receiveId);
        message.setImgId(0);
        message.setType(type);
        messageManagerMapper.sendMessageGroup(message);
        return message.getId();
    }

    @Override
    public List<Integer> selectGroupMenber(String group,String userId) {
        return messageManagerMapper.selectGroupMenber(group,userId);
    }

    @Override
    public int sendImageMessageGroup(int sendId, int imgId, int receiveId, String createTime, String type) {
        Message message = new Message();
        message.setText(null);
        message.setCreateTime(createTime);
        message.setUserId(sendId);
        message.setReceiveId(receiveId);
        message.setImgId(imgId);
        message.setType(type);
        messageManagerMapper.sendImageMessageGroup(message);
        return message.getId();
    }

    @Override
    public void insertMsgAffiliationGroup(int userId, int group, int msgId) {
        messageManagerMapper.insertMsgAffiliationGroup(userId,group,msgId);
    }

    @Override
    public void deleteMsgAffiliationGroup(int userId, int group) {
        messageManagerMapper.deleteMsgAffiliationGroup(userId, group);
    }

}
