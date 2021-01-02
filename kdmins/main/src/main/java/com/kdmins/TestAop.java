package com.kdmins;
import com.kdmins.common.util.JwtUtil;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
/*@Aspect
@Component*/
public class TestAop {
    HashMap<String, String> userGroupInfo = new HashMap<>();
    HashMap<String, HashMap<String, Boolean>> authorityInfo = new HashMap<>();
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    com.kdmins.mapper.authorityMapper authorityMapper;

    @Pointcut("execution(* com.kdmins.report.controller.Report.*(..))")
    public void report() {
    }

    @Pointcut("execution(* com.kdmins.shfManager.controller.shfManagerController.*(..))")
    public void shfManager() {
    }

    public String getUserId() {
        return JwtUtil.getUsername(request.getHeader("token"));
    }

    public String getGroupId() {
        String userGroupId = userGroupInfo.get(request.getHeader("token"));
        if (userGroupId == null) {
            userGroupInfo.put(request.getHeader("token"), "");
            authorityMapper.getGroupInfoByUser(getUserId(), new ResultHandler() {
                @Override
                public void handleResult(ResultContext resultContext) {
                    HashMap resultMap = (HashMap) resultContext.getResultObject();
                    userGroupInfo.put(request.getHeader("token"), userGroupInfo.get(request.getHeader("token")) + resultMap.get("groupId") + "@");
                }
            });
        }
        return userGroupInfo.get(request.getHeader("token"));
    }

    Boolean authority(String groupId) {
        HashMap<String, Boolean> authority = authorityInfo.get("groupId");
        if (authority == null) {
            resultHandler resultHandler = new resultHandler();
            authorityMapper.getGrantFun(getUserId(), resultHandler);
            authorityInfo.put(groupId, resultHandler.getResults());
        }

        return authorityInfo.get(groupId).get(request.getRequestURI());
    }

    class resultHandler implements ResultHandler {

        HashMap<String, Boolean> authority = new HashMap<String, Boolean>();

        @Override
        public void handleResult(ResultContext resultContext) {
            HashMap resultMap = (HashMap) resultContext.getResultObject();
            System.out.println(resultMap + "測試");
            authority.put(resultMap.get("funName").toString(), true);
        }

        public HashMap<String, Boolean> getResults() {
            System.out.println("ssssssssssss" + authority);
            return authority;
        }

    }

    @Around("report() || shfManager()")
    public Object arround(ProceedingJoinPoint pjp) {
        Boolean authorityFlag = authority(getGroupId());
        if (authorityFlag == null) {
            authorityFlag = false;
        }
        System.out.println();
        try {
            if (authorityFlag) {
                Object o = pjp.proceed();
                return o;
            } else {
                response.getWriter().write("无权限");
                return null;
            }


        } catch (Throwable e) {
            e.printStackTrace();

        }
        return null;
    }

}
