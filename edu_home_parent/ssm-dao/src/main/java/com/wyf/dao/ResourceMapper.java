package com.wyf.dao;

import com.wyf.domain.Resource;

import java.util.List;

public interface ResourceMapper {
    // 分页查询&多条件查询
    public List<Resource> findAllResource();

    // 添加资源
    public void saveResource(Resource resource);
    // 修改资源
    public void updateResource(Resource resource);
    // 删除资源
    public void deleteResource(Integer id);
}
