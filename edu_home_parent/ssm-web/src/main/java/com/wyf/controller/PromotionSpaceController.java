package com.wyf.controller;

import com.wyf.domain.PromotionSpace;
import com.wyf.domain.ResponseResult;
import com.wyf.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;

    // 查询所有的广告位信息
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){

        List<PromotionSpace> spaceList = promotionSpaceService.findAllPromotionSpace();

        return new ResponseResult(true,200,"查询广告位成功",spaceList);
    }

    // 添加/修改广告位
    @RequestMapping("/saveOrUpdatePromotionSpace") // 只要前台是post请求且JSON串，就需要借助注解转换封装
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){
        if (promotionSpace.getId() == null){
            // 新增操作
            promotionSpaceService.savePromotionSpace(promotionSpace);
            //响应
            return new ResponseResult(true,200,"添加广告位成功",null);
        }else{
            // 修改操作
            promotionSpaceService.updatePromotionSpace(promotionSpace);
            //响应
            return new ResponseResult(true,200,"修改广告位成功",null);
        }
    }

    // 根据id查询广告位信息
    @RequestMapping("/findPromotionSpaceById") // 接收的是get请求
    public ResponseResult findPromotionById(int id){
        PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);
        return new ResponseResult(true,200,"根据id查询广告位信息成功",promotionSpace);
    }

}
