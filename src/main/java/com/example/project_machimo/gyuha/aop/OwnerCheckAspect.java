package com.example.project_machimo.gyuha.aop;

import com.example.project_machimo.gyuha.TestUsersDto;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class OwnerCheckAspect {
    @Around("@annotation(OwnerCheck)")
    public Object userSessionCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = attr.getRequest().getSession(false);

        TestUsersDto user = (TestUsersDto) session.getAttribute("user");
        int uRole = user.getURole();
        if (uRole != 1) {
            return ResponseEntity.badRequest().body("관리자만 접근이 가능합니다");
        }

        return joinPoint.proceed();
    }
}
