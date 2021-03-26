package org.example.service;

import org.apache.ibatis.annotations.Param;
import org.example.entity.Info;

import java.util.List;

public interface InfoManagerService {

    public void sendInfo(int userId,int userId2,String text);

    public void changeInfoStatus(int userId,int userId2,String status);

    public void deleteInfo(int userId,int userId2);

    public Info searchInfo(int userId,int userId2);

    public List<Info> searchInfos(int userId);

    public void deleteInfoForAll(int userId);
}
