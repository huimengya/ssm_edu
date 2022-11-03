package com.wyf.controller;

import com.wyf.domain.Course;
import com.wyf.domain.CourseLesson;
import com.wyf.domain.CourseSection;
import com.wyf.domain.ResponseResult;
import com.wyf.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    // 查询章节及对应的课时信息
    @RequestMapping("/findSectionAndLesson") //get请求
    public ResponseResult findSectionAndLessonByCourseId(int courseId){
        List<CourseSection> sectionList = courseContentService.findSectionAndLessonByCourseId(courseId);
        ResponseResult result = new ResponseResult(true, 200, "查询章节和课时信息成功", sectionList);
        return result;
    }

    // 回显章节对应的课程信息
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseById(int courseId){
        Course course = courseContentService.findCourseById(courseId);

        ResponseResult result = new ResponseResult(true, 200, "课程信息回显成功", course);

        return result;
    }
    // 新增或修改章节信息
    @RequestMapping("/saveOrUpdateSection")   // 因为前台发送的数据是JSON串
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){

        // 通过对是否携带章节id进行判断
        if (courseSection.getId() == null){
            // 新增章节信息
            // 将接收到的参数发送给service
            courseContentService.saveSection(courseSection);
            // 响应
            ResponseResult result = new ResponseResult(true, 200, "添加章节成功", null);
            return result;
        }else{
            // 更新章节信息
            courseContentService.updateSection(courseSection);
            // 响应
            ResponseResult result = new ResponseResult(true, 200, "修改章节成功", null);
            return result;
        }
    }

    // 修改章节状态
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id,int status){
        courseContentService.updateSectionStatus(id, status);
        // 响应数据
        Map<Object, Object> map = new HashMap<>();
        map.put("status",status);

        return new ResponseResult(true,200,"修改章节状态成功",map);
    }

    // 新建课时信息
    @RequestMapping("/saveLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson courseLesson){
        courseContentService.saveLesson(courseLesson);

        // 响应
        return new ResponseResult(true,200,"新建课时信息成功",null);
    }

}
