package org.example.service.impl;

import com.sun.corba.se.impl.protocol.giopmsgheaders.FragmentMessage;
import org.example.entity.UserAccount;
import org.example.entity.UserInfo;
import org.example.mapper.FriendManagerMapper;
import org.example.mapper.UserAccountMapper;
import org.example.mapper.UserInfoMapper;
import org.example.service.FriendManagerService;
import org.example.service.UserAccountService;
import org.example.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.Style;
import java.util.List;
import java.util.Random;

@Service
public class UserServicelmpl implements UserAccountService, UserInfoService, FriendManagerService {

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private FriendManagerMapper friendManagerMapper;

    @Override
    public UserAccount login(String username,String password) {
        UserAccount userAccount;
        if ((userAccount = userAccountMapper.queryUserAccount(username,password)) != null)
            return userAccount;
        else
            return null;
    }

    @Override
    public String register(String username, String password,String phone) {
        UserAccount userAccount;
        Random random = new Random(1000000000);
        int i = random.nextInt() + 100000;
        String name = "hw" + Integer.toString(i);
        System.out.println(name);
        if(searchOne(username) == null){
            userAccountMapper.insertUserAccount(username,password,phone);
            userAccount = searchOne(username);
            System.out.println(userAccount.getUserid()+userAccount.getUsername());
            int userid = userAccount.getUserid();
            insertDefaultUserInfo(userid,username,name);
            return "succeed";
        }
        else
            return "defeat";
    }

    @Override
    public UserAccount searchOne(String username) {
        return userAccountMapper.queryUserAccountIf(username);
    }

    @Override
    public List<UserInfo> queryUserAccountLike(String username, String name) {
        List<UserInfo> userInfoList = userAccountMapper.queryUserAccountLike(username,name);
        if (userInfoList != null)
            return userInfoList;
        else
            return null;
    }

    @Override
    public void onOrOffLineUserAccount(int userId,int userId2, int ctrl) {
        if (ctrl == 1){
            userAccountMapper.onlineUserAccount(userId,userId2);
        }
        else
            if (ctrl == 0){
                userAccountMapper.offlineUserAccount(userId);
            }
    }

    @Override
    public void insertOnlineList(int userId) {
        userAccountMapper.insertOnlineList(userId);
    }

    @Override
    public boolean selectOnlineList(int userId) {
        Integer i = null;
        if ((i = userAccountMapper.selectOnlineList(userId)) == null)
            return false;
        else
            return true;
    }

    @Override
    public void deleteOnlineList(int userId) {
        if (userAccountMapper.deleteOnlineList(userId))
            System.out.println("数据已更新！");
        else
            System.out.println("error!");
    }

    @Override
    public void updateImgFace(int userId, int imgId) {
        userAccountMapper.updateImg(userId,imgId);
    }

    @Override
    public void updateUserAccountByPhone(String password, String phone) {
        userAccountMapper.updateUserAccountByPhone(password, phone);
    }

    @Override
    public UserAccount phoneLogin(String phone) {
        UserAccount userAccount;
        if ((userAccount = userAccountMapper.queryUserAccountByPhone(phone)) != null)
            return userAccount;
        else
            return null;
    }

    @Override
    public void updateUserAccount(String username, String password) {
        userAccountMapper.updateUserAccount(username,password);
    }

    @Override
    public void deleteOnlineListForALLServer() {
        userAccountMapper.deleteOnlineListForALLServer();
    }

    @Override
    public UserInfo searchUserInfo(int userId) {
        UserInfo userInfo;
        if ((userInfo = userInfoMapper.searchUserInfo(userId)) != null)
            return userInfo;
        else
            return null;
    }

    @Override
    public void insertDefaultUserInfo(int userId, String username, String name) {
        userInfoMapper.insertDefaultUserInfo(userId,username,name);
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {
        userInfoMapper.updateUserInfo(userInfo.getUserId(),userInfo.getName(),userInfo.getAge(),userInfo.getSex(),userInfo.getPersonalProfile(),userInfo.getCity());
    }

    @Override
    public List<UserInfo> searchFriendList(int hostUserId) {
        List<UserInfo> userInfoList;
        if ((userInfoList = userInfoMapper.searchFriendList(hostUserId)) != null)
            return userInfoList;
        else
            return null;
        }

    @Override
    public String addFriend(int hostUserId, int userId) {
        if (searchFriend(hostUserId,userId) == null)
            friendManagerMapper.addFriend(hostUserId, userId);
        if (searchFriend(userId,hostUserId) == null)
            friendManagerMapper.addFriend(userId, hostUserId);
            return "succeed";
    }

    @Override
    public UserAccount searchFriend(int hostUserId, int userid) {
        UserAccount userAccount;
        if ((userAccount = friendManagerMapper.searchFriend(hostUserId, userid)) != null){
            return userAccount;
        }
        else
            return null;
    }

    @Override
    public String deleteFriend(int hostUserId, int userId) {
        friendManagerMapper.deleteFriend(hostUserId, userId);
        if (searchFriend(hostUserId,userId) == null)
            return "succeed";
        else
            return "defeat";
    }


}
