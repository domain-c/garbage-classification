package com.garbage.classification.config;

import com.garbage.classification.interceptor.ApiInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author domain
 * @date 2018/9/12
 * addInterceptors 添加拦截路径，与过滤路径
 * addResourceHandlers 添加静态资源过滤
 */
@Configuration
public class SpringApiConfiguration implements WebMvcConfigurer, WebMvcRegistrations {

    @Autowired
    ApiInterceptor apiInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiInterceptor)
                .addPathPatterns("/api/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new ApiRequestMappingHandlerMapping();
    }


// 重写返回格式json
//    @Override
//    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        ObjectMapper objectMapper = new ObjectMapper();
//        SimpleModule module = new SimpleModule();
//        module.addSerializer(new ToStringSerializer(Long.TYPE));
//        module.addSerializer(new ToStringSerializer(Long.class));
//        module.addSerializer(new ToStringSerializer(BigInteger.class));
//        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
//            @Override
//            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//                jsonGenerator.writeString("");
//            }
//        });
//        objectMapper.registerModule(module);
//        converter.setObjectMapper(objectMapper);
//        //这里是fastJSON的配置方式，更多的内容可以查看SerializerFeature
////        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
////        converter.setFeatures(SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero,
////                SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullListAsEmpty);
//        converters.add(converter);
//    }
}
