package com.szs.test.config;

import com.szs.test.interceptor.AccessTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AccessTokenInterceptor())
                .addPathPatterns("/szs/scrap", "/szs/refund") // 스크래핑, 환불 API 만 Access Token Interceptor 적용
                .excludePathPatterns("/szs/signup", "/szs/login"); // 로그인 및 회원가입 API는 Interceptor 미적용
    }

}
