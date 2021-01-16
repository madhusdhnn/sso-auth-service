package com.thetechmaddy.authservice;

import com.thetechmaddy.authservice.interceptors.InboundApiKeyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AuthServiceApplication implements WebMvcConfigurer {

    private final InboundApiKeyInterceptor inboundApiKeyInterceptor;

    @Autowired
    public AuthServiceApplication(InboundApiKeyInterceptor inboundApiKeyInterceptor) {
        this.inboundApiKeyInterceptor = inboundApiKeyInterceptor;
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.inboundApiKeyInterceptor);
    }

}
