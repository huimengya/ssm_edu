package com.wyf.domain;

import java.util.List;

// 用于封装前台传递的给角色分配的菜单信息
public class RoleMenuVO {

    // 角色id
    private Integer roleId;
    // 前台所选的菜单列表(菜单的id值)
    private List<Integer> menuIdList;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Integer> menuIdList) {
        this.menuIdList = menuIdList;
    }
}
