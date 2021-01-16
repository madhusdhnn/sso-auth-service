package com.thetechmaddy.authservice.interceptors;

import com.thetechmaddy.authservice.annotations.ValidateAPIKey;
import com.thetechmaddy.authservice.exceptions.BadRequestException;
import com.thetechmaddy.authservice.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class InboundApiKeyInterceptor implements HandlerInterceptor {

    private final String inboudUserApiKey;

    public InboundApiKeyInterceptor(@Value("${inbound.user.api.key}") String inboudUserApiKey) {
        this.inboudUserApiKey = inboudUserApiKey;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean isAnnotated = ((HandlerMethod) handler).getMethodAnnotation(ValidateAPIKey.class) != null;

        if (isAnnotated) {
            String apiKey = request.getHeader("x-api-key");
            if (apiKey == null) {
                throw new BadRequestException("API key not provided");
            }

            if (!inboudUserApiKey.equals(apiKey)) {
                throw new ForbiddenException("Invalid API key");
            }
        }

        return true;
    }
}
