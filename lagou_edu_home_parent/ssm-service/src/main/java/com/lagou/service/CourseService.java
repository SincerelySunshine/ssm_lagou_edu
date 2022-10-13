package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {
    /**
     * 多条件查询课程列表
     * */
    public List<Course> findCourseByCondition(CourseVO courseVO);
    /*
    * 新建课程讲师信息
    * */
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;
    /**
     * 根据id获取课程信息
     * */
    public CourseVO findCourseById(Integer id);
    /**
     * 修改课程及讲师信息
     * */
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;
    /**
     * 修改课程状态
     * */
    public void updateCourseStatus(int id,int status);
}
