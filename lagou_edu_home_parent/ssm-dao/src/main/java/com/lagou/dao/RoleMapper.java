package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {
    /*
        查询角色列表(条件)
    */
    public List<Role> findAllRole(Role role);

    /*
        根据角色ID查询菜单信息ID
    */
    public List<Integer> findMenuByRoleId(Integer roleid);
    /*
        根据roleid 删除中间表关联信息
     */
    public void deleteRoleContextMenu(Integer rid);
    /*
        为角色分配菜单
     */
    public void RoleContextMenu(Role_menu_relation role_menu_relation);

    /*
        删除角色
     */
    public void deleteRole(Integer roleid);
}
