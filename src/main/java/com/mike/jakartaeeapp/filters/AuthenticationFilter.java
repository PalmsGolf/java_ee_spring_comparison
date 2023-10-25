package com.mike.jakartaeeapp.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "jwtFilter", urlPatterns = "/api/*")
public class AuthenticationFilter implements Filter {
    private static final String AUTH_HEADER_NAME = "authorization";
    private static final String AUTH_SECRET = "12345";

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("Auth filter is working!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        doAuthFiltering(servletRequest, servletResponse, filterChain);
    }

    public void doAuthFiltering(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        String authHeader = request.getHeader(AUTH_HEADER_NAME);
        if (authHeader == null || !authHeader.equals(AUTH_SECRET)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getOutputStream().print("Unauthorized");
            return;
        } else {
            filterChain.doFilter(request, response);
        }
    }
}