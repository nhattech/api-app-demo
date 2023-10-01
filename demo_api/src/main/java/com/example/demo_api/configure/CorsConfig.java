package com.example.demo_api.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000") // Điền danh sách các origin bạn muốn cho phép
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Các phương thức HTTP được phép
                        .allowCredentials(true)
                        .allowedHeaders("*"); // Cho phép tất cả các header
            }
        };
    }
}
