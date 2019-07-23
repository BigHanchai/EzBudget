package com.digitalacademy.ezbudgetservice.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class EzBudgetServiceInterceptorAppConfig implements WebMvcConfigurer {

    @Autowired
    com.digitalacademy.ezbudgetservice.interceptors.EzBudgetServiceInterceptor ezBudgetServiceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ezBudgetServiceInterceptor);
    }
}
