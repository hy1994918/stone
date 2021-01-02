//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kdmins.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final long EXPIRE_TIME = 10000L;
    private static final String TOKEN_SECRET = "FDSFSDAF32131FDSFSA";

    public JwtUtil() {
    }

    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("FDSFSDAF32131FDSFSA");
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception var3) {
            return false;
        }
    }

    public static void lengthenDate(String token) {
        Date date = new Date(System.currentTimeMillis() + 10000L);

        try {
            Algorithm algorithm = Algorithm.HMAC256("FDSFSDAF32131FDSFSA");
            JWTVerifier var3 = JWT.require(algorithm).withClaim(token, date).build();
        } catch (UnsupportedEncodingException var4) {
            var4.printStackTrace();
        }

    }

    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException var2) {
            return null;
        }
    }
    public static String getSessionId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("sessionId").asString();
        } catch (JWTDecodeException var2) {
            return null;
        }
    }
    public static String username(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException var2) {
            return null;
        }
    }
    public static String sessionId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("sessionId").asString();
        } catch (JWTDecodeException var2) {
            return null;
        }
    }
    public static String createToken(String username,String sessionId) {
        try {
            Date date = new Date(System.currentTimeMillis() + 10000L);
            Algorithm algorithm = Algorithm.HMAC256("FDSFSDAF32131FDSFSA");
            Map<String, Object> header = new HashMap(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            return JWT.create().withHeader(header).withClaim("username", username).withClaim("sessionId", sessionId).sign(algorithm);
        } catch (UnsupportedEncodingException var6) {
            return null;
        }
    }
    public static void main(String[] args) {
    }
}
