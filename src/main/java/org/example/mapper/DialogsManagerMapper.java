package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Dialog;
import org.example.entity.UserAccount;
import org.example.entity.UserInfo;

import java.util.List;

@Mapper
public interface DialogsManagerMapper {
    public List<Dialog> searchDialogs(@Param("userId") int userId);

    public List<Dialog> searchGroupDialogs(@Param("userId") int userId);

    public List<UserInfo> searchGroupUserInfo(@Param("group") int group);

    public List<Dialog> searchDialog(@Param("hostUserId")int userId,@Param("userId") int userId2);

    public Dialog selectDialog(@Param("hostUserId")int userId,@Param("userId") int userId2);

    public void insertDialog(@Param("hostUserId") int hostUserId,@Param("userId") int userId,@Param("username") String username);

    public Integer searchDialogGroup(@Param("userId") int userId,@Param("group") int group);

    public void deleteDialogGroup(@Param("userId") int userId,@Param("group") int group);

    public void insertDialogGroup(@Param("userId") int userId,@Param("group") int group,@Param("name") String name);

    public void deleteDialog(@Param("hostUserId") int hostUserId, @Param("userId") int userId);

    public String selectGroupName(@Param("group") int group);
}
