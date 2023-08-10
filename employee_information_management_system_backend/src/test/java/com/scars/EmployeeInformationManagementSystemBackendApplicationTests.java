package com.scars;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class EmployeeInformationManagementSystemBackendApplicationTests {

    @Test
    void contextLoads() {
    }

//    // 生成JWT令牌
//    @Test
//    public void testGenJwt() {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("id", 1);
//        claims.put("name", "Li Tiansuo");
//
//        String jwt = Jwts.builder()
//                .signWith(SignatureAlgorithm.HS256, "Scars")  // 指定签名算法
//                .setClaims(claims)  // 自定义Payload
//                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))  // 设置令牌有效期（1h）
//                .compact(); // 生成并返回JWT令牌
//        System.out.println(jwt);
//    }
//
//    // 解析JWT令牌
//    @Test
//    public void testParseJwt() {
//        Claims claims = Jwts.parser()
//                .setSigningKey("Scars") // 设置签名密钥
//                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiTGkgVGlhbnN1byIsImlkIjoxLCJleHAiOjE2OTEzODAwOTF9.ud5ED2VhanS6pghjnoE36VPek-Nl5p26tg5BmchzBrc")
//                .getBody();
//        System.out.println(claims);
//    }
}
