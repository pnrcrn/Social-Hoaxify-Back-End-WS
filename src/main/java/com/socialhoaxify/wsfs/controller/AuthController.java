package com.socialhoaxify.wsfs.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.socialhoaxify.wsfs.error.ApiError;
import com.socialhoaxify.wsfs.model.UserInformation;
import com.socialhoaxify.wsfs.repository.UserRepository;
import com.socialhoaxify.wsfs.sharedGenericResponse.Views;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.util.Base64;
//crendential for user loginpage

@RestController
@AllArgsConstructor
public class AuthController {

    private BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @JsonView(Views.Base.class)//response body for loginPage
    @PostMapping("/api/1.0/auth")
    ResponseEntity<?> handleAuthentication(@RequestHeader(name = "Authorization", required = false) String authorization){//required=false olması bu header olmasada sen bu requesti methodumuza ulaştır diyoruz
    if (authorization==null){
        ApiError error=new ApiError(401,"Unauthorizated","/api/1.0/auth");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
        String base64encoded=authorization.split("Basic ")[1];//Basic dXNlcjo5eGFoZXNQNUJyaW5odDU=
        String decoded=new String( Base64.getDecoder().decode(base64encoded));//user:Password12
        String[] parts=decoded.split(":");//["user","Password12"]
        String username=parts[0];
        String password=parts[1];
        UserInformation inDB= userRepository.findByUsername(username);
        if(inDB==null){
            ApiError error=new ApiError(401,"Unauthorizated","/api/1.0/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
        String hashedPassword=inDB.getPassword();
        if(!this.passwordEncoder.matches(password,hashedPassword)){//encode edilmiş password ve gelen password match edilmişse
            ApiError error=new ApiError(401,"Unauthorizated","/api/1.0/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

    return ResponseEntity.ok().build();
    }
}
