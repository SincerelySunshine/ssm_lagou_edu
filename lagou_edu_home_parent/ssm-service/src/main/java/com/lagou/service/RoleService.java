package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {
    public List<Role> findAllRole(Role role);

    /*
       根据角色ID查询菜单信息ID
   */
    public List<Integer> findMenuByRoleId(Integer roleid);
    /*
        为角色分配菜单
     */
    public void roleContextMenu(RoleMenuVo roleMenuVo);
    /*
        删除角色
     */
    public void deleteRole(Integer roleid);
}
