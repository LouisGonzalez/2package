package com.packages.auth.security;

import com.packages.auth.dto.RequestDto;
import com.packages.auth.entity.AuthUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    RouteValidator routeValidator;

    @PostConstruct
    protected void init(){
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String createToken(AuthUser authUser) {
        ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
        Map<String, Object> claims = new HashMap<>();
        claims = Jwts.claims().setSubject(authUser.getUsername());
        claims.put("id", authUser.getId());
        claims.put("role", authUser.getRole());
        Date now = new Date();
        Date exp = new Date(now.getTime() + 3600000);
        String token = Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(exp).signWith(SignatureAlgorithm.HS256, secret).compact();
        //TODO save the token in redis
        valueOp.set(authUser.getUsername(), token, Duration.ofMinutes(1));
        return token;
    }

    public boolean validate(String token, RequestDto dto) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        } catch(Exception e){
            return false;
        }
        if(!isAdmin(token) && routeValidator.isAdminPath(dto))
            return false;
        return true;
    }

    public String getUserNameFromToken(String token){
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e){
            return "bad token";
        }
    }

    public void renewTokenExpiration(String token) {
        Date now = new Date();
        Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().setExpiration(new Date(now.getTime() + 3600000));
    }

    private boolean isAdmin(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("role").equals("admin");
    }

}
