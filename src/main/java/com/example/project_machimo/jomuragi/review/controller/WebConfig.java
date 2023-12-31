package com.example.project_machimo.jomuragi.review.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

// 스태틱 경로에 넣은 이미지 서버 재구동없이도 바로 찾을 수 있게 하는 config
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/upload/**")
                .addResourceLocations("file:/D:/dev/sts-bundle/work_spring/project_machimo/src/main/resources/static/upload/");
//                .addResourceLocations("classpath:../static/upload/**");

    }
}
