package org.example.service;

import org.example.entity.UserAccount;
import org.example.entity.UserInfo;

import java.util.List;

public interface UserInfoService {

    UserInfo searchUserInfo(int userId);

    void insertDefaultUserInfo(int userId,String username,String name);

    void updateUserInfo(UserInfo userInfo);

    List<UserInfo> searchFriendList(int hostUserId);

}
