package com.wyf.dao;

import com.wyf.domain.*;

import java.util.List;

public interface UserMapper {

    // 分页查询和多条件查询
    public List<User> findAllUserByPage(UserVO userVO);

    // 用户状态修改：根据前台传递的id
    public void updateUserStatus(User user);

    // 用户登录
    public User login(User user);

    // 分配角色之前需要先清空用户关联的角色信息，避免数据重复
    public void deleteUserRelationRole(Integer userId);

    // 给用户分配角色信息
    public void userContextRole(User_Role_relation userRoleRelation);


    // 1.分配角色的回显：通过用户id查询所关联的角色信息
    public List<Role> findUserRoleById(Integer id);

    // 2.根据角色id查询角色所关联的顶级菜单信息：多对多,
    public List<Menu> findMenuByRoleId(List<Integer> ids);

    // 3.根据parent_id查询所有的子级菜单信息：parent_id指向id，相当于外键
    public List<Menu> findSubMenuByPid(Integer id);

    // 4.根据查询出来的角色信息查询所关联的资源信息, 角色-资源
    public List<Resource> findResourceByRoleId(List<Integer> ids);

    public void test1();
    public void test2();
    public void test3();
    public void test4();
    public void test5();
    public void test6();
}
