package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public PageInfo<Resource> findAllResourceByPage(ResourceVO resourceVO) {
        //分页查询
        PageHelper.startPage(resourceVO.getCurrentPage(),resourceVO.getPageSize());
        List<Resource> allResourceByPage = resourceMapper.findAllResourceByPage(resourceVO);

        PageInfo<Resource> pageInfo = new PageInfo<>(allResourceByPage);
        return pageInfo;
    }
}
