package com.garbage.classification.config;

import com.garbage.classification.interceptor.AdminInterceptor;
import com.garbage.classification.interceptor.ApiInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: domain
 * @date: 2018/9/12
 * addInterceptors 添加拦截路径，与过滤路径
 * addResourceHandlers 添加静态资源过滤
 */
@Configuration
public class SpringApiConfiguration implements WebMvcConfigurer {

    @Autowired
    ApiInterceptor apiInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiInterceptor)
                .addPathPatterns("/api/**");
//                .excludePathPatterns("/admin/toLogin")
//                .excludePathPatterns("/admin/login")
//                .excludePathPatterns("/setError")
//                .excludePathPatterns("/static/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
