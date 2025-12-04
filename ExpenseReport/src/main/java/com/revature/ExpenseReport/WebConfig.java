package com.revature.ExpenseReport;

import com.revature.ExpenseReport.Model.BasicAuthInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    //fields
    private final BasicAuthInterceptor basicAuthInterceptor;

    //constructor
    public WebConfig(BasicAuthInterceptor bai){
        this.basicAuthInterceptor = bai;
    }

    //method
    @Override
    public void addInterceptors(InterceptorRegistry reg){
        //adding interceptors to list of active interceptors that are scanning requests
        reg.addInterceptor(basicAuthInterceptor).addPathPatterns("/api/**").excludePathPatterns("/api/hello");
    }

}
