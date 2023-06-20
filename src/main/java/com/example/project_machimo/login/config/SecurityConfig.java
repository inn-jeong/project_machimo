package com.example.project_machimo.login.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request
                    .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                    .requestMatchers("/status", "/images/**", "/view/join", "/login/page").permitAll()
                    .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
            )
            .formLogin(login -> login	// form 방식 로그인 사용
                    .loginPage("/view/login")
                    .loginProcessingUrl("/login-process")
                    .usernameParameter("userid")
                    .passwordParameter("pw")
                    .defaultSuccessUrl("/login/page", true)	// 성공 시 dashboard로
                    .permitAll()	// 대시보드 이동이 막히면 안되므로 얘는 허용
            )
            .logout(Customizer.withDefaults());	// 로그아웃은 기본설정으로 (/logout으로 인증해제)

        return http.build();
    }
}
