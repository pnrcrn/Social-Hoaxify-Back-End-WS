package com.socialhoaxify.wsfs.annotations;

import com.socialhoaxify.wsfs.model.UserInformation;
import com.socialhoaxify.wsfs.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
//2
@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername,String > {

//    Kendi annotationımız username farklı olması için yaptık
   private final UserRepository userRepository;
    @Override
    public boolean isValid(String username, ConstraintValidatorContext context ) {
        UserInformation userInformation=userRepository.findByUsername(username);
        if(userInformation!=null){
            return false;
        }
        return true;
    }
}
