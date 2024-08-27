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
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        final String token = AuthorizationExtractor.extract(request);
        final String rolePayload = jwtTokenProvider.getRolePayload(token);

        validateRoleType(rolePayload);

        return true;
    }

    private static void validateRoleType(final String rolePayload) {
        if (isMatchAtRole(rolePayload)) {
            throw new InvalidRoleTypeException();
        }
    }

    private static boolean isMatchAtRole(final String rolePayload) {
        return RoleType.ADMIN != RoleType.valueOf(rolePayload);
    }
}
