package com.socialhoaxify.wsfs.model;
import com.fasterxml.jackson.annotation.JsonView;
import com.socialhoaxify.wsfs.annotations.UniqueUsername;
import com.socialhoaxify.wsfs.sharedGenericResponse.Views;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.awt.*;

@Data
@Entity
public class UserInformation {
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

}
