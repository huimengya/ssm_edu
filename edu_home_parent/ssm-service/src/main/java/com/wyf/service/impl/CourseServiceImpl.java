package com.wyf.service.impl;

import com.wyf.dao.CourseMapper;
import com.wyf.domain.Course;
import com.wyf.domain.CourseVO;
import com.wyf.domain.Teacher;
import com.wyf.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findCourseByCondition(CourseVO courseVO) {
        List<Course> courseList = courseMapper.findCourseByCondition(courseVO);
        return courseList;
    }

    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        // 1.封装课程信息
        Course course = new Course();
            // 借助工具类将courseVO中的信息封装到course中
        BeanUtils.copyProperties(course,courseVO);
            // 补全信息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);
            // 调用dao层的方法:保存课程信息
        courseMapper.saveCourse(course);
            // 获取保存成功之后的课程id
        int id = course.getId();

        // 2.封装老师信息
        Teacher teacher = new Teacher();
            // 借助工具类将courseVO中的信息封装到course中
        BeanUtils.copyProperties(teacher,courseVO);
            // 补全信息
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(1);
        teacher.setCourseId(id);
            // 保存老师的信息
        courseMapper.saveTeacher(teacher);
    }

    // 课程信息的回显
    @Override
    public CourseVO findCourseById(Integer id) {

        return courseMapper.findCourseById(id);
    }

    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        // 1.封装课程信息
        Course course = new Course();
            // 补全信息
        Date date = new Date();
        course.setUpdateTime(date);

        BeanUtils.copyProperties(course,courseVO);

            // 更新课程信息
        courseMapper.updateCourse(course);

        // 2.更新课程所关联的讲师信息
        Teacher teacher = new Teacher();
            // 补全信息
        teacher.setCourseId(course.getId());
        teacher.setUpdateTime(date);
        BeanUtils.copyProperties(teacher,courseVO);
            // 更新讲师信息
        courseMapper.updateTeacher(teacher);
    }
    // 修改课程状态

    @Override
    public void updateCourseStatus(int id, int status) {
        // 封装数据、补全信息
        Course course = new Course();
        course.setId(id);
        course.setStatus(status);
        course.setUpdateTime(new Date());
        // 修改课程状态
        courseMapper.updateCourseStatus(course);
    }
}
