package com.kdmins.login.shiro;

import com.kdmins.login.mapper.SysUserLoginMapper;
import com.kdmins.login.pojo.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
//自定义安全策略

public class MyShiroRealm extends AuthorizingRealm {
     @Autowired
    SysUserLoginMapper SysUserLoginMapper;
    //授权 (用户权限方面)   如果重写permsFilter  则次权限方法失效  (重要)
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       //获取主体信息
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();//得到用户信息
        System.out.println("权限用户名="+principal);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        authorizationInfo.setRoles(roles);
        return authorizationInfo;
    }

    //认证  (登录注册方面)
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("---------------------用户认证-------------------");
        //获取用户名称
        String loginName = authenticationToken.getPrincipal().toString();
        try{
            UserInfo userInfo = SysUserLoginMapper.getUserInfo(loginName);
        }catch (Exception ex){
            System.out.println("报错信息"+ex);
        }
        UserInfo userInfo = SysUserLoginMapper.getUserInfo(loginName);
        System.out.println("----------查询测试测试----------------");
        //System.out.println("userInfo="+userInfo);
        //如果用户信息不为空,
        if (userInfo != null) {
            //获取查询出的密码
            String password = userInfo.getUserPassword();

            //获取realm的名称
            String realName = this.getName();
            //交给shiro 进行比对
            System.out.println("用户名="+loginName+"===密码="+password);
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo, password, realName);
            return authenticationInfo;
        }
        return null;
    }
}
