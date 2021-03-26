package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entity.UserAccount;

@Mapper
public interface FriendManagerMapper {
    public void addFriend(@Param("hostUserId") int hostUserId, @Param("userId") int userId);

    public UserAccount searchFriend(@Param("hostUserId") int hostUserId, @Param("userId") int userId);

    public void deleteFriend(@Param("hostUserId") int hostUserId, @Param("userId") int userId);
}
