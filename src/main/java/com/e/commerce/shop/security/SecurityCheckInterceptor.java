package com.e.commerce.shop.security;

import com.e.commerce.shop.security.annotation.RequiresAuth;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@NoArgsConstructor
public class SecurityCheckInterceptor extends AbstractSecurityCheckInterceptor {

    @Override
    protected boolean validate(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

        }

        return true;
    }

    private boolean isAuthRequired(Method method) {
        return Arrays.stream(method.getAnnotations())
                .anyMatch(annotation -> annotation.annotationType().equals(RequiresAuth.class));
    }
}
