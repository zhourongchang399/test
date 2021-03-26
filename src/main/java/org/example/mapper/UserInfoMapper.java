package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entity.UserAccount;
import org.example.entity.UserInfo;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    public UserInfo searchUserInfo(@Param("userId") int userId);

    public void insertDefaultUserInfo(@Param("userId") int userId,@Param("username") String username,@Param("name") String name);

    public void updateUserInfo(@Param("userId") int userId,@Param("name") String name,@Param("age") int age,@Param("sex") String sex,@Param("personalProfile") String personalProfile,@Param("city") String city);

    public List<UserInfo> searchFriendList(@Param("hostUserId") int hostUserId);

}
