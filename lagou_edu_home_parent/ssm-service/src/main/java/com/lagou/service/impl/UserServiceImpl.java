package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo findAllUserByPage(UserVO userVO) {
        PageHelper.startPage(userVO.getCurrentPage(),userVO.getPageSize());
        List<User> allUserByPage = userMapper.findAllUserByPage(userVO);
        PageInfo<User> pageInfo = new PageInfo<>(allUserByPage);
        return pageInfo;
    }

    @Override
    public void updateUserStatus(Integer id, String status) {
        userMapper.updateUserStatus(id,status);
    }

    @Override
    public User login(User user) throws Exception {
        //调用mapper方法 包含密文密码
        User user1 = userMapper.login(user);
        if (user1 != null && Md5.verify(user.getPassword(),"lagou",user1.getPassword())){
            return user1;
        }else {
            return null;
        }
    }

    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        List<Role> list = userMapper.findUserRelationRoleById(id);
        return list;
    }

    @Override
    public void userContextRole(UserVO userVO) {
        //根据用户id清空中间表关联关系
        userMapper.deleteUserContextRole(userVO.getUserId());
        //重新建立关联关系
        List<Integer> roleIdList = userVO.getRoleIdList();
        for (Integer roleid : roleIdList) {
            //封装数据
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVO.getUserId());
            user_role_relation.setRoleId(roleid);

            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");
            userMapper.userContextRole(user_role_relation);
        }
    }

    /*
        动态获取用户权限信息
     */
    @Override
    public ResponseResult getUserPermissions(Integer userid) {
        //1.获取当前用户拥有的角色
        List<Role> roleList = userMapper.findUserRelationRoleById(userid);
        //2.获取角色ID,保存到 list
        ArrayList<Integer> roleIds = new ArrayList<>();
        for (Role role : roleList) {
            roleIds.add(role.getId());
        }
        //3.根据角色id查询 父菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIds);
        //4.封装父菜单下的子菜单
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }
        //5.获取资源权限
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);
        //6.封装数据
        HashMap<String, Object> map = new HashMap<>();
        map.put("menuList",parentMenu);
        map.put("resourceList",resourceList);
        return new ResponseResult(true,200,"获取用户权限信息完成",map);
    }
}
