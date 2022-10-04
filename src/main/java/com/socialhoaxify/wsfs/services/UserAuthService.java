package com.socialhoaxify.wsfs.services;

import com.socialhoaxify.wsfs.model.UserInformation;
import com.socialhoaxify.wsfs.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//
@Service
@AllArgsConstructor
public class UserAuthService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInformation inDB= userRepository.findByUsername(username);
        if(inDB==null){
            throw  new UsernameNotFoundException("User not found");
        }
        return null;
    }
}
