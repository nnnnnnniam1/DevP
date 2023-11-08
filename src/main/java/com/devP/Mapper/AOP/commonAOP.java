package com.devP.Mapper.AOP;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class commonAOP {

    @Autowired
    private HttpSession session;
    @Pointcut("execution(* com.devP.Mapper..*Impl.*View(..))")
    public void allPointcut() {
    }
    
    @Before("allPointcut()")
    public void beforeServiceMethod(JoinPoint joinPoint) {
    	//세션 조회 시 로그인 값 없으면 로그인 페이지로
//    	if(session.getAttribute("user")==null) {
//    		return
//    	};
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Before executing: " + methodName);
    }
}
