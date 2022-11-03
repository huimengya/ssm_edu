package com.wyf.controller;

import com.wyf.domain.Menu;
import com.wyf.domain.ResponseResult;
import com.wyf.domain.Role;
import com.wyf.domain.RoleMenuVO;
import com.wyf.service.MenuService;
import com.wyf.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // 查询角色信息&多条件查询
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){

        List<Role> allRole = roleService.findAllRole(role);

        return new ResponseResult(true,200,"查询角色信息成功",allRole);
    }
    @Autowired
    private MenuService menuService;

    // 查询所有的父子级菜单
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> menuList = menuService.findAllMenuById(-1);

        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);

        return new ResponseResult(true,200,"查询所有的菜单信息",map);
    }

    // 根据角色id查询所关联的菜单信息
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findAllMenuByRoleId(Integer roleId){
        List<Integer> integerList = roleService.findAllMenuIdByRoleId(roleId);

        return new ResponseResult(true,200,"根据角色ID查询关联菜单ID成功",integerList);
    }

    // 给角色分配菜单列表
    @RequestMapping("/RoleContextMenu")
    public ResponseResult saveRoleMenu(@RequestBody RoleMenuVO roleMenuVO){
        roleService.saveRoleMenu(roleMenuVO);

        return new ResponseResult(true,200,"响应成功",null);
    }

    // 删除角色信息
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleService.deleteRole(id);

        return new ResponseResult(true,200,"删除角色成功",null);
    }
}
