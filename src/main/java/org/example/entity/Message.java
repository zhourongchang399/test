package org.example.entity;

import com.example.helloworld.socket.MessageInfo;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;

public class Message {
    //id
    //用户id
    //接收用户id
    //头像id
    //内容
    //创建时间
    //类型
    int id;
    int userId;
    int receiveId;
    int imgId;
    String text;
    String createTime;
    String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(int receiveId) {
        this.receiveId = receiveId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public MessageInfo transMessageInfo(Message message){
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setCreateTime(message.getCreateTime());
        messageInfo.setReceiveId(message.getReceiveId());
        messageInfo.setUserId(message.getUserId());
        messageInfo.setText(message.getText());
        messageInfo.setType(message.getType());
        return messageInfo;
    }

    @Override
    public String toString() {
        return "Message{" +
                "userId=" + userId +
                ", receiveId=" + receiveId +
                ", text='" + text + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
