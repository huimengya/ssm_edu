package com.wyf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyf.dao.UserMapper;
import com.wyf.domain.*;
import com.wyf.service.UserService;
import com.wyf.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo findAllUserByPage(UserVO userVO) {
        // 1.开启分页功能
        PageHelper.startPage(userVO.getCurrentPage(),userVO.getPageSize());// 参数一：当前页  参数二：每页显示的条数
        // 2.执行分页查询
        List<User> userList = userMapper.findAllUserByPage(userVO);
        // 3.有参构造创建PageInfo并返回
        PageInfo<User> userPageInfo = new PageInfo<>(userList);

        return userPageInfo;
    }
    // 修改用户状态
    @Override
    public void updateUserStatus(int id, String status) {
        // 1.封装数据
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        user.setUpdate_time(new Date());

        // 2.业务处理
        userMapper.updateUserStatus(user);
    }

    // 用户登录
    @Override
    public User login(User user) throws Exception {
        // 1.调用mapper方法
        User user2 = userMapper.login(user);

        // 2.判断，为空返回null，不为空，使用MD5工具
        if (user2 !=null && Md5.verify(user.getPassword(),"wyf",user2.getPassword())){
            return user2;
        }else{
            return null;
        }

    }

    // 分配角色回显：根据接收到的用户id查询所关联的角色信息
    @Override
    public List<Role> findUserRoleById(Integer id) {
        List<Role> roleList = userMapper.findUserRoleById(id);
        return roleList;
    }

    // 给用户分配角色信息
    @Override
    public void userContextRole(UserVO userVO) {
        // 1.清空用户所关联的角色信息
        userMapper.deleteUserRelationRole(userVO.getUserId());

        // 2.重新建立用户关联的角色信息：本质上是向中间表中插入数据
        for (Integer roleId : userVO.getRoleIdList()) {
            // 2.1补全信息
            Date date = new Date();
            User_Role_relation relation = new User_Role_relation();
            // 用户id和角色id
            relation.setUserId(userVO.getUserId());// 用户id
            relation.setRoleId(roleId); // 角色id

            relation.setCreatedTime(date);
            relation.setUpdatedTime(date);
            relation.setCreatedBy("system");
            relation.setUpdatedby("system");

            // 2.2将封装好的数据传递
            userMapper.userContextRole(relation);
        }
    }

    // 获取用户权限
    @Override
    public ResponseResult getUserPermissions(Integer uid) {
        // 1.获取用户拥有的角色信息的id值，因为关联的信息是通过id值维护在中间表的
        List<Role> roleList = userMapper.findUserRoleById(uid);

        // 2.从roleList中取出id值，保存到list集合中
        List<Integer> roleId = new ArrayList<>();

        for (Role role : roleList) {
            roleId.add(role.getId());
        }

        // 3.根据角色的id值查询出所关联的顶级菜单信息
        List<Menu> menuList = userMapper.findMenuByRoleId(roleId);

        // 4.根据顶级菜单的id值查询子级菜单信息
        for (Menu menu : menuList) {
            List<Menu> subMenuByPid = userMapper.findSubMenuByPid(menu.getId());
            // 将查询出来的子级菜单信息封装到subMenuList属性上
            menu.setSubMenuList(subMenuByPid);
        }

        // 5.根据角色id查询所关联的资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleId);

        // 6.响应
        Map<String, Object> map = new HashMap<>();
        map.put("menuList",menuList);
        map.put("resourceList",resourceList);

        return new ResponseResult(true,200,"查询用户权限成功",map);
    }
}
