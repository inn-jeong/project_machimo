package com.example.project_machimo.gyuha.aop;

import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoginCheckAspect {

    @Around("@annotation(LoginCheck)")
    public Object userSessionCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = attr.getRequest().getSession(false);

        Integer userId = (Integer) session.getAttribute("userId");
        System.out.println();
        if (userId == null) {
            return ResponseEntity.badRequest().body("로그인이 필요한 서비스입니다");
        }

        // Proceed with the method invocation
        return joinPoint.proceed();
    }
}

