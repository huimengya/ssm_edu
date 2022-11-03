package com.wyf.service;

import com.wyf.domain.Course;
import com.wyf.domain.CourseLesson;
import com.wyf.domain.CourseSection;

import java.util.List;

public interface CourseContentService {
    // 根据课程id查询章节及所关联的课时信息
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId);

    // 回显章节对应的课程信息
    public Course findCourseById(int courseId);

    // 新增章节信息
    public void saveSection(CourseSection courseSection);

    // 修改章节信息
    void updateSection(CourseSection courseSection);

    // 修改章节状态
    public void updateSectionStatus(int id,int status);

    // 新建课时信息
    public void saveLesson(CourseLesson courseLesson);
}
