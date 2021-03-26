package org.example.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Test;

import java.util.List;

@Mapper
public interface TestMapper {

    public List<Test> queryTestList();
}
