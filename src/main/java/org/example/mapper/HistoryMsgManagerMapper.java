package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Message;

import java.util.List;

@Mapper
public interface HistoryMsgManagerMapper {

    public List<Message> searchHistoryBySearch(@Param("send") int send, @Param("receive") int receive, @Param("search") String search);

    public List<Message> searchHistoryByOwner(@Param("send") int send, @Param("receive") int receive,@Param("owner") int owner);

    public List<Message> searchHistoryByType(@Param("send") int send, @Param("receive") int receive, @Param("type") String type);

    public List<Message> searchHistoryByDate(@Param("send") int send, @Param("receive") int receive, @Param("date") String date);

    public List<Message> searchHistoryBySearchGroup(@Param("group") int group, @Param("userId") int userId, @Param("search") String search);

    public List<Message> searchHistoryByOwnerGroup(@Param("group") int group, @Param("userId") int userId,@Param("owner") int owner);

    public List<Message> searchHistoryByTypeGroup(@Param("group") int group, @Param("userId") int userId);

    public List<Message> searchHistoryByDateGroup(@Param("group") int group, @Param("userId") int userId, @Param("date") String date);

    public void deleteHistoryForAll(@Param("send") int send, @Param("receive") int receive);

}
