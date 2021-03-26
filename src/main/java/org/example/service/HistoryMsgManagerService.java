package org.example.service;

import org.apache.ibatis.annotations.Param;
import org.example.entity.Message;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HistoryMsgManagerService {

    public List<Message> searchHistoryMsgBySearch(int send,int receive,String search);

    public List<Message> searchHistoryMsgByType(int send,int receive,String type);

    public List<Message> searchHistoryMsgByOwner(int send,int receive,int owner);

    public List<Message> searchHistoryMsgByDate(int send,int receive,String date);

    public void dedleteHistoryMsgForALL(int send,int receive);

    public List<Message> searchHistoryBySearchGroup(int group, int userId, String search);

    public List<Message> searchHistoryByOwnerGroup(int group, int userId,int owner);

    public List<Message> searchHistoryByTypeGroup(int group, int userId);

    public List<Message> searchHistoryByDateGroup(int group, int userId, String date);
}
