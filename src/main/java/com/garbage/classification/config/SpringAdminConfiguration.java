package com.garbage.classification.config;

import com.garbage.classification.interceptor.AdminInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: domain
 * @date: 2018/9/12
 * 配置boot 自定义拦截器 admin配置拦截
 * addInterceptors 添加拦截路径，与过滤路径
 * addResourceHandlers 添加静态资源过滤
 */
@Configuration
public class SpringAdminConfiguration implements WebMvcConfigurer {

    @Autowired
    AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/toLogin")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/setError")
                .excludePathPatterns("/static/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
