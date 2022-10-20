package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuVO;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findSubMenuListByPid(int pid) {
        List<Menu> subMenuListByPid = menuMapper.findSubMenuListByPid(pid);
        return subMenuListByPid;
    }

    @Override
    public PageInfo<Menu> findAllMenu(MenuVO menuVo) {

        PageHelper.startPage(menuVo.getCurrentPage(),menuVo.getPageSize());
        List<Menu> allMenu = menuMapper.findAllMenu();
        return new PageInfo<>(allMenu);

    }

    @Override
    public Menu findMenuById(Integer id) {
        return menuMapper.findMenuById(id);

    }

    @Override
    public void saveMenu(Menu menu) {

        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");
        menuMapper.saveMenu(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        Date date = new Date();
        menu.setUpdatedBy("system");
        menu.setUpdatedTime(date);
        menuMapper.updateMenu(menu);
    }

    @Override
    public void deleteMenu(Integer id) {
        menuMapper.deleteMenu(id);
    }
}
