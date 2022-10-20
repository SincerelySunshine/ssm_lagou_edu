package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuVO;

import java.util.List;

public interface MenuService {
    //查询所有父子菜单信息
    public List<Menu> findSubMenuListByPid(int pid);
    /*
  查询所有菜单列表
   */
    public PageInfo<Menu> findAllMenu(MenuVO menuVO);

    public Menu findMenuById(Integer id);
    public void saveMenu(Menu menu);
    public void updateMenu(Menu menu);
    public void deleteMenu(Integer id);
}
