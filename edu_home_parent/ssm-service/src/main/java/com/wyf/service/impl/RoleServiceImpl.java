package com.wyf.service.impl;

import com.wyf.dao.RoleMapper;
import com.wyf.domain.Menu;
import com.wyf.domain.Role;
import com.wyf.domain.RoleMenuVO;
import com.wyf.domain.Role_menu_relation;
import com.wyf.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    // 查询所有角色信息&条件查询
    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);

        return allRole;
    }

    // 根据角色id查询关联的菜单信息
    @Override
    public List<Integer> findAllMenuIdByRoleId(int roleId) {
        List<Integer> integerList = roleMapper.findAllMenuIdByRoleId(roleId);

        return integerList;
    }

    // 为角色分配菜单列表
    @Override
    public void saveRoleMenu(RoleMenuVO roleMenuVO) {
        // 1.清空角色和菜单的中间表
        roleMapper.deleteRoleMenu(roleMenuVO.getRoleId());

        Date date = new Date();
        Role_menu_relation relation = new Role_menu_relation();

        // 3.遍历接收到的菜单id&封装数据
        for (Integer mid : roleMenuVO.getMenuIdList()) {
            relation.setRoleId(roleMenuVO.getRoleId());
            relation.setMenuId(mid);
            relation.setCreatedTime(date);
            relation.setUpdatedTime(date);
            relation.setCreatedBy("system");
            relation.setUpdatedby("system");

            roleMapper.saveRoleMenu(relation);
        }
    }

    // 删除角色信息
    @Override
    public void deleteRole(Integer rid) {
        // 1.先删除关联信息
        roleMapper.deleteRoleMenu(rid);
        // 2.删除角色
        roleMapper.deleteRole(rid);
    }
}
