package org.example.service;

import org.apache.ibatis.annotations.Param;
import org.example.entity.UserInfo;

import java.util.List;

public interface BlackListManagerService {

    public String insertBlackList(int hostId,int userId);

    public List<UserInfo> searchBlackList(int hostId);

    public void deleteBlackList(int hostId,int userId);

    public UserInfo searchBlackListByOne(int hostId,int userId);
}

