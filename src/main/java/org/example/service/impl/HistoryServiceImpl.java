package org.example.service.impl;

import org.example.entity.Message;
import org.example.mapper.HistoryMsgManagerMapper;
import org.example.service.HistoryMsgManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryMsgManagerService {

    @Autowired
    HistoryMsgManagerMapper historyMsgManagerMapper;

    @Override
    public List<Message> searchHistoryMsgBySearch(int send, int receive, String search) {
        List<Message> messageList = null;
        messageList = historyMsgManagerMapper.searchHistoryBySearch(send, receive, search);
        if (messageList.size() != 0)
            return messageList;
        else
            return null;
    }

    @Override
    public List<Message> searchHistoryMsgByType(int send, int receive, String type) {
        List<Message> messageList = null;
        messageList = historyMsgManagerMapper.searchHistoryByType(send, receive, type);
        if (messageList.size() != 0)
            return messageList;
        else
            return null;
    }

    @Override
    public List<Message> searchHistoryMsgByOwner(int send, int receive,int owner) {
        List<Message> messageList = null;
        messageList = historyMsgManagerMapper.searchHistoryByOwner(send, receive, owner);
        if (messageList.size() != 0)
            return messageList;
        else
            return null;
    }

    @Override
    public List<Message> searchHistoryMsgByDate(int send, int receive, String date) {
        List<Message> messageList = null;
        messageList = historyMsgManagerMapper.searchHistoryByDate(send, receive, date);
        if (messageList.size() != 0)
            return messageList;
        else
            return null;
    }

    @Override
    public void dedleteHistoryMsgForALL(int send, int receive) {
        historyMsgManagerMapper.deleteHistoryForAll(send, receive);
    }

    @Override
    public List<Message> searchHistoryBySearchGroup(int group, int userId, String search) {
        return historyMsgManagerMapper.searchHistoryBySearchGroup(group, userId, search);
    }

    @Override
    public List<Message> searchHistoryByOwnerGroup(int group, int userId,int owner) {
        return historyMsgManagerMapper.searchHistoryByOwnerGroup(group, userId, owner);
    }

    @Override
    public List<Message> searchHistoryByTypeGroup(int group, int userId) {
        return historyMsgManagerMapper.searchHistoryByTypeGroup(group, userId);
    }

    @Override
    public List<Message> searchHistoryByDateGroup(int group, int userId, String date) {
        return historyMsgManagerMapper.searchHistoryByDateGroup(group, userId, date);
    }

}
