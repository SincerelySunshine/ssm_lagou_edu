package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {
    @Autowired
    private PromotionAdService promotionAdService;

    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllAdByPage(PromotionAdVO promotionAdVO){
        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", pageInfo);
        return responseResult;
    }
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        //1.判断文件是否为空
        if (file.isEmpty()){
            throw new RuntimeException();
        }
        //2.获取项目部署路径
        //D:\ProfessionalSoft\apache-tomcat-8.5.81\webapps\ssm_web
        String realPath = request.getServletContext().getRealPath("/");
        //D:\ProfessionalSoft\apache-tomcat-8.5.81\webapps\
        String substring = realPath.substring(0, realPath.indexOf("ssm_web"));
        //3.获取原文件名
        String originalFilename = file.getOriginalFilename();
        //4.新文件名
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
        //5.上传文件
        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath, newFileName);
        //如果目录不存在就创建目录
        if (!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录"+filePath);
        }
        file.transferTo(filePath);
        //6.将文件名和文件路径返回
        HashMap<String, String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/"+newFileName);

        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);
        return responseResult;
    }
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionStatus(Integer id,Integer status){
        promotionAdService.updatePromotionAdStatus(id,status);
        ResponseResult responseResult = new ResponseResult(true, 200, "广告动态上下线", null);
        return responseResult;
    }
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){

        if (promotionAd.getId()!=null){
            //更新操作
            promotionAdService.updatePromotionAd(promotionAd);
            return new ResponseResult(true,200,"修改广告信息成功",null);
        }
        else {
            promotionAdService.savePromotionAd(promotionAd);
            return new ResponseResult(true,200,"添加广告成功",null);
        }

    }
    @RequestMapping("/findAllPromotionAdById")
    public ResponseResult findAllPromotionAdById(Integer id){
        PromotionAd allPromotionAdById = promotionAdService.findAllPromotionAdById(id);
        return new ResponseResult(true,200,"添加广告成功",allPromotionAdById);
    }

}
