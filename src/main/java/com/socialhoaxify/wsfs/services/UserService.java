package com.socialhoaxify.wsfs.services;

import com.socialhoaxify.wsfs.model.UserInformation;
import com.socialhoaxify.wsfs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(UserInformation userInformation) {
        userRepository.save(userInformation);
    }
}
