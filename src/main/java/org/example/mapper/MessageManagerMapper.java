package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Message;
import org.example.entity.UserAccount;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Mapper
public interface MessageManagerMapper {
    public int sendMessage(@Param("message") Message message);

    public List<Message> historyMessage(@Param("userId") int userId,@Param("userId2") int userId2);

    public Message lastMessage(@Param("hostUserId") int hostUserId,@Param("userId") int userId);

    public UserAccount searchUserAccount(@Param("userId") int userId);

    public void insertUnreadMessage(@Param("sendId") int sendId,@Param("text") String text,@Param("imgId") int imgId,@Param("receiveId") int receiveId,@Param("createTime") String createTime,@Param("type") String type);

    public Message searchOnline(@Param("userId") int userId,@Param("receiveUserId") int receiveUserId);

    public List<Message> searchUnreadMessage(@Param("userId") int userId);

    public void resetUnreadMessage(@Param("userId") int userId,@Param("userId2") int userId2);

    public int sendImageMessage(@Param("message") Message message);

    public void sendImage(@Param("path") String path);

    public int selectImageId(@Param("path") String path);

    public String selectImagePath(@Param("imgId") int imgId);

    public Integer searchDialogUnreadMessage(@Param("userId") int userId,@Param("sendUserId") int userId2);

    public void insertMsgAffiliation(@Param("userId") int userId,@Param("userId2") int userId2,@Param("msgId") int msgId);

    public List<Integer> searchMsgId(@Param("userId") int userId,@Param("userId2") int userId2,@Param("date") Date createDate);

    public void deleteMsg(@Param("userId") int userId,@Param("userId2") int userId2);

    public List<Message> historyMessageGroup(@Param("group") String group,@Param("userId") int userId);

    public Message lastMessageGroup(@Param("group") String group,@Param("userId") int userId);

    public Integer searchDialogUnreadMessageGroup(@Param("group") String group,@Param("userId") int hostId);

    public void resetUnreadMessageGroup(@Param("group") String group,@Param("userId") int hostId);

    public void insertUnreadMessageGroup(@Param("msgId") int msgId,@Param("group") String group,@Param("userId") int hostId);

    public void sendMessageGroup(@Param("message") Message message);

    public void sendImageMessageGroup(@Param("message") Message message);

    public List<Integer> selectGroupMenber(@Param("group") String group,@RequestParam String userId);

    public void insertMsgAffiliationGroup(@Param("userId") int userId,@Param("group") int group,@Param("msgId") int msgId);

    public void deleteMsgAffiliationGroup(@Param("userId") int userId,@Param("group") int group);
}
