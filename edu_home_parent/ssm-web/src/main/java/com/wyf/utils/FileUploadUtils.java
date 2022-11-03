package com.wyf.utils;

import com.wyf.domain.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class FileUploadUtils {
    public static ResponseResult fileUpload(MultipartFile file, HttpServletRequest request){

        // 1.判断文件是否为空
        if (file.isEmpty()){
            System.out.println("接收到的文件为空");
        }
        // 2.获取项目的路径
        // D:\apache-tomcat-8.5.56\webapps\ssm-web\
        String realPath = request.getServletContext().getRealPath("/");
        // 因为要将图片保存到webapps目录下的upload文件，所以得对获取到的项目路径切割
        String webapp = realPath.substring(0, realPath.indexOf("ssm-web"));

        // 3.获取文件名
        String filename = file.getOriginalFilename();

        // 4.使用时间戳给文件起新的名  例如：qq.jpg ===> 1234.jpg               将文件名从后往前切割，只要后缀
        String newFileName = System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."));

        // 5.将文件上传到哪里

        String upload = webapp + "upload\\";
        File files = new File(upload,newFileName); // 上传路径+上传的文件

        // 如果文件目录不存在就创建目录
        // 通过获取父路径来判断upload是否存在
        if(!files.getParentFile().exists()){
            files.getParentFile().mkdirs();
            System.out.println("创建了目录："+files);
        }
        try {
            // 真正的文件上传
            file.transferTo(files);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 将文件名和文件地址返回
        Map<String,String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/"+newFileName);

        // 响应
        ResponseResult result = new ResponseResult(true, 200, "图片上传成功", map);

        return result;
    }

}
