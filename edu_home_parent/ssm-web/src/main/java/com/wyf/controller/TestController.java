package com.wyf.controller;

import com.wyf.domain.Test;
import com.wyf.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // 组合注解：@Controller + @ResponseBody:将对象转为JSON
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    // 查询所有用户
    @RequestMapping("/findAll")
    public List<Test> findAll(){
        List<Test> testList = testService.findAll();

        return testList;
    }
}
