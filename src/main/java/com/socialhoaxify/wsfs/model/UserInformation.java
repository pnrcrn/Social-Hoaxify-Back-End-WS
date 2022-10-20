package com.socialhoaxify.wsfs.model;
import com.fasterxml.jackson.annotation.JsonView;
import com.socialhoaxify.wsfs.annotations.UniqueUsername;
import com.socialhoaxify.wsfs.sharedGenericResponse.Views;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.awt.*;
import java.util.Collection;

@Data
@Entity
public class UserInformation implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 4,max = 255)
    @UniqueUsername
    @JsonView(Views.Base.class)//response body for loginPage
    private String username;

    @NotNull
    @Size(min = 4,max = 255)
    @JsonView(Views.Base.class)//response body for loginPage
    private String displayName;

    @NotNull
    @Size(min = 8,max = 255)
    @Pattern(regexp = "^(?=[a-zA-Z])[-\\w.]{5,16}([a-zA-Z\\d]|(?<![-.])_)$")
    private String password;

    @JsonView(Views.Base.class)//response body for loginPage
    private String image;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("Role_user");
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
