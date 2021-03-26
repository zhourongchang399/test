package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entity.UserInfo;

import javax.annotation.security.PermitAll;
import java.util.List;

@Mapper
public interface BlackListManagerMapper {

    public void insertBlackList(@Param("hostId") int hostId, @Param("userId") int userId);

    public List<UserInfo> searchBlackList(@Param("hostId") int hostId);

    public void deleteBlackList(@Param("hostId") int hostId, @Param("userId") int userId);

    public UserInfo searchBlackListByOne(@Param("hostId") int hostId, @Param("userId") int userId);

}
