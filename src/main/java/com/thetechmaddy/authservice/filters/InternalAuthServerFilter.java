package com.thetechmaddy.authservice.filters;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Order(1)
@Component
public class InternalAuthServerFilter extends OncePerRequestFilter {

    private final Integer internalServerPort;

    public InternalAuthServerFilter(@Value("${internal.server.port}") Integer internalServerPort) {
        this.internalServerPort = internalServerPort;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Integer port = request.getLocalPort();
        String requestURI = request.getRequestURI();

        boolean isInternalRequest = requestURI.startsWith("/internal/auth");
        if ((internalServerPort.equals(port) && isInternalRequest)) {
            filterChain.doFilter(request, response);
        } else if (!isInternalRequest) {
            filterChain.doFilter(request, response);
        } else {
            log.error(String.format("Internal Auth server not allowed to access on different port - %d", port));
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }
    }
}
