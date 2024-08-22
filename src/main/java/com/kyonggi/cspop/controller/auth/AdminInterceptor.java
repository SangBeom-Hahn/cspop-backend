package com.kyonggi.cspop.controller.auth;

import com.kyonggi.cspop.domain.student.RoleType;
import com.kyonggi.cspop.exception.InvalidRoleTypeException;
import com.kyonggi.cspop.support.AuthorizationExtractor;
import com.kyonggi.cspop.support.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class AdminInterceptor implements HandlerInterceptor {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String token = AuthorizationExtractor.extract(request);
        String rolePayload = jwtTokenProvider.getRolePayload(token);

        validateRoleType(rolePayload);

        return true;
    }

    private static void validateRoleType(String rolePayload) {
        if (RoleType.ADMIN != RoleType.valueOf(rolePayload)) {
            throw new InvalidRoleTypeException();
        }
    }
}
