package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;

import java.util.List;

public interface ResourceMapper {
    /*
        资源分页 及 多条件查询
     */
    public List<Resource> findAllResourceByPage(ResourceVO resourceVO);
    public void saveResource(Resource resource);

    public void updateResource(Resource resource);

    public void deleteResource(Integer id);
}
