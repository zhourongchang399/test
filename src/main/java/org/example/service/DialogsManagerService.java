package org.example.service;

import org.example.entity.Dialog;

import java.util.List;

public interface DialogsManagerService {
    public List<Dialog> searchDialogs(int userId);

    public void deleteDialog(int hostUserId,int userId);

    public Dialog selectDialog(int hostUserId, int userId);

    public void insertDialog(int userId,int group);

    public void deleteDialogGroup(int userId,int group);
}
