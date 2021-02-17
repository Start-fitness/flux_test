package com.home.fitness.security;

import com.home.fitness.repository.UsersRepository;
import com.home.fitness.security.JwtUtil.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {
   @Autowired
    UsersRepository usersRepository;
    private final JwtUtil jwtUtil;

    public AuthenticationManager(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        String email = jwtUtil.getEmailFromToken(authToken);
        return usersRepository.findByEmail(email)
                .flatMap(userDetails -> {
                    if (email.equals(userDetails.getEmail()) && jwtUtil.validateToken(authToken)) {
                        return Mono.just(authentication);
                    } else {
                        return Mono.empty();
                    }
                });
    }
//            if (!jwtUtil.validateToken(authToken)) {
//                return Mono.empty();
//            }
//            Claims claims = jwtUtil.getAllClaimsFromToken(authToken);
//            List<String> rolesMap = claims.get("role", List.class);
//            List<GrantedAuthority> authorities = new ArrayList<>();
//            for (String rolemap : rolesMap) {
//                authorities.add(new SimpleGrantedAuthority(rolemap));
//            }
//            return Mono.just(new UsernamePasswordAuthenticationToken(email, null, authorities));



}
