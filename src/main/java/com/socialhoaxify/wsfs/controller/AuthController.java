package com.socialhoaxify.wsfs.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.socialhoaxify.wsfs.annotations.CurrentUser;
import com.socialhoaxify.wsfs.model.UserInformation;
import com.socialhoaxify.wsfs.repository.UserRepository;
import com.socialhoaxify.wsfs.sharedGenericResponse.Views;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
//crendential for user loginpage

@RestController
@AllArgsConstructor
public class AuthController {

    private final UserRepository userRepository;


//    @JsonView(Views.Base.class)//response body for loginPage
//    @PostMapping("/api/1.0/auth")
//    ResponseEntity<?> handleAuthentication(Authentication authentication){//required=false olması bu header olmasada sen bu requesti methodumuza ulaştır diyoruz
////        UserInformation user=(UserInformation) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserInformation user=(UserInformation) authentication.getPrincipal();
//        return ResponseEntity.ok(user);
//        }
@JsonView(Views.Base.class)//response body for loginPage
@PostMapping("/api/1.0/auth")
ResponseEntity<?> handleAuthentication(@CurrentUser UserInformation user){
    return ResponseEntity.ok(user);
}
}
