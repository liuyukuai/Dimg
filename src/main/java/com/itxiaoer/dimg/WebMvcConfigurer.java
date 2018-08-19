package com.itxiaoer.dimg;

import com.itxiaoer.dimg.config.ImgConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
/**
 *
 * @author : liuyk
 */
@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {
    private ImgConfig imgConfig;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/sample/**")
                .addResourceLocations("classpath:/static/sample/").setCachePeriod(31536000);

//        registry.addResourceHandler("/**").setCachePeriod(31536000)
//                .addResourceLocations("file:" + imgConfig.getPath() + "/");
        org.springframework.web.servlet.config.annotation.WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/sample/upload.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        org.springframework.web.servlet.config.annotation.WebMvcConfigurer.super.addViewControllers(registry);
    }

    @Autowired
    public void setImgConfig(ImgConfig imgConfig) {
        this.imgConfig = imgConfig;
    }
}
