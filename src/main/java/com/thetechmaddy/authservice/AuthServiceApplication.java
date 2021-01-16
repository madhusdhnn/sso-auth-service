package com.thetechmaddy.authservice;

import com.thetechmaddy.authservice.interceptors.InboundApiKeyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AuthServiceApplication implements WebMvcConfigurer {

    private final String authCookieName;
    private final InboundApiKeyInterceptor inboundApiKeyInterceptor;

    @Autowired
    public AuthServiceApplication(@Value("${auth.cookie.name}") String authCookieName,
                                  InboundApiKeyInterceptor inboundApiKeyInterceptor) {
        this.authCookieName = authCookieName;
        this.inboundApiKeyInterceptor = inboundApiKeyInterceptor;
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.inboundApiKeyInterceptor)
                .addPathPatterns("/auth/**", "/internal/**");
    }

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName(this.authCookieName);
        serializer.setCookiePath("/");
        serializer.setUseHttpOnlyCookie(true);
        return serializer;
    }

}
