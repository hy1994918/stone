package com.kdmins.login.controller;
import com.kdmins.common.base.BaseResult;
import com.kdmins.common.util.JwtUtil;
import com.kdmins.login.mapper.SysUserLoginMapper;
import com.kdmins.login.pojo.UserInfo;
import com.kdmins.login.service.loginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
@Controller
@RequestMapping({"admin"})
@CrossOrigin
public class LoginController {
    @Autowired
    SysUserLoginMapper SysUserLoginMapper;
    @Autowired
    HttpServletRequest request;
    @Autowired
    loginService loginServices;
    @ResponseBody
    @PostMapping({"login"})
    public BaseResult loginToken(@RequestParam("username") String username, @RequestParam("password") String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        if (subject.isAuthenticated()) {
            String jwtToken = JwtUtil.createToken(username,subject.getSession().getId().toString());
            UserInfo UserInfo= (UserInfo) subject.getPrincipal();
            HashMap result=new HashMap();
            result.put("token",jwtToken);
            result.put("userInfo",UserInfo);
            return BaseResult.success("登录成功", result);
        }
        return BaseResult.fail(501, "账号或密码错误");
  }
    @ResponseBody
    @GetMapping({"getTopMenu"})
    public BaseResult getTopMenu() {

        return BaseResult.success(SysUserLoginMapper.getTopMenu(JwtUtil.getUsername(request.getHeader("token"))));
    }

    @ResponseBody
    @GetMapping({"getLeftMenu"})
    public BaseResult getLeftMenu(@RequestParam("topMenuId") Integer topMenuId) {
        return BaseResult.success(SysUserLoginMapper.getLeftMenu(JwtUtil.getUsername(request.getHeader("token")),topMenuId));
    }


    @ResponseBody
    @GetMapping({"getDepartmentTree"})
    public BaseResult getDepartmentTree() {
        return BaseResult.success(loginServices.getDepartmentTree());
    }

    /*    Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    SysUserLoginService sysUserLoginService;
    @Autowired
    ObjectMapper ObjectMapper;

    public LoginController() {
    }





    @RequestMapping(
            value = {"/getAllApp"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public BaseResult getAllApp() {
        return BaseResult.success("查询成功", this.sysUserLoginService.getAllApp());
    }

    @RequestMapping(
            value = {"/findAllUser"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public BaseResult findAllUser() {
        return BaseResult.success("查询成功", this.sysUserLoginService.findAllUser());
    }

  *//*  @RequestMapping(
            value = {"/gettree"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public BaseResult getTree(HttpServletRequest HttpServletRequest, @RequestParam("id") String id) {
        String token = HttpServletRequest.getHeader("token");
        System.out.println("ssssssssssss" + token);
        return BaseResult.success("查询成功", this.sysUserLoginService.getUserMenuTree(token, id));
    }*//*

    @RequestMapping(
            value = {"/getModule"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public BaseResult getModule(HttpServletRequest HttpServletRequest) {
        String token = HttpServletRequest.getHeader("token");
        return BaseResult.success("查询成功", this.sysUserLoginService.getUserModule(token));
    }

    @RequestMapping(
            value = {"/getAllUserGroup"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public BaseResult getAllUserGroup() {
        return BaseResult.success("查询成功", this.sysUserLoginService.getAllUserGroup());
    }

    @RequestMapping(
            value = {"/getAllUserByGroup"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public BaseResult getUserByGroup(@RequestParam("groupId") String groupId) {
        return BaseResult.success("查询成功", this.sysUserLoginService.getUserByGroup(groupId));
    }*/
/*
    @ResponseBody
    @PostMapping({"login"})
    public Object loginToken(@RequestParam("username") String username, @RequestParam("password") String password) {
                    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                    Subject subject = SecurityUtils.getSubject();
                    subject.login(token);
                    if (subject.isAuthenticated()) {
                        String jwtToken = JwtUtil.createToken(username, groupInfo.getUserCode(), groupInfo.getGroupId());
                        TokenSubjectUtil.saveSubject(jwtToken, subject);
                        Map map = ToeknUtil.setToken(jwtToken);
                        this.sysUserLoginService.emptyErrorCount(username);
                        return BaseResult.success("登录成功", map);
                    }
                }







        return BaseResult.fail(501, "账号或密码错误");
    }*/


}
