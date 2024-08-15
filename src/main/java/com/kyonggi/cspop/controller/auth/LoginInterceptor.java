package com.kyonggi.cspop.controller.auth;

import com.kyonggi.cspop.support.AuthorizationExtractor;
import com.kyonggi.cspop.support.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    public LoginInterceptor(final JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
                             final Object handler) {

        final String token = AuthorizationExtractor.extract(request);
        jwtTokenProvider.validateAbleToken(token);
        return true;
    }
}
