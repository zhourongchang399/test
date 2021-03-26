package org.example.controller;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.ibatis.annotations.Param;
import org.example.Utils.DateUtils;
import org.example.entity.Message;
import org.example.entity.Test;
import org.example.entity.UserInfo;
import org.example.service.*;
import org.example.socketServer.SocketManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/messageManager")
public class messageManagerController {
    static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();
    //消息管理
    @Autowired
    MessageManagerService messageManagerServices;
    //对话框管理
    @Autowired
    DialogsManagerService dialogsManagerService;
    //用户账号管理
    @Autowired
    UserAccountService userAccountService;
    //黑名单管理
    @Autowired
    BlackListManagerService blackListManagerService;
    //朋友管理
    @Autowired
    FriendManagerService friendManagerService;
    //发送消息
    @PostMapping("/sendMessage")
    public String sendMessage(@RequestBody Message message){
        if (blackListManagerService.searchBlackListByOne(message.getReceiveId(),message.getUserId()) == null) {
            if (friendManagerService.searchFriend(message.getReceiveId(), message.getUserId()) != null) {
                messageManagerServices.sendMessage(message.getUserId(), message.getText(), message.getReceiveId(), message.getCreateTime(), message.getType());
                if (userAccountService.selectOnlineList(message.getReceiveId())) {
                    if (SocketManager.getSocketManager().getOnline(Integer.toString(message.getReceiveId()))) {
                        if (messageManagerServices.searchOnline(message.getUserId(), message.getReceiveId()))
                            SocketManager.getSocketManager().outputMessage(Integer.toString(message.getReceiveId()), 2, message);
                        else {
                            SocketManager.getSocketManager().outputMessage(Integer.toString(message.getReceiveId()), 0, null);
                            messageManagerServices.insertUnreadMessage(message.getUserId(), message.getText(), 0, message.getReceiveId(), message.getCreateTime(), message.getType());
                        }
                    } else
                        messageManagerServices.insertUnreadMessage(message.getUserId(), message.getText(), 0, message.getReceiveId(), message.getCreateTime(), message.getType());
                } else
                    messageManagerServices.insertUnreadMessage(message.getUserId(), message.getText(), 0, message.getReceiveId(), message.getCreateTime(), message.getType());
            } else
                return "defect";
        }
        else
            return "defect";
        return "succeed";
    }
    //读取历史消息
    @PostMapping("/historyMessage")
    public List<Message> getHistoryMessage(@RequestBody UserInfo userInfo) {
        List<Message> messages = messageManagerServices.historyMessage(userInfo.getUserId(), userInfo.getAge());
        for (int i = 0;i < messages.size();i++)
            if (messages.get(i).getImgId() != 0){
//                String base64 = imageToBase64String(messageManagerServices.selectImagePath(messages.get(i).getImgId()),messages.get(i).getType());
                String base64 = messageManagerServices.selectImagePath(messages.get(i).getImgId());
                messages.get(i).setText(base64);
            }
        return messages;
    }
    //读取群组历史消息
    @PostMapping("/historyMessageGroup")
    public List<Message> getHistoryMessage(@RequestParam String group,@RequestParam int userId) {
        List<Message> messages = messageManagerServices.historyMessageGroup(group,userId);
        for (int i = 0;i < messages.size();i++)
            if (messages.get(i).getImgId() != 0){
//                String base64 = imageToBase64String(messageManagerServices.selectImagePath(messages.get(i).getImgId()),messages.get(i).getType());
                String base64 = messageManagerServices.selectImagePath(messages.get(i).getImgId());
                messages.get(i).setText(base64);
            }
        return messages;
    }
    //读取最新消息
    @PostMapping("/lastMessage")
    public Message searchLastMessage(@RequestBody UserInfo userInfo) {
        return messageManagerServices.searchLastMessage(userInfo.getUserId(), userInfo.getAge());
    }
    //读取群组最新消息
    @PostMapping("/lastMessageGroup")
    public Message searchLastMessageGroup(@RequestParam String group,@RequestParam int userId) {
        return messageManagerServices.lastHistoryMessageGroup(group,userId);
    }
    //读取未读消息
    @PostMapping("/unreadMessage")
    public List<Message> searchUnreadMessage(@RequestBody UserInfo userInfo) {
        List<Message> messages =  messageManagerServices.unreadMessage(userInfo.getUserId());
        return messageManagerServices.unreadMessage(userInfo.getUserId());
    }
    //重置未读消息
    @PostMapping("/resetUnreadMessage")
    public void resetUnreadMessage(@RequestBody UserInfo userInfo) {
        messageManagerServices.resetUnreadMessage(userInfo.getUserId(), userInfo.getAge());
    }
    //重置群组未读消息
    @PostMapping("/resetUnreadMessageGroup")
    public void resetUnreadMessageGroup(@RequestParam String group,@RequestParam int userId) {
        messageManagerServices.resetUnreadMessageGroup(group,userId);
    }
    //删除群组消息
    @PostMapping("/deleteMsgAffiliationGroup")
    public void deleteMsgAffiliationGroup(@RequestParam int group,@RequestParam int userId) {
        messageManagerServices.deleteMsgAffiliationGroup(userId,group);
    }
    //发送图片消息
    @PostMapping("/sendImageMessage")
    public String sendImageMessage(@RequestBody Message message){
        String base4 = message.getText();
        Date dateTime = DateUtils.transStringToDate(message.getCreateTime());
        String date = message.getCreateTime().replace(" ","");
        date = date.replace(":","");
        date = date.replace("-","");
        String path = base64StringToImage(base4,message.getUserId(),message.getReceiveId(),date,message.getType());
        messageManagerServices.sendImage(path);
        int imgId = messageManagerServices.selectImageId(path);
        if (blackListManagerService.searchBlackListByOne(message.getReceiveId(),message.getUserId()) == null)
            if (friendManagerService.searchFriend(message.getReceiveId(),message.getUserId()) != null) {
                messageManagerServices.sendImageMessage(message.getUserId(), imgId, message.getReceiveId(), message.getCreateTime(), message.getType());
                if (userAccountService.selectOnlineList(message.getReceiveId())) {
                    if (SocketManager.getSocketManager().getOnline(Integer.toString(message.getReceiveId()))) {
                        if (messageManagerServices.searchOnline(message.getUserId(), message.getReceiveId()))
                            SocketManager.getSocketManager().outputMessage(Integer.toString(message.getReceiveId()), 2, message);
                        else {
                            SocketManager.getSocketManager().outputMessage(Integer.toString(message.getReceiveId()), 0, null);
                            messageManagerServices.insertUnreadMessage(message.getUserId(), null, imgId, message.getReceiveId(), message.getCreateTime(), message.getType());
                        }
                    } else
                        messageManagerServices.insertUnreadMessage(message.getUserId(), null, imgId, message.getReceiveId(), message.getCreateTime(), message.getType());
                } else
                    messageManagerServices.insertUnreadMessage(message.getUserId(), null, imgId, message.getReceiveId(), message.getCreateTime(), message.getType());
            }
            else
                return "defect";
        else
            return "defect";
        return path;
    }
    //发送群组消息
    @PostMapping("/sendMessageGroup")
    public String sendMessageGroup(@RequestBody Message message){
        int id = messageManagerServices.sendMessageGroup(message.getUserId(), message.getText(), message.getReceiveId(), message.getCreateTime(), message.getType());
        List<Integer> ints = messageManagerServices.selectGroupMenber(Integer.toString(message.getReceiveId()),Integer.toString(message.getUserId()));
        for (int i = 0; i < ints.size(); i++) {
            messageManagerServices.insertUnreadMessageGroup(id,Integer.toString(message.getReceiveId()),ints.get(i));
            messageManagerServices.insertMsgAffiliationGroup(ints.get(i), message.getReceiveId(),id);
            dialogsManagerService.insertDialog(ints.get(i),message.getReceiveId());
        }
        messageManagerServices.insertMsgAffiliationGroup(message.getUserId(), message.getReceiveId(),id);
        SocketManager.getSocketManager().outputMessageGroup(ints, 10, message);
        return "succeed";
    }
    //发送群组图片消息
    @PostMapping("/sendImageMessageGroup")
    public String sendImageMessageGroup(@RequestBody Message message){
        String base4 = message.getText();
        Date dateTime = DateUtils.transStringToDate(message.getCreateTime());
        String date = message.getCreateTime().replace(" ","");
        date = date.replace(":","");
        date = date.replace("-","");
        String path = base64StringToImage(base4,message.getUserId(),message.getReceiveId(),date,message.getType());
        messageManagerServices.sendImage(path);
        int imgId = messageManagerServices.selectImageId(path);
        int id = messageManagerServices.sendImageMessageGroup(message.getUserId(), imgId, message.getReceiveId(), message.getCreateTime(), message.getType());
        List<Integer> ints = messageManagerServices.selectGroupMenber(Integer.toString(message.getReceiveId()),Integer.toString(message.getUserId()));
        for (int i = 0; i < ints.size(); i++) {
            messageManagerServices.insertUnreadMessageGroup(id,Integer.toString(message.getReceiveId()),ints.get(i));
            messageManagerServices.insertMsgAffiliationGroup(ints.get(i), message.getReceiveId(),id);
            dialogsManagerService.insertDialog(ints.get(i),message.getReceiveId());
        }
        messageManagerServices.insertMsgAffiliationGroup(message.getUserId(), message.getReceiveId(),id);
        SocketManager.getSocketManager().outputMessageGroup(ints, 10, message);
        return path;
    }
    //删除消息
    @PostMapping("/deleteMsg")
    public String deleteMsg(@RequestParam int send,@RequestParam int receive) {
        messageManagerServices.deleteMsg(send, receive);
        return "succeed";
    }

    static String base64StringToImage(String base64String,int id,int receiveId,String date,String type) {
        String path = null;
        String imageName = null;
        try {
            byte[] bytes1 = decoder.decodeBuffer(base64String);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            BufferedImage bi1 = ImageIO.read(bais);
            imageName = id+receiveId+date+"."+type;
            path = "src\\main\\resources\\static\\image\\"+imageName;
            File f1 = new File(path);
            ImageIO.write(bi1, type, f1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageName;
    }

    static String imageToBase64String(String path,String type) {
        File file = new File(path);
        String base64 = null;
        try {
            BufferedImage image = ImageIO.read(file);
            if (image.getTransparency() != BufferedImage.OPAQUE) {
                int w = image.getWidth();
                int h = image.getHeight();
                int[] pixels = new int[w * h];
                image.getRGB(0, 0, w, h, pixels, 0, w);
                BufferedImage bi2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                bi2.setRGB(0, 0, w, h, pixels, 0, w);
                image = bi2;
            }
            //输出流
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(image, type, stream);
            base64 = Base64.encode(stream.toByteArray());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return base64;
    }

}
