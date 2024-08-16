package com.kyonggi.cspop.domain.refreshtoken;

@FunctionalInterface
public interface RefreshTokenGenerator {

    String generate();
}
