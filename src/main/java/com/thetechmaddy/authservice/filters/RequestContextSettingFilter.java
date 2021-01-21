package com.thetechmaddy.authservice.filters;

import com.thetechmaddy.authservice.models.RequestContext;
import com.thetechmaddy.authservice.utils.BeanUtil;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(2)
@Component
public class RequestContextSettingFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        RequestContext context = BeanUtil.getBean(RequestContext.class);
        context.setCompanyId(getCompanyId(request));
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
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
