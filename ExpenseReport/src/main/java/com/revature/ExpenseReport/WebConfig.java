package com.revature.ExpenseReport;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    //fields
    //private final BasicAuthInterceptor basicAuthInterceptor;
    private final JwtInterceptor jwtInterceptor;

    //constructor
    public WebConfig(JwtInterceptor jwt){
        this.jwtInterceptor = jwt;
    }

    //method
    /*@Override
    public void addInterceptors(InterceptorRegistry reg){
        //adding interceptors to list of active interceptors that are scanning requests
        reg.addInterceptor(basicAuthInterceptor).addPathPatterns("/api/**").excludePathPatterns("/api/hello");
    }*/

    @Override 
    public void addInterceptors(InterceptorRegistry reg){
        //adding interceptors to list of active interceptors that are scanning requests
        reg.addInterceptor(jwtInterceptor).addPathPatterns("/api/**").excludePathPatterns("/api/hello", "/api/auth/login");
    }

}
