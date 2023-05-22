package com.packages.auth.controller;

import com.packages.auth.dto.AuthUserDto;
import com.packages.auth.dto.NewUserDto;
import com.packages.auth.dto.RequestDto;
import com.packages.auth.dto.TokenDto;
import com.packages.auth.entity.AuthUser;
import com.packages.auth.service.AuthUserService;
import com.packages.auth.source.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin (origins = Constants.URL_FRONTEND, allowCredentials = "true")
@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    AuthUserService authUserService;

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto dto) {
        System.out.println("entro al endpoint de login");
        System.out.println(dto);
        TokenDto tokenDto = authUserService.login(dto);
        if(tokenDto == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token, @RequestBody RequestDto dto){
        TokenDto tokenDto = authUserService.validate(token, dto);
        if(tokenDto == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/create")
    public ResponseEntity<AuthUser> create(@RequestBody NewUserDto dto){
        AuthUser authUser = authUserService.save(dto);
        if(authUser == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(authUser);
    }

}
