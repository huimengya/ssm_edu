package com.wyf.dao;

import com.wyf.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    // 分页查询广告信息
    public List<PromotionAd> findAllPromotionAd();

    // 添加广告信息
    public void savePromotionAd(PromotionAd promotionAd);

    // 回显广告信息
    public PromotionAd findPromotionAdById(int id);

    // 修改广告信息
    public void updatePromotionAd(PromotionAd promotionAd);

    // 广告动态上下线
    public void updatePromotionAdStatus(PromotionAd promotionAd);
}
