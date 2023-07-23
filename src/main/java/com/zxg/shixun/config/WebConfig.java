package com.zxg.shixun.config;

import com.zxg.shixun.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Slf4j
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Override
//    设置静态资源映射
   protected void addResourceHandlers(ResourceHandlerRegistry registry) {
       log.info("开始进行资源映射");
       registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
       registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }

    @Override//扩展mvc消息转换器
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器，底层使用Iackon将java对象转为json
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //将上面的转换器追加到mvc框架的转换器集合中
        converters.add(0,messageConverter);
    }
}
