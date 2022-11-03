package com.wyf.controller;

import com.github.pagehelper.PageInfo;
import com.wyf.domain.PromotionAd;
import com.wyf.domain.PromotionAdVO;
import com.wyf.domain.ResponseResult;
import com.wyf.service.PromotionAdService;
import com.wyf.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    // 分页查询广告信息
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAd(PromotionAdVO promotionAdVO){
        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAd(promotionAdVO);

        return new ResponseResult(true,200,"分页查询成功",pageInfo);
    }

    /**
     *  图片上传的接口
     */
    @RequestMapping("/PromotionAdUpload") // @RequestParam：将请求参数绑定到方法的参数上
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        // 使用工具类完成图片上传
        ResponseResult fileUpload = FileUploadUtils.fileUpload(file, request);

        // 响应
        return fileUpload;
    }
    /**
     *  添加/修改广告信息
     */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){

        if (promotionAd.getId() == null){
            // 添加操作
            promotionAdService.savePromotionAd(promotionAd);

            return new ResponseResult(true,200,"添加广告信息成功",null);
        }else{
            // 修改操作
            promotionAdService.updatePromotionAd(promotionAd);

            return new ResponseResult(true,200,"修改广告信息成功",null);
        }

    }

    /**
     *  广告信息的回显
     */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(int id){

        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);

        return new ResponseResult(true,200,"广告信息回显成功",promotionAd);
    }


    /**
     *  广告上下线
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(int id,int status){
        promotionAdService.updatePromotionAdStatus(id, status);

        HashMap<String, Object> map = new HashMap<>();
        map.put("status",status);

        // 响应
        return new ResponseResult(true,200,"广告状态设置成功",map);
    }
}
