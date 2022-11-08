package com.example.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@Slf4j
public class TokenUtils {
    /**
     * 签发人
     */
    private static final String ISSUER = "cold-chain";

    public static String generateToken(Long userId) throws Exception {
        Algorithm algorithm = Algorithm.RSA256(RSAUtils.getPublicKey(), RSAUtils.getPrivateKey());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        // 48小时token失效
        calendar.add(Calendar.HOUR, 48);

        return JWT.create()
                .withKeyId(String.valueOf(userId))
                .withIssuer(ISSUER)
                .withExpiresAt(calendar.getTime())
                .sign(algorithm);
    }

    public static Long verifyToken(String token) throws Exception {
        Algorithm algorithm = Algorithm.RSA256(RSAUtils.getPublicKey(), RSAUtils.getPrivateKey());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        String userId = jwt.getKeyId();
        return Long.valueOf(userId);
    }

    public static String generateRefreshToken(Long userId) throws Exception {
        Algorithm algorithm = Algorithm.RSA256(RSAUtils.getPublicKey(), RSAUtils.getPrivateKey());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 7);

        return JWT.create()
                .withKeyId(String.valueOf(userId))
                .withIssuer(ISSUER)
                .withExpiresAt(calendar.getTime())
                .sign(algorithm);
    }
}
