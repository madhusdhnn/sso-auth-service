package com.thetechmaddy.authservice.filters;

import com.thetechmaddy.authservice.models.RequestContext;
import com.thetechmaddy.authservice.models.RequestContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(2)
@Component
public class RequestContextGrabbingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String companyId = getCompanyId(request);
            RequestContextHolder.setContext(new RequestContext(companyId));
            filterChain.doFilter(request, response);
        } finally {
            RequestContextHolder.clearContext();
        }
    }

    @Override
    public void destroy() {
        RequestContextHolder.clearContext();
    }

    private String getCompanyId(HttpServletRequest request) {
        String host = request.getServerName();
        String[] split = host.split("\\.");
        if (split.length <= 2) {
            return null;
        }
        return split[0];
    }

}
