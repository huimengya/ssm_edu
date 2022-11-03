package com.wyf.service.impl;

import com.wyf.dao.CourseContentMapper;
import com.wyf.domain.Course;
import com.wyf.domain.CourseLesson;
import com.wyf.domain.CourseSection;
import com.wyf.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    // 查询章节及对应的课时信息
    @Autowired
    private CourseContentMapper courseContentMapper;
    @Override

    public List<CourseSection> findSectionAndLessonByCourseId(int courseId) {
        List<CourseSection> sectionList = courseContentMapper.findSectionAndLessonByCourseId(courseId);
        return sectionList;
    }

    // 回显章节对应的课程信息

    @Override
    public Course findCourseById(int courseId) {

        Course course = courseContentMapper.findCourseById(courseId);

        return course;
    }

    // 新增章节信息
    @Override
    public void saveSection(CourseSection courseSection) {
        // 1.补全信息
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);

        // 2.添加章节信息
        courseContentMapper.saveSection(courseSection);
    }

    // 修改章节信息
    @Override
    public void updateSection(CourseSection courseSection) {
        // 补全信息
        courseSection.setUpdateTime(new Date());

        courseContentMapper.updateSection(courseSection);
    }
    // 修改章节状态

    @Override
    public void updateSectionStatus(int id, int status) {
        // 封装数据
        CourseSection courseSection = new CourseSection();
        courseSection.setId(id);
        courseSection.setStatus(status);
        courseSection.setUpdateTime(new Date());
        // 将封装好的数据发送给dao
        courseContentMapper.updateSectionStatus(courseSection);
    }

    // 新建课时信息
    @Override
    public void saveLesson(CourseLesson courseLesson) {
        // 1.补全信息
        Date date = new Date();
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);

        courseContentMapper.saveLesson(courseLesson);
    }
}
