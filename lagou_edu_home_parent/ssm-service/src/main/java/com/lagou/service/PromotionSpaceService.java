package com.lagou.service;

import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {
    /*
        获取所有的广告位
    */
    public List<PromotionSpace> findAllPromotionSpace();
    /*
        添加广告位
    */
    public void savePromotionSpace(PromotionSpace promotionSpace);
    /*
        更新广告位
    */
    public void updatePromotionSpace(PromotionSpace promotionSpace);
    /*
        根据id 查询广告位信息
    */
    public  PromotionSpace findPromotionSpaceById(int id);
}
