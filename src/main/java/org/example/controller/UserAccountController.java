package org.example.controller;

import io.swagger.annotations.Api;
import org.example.Utils.DateUtils;
import org.example.Utils.ImageUtils;
import org.example.entity.User;
import org.example.entity.UserAccount;
import org.example.entity.UserInfo;
import org.example.service.MessageManagerService;
import org.example.service.UserAccountService;
import org.example.socketServer.SocketManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/userAccount")
public class UserAccountController {
    //用户账号管理
    @Autowired
    UserAccountService userAccountService;
    //消息管理
    @Autowired
    MessageManagerService messageManagerService;

    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();
    //登录
    @PostMapping("/login")
    public UserAccount login(@RequestBody UserAccount userAccount){
        userAccount = userAccountService.login(userAccount.getUsername(),userAccount.getPassword());
        if (userAccount != null) {
            System.out.println(userAccount.getUserid() + "用户登录成功！");
            userAccountService.insertOnlineList(userAccount.getUserid());
            return userAccount;
        }
        else {
            System.out.println("用户登录失败！");
            return null;
        }
    }
    //手机登录
    @PostMapping("/phoneLogin")
    public UserAccount phoneLogin(@RequestParam String phone){
        UserAccount userAccount = userAccountService.phoneLogin(phone);
        if (userAccount != null) {
            System.out.println(userAccount.getUserid() + "用户登录成功！");
            userAccountService.insertOnlineList(userAccount.getUserid());
            return userAccount;
        }
        else {
            System.out.println("用户登录失败！");
            return null;
        }
    }
    //重置在线用户
    @PostMapping("/deleteOnlineList")
    public void deleteOnlineList(@RequestBody UserAccount userAccount){
        userAccountService.deleteOnlineList(userAccount.getUserid());
    }
    //重置所有在线用户
    @PostMapping("/deleteOnlineListForAllServer")
    public void deleteOnlineListForALLServer(){
        userAccountService.deleteOnlineListForALLServer();
    }
    //重置所有在线用户
    @GetMapping("/deleteOnlineListByServer")
    public void deleteOnlineList(@RequestParam int userid){
        System.out.println(userid);
        userAccountService.deleteOnlineList(userid);
    }
    //注册
    @PostMapping("/register")
    public String register(@RequestParam String username,@RequestParam String password,@RequestParam String phone) {
        UserAccount userAccount;
        if ((userAccount = userAccountService.phoneLogin(phone)) == null) {
            String result = userAccountService.register(username, password, phone);
            if (result.equals("succeed")) {
                System.out.println(username + "用户注册成功！");
                return "succeed";
            } else if (result.equals("defeat")) {
                System.out.println("用户注册失败！");
            }
        }
        return "defeat";
    }
    //遗忘密码
    @PostMapping("/forgive")
    public void updateUserAccountByPhone(@RequestParam String password,@RequestParam String phone) {
        userAccountService.updateUserAccountByPhone(password, phone);
    }
    //更改密码
    @PostMapping("/update")
    public void updateUserAccount(@RequestParam String password,@RequestParam String username) {
        userAccountService.updateUserAccount(username, password);
    }
    //模糊查找用户账号
    @GetMapping("/searchUserAccountLike")
    public List<UserInfo> searchUserAccountLike(@RequestParam String username){
        List<UserInfo> userInfoList;
        if((userInfoList = userAccountService.queryUserAccountLike(username,username)) != null){
            for (int i = 0; i < userInfoList.size(); i++) {
//                String base64 = ImageUtils.imageToBase64String(messageManagerService.selectImagePath(userInfoList.get(i).getImgId()),"jpg");
                String base64 = messageManagerService.selectImagePath(userInfoList.get(i).getImgId());
                userInfoList.get(i).setImg(base64);
            }
            System.out.println("搜索成功！");
            return userInfoList;
        }
        else {
            System.out.println("搜索失败！");
            return null;
        }
    }
    //添加在线用户
    @GetMapping("/onLineUserAccount")
    public void onLineUserAccount(@RequestParam int userId,@RequestParam int userId2){
        userAccountService.onOrOffLineUserAccount(userId,userId2,1);
    }
    //用户离线
    @GetMapping("/offLineUserAccount")
    public void offLineUserAccount(@RequestParam int userId){
        userAccountService.onOrOffLineUserAccount(userId,0,0);
    }
    //更改头像
    @PostMapping("/updateFace")
    public String updateFace(@RequestBody UserInfo userInfo){
        String path = base64StringToImage(userInfo.getImg(),userInfo.getUserId(),"jpg");
        messageManagerService.sendImage(path);
        int imgId = messageManagerService.selectImageId(path);
        userAccountService.updateImgFace(userInfo.getUserId(),imgId);
        return "succeed";
    }

    static String base64StringToImage(String base64String,int id,String type) {
        String path = null;
        String imageName = null;
        try {
            byte[] bytes1 = decoder.decodeBuffer(base64String);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            BufferedImage bi1 = ImageIO.read(bais);
            imageName = id + "face" + DateUtils.getNowDateStringNotBlank() + "." + type;
            path = "src\\main\\resources\\static\\image\\" + imageName;
            File f1 = new File(path);
            ImageIO.write(bi1, type, f1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageName;
    }
}
