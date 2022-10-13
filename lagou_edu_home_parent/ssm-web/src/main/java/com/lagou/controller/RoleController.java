package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> allRole = roleService.findAllRole(role);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询角色成功", allRole);
        return responseResult;
    }
    /*
        查询所有菜单信息
    */
    @Autowired
    private MenuService menuService;
    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid(){
        ////-1 表示查询所有父子级菜单数据
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);
        HashMap<String, Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有父子信息成功", map);
        return responseResult;
    }
    /**
     * 查询角色关联菜单列表ID
     * */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询角色信息菜单成功", menuByRoleId);
        return responseResult;
    }
    /*
        为角色分配菜单
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.roleContextMenu(roleMenuVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "分配菜单成功", null);
        return responseResult;
    }
    /**
     * 删除角色
     * */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleService.deleteRole(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "删除角色成功", null);
        return responseResult;
    }

}
