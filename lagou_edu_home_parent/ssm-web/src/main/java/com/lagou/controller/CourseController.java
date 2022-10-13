package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

@RestController //组合注解 @Controller + @ResponseBody(转JSON)
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){
        List<Course> list = courseService.findCourseByCondition(courseVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);
        return responseResult;
    }

    @RequestMapping("/courseUpload")
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
    /*
    * 新增课程讲师信息
    * */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        if (courseVO.getId() == null){
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "新增成功", null);
            return responseResult;
        }else {
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
            return responseResult;
        }
    }
    /**
     * 根据id获取课程信息
     * */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id) {
        CourseVO courseVO = courseService.findCourseById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "根据ID查询课程信息成功", courseVO);
        return responseResult;
    }
    /**
     * 修改课程状态
     * */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id,Integer status){
        courseService.updateCourseStatus(id,status);
        HashMap<String, Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult responseResult = new ResponseResult(true, 200, "课程状态变更成功", map);
        return responseResult;
    }
}
