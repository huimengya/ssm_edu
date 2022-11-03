package com.wyf.dao;

import com.wyf.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryMapper {
    // 查询资源的分类信息
    public List<ResourceCategory> findAllResourceCategory();
}
