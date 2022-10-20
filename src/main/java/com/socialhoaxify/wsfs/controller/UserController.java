package com.socialhoaxify.wsfs.controller;


import com.socialhoaxify.wsfs.error.ApiError;
import com.socialhoaxify.wsfs.model.UserInformation;
import com.socialhoaxify.wsfs.services.UserService;
import com.socialhoaxify.wsfs.sharedGenericResponse.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.lang.reflect.GenericArrayType;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private  static final Logger log=LoggerFactory.getLogger(UserController.class);

    @PostMapping("/api/1.0/users")
    public GenericResponse createUser(@Valid @RequestBody UserInformation userInformation){
        userService.save(userInformation);
        return new GenericResponse("User created.") ;
    }

    @GetMapping("/api/1.0/getUser")
    @ResponseBody
    public UserInformation getUserInformation(@RequestParam Long dataId) {
      return  userService.getUserInformationDataId(dataId);

//        HashMap<String, String> map = new HashMap<>();
//        map.put("username", userService.getUserInformationDataId(dataId).getUsername());
//        map.put("displayName", userService.getUserInformationDataId(dataId).getDisplayName());
//        map.put("password", userService.getUserInformationDataId(dataId).getPassword());
//        return map;

    }


}
