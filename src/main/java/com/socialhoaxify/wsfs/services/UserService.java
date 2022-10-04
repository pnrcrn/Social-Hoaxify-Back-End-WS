package com.socialhoaxify.wsfs.services;

import com.socialhoaxify.wsfs.model.UserInformation;
import com.socialhoaxify.wsfs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {


    private final UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
        this.passwordEncoder=new BCryptPasswordEncoder();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserInformation getUserInformationDataId(Long dataId){
     return userRepository.getReferenceById(dataId);
    }

    public void save(UserInformation userInformation) {
        userInformation.setPassword(this.passwordEncoder.encode(userInformation.getPassword()));
        userRepository.save(userInformation);
    }
}
