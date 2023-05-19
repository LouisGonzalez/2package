package com.packages.auth.service;

import com.packages.auth.dto.AuthUserDto;
import com.packages.auth.dto.NewUserDto;
import com.packages.auth.dto.RequestDto;
import com.packages.auth.dto.TokenDto;
import com.packages.auth.entity.AuthUser;
import com.packages.auth.repository.AuthUserRepository;
import com.packages.auth.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserService {

    @Autowired
    AuthUserRepository authUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    public AuthUser save(NewUserDto dto) {
        Optional<AuthUser> user = authUserRepository.findByUsername(dto.getUsername());
        if(user.isPresent()) return null;
        String password = passwordEncoder.encode(dto.getPassword());
        AuthUser authUser = AuthUser.builder().username(dto.getUsername()).password(password).role(dto.getRole()).build();
        return authUserRepository.save(authUser);
    }

    public TokenDto login(AuthUserDto dto){
        Optional<AuthUser> user = authUserRepository.findByUsername(dto.getUsername());
        if(!user.isPresent()) return null;
        if(passwordEncoder.matches(dto.getPassword(), user.get().getPassword()))
            return new TokenDto(jwtProvider.createToken(user.get()));
        return null;
    }

    public TokenDto validate(String token, RequestDto dto){
        if(!jwtProvider.validate(token, dto)) return null;
        String username = jwtProvider.getUserNameFromToken(token);
        if(!authUserRepository.findByUsername(username).isPresent())
            return null;
        jwtProvider.renewTokenExpiration(token);
        return new TokenDto(token);
    }

}
