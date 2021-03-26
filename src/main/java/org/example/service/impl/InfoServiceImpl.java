package org.example.service.impl;

import org.example.entity.Info;
import org.example.mapper.InfoManagerMapper;
import org.example.service.InfoManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoManagerService {

    @Autowired
    InfoManagerMapper infoManagerMapper;

    @Override
    public void sendInfo(int userId, int userId2, String text) {
        infoManagerMapper.sendInfo(userId, userId2, text);
    }

    @Override
    public void changeInfoStatus(int userId, int userId2, String status) {
        infoManagerMapper.changeInfoStatus(userId, userId2, status);
    }

    @Override
    public void deleteInfo(int userId, int userId2) {
        infoManagerMapper.deleteInfo(userId, userId2);
    }

    @Override
    public Info searchInfo(int userId, int userId2) {
        Info info;
        if ((info = infoManagerMapper.searchInfo(userId, userId2)) != null)
            return info;
        else
            return null;
    }

    @Override
    public List<Info> searchInfos(int userId) {
        List<Info> infos;
        if ((infos = infoManagerMapper.searchInfos(userId)) != null)
            return infos;
        else
            return null;
    }

    @Override
    public void deleteInfoForAll(int userId) {
        infoManagerMapper.deleteInfoForAll(userId);
    }

}
