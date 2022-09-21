package com.socialhoaxify.wsfs.controller;


import com.socialhoaxify.wsfs.model.UserInformation;
import com.socialhoaxify.wsfs.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private  static final Logger log=LoggerFactory.getLogger(UserController.class);

    @PostMapping("/api/1.0/users")
    public void createUser(@RequestBody UserInformation userInformation){
       userService.save(userInformation);
    }


}
