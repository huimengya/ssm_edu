package com.wyf.service.impl;

import com.wyf.dao.TestDao;
import com.wyf.domain.Test;
import com.wyf.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;
    @Override
    public List<Test> findAll() {

        // 业务处理
        List<Test> testList = testDao.findAll();

        return testList;
    }
}
