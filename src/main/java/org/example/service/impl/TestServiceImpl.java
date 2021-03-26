package org.example.service.impl;

import org.example.entity.Test;
import org.example.mapper.TestMapper;
import org.example.service.TestService;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;
    @Override
    public List<Test> getTestList() {
        return testMapper.queryTestList();
    }
}
