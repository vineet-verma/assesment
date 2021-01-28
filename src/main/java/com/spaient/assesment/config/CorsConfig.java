package com.spaient.assesment.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsConfig implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Do nothing
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, PATCH, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Max-Age", "3600");

        //Comment below line before check-in
        if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) request).getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(request, servletResponse);
        }

        //filterChain.doFilter(request, servletResponse);
    }


    @Override
    public void destroy() {
        //Do nothing
    }
}
