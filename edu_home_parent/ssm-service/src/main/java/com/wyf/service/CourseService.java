package com.wyf.service;

import com.wyf.domain.Course;
import com.wyf.domain.CourseVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

    // 多条件查询
    public List<Course> findCourseByCondition(CourseVO courseVO);

    // 保存课程信息和讲师信息
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    // 课程信息和讲师信息的回显
    public CourseVO findCourseById(Integer id);

    // 更新课程信息及所关联的讲师信息
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    // 修改课程状态
    public void updateCourseStatus(int id,int status);
}
