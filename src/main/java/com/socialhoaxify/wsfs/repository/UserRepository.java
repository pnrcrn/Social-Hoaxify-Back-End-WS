package com.socialhoaxify.wsfs.repository;

import com.socialhoaxify.wsfs.model.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInformation,Long> {

UserInformation findByUsername(String username);//username için query,databasemizde birden fazla aynı olamaz, (sign up page)

}
