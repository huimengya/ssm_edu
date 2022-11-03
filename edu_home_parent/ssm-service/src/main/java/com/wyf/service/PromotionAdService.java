package com.wyf.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyf.domain.PromotionAd;
import com.wyf.domain.PromotionAdVO;

import java.util.List;

public interface PromotionAdService {

    // 分页查询广告信息
    public PageInfo<PromotionAd> findAllPromotionAd(PromotionAdVO promotionAdVO);

    // 添加广告信息
    public void savePromotionAd(PromotionAd promotionAd);

    // 广告信息的回显
    public PromotionAd findPromotionAdById(int id);

    // 广告信息的修改
    public void updatePromotionAd(PromotionAd promotionAd);

    // 广告上线下线
    public void updatePromotionAdStatus(int id,int status);
}
