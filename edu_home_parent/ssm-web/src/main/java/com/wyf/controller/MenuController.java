package com.wyf.controller;

import com.wyf.domain.Menu;
import com.wyf.domain.ResponseResult;
import com.wyf.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    // 查询所有菜单信息
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){

        List<Menu> allMenu = menuService.findAllMenu();

        return new ResponseResult(true,200,"查询所有菜单信息",allMenu);
    }

    // 回显菜单信息
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){

        Map<String, Object> map = new HashMap<>();


        if (id == -1){
            // 添加菜单的回显：当为添加菜单时，只需要回显所有父级菜单即可：权限管理、课程管理...
            List<Menu> menuList = menuService.findAllMenuById(-1);
            map.put("menuInfo",null);
            map.put("parentMenuList",menuList);

            return new ResponseResult(true,200,"回显菜单信息成功",map);

        }else{
            // 修改菜单的回显
            Menu menu = menuService.findMenu(id);
            List<Menu> menuList = menuService.findAllMenuById(-1);

            map.put("menuInfo",menu);
            map.put("parentMenuList",menuList);
            return new ResponseResult(true,200,"回显菜单信息成功",map);
        }
    }

    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu){
        // 判断是否携带参数id
        if (menu.getId() == null){
            // 添加菜单
            menuService.saveMenu(menu);
            // 响应
            return new ResponseResult(true,200,"添加菜单成功",null);
        }else{
            // id不等于空表示修改
            menuService.updateMenu(menu);
            // 响应
            return new ResponseResult(true,200,"修改菜单成功",null);
        }
    }

    @RequestMapping("/deleteMenu")
    public ResponseResult deleteMenu(Integer id){
        menuService.deleteMenu(id);
        return new ResponseResult(true,200,"删除菜单成功",null);
    }
}
