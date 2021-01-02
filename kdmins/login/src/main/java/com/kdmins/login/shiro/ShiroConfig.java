package com.kdmins.login.shiro;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kdmins.common.testJob;
import com.kdmins.login.shiro.MyShiroRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
//shiro配置对象
/*@Configuration  //标识本类为配置类*/
public class ShiroConfig {
    Logger logger =
            LoggerFactory.getLogger(ShiroConfig.class);
    org.springframework.beans.factory.support.AbstractBeanFactory AbstractBeanFactory;
    @Autowired
    DataSource DataSource;
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager securityManager) {
        /*
         * Shiro内置过滤器,可以实现权限相关拦截
         *       anon:无需认证(登录),可以访问
         *       authc: 必须认证才可以访问
         *       user: 如果使用rememberMe的功能可以直接访问
         *       perms: 该资源必须得到资源权限才可以访问
         *       role:该资源必须得到角色权限才可以访问
         * */
        RolesAuthorizationFilter myRoles=new RolesAuthorizationFilter(){
            @Override
            protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                if(httpServletRequest.getRequestURI().equals("/")){
                    (( HttpServletResponse) response).sendRedirect("/blog/index.html/#/home/context");
                }
                System.out.println();
                response.setContentType("application/json; charset=utf-8");//返回json
                JSONObject result=new  JSONObject();
                result.put("code",403);
                result.put("message","请登录");
                        response.getWriter().write(result.toJSONString());
            }

            @Override
            protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

                HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                logger.info("访问者名称"+httpServletRequest.getRemoteHost());
                logger.info("访问者  Ip"+httpServletRequest.getRemoteAddr());
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.setHeader("Access-control-Allow-Origin", "*");
                httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
                httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
                // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
                if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
                    httpServletResponse.setStatus(HttpStatus.OK.value());
                    return false;
                }
                return super.preHandle(request, response);
            }
            @Override
            public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {

                Subject subject = getSubject(request, response);
                String[] rolesArray = (String[]) mappedValue;

                if (rolesArray == null || rolesArray.length == 0) {
                    //no roles specified, so nothing to check - allow access.
                    return true;
                }

                Set<String> roles = CollectionUtils.asSet(rolesArray);
                return subject.hasAllRoles(roles);
            }
        };
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();//创建过滤器工厂
        filterFactoryBean.setSecurityManager(securityManager);//给过滤器配置安全管理器
        Map<String, Filter> filterMap = new LinkedHashMap<>();

        filterMap.put("myRoles",myRoles);

        filterFactoryBean.setFilters(filterMap);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/**/index.html", "anon");////登录方法一定要开放 否则登录方法就被拦截了
        map.put("/**/static/**", "anon");////登录方法一定要开放 否则登录方法就被拦截了

        map.put("/**/*.jpg", "anon");////登录方法一定要开放 否则登录方法就被拦截了
        map.put("/blog/**", "anon");////登录方法一定要开放 否则登录方法就被拦截了
        map.put("/resume/**", "anon");////登录方法一定要开放 否则登录方法就被拦截了
        map.put("/admin/login", "anon");////登录方法一定要开放 否则登录方法就被拦截了
        map.put("/**", "myRoles[admin]");////登录方法一定要开放 否则登录方法就被拦截了
        filterFactoryBean.setFilterChainDefinitionMap(map);//设置拦截过滤的权限  用map添加进去
        System.out.println("----------------------------shiro 拦截器 启动完成----------------------------");

        return filterFactoryBean;
    }


    @Bean("defaultWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("sessionManager") SessionManager SessionManager,@Qualifier("myShiroRealm") MyShiroRealm myShiroRealm) {//通过名称注入
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm);//给安全管理器配置自定义的安全策略
        securityManager.setSessionManager(SessionManager);
        return securityManager;
    }

   @Bean("sessionManager")
    public SessionManager sessionManager(){
        CustomSessionManager manager = new CustomSessionManager();
        manager.setSessionDAO(new EnterpriseCacheSessionDAO());
        return manager;
    }
    //创建自定义的安全策略对象
    @Bean("myShiroRealm") //创建自定义的安全策略对象,并交给spring容器管理
    public MyShiroRealm myShiroRealm(/*@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher credentialsMatcher*/) {
        MyShiroRealm myShiroRealm = new MyShiroRealm();  //自定义的安全策略
        myShiroRealm.setAuthenticationCachingEnabled(false); //关闭认证的缓存
        //myShiroRealm.setCredentialsMatcher(credentialsMatcher); //设定凭证匹配器  md5
        return myShiroRealm;
    }

    /**
     * 开启Shiro注解(如@RequiresRoles,@RequiresPermissions),
     * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)
     */
    //注解模式 上面有注释
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);//设置动态代理为cglib,默认是jdk代理
        //defaultAdvisorAutoProxyCreator.setUsePrefix(true);  //解决权限404的问题
        return defaultAdvisorAutoProxyCreator;
    }

    //注解模式 上面有注释
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(defaultWebSecurityManager);
        return sourceAdvisor;
    }

/*    //登录的时候 使用明文密码加密后 和 数据库的密文密码比对
    //****************************使得shiro可以进行md5 凭证匹配****************************
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");//加密算法
        credentialsMatcher.setHashIterations(1024); //加密次数
        return credentialsMatcher;
    }*/


}
