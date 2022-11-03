package com.wyf.dao;

import com.wyf.domain.Course;
import com.wyf.domain.CourseVO;
import com.wyf.domain.Teacher;

import java.util.List;

public interface CourseMapper {

    // 多条件查询
    public List<Course> findCourseByCondition(CourseVO courseVO);

    // 保存课程信息，返回该记录的id值
    public int saveCourse(Course course);

    // 保存讲师信息
    public void saveTeacher(Teacher teacher);

    // 课程信息和教师信息的回显：当点击编辑课程时
    public CourseVO findCourseById(Integer id);

    // 更新课程信息
    public void updateCourse(Course course);

    // 更新讲师信息
    public void updateTeacher(Teacher teacher);

    // 修改课程状态
    public void updateCourseStatus(Course course);

}
