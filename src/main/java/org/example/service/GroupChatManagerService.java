package org.example.service;

import org.apache.ibatis.annotations.Param;
import org.example.entity.Group;
import org.example.entity.UserInfo;

import java.util.List;

public interface GroupChatManagerService {
    public void insertGroupChat(int group, int userId);

    public void deleteGroupChatSomeone(int group, int userId);

    public List<UserInfo> selectGroupChat(int group);

    public void changeGroupChatName(String group, String name);

    public void insertNewGroupChat(Group name);

    public List<UserInfo> selectHismsgMenber(String group);
}
