package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO){
        PageInfo pageInfo = userService.findAllUserByPage(userVO);
        return new ResponseResult(true,200,"分页多条件查询成功",pageInfo);
    }
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User user1 = userService.login(user);
        if (user1 != null){
            //保存用户id 及 access_token到session中
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",user1.getId());
            //返回信息响应到前台
            HashMap<String, Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id",user1.getId());
            return new ResponseResult(true,200,"登录成功",map);
        }else {
            return new ResponseResult(true,400,"用户名错误",null);
        }
    }

    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(Integer id,String status){
        if ("ENABLE".equalsIgnoreCase(status)) {
            status = "DISABLE";
        } else {
            status = "ENABLE";
        }
        userService.updateUserStatus(id,status);
        return new ResponseResult(true,200,"用户状态修改成功",status);
    }

    /*
        分配角色(回显)
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRoleById(Integer id){
        List<Role> roleList = userService.findUserRelationRoleById(id);
        return new ResponseResult(true,200,"分配角色回显成功",roleList);
    }
    /*
        分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVO userVO){
        userService.userContextRole(userVO);
        return new ResponseResult(true,200,"分配角色成功",null);
    }
    /*
     * 获取用户权限,进行菜单动态显示
     * */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        //获取请求头中的 token
        String header_token = request.getHeader("Authorization");
        //获取session中的access_token
        HttpSession session = request.getSession();
        String access_token = (String)session.getAttribute("access_token");
        //判断token是否一致
        if (header_token.equals(access_token)){
            //获取用户id
            Integer user_id = (Integer)request.getSession().getAttribute("user_id");
            ResponseResult responseResult = userService.getUserPermissions(user_id);
            return responseResult;
        }else {
            ResponseResult responseResult = new ResponseResult(false, 400, "获取·菜单信息失败", null);
            return responseResult;
        }
    }
}
