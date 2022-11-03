package com.wyf.service;

import com.github.pagehelper.PageInfo;

import com.wyf.domain.ResponseResult;
import com.wyf.domain.Role;
import com.wyf.domain.User;
import com.wyf.domain.UserVO;

import java.util.List;


public interface UserService {

    // 分页查询和多条件查询
    public PageInfo findAllUserByPage(UserVO userVO);

    // 用户状态修改：根据前台传递的id
    public void updateUserStatus(int id,String status);

    // 用户登录
    public User login(User user) throws Exception;

    // 分配角色：回显
    public List<Role> findUserRoleById(Integer id);

    // 分配角色
    public void userContextRole(UserVO userVO);

    // 获取用户权限
    public ResponseResult getUserPermissions(Integer uid);
}
