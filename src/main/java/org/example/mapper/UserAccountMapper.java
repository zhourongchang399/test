package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entity.UserAccount;
import org.example.entity.UserInfo;

import java.util.List;

@Mapper
public interface UserAccountMapper {

    public UserAccount queryUserAccount(@Param("username")String username,@Param("password") String password);

    public void updateUserAccountByPhone(@Param("password") String password,@Param("phone") String phone);

    public void insertUserAccount(@Param("username")String username,@Param("password") String password,@Param("phone") String phone);

    public UserAccount queryUserAccountIf(@Param("username")String username);

    public List<UserInfo> queryUserAccountLike(@Param("username") String username, @Param("name")String name);

    public void onlineUserAccount(@Param("userId") int userId,@Param("receiveUserId") int receiveUserId);

    public void offlineUserAccount(@Param("userId") int userId);

    public void insertOnlineList(@Param("userId") int userId);

    public Integer selectOnlineList(@Param("userId") int userId);

    public boolean deleteOnlineList(@Param("userId") int userId);

    public void updateImg(@Param("userId") int userId,@Param("imgId") int imgId);

    public UserAccount queryUserAccountByPhone(@Param("phone") String phone);

    public void updateUserAccount(@Param("username")String username,@Param("password") String password);

    public void deleteOnlineListForALLServer();
}
