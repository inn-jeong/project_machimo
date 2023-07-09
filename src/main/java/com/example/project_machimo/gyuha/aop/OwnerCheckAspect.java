package com.example.project_machimo.gyuha.aop;

import com.example.project_machimo.inn_jeong.login.Dto.UsersDto;
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
    public ResponseEntity<?> userSessionCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = attr.getRequest().getSession(false);
        System.out.println("test1@#");
        UsersDto user = (UsersDto) session.getAttribute("user");
        if(user ==null){
            return ResponseEntity.ok().body("관리자만 접근이 가능합니다");
        }
        int uRole = user.getURole();
        if (uRole == 1) {
            return ResponseEntity.ok().body("관리자만 접근이 가능합니다");
        }else {
            return ResponseEntity.ok().build();
        }
    }
}
