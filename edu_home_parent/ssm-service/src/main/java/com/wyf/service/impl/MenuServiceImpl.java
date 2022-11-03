package com.wyf.service.impl;

import com.wyf.dao.MenuMapper;
import com.wyf.domain.Menu;
import com.wyf.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    // 查询所有的父子级菜单
    @Override
    public List<Menu> findAllMenuById(int pid) {

        List<Menu> menuList = menuMapper.findAllMenuById(pid);

        return menuList;
    }

    // 查询所有的菜单信息
    @Override
    public List<Menu> findAllMenu() {

        List<Menu> allMenu = menuMapper.findAllMenu();
        return allMenu;
    }


    // 根据id查询所有信息
    @Override
    public Menu findMenu(int id) {
        Menu menu = menuMapper.findMenu(id);
        return menu;
    }

    // 添加和修改菜单
    @Override
    public void saveMenu(Menu menu) {
        // 1、补全信息
        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");

        // 2、传递参数
        menuMapper.saveMenu(menu);
    }

    // 修改菜单信息
    @Override
    public void updateMenu(Menu menu) {
        // 1、补全信息
        Date date = new Date();
        menu.setUpdatedTime(date);
        menu.setUpdatedBy("system");

        // 2.传递参数
        menuMapper.updateMenu(menu);
    }

    // 删除菜单
    @Override
    public void deleteMenu(Integer id) {
        menuMapper.deleteMenu(id);
    }
}
