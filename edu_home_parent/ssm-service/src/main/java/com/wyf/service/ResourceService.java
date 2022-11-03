package com.wyf.service;

import com.github.pagehelper.PageInfo;
import com.wyf.domain.Resource;
import com.wyf.domain.ResourceVO;


public interface ResourceService {
    // 分页查询&多条件查询
    public PageInfo<Resource> findAllResource(ResourceVO resourceVO);

    // 添加资源
    public void saveResource(Resource resource);
    // 修改资源
    public void updateResource(Resource resource);
    // 删除资源
    public void deleteResource(Integer id);


}
