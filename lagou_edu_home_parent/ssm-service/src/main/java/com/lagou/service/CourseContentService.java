package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {

    public List<CourseSection> findSectionAndLessonByCourseId(Integer id);

    public Course findCourseByCourseId(int courseId);

    public void saveSection(CourseSection courseSection);

    public void updateSection(CourseSection courseSection);

    public void updateSectionStatus(int id,int status);
}
