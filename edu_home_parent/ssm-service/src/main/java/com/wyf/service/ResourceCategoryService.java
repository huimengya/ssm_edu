package com.wyf.service;

import com.wyf.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryService {
    // 查询资源的分类信息
    public List<ResourceCategory> findAllResourceCategory();
}
