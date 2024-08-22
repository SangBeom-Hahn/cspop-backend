package com.kyonggi.cspop.config;

import com.kyonggi.cspop.controller.auth.AdminInterceptor;
import com.kyonggi.cspop.controller.auth.AuthenticationPrincipalArgumentResolver;
import com.kyonggi.cspop.controller.auth.LoginInterceptor;
import com.kyonggi.cspop.support.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AuthenticationPrincipalConfig implements WebMvcConfigurer {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(jwtTokenProvider))
                .order(1)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/students")
                .excludePathPatterns("/api/students/validate-duplicate")
                .excludePathPatterns("/api/login")
                .excludePathPatterns("/api/logout");

        registry.addInterceptor(new AdminInterceptor(jwtTokenProvider))
                .order(2)
                .addPathPatterns("/api/finals/admins/**")
                .addPathPatterns("/api/proposals/admins/**")
                .addPathPatterns("/api/middles/admins/**")
                .addPathPatterns("/api/submits/admins/**")
                .addPathPatterns("/api/graduates/admins/**")
                .addPathPatterns("/api/noticeboards/admins")
                .addPathPatterns("/api/admins/guideboards");
    }

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AuthenticationPrincipalArgumentResolver(jwtTokenProvider));
    }
}
