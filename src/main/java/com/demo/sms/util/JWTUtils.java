package com.demo.sms.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTUtils {

    private DecodedJWT decodedJWT;
    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

    public JWTUtils(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        decodedJWT = verifier.verify(token);
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public String getUser() {
        return decodedJWT.getSubject();
    }

    public String[] getRoles() {
        return decodedJWT.getClaim("roles").asArray(String.class);
    }

}
