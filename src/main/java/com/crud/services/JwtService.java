package com.crud.services;

import com.crud.model.enums.UserRole;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;


import java.time.Duration;
import java.util.Collections;
import java.util.Set;

@ApplicationScoped
public class JwtService {
    public String generateJwt(String username, UserRole role) {

        Set<String> roles = Collections.singleton(role.name());

        return Jwt.issuer("auth-jwt")
                .subject(username)
                .groups(roles)
                .expiresIn(Duration.ofHours(2))
                .sign();
    }
}
