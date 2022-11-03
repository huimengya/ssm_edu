package com.wyf.service.impl;

import com.wyf.dao.ResourceCategoryMapper;
import com.wyf.domain.ResourceCategory;
import com.wyf.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        List<ResourceCategory> resourceCategoryList = resourceCategoryMapper.findAllResourceCategory();
        return resourceCategoryList;
    }
}
