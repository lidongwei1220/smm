package com.example.smm.auth;

/**
 * @author lidongwei
 * @date 2019-10-25
 **/
public class JwtUtil {

    private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer";

    public static String getTokenHeader(String rawToken) {
        return AUTHORIZATION_HEADER_PREFIX + rawToken;
    }

    public static String getAuthorizationHeaderPrefix() {
        return AUTHORIZATION_HEADER_PREFIX;
    }
}
