package org.example.service;



import org.apache.ibatis.annotations.Param;
import org.example.entity.Message;
import org.example.entity.UserAccount;
import org.example.entity.UserInfo;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface MessageManagerService {
    public void sendMessage(int sendId, String text, int receiveId, String createTime,String type);

    public List<Message> historyMessage(int userId,int userId2);

    public Message searchLastMessage(int userId,int userId2);

    public void insertDialog(int userId,int userId2,String username1, String username2,int i);

    public void searchDialog(int userId, int userId2);

    public UserAccount searchUserAccount(int userId);

    public UserInfo searchUserInfo(int userId);

    public void insertUnreadMessage(int sendId, String text,int imgId, int receiveId, String createTime,String type);

    public boolean searchOnline(int userId,int receiveUserId);

    public List<Message> unreadMessage(int userId);

    public int unreadMessageForDialog(int userId,int userId2);

    public void resetUnreadMessage(int userId,int userId2);

    public void sendImageMessage(int sendId, int imgId, int receiveId, String createTime,String type);

    public void sendImage(String path);

    public int selectImageId(String path);

    public String selectImagePath(int imgId);

    public void deleteMsg(int userId,int userId2);

    public List<Message> historyMessageGroup(String group,int userId);

    public Message lastHistoryMessageGroup(String group,int userId);

    public Integer searchDialogUnreadMessageGroup(String group,int hostId);

    public void resetUnreadMessageGroup(String group,int hostId);

    public void insertUnreadMessageGroup(int msgId,String group,int hostId);

    public int sendMessageGroup(int userId, String text, int receiveId, String createTime, String type);

    public List<Integer> selectGroupMenber(String group,String userId);

    public int sendImageMessageGroup(int sendId, int imgId, int receiveId, String createTime,String type);

    public void insertMsgAffiliationGroup(int userId,int group,int msgId);

    public void deleteMsgAffiliationGroup(int userId,int group);

}
