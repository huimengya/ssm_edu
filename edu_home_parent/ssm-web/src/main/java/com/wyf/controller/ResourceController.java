package com.wyf.controller;

import com.github.pagehelper.PageInfo;
import com.wyf.domain.Resource;
import com.wyf.domain.ResourceVO;
import com.wyf.domain.ResponseResult;
import com.wyf.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    // 分页查询和多条件查询
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVO resourceVO){
        PageInfo<Resource> pageInfo = resourceService.findAllResource(resourceVO);

        return new ResponseResult(true,200,"分页查询&多条件查询成功",pageInfo);
    }

    // 添加资源和修改资源
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource){

        // 通过id判断是添加操作还是修改操作
        if (resource.getId() == null){
            // 添加操作
            resourceService.saveResource(resource);

            return new ResponseResult(true,200,"添加资源成功",null);
        }else{
            // 修改操作
            resourceService.updateResource(resource);
            return new ResponseResult(true,200,"修改资源成功",null);
        }
    }

    // 删除资源
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(Integer id){

        resourceService.deleteResource(id);
        return new ResponseResult(true,200,"删除资源成功",null);
    }
}
