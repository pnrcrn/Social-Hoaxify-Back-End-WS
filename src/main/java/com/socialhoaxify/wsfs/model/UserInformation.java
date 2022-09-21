package com.socialhoaxify.wsfs.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class UserInformation {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String displayName;
    private String password;

}
