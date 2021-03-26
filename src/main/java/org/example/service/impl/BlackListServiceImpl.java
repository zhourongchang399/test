package org.example.service.impl;

import org.example.entity.UserInfo;
import org.example.mapper.BlackListManagerMapper;
import org.example.service.BlackListManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlackListServiceImpl implements BlackListManagerService {

    @Autowired
    BlackListManagerMapper blackListManagerMapper;

    @Override
    public String insertBlackList(int hostId, int userId) {
        if (blackListManagerMapper.searchBlackListByOne(hostId, userId) == null){
            blackListManagerMapper.insertBlackList(hostId, userId);
            return "succeed";
        }
        else
            return "defect";
    }

    @Override
    public List<UserInfo> searchBlackList(int hostId) {
        return blackListManagerMapper.searchBlackList(hostId);
    }

    @Override
    public void deleteBlackList(int hostId, int userId) {
        blackListManagerMapper.deleteBlackList(hostId, userId);
    }

    @Override
    public UserInfo searchBlackListByOne(int hostId, int userId) {
        return blackListManagerMapper.searchBlackListByOne(hostId,userId);
    }
}
