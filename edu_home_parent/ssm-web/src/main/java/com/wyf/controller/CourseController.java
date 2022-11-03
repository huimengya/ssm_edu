package com.wyf.controller;

import com.wyf.domain.Course;
import com.wyf.domain.CourseVO;
import com.wyf.domain.ResponseResult;
import com.wyf.service.CourseService;
import com.wyf.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController  // 组合注解：@Controller 、@ResponseBody：将方法的返回值转为JSON格式响应给前台
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     *  多条件查询的接口
     * @param courseVO
     * @return
     */
    // 因为前台传递过来的参数是JSON格式的数据，不是k-v形式，不能直接封装到实体类中
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){

        List<Course> list = courseService.findCourseByCondition(courseVO);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);

        return responseResult;
    }


    /**
     *  图片上传的接口
     */
    @RequestMapping("/courseUpload") // @RequestParam：将请求参数绑定到方法的参数上
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        // 使用工具类完成图片上传
        ResponseResult fileUpload = FileUploadUtils.fileUpload(file, request);

        // 响应
        return fileUpload;
    }

    /**
     *  添加课程和老师信息
     *  根据id是否为空：修改课程和所关联的老师的信息
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        if (courseVO.getId() == null){
            // id为空：新建课程
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult result = new ResponseResult(true, 200, "添加成功", null);
            return result;
        }else{
            // id不为空：修改、更新
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult result = new ResponseResult(true, 200, "修改成功", null);
            return result;
        }
    }

    /**
     *  课程回显：根据id
     */
    @RequestMapping("/findCourseById")    // 因为前台是get请求，传递的是K-v键值对，所以springMVC会自动封装
    public ResponseResult findCourseById(Integer id){
        CourseVO courseVO = courseService.findCourseById(id);

        ResponseResult result = new ResponseResult(true, 200, "课程回显成功", courseVO);
        return result;
    }
    /**
     *  修改课程状态
     */
    @RequestMapping("/updateCourseStatus") // get请求
    public ResponseResult updateCourseStatus(int id,int status){
        // 调用service
        courseService.updateCourseStatus(id, status);

        // 响应数据
        Map<String,Object> map = new HashMap<>();
        map.put("status",status);

        ResponseResult responseResult = new ResponseResult(true, 200, "课程状态修改成功", map);

        return responseResult;
    }


}
