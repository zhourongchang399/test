package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Group;
import org.example.entity.UserInfo;

import java.util.List;

@Mapper
public interface GroupChatManagerMapper {

    public void insertGroupChat(@Param("group") int group,@Param("userId") int userId);

    public void deleteGroupChatSomeone(@Param("group") int group,@Param("userId") int userId);

    public List<UserInfo> selectGroupChat(@Param("group") int group);

    public void changeGroupChatName(@Param("group") String group,@Param("name") String name);

    public void insertNewGroupChat(@Param("name") Group name);

    public void updateGroupChatName(@Param("group") String group,@Param("name") String name);

    public List<UserInfo> selectHismsgMenber(@Param("group") String group);
}
