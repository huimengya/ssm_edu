package com.wyf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyf.dao.PromotionAdMapper;
import com.wyf.domain.PromotionAd;
import com.wyf.domain.PromotionAdVO;
import com.wyf.service.PromotionAdService;
import com.wyf.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    private PromotionAdMapper promotionAdMapper;

    @Override
    public PageInfo<PromotionAd> findAllPromotionAd(PromotionAdVO promotionAdVO) {
        // 1.在操作数据库之前开启分页        当前页                  每页显示的条数
        PageHelper.startPage(promotionAdVO.getCurrentPage(),promotionAdVO.getPageSize());

        // 2.分页查询
        List<PromotionAd> promotionAdList = promotionAdMapper.findAllPromotionAd();

        // 3.将查询到的广告信息封装到PageHelper进行返回
        PageInfo<PromotionAd> pageInfo = new PageInfo<>(promotionAdList);

        return pageInfo;
    }
    // 添加广告信息

    @Override
    public void savePromotionAd(PromotionAd promotionAd) {
        // 补全信息
        Date date = new Date();
        promotionAd.setCreateTime(date);
        promotionAd.setUpdateTime(date);

        promotionAdMapper.savePromotionAd(promotionAd);
    }
    // 广告信息回显
    @Override
    public PromotionAd findPromotionAdById(int id) {
        PromotionAd promotionAd = promotionAdMapper.findPromotionAdById(id);
        return promotionAd;
    }

    // 修改广告信息
    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {
        // 补全信息
        promotionAd.setUpdateTime(new Date());
        promotionAdMapper.updatePromotionAd(promotionAd);
    }


    // 广告上线下线

    @Override
    public void updatePromotionAdStatus(int id, int status) {
        // 1.封装数据
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAd.setUpdateTime(new Date());

        // 2.传递封装好的promotion对象
        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }
}
