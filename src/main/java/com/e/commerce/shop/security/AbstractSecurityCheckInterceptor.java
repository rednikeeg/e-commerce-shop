package com.e.commerce.shop.security;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractSecurityCheckInterceptor implements HandlerInterceptor {


    protected abstract boolean validate(HttpServletRequest request, HttpServletResponse response, Object handler);

    @Override
    public final boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        return validate(request, response, handler);
    }
}
