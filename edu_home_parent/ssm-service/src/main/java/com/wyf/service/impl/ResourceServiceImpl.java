package com.wyf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyf.dao.ResourceMapper;
import com.wyf.domain.Resource;
import com.wyf.domain.ResourceVO;
import com.wyf.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public PageInfo<Resource> findAllResource(ResourceVO resourceVO) {
        // 1.开启分页查询
        PageHelper.startPage(resourceVO.getCurrentPage(),resourceVO.getPageSize());
        // 2.查询数据库
        List<Resource> resourceList = resourceMapper.findAllResource();
        // 3.将查询结果封装到PageInfo中
        PageInfo<Resource> pageInfo = new PageInfo<>(resourceList);

        return pageInfo;
    }

    //添加资源
    @Override
    public void saveResource(Resource resource) {
        // 1.补全信息
        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);
        resource.setCreatedBy("system");
        resource.setUpdatedBy("system");

        // 2.传递参数
        resourceMapper.saveResource(resource);
    }

    //修改资源
    @Override
    public void updateResource(Resource resource) {
        // 1.补全信息
        Date date = new Date();
        resource.setUpdatedTime(date);
        resource.setUpdatedBy("system");
        // 2.传递信息
        resourceMapper.updateResource(resource);
    }

    // 删除资源
    @Override
    public void deleteResource(Integer id) {
        resourceMapper.deleteResource(id);
    }
}
