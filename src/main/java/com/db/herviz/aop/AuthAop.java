package com.db.herviz.aop;

import cn.dev33.satoken.stp.StpUtil;
import com.db.herviz.domain.RequireLogin;
import com.db.herviz.domain.ResponseX;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-04-27
 */
@Aspect
@Component
public class AuthAop {

    @Around("@annotation(requireLogin)")
    public Object checkLogin(ProceedingJoinPoint joinPoint, RequireLogin requireLogin) {
        Object result = null;

        try {
            if (StpUtil.isLogin()) {
                // is login
                result = joinPoint.proceed();
            } else {
                return ResponseX.fail("Require Login");
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return result;
    }
}
