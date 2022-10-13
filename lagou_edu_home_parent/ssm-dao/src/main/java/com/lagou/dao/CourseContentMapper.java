package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {
    /**
     * 查询课程下的章节与课时信息
     * */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);
    /**
     * 回显章节对应的课程信息
     * */
    public Course findCourseByCourseId(int courseId);
    /**
     * 新增章节
     * */
    public void saveSection(CourseSection courseSection);
    /**
     * 修改章节
     * */
    public void updateSection(CourseSection courseSection);
    /**
     * 修改章节状态
     * */
    public void updateSectionStatus(CourseSection courseSection);

}
