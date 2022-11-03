package com.wyf.dao;

import com.wyf.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceMapper {

    // 查询所有广告位列表
    public List<PromotionSpace> findAllPromotionSpace();

    // 添加广告位
    public void savePromotionSpace(PromotionSpace promotionSpace);

    // 回显广告位信息：根据id
    public PromotionSpace findPromotionSpaceById(int id);

    // 修改广告位名字
    public void updatePromotionSpace(PromotionSpace promotionSpace);
}
