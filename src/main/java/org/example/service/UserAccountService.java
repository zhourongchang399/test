package org.example.service;

import org.apache.ibatis.annotations.Param;
import org.example.entity.UserAccount;
import org.example.entity.UserInfo;

import java.util.List;

public interface UserAccountService {
    UserAccount login(String username,String password);

    String register(String username,String password,String phone);

    UserAccount searchOne(String username);

    List<UserInfo> queryUserAccountLike(String username, String name);

    void onOrOffLineUserAccount(int userId,int userId2,int ctrl);

    void insertOnlineList(int userId);

    boolean selectOnlineList(int userId);

    void deleteOnlineList(int userId);

    void updateImgFace(int userId,int imgId);

    public void updateUserAccountByPhone(String password, String phone);

    UserAccount phoneLogin(String phone);

    public void updateUserAccount(String username, String password);

    void deleteOnlineListForALLServer();
}
