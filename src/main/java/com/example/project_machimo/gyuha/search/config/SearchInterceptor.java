package com.example.project_machimo.gyuha.search.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class SearchInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String requestURI = request.getRequestURI();
        if (!requestURI.startsWith("/search/searchList")) { // '/search'로 시작하지 않는 요청에 대해서만 검색 기록 삭제
            session.removeAttribute("keyword");
            session.removeAttribute("searchOption");
        }
        return true;
    }
}

