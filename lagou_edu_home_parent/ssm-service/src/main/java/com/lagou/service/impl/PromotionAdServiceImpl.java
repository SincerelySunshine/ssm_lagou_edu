package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    private PromotionAdMapper promotionAdMapper;

    @Override
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO) {
        PageHelper.startPage(promotionAdVO.getCurrentPage(),promotionAdVO.getPageSize());
        List<PromotionAd> allPromotionAdByPage = promotionAdMapper.findAllPromotionAdByPage();
        PageInfo<PromotionAd> PageInfo = new PageInfo<>(allPromotionAdByPage);
        return PageInfo;
    }

    @Override
    public void updatePromotionAdStatus(int id, int status) {
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAd.setUpdateTime(new Date());
        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }
}
