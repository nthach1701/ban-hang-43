package com.nnt.banhang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
  
@Configuration
public class WebSpringConfig implements WebMvcConfigurer {
  
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //
        // Access Bootstrap static resource:
        //
  
        //
        // http://somedomain/SomeContextPath/jquery/jquery.min.css
        //
        registry.addResourceHandler("/jquery/**") //
                .addResourceLocations("classpath:/META-INF/resources/webjars/jquery/3.3.1-1/");
  
        //
        // http://somedomain/SomeContextPath/popper/popper.min.js
        //
        registry.addResourceHandler("/popper/**") //
                .addResourceLocations("classpath:/META-INF/resources/webjars/popper.js/1.14.1/umd/");
  
        // http://somedomain/SomeContextPath/bootstrap/css/bootstrap.min.css
        // http://somedomain/SomeContextPath/bootstrap/js/bootstrap.min.js
        //
        registry.addResourceHandler("/mdbootstrap/**") //
                .addResourceLocations("classpath:/META-INF/resources/webjars/mdbootstrap-bootstrap-material-design/4.8.8");

    }
  
}
