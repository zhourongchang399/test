package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Info;

import java.util.List;

@Mapper
public interface InfoManagerMapper {
    public void sendInfo(@Param("send") int userId,@Param("receive") int userId2,@Param("text") String text);

    public void changeInfoStatus(@Param("send") int userId,@Param("receive") int userId2,@Param("status") String status);

    public void deleteInfo(@Param("send") int userId,@Param("receive") int userId2);

    public Info searchInfo(@Param("send") int userId, @Param("receive") int userId2);

    public List<Info> searchInfos(@Param("receive") int userId);

    public void deleteInfoForAll(@Param("userId") int userId);
}
