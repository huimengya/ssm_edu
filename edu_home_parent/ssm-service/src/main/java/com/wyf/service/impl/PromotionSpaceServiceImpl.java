package com.wyf.service.impl;

import com.wyf.dao.PromotionSpaceMapper;
import com.wyf.domain.PromotionSpace;
import com.wyf.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {

    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;

    // 查询所有广告位
    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        List<PromotionSpace> spaceList = promotionSpaceMapper.findAllPromotionSpace();

        return spaceList;
    }

    // 添加广告位

    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {
        // 补全参数
        Date date = new Date();
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());
        promotionSpace.setIsDel(0);
        // 调用方法
        promotionSpaceMapper.savePromotionSpace(promotionSpace);
    }
    // 根据id查询广告位信息
    @Override
    public PromotionSpace findPromotionSpaceById(int id) {
        PromotionSpace promotionSpace = promotionSpaceMapper.findPromotionSpaceById(id);
        return promotionSpace;
    }

    // 根据id修改广告位名字
    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {
        // 补全信息
        promotionSpace.setUpdateTime(new Date());
        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }
}
