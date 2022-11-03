package com.wyf.controller;

import com.github.pagehelper.PageInfo;
import com.wyf.domain.ResponseResult;
import com.wyf.domain.Role;
import com.wyf.domain.User;
import com.wyf.domain.UserVO;
import com.wyf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 分页查询和多条件查询
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findUserByPage(@RequestBody UserVO userVO){
        PageInfo pageInfo = userService.findAllUserByPage(userVO);

        return new ResponseResult(true,200,"多条件分页查询成功",pageInfo);
    }

    // 修改用户状态
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(int id,String status){
        userService.updateUserStatus(id, status);
        // 响应
        HashMap<String, Object> map = new HashMap<>();
        map.put("status",status);

        return new ResponseResult(true,200,"用户状态修改成功",status);
    }

    /**
     *  用户登录
     */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {

        // 1.调用service
        User user2 = userService.login(user);

        // 2.判断返回值为不为空，为空返回null，不为空的话将用户id和access_token存到Session域中，作为下次请求的令牌
        if (user2 != null){
            // 登陆成功
            // 2.1获取session域,并将信息保存到域中
            HttpSession session = request.getSession();
            session.setAttribute("user_id",user2.getId());
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token",access_token);

            // 2.2响应
            Map<Object, Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id",user2.getId());
            return new ResponseResult(true,200,"响应成功",map);

        }else {
            return new ResponseResult(true,400,"用户名或密码错误",null);
        }
    }

    /**
     *  回显分配角色
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRole(Integer id){
        List<Role> roleList = userService.findUserRoleById(id);

        return new ResponseResult(true,200,"分配角色回显成功",roleList);
    }

    /**
     *  分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVO userVO){
        userService.userContextRole(userVO);

        // 响应
        return new ResponseResult(true,200,"为用户分配角色成功",null);
    }

    /**
     *  当用户登录后，查询用户所具有的的权限信息
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        // 1.从请求头中获取token
        String header_token = request.getHeader("Authorization");
        // 2.从session中获取token
        HttpSession session = request.getSession();
        Object session_token = session.getAttribute("access_token");

        // 3.判断该令牌是否一致，不一致登录失败
        if (header_token.equals(session_token)){
            // 登录成功 从session中获取用户id
            Integer user_id = (Integer) session.getAttribute("user_id");

            // 调用service业务处理
            ResponseResult responseResult = userService.getUserPermissions(user_id);

            return responseResult;
        }
        return new ResponseResult(true,400,"获取用户权失败",null);
    }

}
