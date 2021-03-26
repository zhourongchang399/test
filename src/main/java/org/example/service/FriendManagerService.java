package org.example.service;

import org.example.entity.UserAccount;

public interface FriendManagerService {
    public String addFriend(int hostUserId,int userId);

    public UserAccount searchFriend(int hostUserId,int userid);

    public String deleteFriend(int hostUserId,int userId);
}
