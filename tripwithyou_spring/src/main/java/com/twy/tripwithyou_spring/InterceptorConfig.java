package com.twy.tripwithyou_spring;

import com.twy.tripwithyou_spring.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    //@Autowired
    LoginCheckInterceptor loginCheckInterceptor;
    public InterceptorConfig(LoginCheckInterceptor loginCheckInterceptor) {
        this.loginCheckInterceptor = loginCheckInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/matching/matchingRegister.do")
                .addPathPatterns("/upload/register.do")
                .addPathPatterns("/course/*/modifyMap")
                .addPathPatterns("/course/*/modify")
                .addPathPatterns("/course/delete")
                .addPathPatterns("/course/register")
                .addPathPatterns("/course/map");
    }
}