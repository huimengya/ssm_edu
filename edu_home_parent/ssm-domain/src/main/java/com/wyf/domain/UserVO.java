package com.wyf.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

// 接收前台参数
public class UserVO {

    // 当前页
    private Integer currentPage;

    // 每页显示的条数
    private Integer pageSize;

    // 用户名===》数据库中手机号
    private String username;

    // 注册开始的时间 前台发送的时间格式：2022-10-29 springMVC接收的时间： 2022/10/29
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startCreateTime;

    // 注册结束的时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endCreateTime;

    // 用户id
    private Integer userId;

    // 用户对应的角色(id组成的集合)信息
    private List<Integer> roleIdList;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Integer> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Integer> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(Date startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }
}
