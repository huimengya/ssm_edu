package com.wyf.service;

import com.wyf.domain.Menu;

import java.util.List;

public interface MenuService {

    // 查询所有的父子级菜单 menu表中的parent_id相当于外键==指向==》id(其中id=-1表示父级菜单，id=1表示子级菜单)
    public List<Menu> findAllMenuById(int pid);

    // 查询所有的菜单信息
    public List<Menu> findAllMenu();

    // 根据id查询菜单信息
    public Menu findMenu(int id);

    // 添加菜单和修改菜单
    public void saveMenu(Menu menu);

    // 修改菜单信息
    public void updateMenu(Menu menu);

    // 根据id删除菜单
    public void deleteMenu(Integer id);
}
